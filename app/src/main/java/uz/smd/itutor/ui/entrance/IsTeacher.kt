package uz.smd.itutor.ui.entrance

import android.os.Bundle
import android.view.View
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_is_teacher.*
import uz.smd.itutor.R
import uz.smd.itutor.data.prefs.LocalStorage
import uz.smd.itutor.ui.root.BaseFragment
import javax.inject.Inject

/**
 * Created by Siddikov Mukhriddin on 4/19/21
 */
@AndroidEntryPoint
class IsTeacher :BaseFragment(R.layout.fragment_is_teacher){
    @Inject
    lateinit var prefs:LocalStorage
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prefs.isAuthed=true
        btnTeсрук.setOnClickListener {
navController.navigate(R.id.showSendLocation)
        }
        btnStudent.setOnClickListener {
            navController.navigate(R.id.showSendLocation)
        }
    }

}