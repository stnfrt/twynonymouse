package id.twynonymouse.ui.auth.viewmodel

sealed class LoginViewState

object Initial : LoginViewState()
object Loading : LoginViewState()
class Error(val errorMessage: String?) : LoginViewState()
data class Success(val message: String) : LoginViewState()
