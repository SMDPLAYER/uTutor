package uz.smd.itutor.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.Interpolator
import androidx.fragment.app.Fragment
import uz.smd.itutor.R

import java.util.concurrent.Executors

fun Activity.animateView(view: View){
    Executors.newSingleThreadExecutor().execute {
        Thread.sleep(100)
        this.runOnUiThread {
            val myAnim =
                AnimationUtils.loadAnimation(this, R.anim.bounce)

            // Use bounce interpolator with amplitude 0.2 and frequency 20
            val interpolator = MyBounceInterpolator(0.2, 20.0)
            myAnim.interpolator = interpolator
            view.startAnimation(myAnim)
        }

    }
}
fun Fragment.animateView(view: View){
    Executors.newSingleThreadExecutor().execute {
        Thread.sleep(100)
        requireActivity().runOnUiThread {
            val myAnim =
                AnimationUtils.loadAnimation(context, R.anim.bounce)

            // Use bounce interpolator with amplitude 0.2 and frequency 20
            val interpolator = MyBounceInterpolator(0.2, 20.0)
            myAnim.interpolator = interpolator
            view.startAnimation(myAnim)
        }

    }
}


fun didClickButton(view: View,context: Context) {

    val myAnim = AnimationUtils.loadAnimation(context, R.anim.bounce)
    // Use bounce interpolator with amplitude 0.2 and frequency 20
    val interpolator = MyClickBounceInterpolator(0.9, 20.0)
    myAnim.interpolator = interpolator
    view.startAnimation(myAnim)
}

fun Fragment.didClickButton(view: View) { didClickButton(view,requireContext()) }
internal class MyBounceInterpolator(
    amplitude: Double,
    frequency: Double
) :
    Interpolator {
    private var mAmplitude = 1.0
    private var mFrequency = 10.0
    override fun getInterpolation(time: Float): Float {
        return (-1 * Math.pow(Math.E, -time / mAmplitude) *
                Math.cos(mFrequency * time) + 1).toFloat()
    }

    init {
        mAmplitude = amplitude
        mFrequency = frequency
    }
}
internal class MyClickBounceInterpolator(amplitude: Double, frequency: Double) : Interpolator {
    private var mAmplitude = 1.0
    private var mFrequency = 10.0
    override fun getInterpolation(time: Float): Float {
        return (-1 * Math.pow(Math.E, -time / mAmplitude) * 0.3*Math.cos(90+mFrequency * time) + 0.87).toFloat()
    }

    init {
        mAmplitude = amplitude
        mFrequency = frequency
    }
}