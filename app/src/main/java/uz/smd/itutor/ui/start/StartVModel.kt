package uz.smd.itutor.ui.start

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.smd.itutor.data.prefs.LocalStorage
import javax.inject.Inject

/**
 * Created by Siddikov Mukhriddin on 2/10/21
 */
class StartVModel @ViewModelInject constructor(prefss: LocalStorage) : ViewModel() {


    val k = MutableLiveData<String>()

    init {
        prefss.name="hello"
        k.value = prefss.name
    }



    fun omg(){

    }
}