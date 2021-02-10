package uz.smd.itutor.ui.start

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import uz.smd.itutor.R
/**
 * Created by Siddikov Mukhriddin on 2/10/21
 */
class StartFragment:Fragment(R.layout.fragment_start) {
    private val viewModel: StartVModel by viewModels()
    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.ks.observe(this, Observer {
            Toast.makeText(requireContext(),"salom",Toast.LENGTH_SHORT).show()
        })
        viewModel.ks.observe(this, Observer {
            Toast.makeText(requireContext(),it,Toast.LENGTH_SHORT).show()
        })
    }
}