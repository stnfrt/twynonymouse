package id.twynonymouse.ui.auth

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import co.zsmb.rainbowcake.base.RainbowCakeFragment
import co.zsmb.rainbowcake.dagger.getViewModelFromFactory
import co.zsmb.rainbowcake.navigation.navigator
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.OAuthProvider
import id.twynonymouse.databinding.FragmentLoginBinding
import id.twynonymouse.ui.MainActivity
import id.twynonymouse.ui.auth.viewmodel.*
import id.twynonymouse.ui.home.HomeFragment
import timber.log.Timber
import java.lang.Exception


class LoginFragment : RainbowCakeFragment<LoginViewState, LoginViewModel>() {
    override fun getViewResource() = 0

    override fun provideViewModel() = getViewModelFromFactory()

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private var provider = OAuthProvider.newBuilder("twitter.com")
    private lateinit var firebaseAuth : FirebaseAuth
    private lateinit var mActivity:Activity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = activity as MainActivity
    }

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
           is UserSaved -> {
                navigator?.replace(HomeFragment())
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initVar()
        binding.apply {
            btnLogin.setOnClickListener {
                binding.btnLogin.isEnabled = false
                firebaseAuth
                    .startActivityForSignInWithProvider(mActivity, provider.build())
                    .addOnSuccessListener {
                        setSuccessLogin(it)
                    }
                    .addOnFailureListener {
                        setFailedLogin(it)
                    }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        setupPendingResult()
    }

    private fun initVar() {
        FirebaseApp.initializeApp(mActivity)
        firebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth.currentUser.apply {
            this?.startActivityForReauthenticateWithProvider(mActivity, provider.build())
                ?.addOnSuccessListener { setSuccessLogin(it) }
                ?.addOnFailureListener { setFailedLogin(it) }
        }
    }

    private fun setupPendingResult() {
        firebaseAuth.pendingAuthResult.apply {
            this?.addOnSuccessListener {
                setSuccessLogin(it)
            }?.addOnFailureListener {
                setFailedLogin(it)
            } ?: Timber.d("no pending result")
        }
    }

    private fun setFailedLogin(it: Exception) {
        binding.btnLogin.isEnabled = true
        Timber.e(it)
        Toast.makeText(context, "${it.message}", Toast.LENGTH_SHORT).show()
    }

    private fun setSuccessLogin(result: AuthResult?) {
        viewModel.saveUser(result)
    }

}