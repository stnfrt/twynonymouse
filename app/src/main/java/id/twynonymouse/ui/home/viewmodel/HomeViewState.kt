package id.twynonymouse.ui.home.viewmodel

import id.twynonymouse.core.model.response.TwitterUserResponse

sealed class HomeViewState

object Initial : HomeViewState()
object Loading : HomeViewState()
class Error(val errorMessage: String?) : HomeViewState()
data class UserReady(val data: TwitterUserResponse) : HomeViewState()
