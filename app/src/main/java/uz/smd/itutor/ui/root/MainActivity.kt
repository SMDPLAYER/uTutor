package uz.smd.itutor.ui.root

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.bottom_menu.*
import uz.smd.itutor.R
import uz.smd.itutor.utils.gone
import uz.smd.itutor.utils.visible

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = findNavController(R.id.fragment_container)
        clickListener()
    }

    fun clickListener() {
//        bottomStory.setOnClickListener {
////
//        }
        bottomSettings.setOnClickListener {
            navController.navigate(R.id.showProfileFragment)
//            inSettings()
        }
        bottomPrograms.setOnClickListener {
            navController.navigate(R.id.showFavorite)
//            inProgram()
        }
//        bottomActivnost.setOnClickListener {
//            navController.navigate(R.id.showSEarch)
//            inProfile()
//        }
    }

    fun inProfile() {
        btn_search2.setImageResource(R.drawable.ic_search1)
        btn_search1.setTextColor(resources.getColor(R.color.blue))
        btnFavorite.setImageResource(R.drawable.ic_doublehearts)
        btnFavorite1.setTextColor(resources.getColor(R.color.black))
        bottomActivnost.isClickable = false
        bottomSettings.isClickable = true

    }

    fun inSettings() {
//        btn_search.setImageResource(R.drawable.ic_search1)
//        btn_search1.setTextColor(resources.getColor(R.color.blue))
        bottomActivnost.isClickable = true
        bottomSettings.isClickable = false
    }

    fun inProgram() {
       btnFavorite.setImageResource(R.drawable.ic_doublehearts1)
       btnFavorite1.setTextColor(resources.getColor(R.color.blue))

        (btn_search2 as ImageView).setImageResource(R.drawable.ic_search)
        (btn_search1 as TextView).setTextColor(resources.getColor(R.color.black))

        bottomSettings.isClickable = true
        bottomActivnost.isClickable = true
    }

    fun hideBottomMenu() {
        bottom_navigation.gone()
    }
    fun showBottomMenu() {
        bottom_navigation.visible()
    }
}