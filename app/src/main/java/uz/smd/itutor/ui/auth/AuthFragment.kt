package uz.smd.itutor.ui.auth

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_auth.*
import uz.smd.itutor.R
import uz.smd.itutor.data.prefs.LocalStorage
import uz.smd.itutor.ui.root.BaseFragment
import uz.smd.itutor.ui.root.MainActivity
import uz.smd.itutor.utils.showKeyBoard
import uz.smd.itutor.utils.showKeyboard
import uz.smd.itutor.utils.showToast
import javax.inject.Inject


/**
 * Created by Siddikov Mukhriddin on 2/10/21
 */
@SuppressLint("FragmentLiveDataObserve")
@AndroidEntryPoint
class AuthFragment : BaseFragment(R.layout.fragment_auth) {
    private val viewModel: AuthVModel by viewModels()
@Inject
lateinit var prefs:LocalStorage
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as MainActivity).hideBottomMenu()
        handleLiveData()
        btnPhone.setOnClickListener {
            phoneNumber.showKeyboard()
        }
        btnAuth.setOnClickListener {

            val bundle = bundleOf("number" to "+998${phoneNumber.text}")
            if (phoneNumber.text.toString().length>8)
            navController.navigate(R.id.action_authFragment_to_verifyFragment, bundle)
            else{
                phoneNumber.showKeyboard()
                showToast("Iltimos telefon nomerini to'g'ri kiriting")
            }
        }
//        verifySMSCode("1234567")
    }





    fun handleLiveData() {
        phoneNumber.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (event != null && event.keyCode == KeyEvent.KEYCODE_ENTER || actionId == EditorInfo.IME_ACTION_DONE) {
                val bundle = bundleOf("number" to "+998${phoneNumber.text}")
                if (phoneNumber.text.toString().length>8)
                    navController.navigate(R.id.action_authFragment_to_verifyFragment, bundle)
                else{
                    showToast("Iltimos telefon nomerini to'g'ri kiriting")
                    phoneNumber.showKeyBoard()
                }

            }
            false
        })
        viewModel.messageToast.observe(this, toast)
    }
    val toast= Observer(::showToast)

}