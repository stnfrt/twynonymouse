package id.twynonymouse.ui.home

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import co.zsmb.rainbowcake.base.RainbowCakeFragment
import co.zsmb.rainbowcake.dagger.getViewModelFromFactory
import co.zsmb.rainbowcake.extensions.exhaustive
import id.twynonymouse.R
import id.twynonymouse.core.model.response.TweetResponse
import id.twynonymouse.core.utils.getTwitterPage
import id.twynonymouse.core.utils.gone
import id.twynonymouse.core.utils.loadWithCrossFade
import id.twynonymouse.core.utils.visibille
import id.twynonymouse.ui.home.viewmodel.HomeViewState
import id.twynonymouse.ui.home.viewmodel.LoadingUser
import id.twynonymouse.ui.home.viewmodel.UserReady
import id.twynonymouse.ui.home.viewmodel.ErrorLoadUser
import id.twynonymouse.databinding.FragmentHomeBinding
import id.twynonymouse.ui.home.adapter.TweetsAdapter
import id.twynonymouse.ui.home.viewmodel.*
import timber.log.Timber

class HomeFragment : RainbowCakeFragment<HomeViewState, HomeViewModel>() {

    override fun getViewResource() = 0

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var tweetsAdapter: TweetsAdapter
    private val list = mutableListOf<TweetResponse>()

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
        when (viewState) {
            is Initial -> {}
            is LoadingUser -> {
                binding.flipper.displayedChild = 1
            }
            is ErrorLoadUser -> {
                binding.flipper.displayedChild = 1
                Toast.makeText(context, "${viewState.errorMessage}", Toast.LENGTH_SHORT).show()
            }
            is UserReady -> {
                binding.flipper.displayedChild = 0
                binding.apply {
                    imgAva.loadWithCrossFade(viewState.data.profile_image_url_https,R.drawable.ic_twitter)
                    txtName.text = viewState.data.name
                    txtUserName.text = "@${viewState.data.screen_name}"
                    txtDesc.text = viewState.data.description
                }
            }

            is ProcessPostTweet -> binding.btnPostTweet.isEnabled = false
            is SuccessPostTweet -> {
                clearField()
                binding.layoutSuccess.visibille()
                binding.txtOpenTweet.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse(getTwitterPage(viewState.tweet.id_str))
                    activity?.startActivity(intent)
                }
                Toast.makeText(context, "tweet posted", Toast.LENGTH_SHORT).show()
            }
            is ErrorPostTweet -> {
                clearField()
                Timber.e(viewState.errorMessage)
                Toast.makeText(context, "${viewState.errorMessage}", Toast.LENGTH_SHORT).show()
            }


            is LoadingGetTweetList -> binding.listFlipper.displayedChild = 1
            is SuccessGetTweetList -> {
                binding.listFlipper.displayedChild = 0
                list.clear()
                list.addAll(viewState.tweets)
                tweetsAdapter.notifyDataSetChanged()
            }
            is ErrorGetTweetList -> {
                binding.listFlipper.displayedChild = 2
                Timber.e(viewState.errorMessage)
                Toast.makeText(context, "${viewState.errorMessage}", Toast.LENGTH_SHORT).show()
            }
        }.exhaustive
    }

    private fun clearField() {
        binding.edtNewTweet.text.clear()
        binding.btnPostTweet.isEnabled = true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tweetsAdapter = TweetsAdapter(list)
        binding.rvTweet.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = tweetsAdapter
        }
        binding.btnPostTweet.setOnClickListener {
            viewModel.postTweet(binding.edtNewTweet.text.toString())
        }
        binding.swiper.setOnRefreshListener {
            viewModel.refreshTweetList()
            binding.swiper.isRefreshing = false
        }
        binding.txtCloseHint.setOnClickListener { binding.layoutSuccess.gone() }
    }

}