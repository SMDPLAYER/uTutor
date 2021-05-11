package uz.smd.itutor.ui.auth

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import uz.smd.itutor.data.prefs.LocalStorage


/**
 * Created by Siddikov Mukhriddin on 2/10/21
 */
class AuthVModel @ViewModelInject constructor(val prefss: LocalStorage) : ViewModel() {
    var storedVerificationId=""
    var resendToken:PhoneAuthProvider.ForceResendingToken?=null
    var getCredential=MutableLiveData<PhoneAuthCredential>()
var messageToast=MutableLiveData<String>()
     var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    init {
         callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                // This callback will be invoked in two situations:
                // 1 - Instant verification. In some cases the phone number can be instantly
                //     verified without needing to send or enter a verification code.
                // 2 - Auto-retrieval. On some devices Google Play services can automatically
                //     detect the incoming verification SMS and perform verification without
                //     user action.
                Log.d("TTT", "onVerificationCompleted:$credential")
                messageToast.value="onVerificationCompleted"
                getCredential.value=credential

            }

            override fun onVerificationFailed(e: FirebaseException) {
                // This callback is invoked in an invalid request for verification is made,
                // for instance if the the phone number format is not valid.
                Log.w("TTT", "onVerificationFailed", e)
                if (e is FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                } else if (e is FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded
                }
                // Show a message and update the UI
                messageToast.value="onVerificationFailed $e"
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                Log.d("TTT", "onCodeSent:$verificationId")
                messageToast.value="onCodeSent"
                // Save verification ID and resending token so we can use them later
                prefss.storedVerificationId=verificationId
                storedVerificationId = verificationId
                 resendToken = token
            }

            override fun onCodeAutoRetrievalTimeOut(p0: String) {
                super.onCodeAutoRetrievalTimeOut(p0)
                messageToast.value="onCodeAutoRetrievalTimeOut"
            }
        }

    }
    fun verifySMSCode(code:String){
        getCredential.value = PhoneAuthProvider.getCredential(prefss.storedVerificationId, code)
    }


}