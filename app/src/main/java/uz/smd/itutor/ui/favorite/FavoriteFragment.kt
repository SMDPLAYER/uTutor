package uz.smd.itutor.ui.favorite

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_favorite.*
import kotlinx.android.synthetic.main.fragment_start.*
import kotlinx.android.synthetic.main.fragment_start.listPrograms
import uz.smd.itutor.R
import uz.smd.itutor.ui.start.ListUserAdapter
import uz.smd.itutor.ui.start.StartVModel
import uz.smd.itutor.ui.start.UserData

/**
 * Created by Siddikov Mukhriddin on 2/10/21
 */
@SuppressLint("FragmentLiveDataObserve")
@AndroidEntryPoint
class FavoriteFragment : Fragment(R.layout.fragment_favorite) {
    private val viewModel: StartVModel by viewModels()

val adapterUser= ListUserAdapter()
val adapterUser1= ListUserAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleLiveData()
        listPrograms.adapter=adapterUser
        listPrograms1.adapter=adapterUser1
        adapterUser.setListenerGetPos {  }
        adapterUser.setTasksDay(listUserData())
        adapterUser1.setTasksDay(listUserData1())
    }

    fun handleLiveData() {
        viewModel.k.observe(this, Observer {
//            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })
    }
    fun listUserData(): List<UserData> {
        val listUseAreas = ArrayList<UserData>()
        listUseAreas.add(UserData(3, "Fizika", "Xojiev Akbar","Fizikadan 15 yillik tajriba 1000 ortiq muvaffaqiyatli bitirgan talabalar. Darslar istalgan shaklda olib boriladi (onlayn, offlayn) ,haftaning qulay paytidagi guruxlarga qushilish imkoniyati",3,"Toshkent"))
        listUseAreas.add(UserData(4, "Oliy matematika", "Oripov Akram","Matematikadan 10 yillik tajriba 1000 ortiq muvaffaqiyatli bitirgan talabalar. Darslar istalgan shaklda olib boriladi (onlayn, offlayn) ,haftaning qulay paytidagi guruxlarga qushilish imkoniyati",3,"Toshkent"))
        listUseAreas.add(UserData(5, "Fizika", "Sodiqov Nodir","Fizika 10 yillik tajriba 1000 ortiq muvaffaqiyatli bitirgan talabalar. Darslar istalgan shaklda olib boriladi (onlayn, offlayn) ,haftaning qulay paytidagi guruxlarga qushilish imkoniyati",3,"Toshkent"))
        listUseAreas.add(UserData(5, "Matematika", "Muzaffarov Axmadjon","Fizika 10 yillik tajriba 1000 ortiq muvaffaqiyatli bitirgan talabalar. Darslar istalgan shaklda olib boriladi (onlayn, offlayn) ,haftaning qulay paytidagi guruxlarga qushilish imkoniyati",3,"Toshkent"))

//    listUseAreas.add(UseArea(8, R.string.text_popular_programs.toString(), R.drawable.icn_popular_stars.toString()))
        return listUseAreas
    }
    fun listUserData1(): List<UserData> {
        val listUseAreas = ArrayList<UserData>()
        listUseAreas.add(UserData(3, "Ingliz tili", "Axmadaliyeva Nigora","Ingliz tilidan 5 yillik tajriba 100 ortiq muvaffaqiyatli bitirgan talabalar. Darslar istalgan shaklda olib boriladi (onlayn, offlayn) ,haftaning qulay paytidagi guruxlarga qushilish imkoniyati",3,"Toshkent"))
        listUseAreas.add(UserData(4, "Rus tili", "Raxmatillayeva Shoira","Rus tilidan 10 yillik tajriba 1000 ortiq muvaffaqiyatli bitirgan talabalar. Darslar istalgan shaklda olib boriladi (onlayn, offlayn) ,haftaning qulay paytidagi guruxlarga qushilish imkoniyati",3,"Toshkent"))

//    listUseAreas.add(UseArea(8, R.string.text_popular_programs.toString(), R.drawable.icn_popular_stars.toString()))
        return listUseAreas
    }
}