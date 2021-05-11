package uz.smd.itutor.ui.subjects

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.subject_fragment.*
import uz.smd.itutor.R
import uz.smd.itutor.ui.root.BaseFragment

/**
 * Created by Siddikov Mukhriddin on 4/26/21
 */
class SubjectFragment:BaseFragment(R.layout.subject_fragment) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mActivity.showBottomMenu()
        textTitle.text="Ingliz tili"
        val items= ArrayList<Tutors>()
        items.add(Tutors("Ismailov Ziyodbek",R.drawable.img_avatar,"180 000 so‘m/oyiga"))
        items.add(Tutors("Ismailov Ziyodbek",R.drawable.img_avatar,"180 000 so‘m/oyiga"))
        items.add(Tutors("Ismailov Ziyodbek",R.drawable.img_avatar,"180 000 so‘m/oyiga"))
        items.add(Tutors("Ismailov Ziyodbek",R.drawable.img_avatar,"180 000 so‘m/oyiga"))
        items.add(Tutors("Ismailov Ziyodbek",R.drawable.img_avatar,"180 000 so‘m/oyiga"))
        items.add(Tutors("Ismailov Ziyodbek",R.drawable.img_avatar,"180 000 so‘m/oyiga"))
        items.add(Tutors("Ismailov Ziyodbek",R.drawable.img_avatar,"180 000 so‘m/oyiga"))


        val itemsAdapter=SubjectAdapter(requireContext(),items)
        listSubject.adapter= itemsAdapter
    }
}
class SubjectAdapter(context: Context, users: ArrayList<Tutors>) :
    ArrayAdapter<Tutors?>(context, 0, users) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Get the data item for this position
        var convertView = convertView
        val user: Tutors? = getItem(position)
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_users, parent, false)
        }
        convertView?.setOnClickListener {
            Toast.makeText(context,"Helo", Toast.LENGTH_SHORT).show()
        }
        // Lookup view for data population
        val tvName = convertView!!.findViewById<View>(R.id.tv_feedback_name) as TextView
        val tvCost = convertView!!.findViewById<View>(R.id.text_cost) as TextView
        val tvHome = convertView.findViewById<View>(R.id.tv_feedback_logo) as TextView
        // Populate the data into the template view using the data object
        tvName.setText(user?.name)
        tvCost.setText(user?.price)
        tvHome.setBackgroundResource(user!!.image)
        // Return the completed view to render on screen
        return convertView
    }
}
class Tutors(
    val name:String,
    val image:Int,
    val price:String
)