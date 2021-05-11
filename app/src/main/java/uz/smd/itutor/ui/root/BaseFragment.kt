package uz.smd.itutor.ui.root

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation

/**
 * Created by Siddikov Mukhriddin on 4/18/21
 */
open class BaseFragment(@LayoutRes layout:Int):Fragment(layout){
  lateinit  var navController:NavController
    lateinit var  mActivity:MainActivity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivity=requireActivity() as MainActivity
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        clickHandler()
    }
    open fun clickHandler(){

    }
    open fun onBackPressed(){
        navController.navigateUp()
    }

}