package uz.smd.itutor.ui.start

/**
 * Created by Siddikov Mukhriddin on 1/4/21
 */
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_programs_list.view.*
import uz.smd.itutor.R
import uz.smd.itutor.utils.bindItem
import uz.smd.itutor.utils.inflate


class ListUserAdapter : RecyclerView.Adapter<ListUserAdapter.ViewHolder>() {
    var listener: ((UserData) -> Unit)? = null

    private var tasksDay: MutableList<UserData> = mutableListOf()
    fun setListenerGetPos(block: (UserData) -> Unit) {
        listener = block
    }

    fun setTasksDay(tasksDay: List<UserData>) {
        this.tasksDay.clear()
        this.tasksDay.addAll(tasksDay)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(parent.inflate(R.layout.item_programs_list))

    override fun getItemCount() = tasksDay.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind()
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val title: TextView = itemView.tileUser
        private val name: TextView = itemView.nameUser
        private val location: TextView = itemView.locationUser
        private val desc: TextView = itemView.descUser
        private val viewUseAreas: LinearLayout = itemView.lineUser
        fun bind() = bindItem {
            val d = tasksDay[adapterPosition]
            title.setText(d.title)
            location.setText(d.location)
            desc.setText(d.desc)
            name.setText(d.name)
            viewUseAreas.setOnClickListener { listener?.invoke(d) }
        }
fun helloWorld(){

}

    }

}