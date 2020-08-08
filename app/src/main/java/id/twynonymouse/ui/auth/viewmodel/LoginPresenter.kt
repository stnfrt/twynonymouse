package id.twynonymouse.ui.auth.viewmodel

import co.zsmb.rainbowcake.withIOContext
import id.twynonymouse.core.interactor.LoginInteract
import id.twynonymouse.core.interactor.UserInteract
import javax.inject.Inject

class LoginPresenter @Inject constructor(private val interact: LoginInteract) {

    suspend fun postLogin(username:String) = withIOContext {
        interact.postLogin(username)
    }
}
