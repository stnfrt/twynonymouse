package id.twynonymouse.core.interactor

import id.twynonymouse.core.api.BaseApi
import id.twynonymouse.core.model.response.User
import id.twynonymouse.core.model.response.toUser
import id.twynonymouse.core.utils.SharedPref
import javax.inject.Inject

class UserInteract @Inject constructor(private val baseApi: BaseApi,
    private val pref: SharedPref/*, val userDao: UserDao*/) {
    fun getUserInfo(): User {
        return pref.getUser().toUser()
    }
}