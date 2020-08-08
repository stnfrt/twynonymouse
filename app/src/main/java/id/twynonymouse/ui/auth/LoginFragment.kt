package id.twynonymouse.ui.auth

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import co.zsmb.rainbowcake.base.RainbowCakeFragment
import co.zsmb.rainbowcake.dagger.getViewModelFromFactory
import co.zsmb.rainbowcake.navigation.navigator
import id.twynonymouse.databinding.FragmentLoginBinding
import id.twynonymouse.ui.auth.viewmodel.*
import id.twynonymouse.ui.home.HomeFragment

class LoginFragment : RainbowCakeFragment<LoginViewState, LoginViewModel>() {
    override fun getViewResource() = 0

    override fun provideViewModel() = getViewModelFromFactory()

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    @SuppressLint("MissingSuperCall")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        return _binding!!.root
    }

    override fun render(viewState: LoginViewState) {
        when (viewState) {
           is Loading -> {
                binding.btnLogin.isEnabled = false
            }
            is Error -> {
                binding.btnLogin.isEnabled = true
                Toast.makeText(context, viewState.errorMessage, Toast.LENGTH_SHORT).show()
            }
            is Success -> {
                binding.btnLogin.isEnabled = true
                if (viewState.message == "true") navigator?.replace(HomeFragment())
                else Toast.makeText(context, viewState.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            btnLogin.setOnClickListener {
                viewModel.login()
            }
        }
    }

}