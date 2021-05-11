package uz.smd.itutor.ui.entrance

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_send_location.*
import uz.smd.itutor.R
import uz.smd.itutor.ui.entrance.location.Geolocator
import uz.smd.itutor.ui.entrance.location.LocationReceiver
import uz.smd.itutor.ui.entrance.location.SimpleLocation
import uz.smd.itutor.ui.entrance.location.toSimple
import uz.smd.itutor.ui.root.BaseFragment


/**
 * Created by Siddikov Mukhriddin on 4/19/21
 */
class SendLocation : BaseFragment(R.layout.fragment_send_location), LocationReceiver {
    var ks=0
    var locationGps: Location? = null
    var locationNetwork: Location? = null
    private lateinit var  locator: Geolocator
    lateinit var locationManager: LocationManager
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mActivity.hideBottomMenu()
         locator= Geolocator(requireActivity())
        btnSendLocation.setOnClickListener {
            locator.getUserPosition(this,true).let {
                it?.toSimple()?.let { it1 ->
                    sendLocation(it1)
                    locator.clearListeners()
                }

            }

        }
    }

    override fun onResume() {
        super.onResume()
        ks=0
    }

    override fun passLocation(location: SimpleLocation?) {
        location?.let { sendLocation(it) }
        if (location != null && location.accuracy > 50){
            locator.getUserPosition(this,true)
            return}
        location?.let {
            locator.clearListeners()
        }


    }
    fun sendLocation(location: SimpleLocation) {
        val uid = Firebase.auth.currentUser?.phoneNumber
        val data=hashMapOf(
            "Longitude" to location.lon.toString(),
            "Latitude" to location.lat.toString())
        if(uid!=null)
        Firebase.firestore.collection(uid).document("Location")
            .set(data, SetOptions.merge())
            .addOnSuccessListener {
                if (ks==0){
                    ks=1
                    navController.navigate(R.id.showSubjectFragment)
                }
                Toast.makeText(
                    requireContext(),
                    "Location Data feeds start",
                    Toast.LENGTH_SHORT
                ).show()
//                                        Snackbar.make(takeabreak, "Location Data feeds start", Snackbar.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Log.e("TTT","fireabase error: $it")
                Toast.makeText(
                    requireContext(),
                    "Failed location feed: $it",
                    Toast.LENGTH_SHORT
                ).show()
            }
        Toast.makeText(requireContext(),
            "Your location \n long: ${location?.lon}\n lat: ${location?.lat}",
            Toast.LENGTH_SHORT).show()
    }
    private fun getLocation() {
        val uid = Firebase.auth.currentUser?.uid
        locationManager =
            requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val hasGps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        val hasNetwork = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        if (hasGps || hasNetwork) {

            if (hasGps) {
                if (ActivityCompat.checkSelfPermission(
                        requireContext(),
                        android.Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                    != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(
                        requireContext(),
                        android.Manifest.permission.ACCESS_FINE_LOCATION
                    )
                    != PackageManager.PERMISSION_GRANTED
                ) {
                    ActivityCompat.requestPermissions(
                        requireActivity(),
                        arrayOf(
                            android.Manifest.permission.ACCESS_FINE_LOCATION,
                            android.Manifest.permission.ACCESS_COARSE_LOCATION
                        ),
                        37
                    )
                    return
                }
                locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    10000,
                    0F,
                    object : LocationListener {
                        override fun onLocationChanged(p0: Location) {
                            if (p0 != null) {
                                locationGps = p0
                                if (uid != null) {
                                    Toast.makeText(
                                        requireContext(),
                                        "Your location \n long: ${locationGps!!.longitude}\n lat: ${locationGps!!.latitude}",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                    Firebase.firestore.collection("Users").document("Users").collection(uid).document().update(
                                        "Longitude",
                                        locationGps!!.longitude.toString(),
                                        "Latitude",
                                        locationGps!!.latitude.toString()
                                    )
                                        .addOnSuccessListener {
                                            Toast.makeText(
                                                requireContext(),
                                                "Location Data feeds start",
                                                Toast.LENGTH_SHORT
                                            ).show()
//                                        Snackbar.make(takeabreak, "Location Data feeds start", Snackbar.LENGTH_SHORT).show()
                                        }
                                        .addOnFailureListener {
                                            Toast.makeText(
                                                requireContext(),
                                                "Failed location feed: $it",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                }
                            }
                        }

                        override fun onProviderEnabled(provider: String) {}

                        override fun onProviderDisabled(provider: String) {}

                        override fun onStatusChanged(
                            provider: String?,
                            status: Int,
                            extras: Bundle?
                        ) {
                        }

                    })

                val localGpsLocation =
                    locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                if (localGpsLocation != null){
                    locationGps = localGpsLocation
                    Toast.makeText(requireContext(),
                        "Your location \n long: ${locationGps!!.longitude}\n lat: ${locationGps!!.latitude}",
                        Toast.LENGTH_SHORT).show()
                }
            }


        } else {
            startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
        }
    }
}