package id.twynonymouse.ui.auth.viewmodel

sealed class LoginViewState

object Initial : LoginViewState()
object Loading : LoginViewState()
object UserSaved : LoginViewState()
