package uz.smd.itutor.ui.start

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import uz.smd.itutor.R

/**
 * Created by Siddikov Mukhriddin on 2/10/21
 */
@SuppressLint("FragmentLiveDataObserve")
@AndroidEntryPoint
class StartFragment : Fragment(R.layout.fragment_start) {
    private val viewModel: StartVModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleLiveData()
    }

    fun handleLiveData() {
        viewModel.k.observe(this, Observer {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })
    }
}