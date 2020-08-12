package id.twynonymouse.ui.home.viewmodel

import co.zsmb.rainbowcake.base.RainbowCakeViewModel
import com.google.gson.Gson
import id.twynonymouse.core.model.response.ErrorResponse
import id.twynonymouse.core.utils.default
import retrofit2.HttpException
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val homePresenter: HomePresenter
) : RainbowCakeViewModel<HomeViewState>(
    Initial
) {

    fun load() = execute {
        try {
            viewState = LoadingUser
            viewState = UserReady(homePresenter.getData())
        } catch (e: Exception) {
            viewState = ErrorLoadUser(e.message)
        }
    }

    fun postTweet(newTweet: String) = execute {
        try {
            viewState = ProcessPostTweet
            viewState = SuccessPostTweet(homePresenter.postTweet(newTweet))
        }catch (e: Throwable) {
            var errMessage = e.message
            if (e is HttpException) Gson().fromJson(e.response()?.errorBody()?.string().default(), ErrorResponse::class.java)
                .let { errMessage = it.errors?.map { it?.message }.toString()}
            viewState = ErrorPostTweet(errMessage)
        }
    }
}
