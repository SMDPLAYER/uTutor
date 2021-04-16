package uz.smd.itutor.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Siddikov Mukhriddin on 1/5/21
 */


fun ViewGroup.inflate(@LayoutRes resId: Int) =
    LayoutInflater.from(context).inflate(resId, this, false)

typealias SingleBlock <T> = (T) -> Unit


fun RecyclerView.ViewHolder.bindItem(block: View.() -> Unit) = block(itemView)

fun runLayoutAnimation(recyclerView: RecyclerView) {
    val context: Context = recyclerView.context
//    val controller: LayoutAnimationController =
//        AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down)
//    recyclerView.layoutAnimation = controller
//    recyclerView.adapter!!.notifyDataSetChanged()
//    recyclerView.scheduleLayoutAnimation()
}