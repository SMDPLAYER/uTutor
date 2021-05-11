package uz.smd.itutor.ui.subjects

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.subject_fragment.*
import uz.smd.itutor.R
import uz.smd.itutor.ui.root.BaseFragment


/**
 * Created by Siddikov Mukhriddin on 4/26/21
 */
class SubjectsFragment:BaseFragment(R.layout.subject_fragment) {
    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mActivity.showBottomMenu()
        val items= ArrayList<Subject>()
        items.add(Subject("Ingliz tili",R.drawable.flag_en))
        items.add(Subject("Ingliz tili",R.drawable.flag_en))
        items.add(Subject("Ingliz tili",R.drawable.flag_en))
        items.add(Subject("Ingliz tili",R.drawable.flag_en))
        items.add(Subject("Ingliz tili",R.drawable.flag_en))
        items.add(Subject("Ingliz tili",R.drawable.flag_en))
        items.add(Subject("Ingliz tili",R.drawable.flag_en))
        items.add(Subject("Ingliz tili",R.drawable.flag_en))
        items.add(Subject("Ingliz tili",R.drawable.flag_en))
        val itemsAdapter=SubjectsAdapter(requireContext(),items)
        listSubject.adapter= itemsAdapter
        itemsAdapter.onclickItem.observe(this, Observer {
            navController.navigate(R.id.showUsersFragment)
        })
    }
}

class SubjectsAdapter(context: Context, users: ArrayList<Subject>) :
    ArrayAdapter<Subject?>(context, 0, users) {
    val onclickItem=MutableLiveData<Subject>()
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Get the data item for this position
        var convertView = convertView
        val user: Subject? = getItem(position)
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_subjects, parent, false)
        }
        convertView?.setOnClickListener {
            onclickItem.value=user
            Toast.makeText(context,"Helo",Toast.LENGTH_SHORT).show()
        }
        // Lookup view for data population
        val tvName = convertView!!.findViewById<View>(R.id.nameSubject) as TextView
        val tvHome = convertView.findViewById<View>(R.id.imageSubject) as ImageView
        // Populate the data into the template view using the data object
        tvName.setText(user?.name)
        tvHome.setImageResource(user!!.image)
        // Return the completed view to render on screen
        return convertView
    }
}
class Subject(
    val name:String,
    val image:Int
)