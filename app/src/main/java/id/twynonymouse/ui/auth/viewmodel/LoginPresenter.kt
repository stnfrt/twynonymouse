package id.twynonymouse.ui.auth.viewmodel

import co.zsmb.rainbowcake.withIOContext
import id.twynonymouse.core.interactor.UserInteract
import javax.inject.Inject

class LoginPresenter @Inject constructor(private val userInteract: UserInteract) {

    suspend fun postLogin() = withIOContext {
        true
    }
}
