package uz.smd.itutor.ui.auth

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_verify.*
import uz.smd.itutor.R
import uz.smd.itutor.ui.root.BaseFragment
import uz.smd.itutor.utils.gone
import uz.smd.itutor.utils.showToast
import uz.smd.itutor.utils.visible
import java.util.concurrent.TimeUnit


@AndroidEntryPoint
class VerifyFragment : BaseFragment(R.layout.fragment_verify) {

    private val viewModel: AuthVModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
     val number=   requireArguments().getString("number")
        textNumber.text="+998 *** ** ${number!!.substring(number.length-2,number.length)}"
        sendSMS(number?:"")
        loader_progress.gone()
        handleLiveData()
        cetMyCode.showKeyWhenStart()
    }

    override fun clickHandler() {
        back_arrow.setOnClickListener { onBackPressed() }
        cetMyCode.setOnCodeChangedListener { (code, completed) ->
            if (completed) {
                loader_progress.visible()
                viewModel.verifySMSCode(code)
            }
        }
        resend_code.setOnClickListener {
            resendVerificationCode(requireArguments().getString("number")?:"+998913684839",viewModel.resendToken)

        }
    }
    fun sendSMS(phoneNumber:String){
        val options = PhoneAuthOptions.newBuilder(Firebase.auth)
            .setPhoneNumber(phoneNumber)       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(requireActivity())                 // Activity (for callback binding)
            .setCallbacks(viewModel.callbacks)          // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }
    @SuppressLint("FragmentLiveDataObserve")
    fun handleLiveData(){
        viewModel.messageToast.observe(this, toast)
        viewModel.getCredential.observe(this, signInWithPhoneAuthCredential)
    }
    var toast= Observer<String> (::showToast)
    private var signInWithPhoneAuthCredential = Observer<PhoneAuthCredential> { credential->
        Firebase.auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                loader_progress.gone()
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("TTT", "signInWithCredential:success")
                    showToast("signInWithCredential:success")
                    navController.popBackStack()
navController.navigate(R.id.showIsTeacher)

                    val user = task.result?.user
                    viewModel.prefss.user=user?.phoneNumber.toString()

                } else {
                    // Sign in failed, display a message and update the UI
                    Log.w("TTT", "signInWithCredential:failure", task.exception)
                    showToast("signInWithCredential:failure ${task.exception}")
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                    }
                    // Update UI

                }
            }
    }
    // [START resend_verification]
    private fun resendVerificationCode(
        phoneNumber: String,
        token: PhoneAuthProvider.ForceResendingToken?
    ) {
        val optionsBuilder = PhoneAuthOptions.newBuilder(Firebase.auth)
            .setPhoneNumber(phoneNumber)       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(requireActivity())                 // Activity (for callback binding)
            .setCallbacks(viewModel.callbacks)          // OnVerificationStateChangedCallbacks
        if (token != null) {
            optionsBuilder.setForceResendingToken(token) // callback's ForceResendingToken
        }
        PhoneAuthProvider.verifyPhoneNumber(optionsBuilder.build())
    }
    // [END resend_verification]


}
