package uz.smd.itutor.ui.entrance.location

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.location.LocationManager.GPS_PROVIDER
import android.location.LocationManager.PASSIVE_PROVIDER
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.PermissionChecker.PERMISSION_GRANTED
import androidx.core.content.PermissionChecker.checkSelfPermission
import android.provider.Settings

class Geolocator(private val context: Activity){
    private val locationManager = context.getSystemService(AppCompatActivity.LOCATION_SERVICE) as LocationManager
    private var simpleLocationListener : SimpleLocationListener? = null

    fun getUserPosition(locationReceiver: LocationReceiver, isGPSEnabled:Boolean=false,block: () -> Unit) :Location?{
        if (checkSelfPermission(context, ACCESS_FINE_LOCATION) != PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(context, arrayOf(ACCESS_FINE_LOCATION), 2)
            return null
        } else {
            if (isGPSEnabled)
            locationEnabled(block)
            if (simpleLocationListener == null){
            simpleLocationListener = SimpleLocationListener(locationReceiver)
            locationManager.requestLocationUpdates(GPS_PROVIDER, 100, 100F, simpleLocationListener!!)}
            locationManager.getLastKnownLocation(GPS_PROVIDER)?.let{ return it }
            locationManager.getLastKnownLocation(PASSIVE_PROVIDER)?.let{ return it }
            return null
        }
    }
     fun locationEnabled(block:()->Unit) {
        val lm = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager?
        var gps_enabled = false
        var network_enabled = false
        try {
            gps_enabled = lm!!.isProviderEnabled(GPS_PROVIDER)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        try {
            network_enabled = lm!!.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        if (!gps_enabled && !network_enabled) {
            AlertDialog.Builder(context)
                .setMessage("Включить GPS?")
                .setPositiveButton("Настройки",
                    DialogInterface.OnClickListener { paramDialogInterface, paramInt ->
                        context.startActivity(Intent(Settings. ACTION_LOCATION_SOURCE_SETTINGS))
                    })
                .setNegativeButton("Отмена", null)
                .show()
        }
         else
            block()
    }

    fun clearListeners(){
        simpleLocationListener?.let {
            locationManager.removeUpdates(it)
        }
        simpleLocationListener = null
    }

    private class SimpleLocationListener(val locationReceiver : LocationReceiver): LocationListener{
        override fun onLocationChanged(location: Location) {
            locationReceiver.passLocation(location?.toSimple())
        }

        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {/*skip*/}

        override fun onProviderEnabled(provider: String) {/*skip*/}

        override fun onProviderDisabled(provider: String) {/*skip*/}
    }
}
interface LocationReceiver{
    fun passLocation(location : SimpleLocation?)
}
fun Location.toSimple() = SimpleLocation(latitude, longitude, accuracy)//todo перенести метод в класс
