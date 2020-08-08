package id.twynonymouse.ui.auth.viewmodel

import co.zsmb.rainbowcake.base.RainbowCakeViewModel
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val presenter: LoginPresenter
) : RainbowCakeViewModel<LoginViewState>(Initial) {

    fun login() = execute {
        try {
            viewState = Loading
            viewState = Success(presenter.postLogin().toString())
        } catch (e: Exception) {
            viewState = Error(e.message)
        }
    }
}
