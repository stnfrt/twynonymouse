package id.twynonymouse.home.viewmodel

import id.twynonymouse.core.model.User

sealed class HomeViewState

object Initial : HomeViewState()
object Loading : HomeViewState()
class Error(val errorMessage: String?) : HomeViewState()
data class UserReady(val data: User) : HomeViewState()
