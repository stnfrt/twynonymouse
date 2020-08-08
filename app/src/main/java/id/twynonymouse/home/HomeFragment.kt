package id.twynonymouse.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import co.zsmb.rainbowcake.base.RainbowCakeFragment
import co.zsmb.rainbowcake.dagger.getViewModelFromFactory
import id.twynonymouse.R
import id.twynonymouse.databinding.FragmentHomeBinding
import id.twynonymouse.home.viewmodel.*
import kotlin.Error

class HomeFragment : RainbowCakeFragment<HomeViewState, HomeViewModel>(){

    override fun getViewResource() = 0

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun provideViewModel(): HomeViewModel = getViewModelFromFactory()

    @SuppressLint("MissingSuperCall")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return _binding!!.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.load()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun render(viewState: HomeViewState) {
        when(viewState){
            is Initial -> {
                binding.textMe.text = "Init"
            }
            is Loading -> {
                binding.textMe.text = "Wait"
            }
            is Error -> {
                binding.textMe.text = "Error"
            }
            is UserReady -> {
                binding.textMe.text = viewState.data.name
            }
        }
    }

}