package uz.smd.itutor.utils

import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment

/**
 * Created by Siddikov Mukhriddin on 4/16/21
 */
fun Fragment.showToast(message:String){
    Toast.makeText(requireContext(),message,Toast.LENGTH_SHORT).show()
}
fun View.gone(){
    this.visibility=View.GONE
}
fun View.visible(){
    this.visibility=View.VISIBLE
}
fun View.hide(){
    this.visibility=View.INVISIBLE
}