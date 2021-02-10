package uz.smd.itutor.ui.start

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.smd.itutor.data.prefs.LocalStorage
import javax.inject.Inject

/**
 * Created by Siddikov Mukhriddin on 2/10/21
 */
class StartVModel @ViewModelInject constructor(val prefss:LocalStorage) :ViewModel(){
    @Inject
   lateinit var  prefs:LocalStorage

     val ks=MutableLiveData<String>()
     val k=MutableLiveData<String>()
    init {
        k.value=prefss.name
        ks.value=prefs.name
    }

}