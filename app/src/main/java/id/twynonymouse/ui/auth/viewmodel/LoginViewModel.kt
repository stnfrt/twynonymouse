package id.twynonymouse.ui.auth.viewmodel

import co.zsmb.rainbowcake.base.RainbowCakeViewModel
import id.twynonymouse.core.utils.SharedPref
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val presenter: LoginPresenter,
    private val pref : SharedPref
) : RainbowCakeViewModel<LoginViewState>(Initial) {

    fun login() = execute {
        try {
            viewState = Loading
            presenter.postLogin("need username, really?").apply {
                this.data?.let { pref.saveUser(it) }
                viewState = Success(this)
            }
        } catch (e: Exception) {
            viewState = Error(e.message)
        }
    }
}
