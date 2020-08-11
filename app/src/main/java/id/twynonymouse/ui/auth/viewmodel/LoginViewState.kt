package id.twynonymouse.ui.auth.viewmodel

import id.twynonymouse.core.model.response.BaseResponse
import id.twynonymouse.core.model.response.LoginResponse

sealed class LoginViewState

object Initial : LoginViewState()
object Loading : LoginViewState()
object UserSaved : LoginViewState()
class Error(val errorMessage: String?) : LoginViewState()
data class Success(val data: BaseResponse<LoginResponse>) : LoginViewState()
