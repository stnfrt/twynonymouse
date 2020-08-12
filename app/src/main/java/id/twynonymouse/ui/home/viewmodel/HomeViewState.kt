package id.twynonymouse.ui.home.viewmodel

import id.twynonymouse.core.model.response.TweetResponse
import id.twynonymouse.core.model.response.TwitterUserResponse

sealed class HomeViewState

object Initial : HomeViewState()
object ProcessPostTweet : HomeViewState()
data class SuccessPostTweet(val tweet: TweetResponse) : HomeViewState()
data class ErrorPostTweet(val errorMessage: String?) : HomeViewState()
object LoadingUser : HomeViewState()
class ErrorLoadUser(val errorMessage: String?) : HomeViewState()
data class UserReady(val data: TwitterUserResponse) : HomeViewState()
