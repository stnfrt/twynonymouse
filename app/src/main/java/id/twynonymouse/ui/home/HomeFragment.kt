package id.twynonymouse.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import co.zsmb.rainbowcake.base.RainbowCakeFragment
import co.zsmb.rainbowcake.dagger.getViewModelFromFactory
import coil.api.load
import id.twynonymouse.R
import id.twynonymouse.ui.home.viewmodel.HomeViewState
import id.twynonymouse.ui.home.viewmodel.LoadingUser
import id.twynonymouse.ui.home.viewmodel.UserReady
import id.twynonymouse.ui.home.viewmodel.ErrorLoadUser
import id.twynonymouse.databinding.FragmentHomeBinding
import id.twynonymouse.ui.home.viewmodel.*
import timber.log.Timber

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
            is LoadingUser -> {
                binding.flipper.displayedChild = 0
            }
            is ErrorLoadUser -> {
                binding.flipper.displayedChild = 0
                Toast.makeText(context, "${viewState.errorMessage}", Toast.LENGTH_SHORT).show()
            }
            is UserReady -> {
                binding.flipper.displayedChild = 1
                binding.apply {
                    imgAva.load(viewState.data.profile_image_url_https){
                        crossfade(true)
                        placeholder(R.drawable.ic_twitter)
                    }
                    txtName.text = viewState.data.screen_name
                }
            }
            
            is ProcessPostTweet -> binding.btnPostTweet.isEnabled  = false
            is SuccessPostTweet -> Toast.makeText(context, "tweet posted", Toast.LENGTH_SHORT).show()
            is ErrorPostTweet -> {
                Timber.e(viewState.errorMessage)
                Toast.makeText(context, "${viewState.errorMessage}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnPostTweet.setOnClickListener {
            viewModel.postTweet(binding.edtNewTweet.text.toString())
        }
    }

}