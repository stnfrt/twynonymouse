package id.twynonymouse.core.interactor

import id.twynonymouse.core.api.UserApi
import id.twynonymouse.core.model.User
import javax.inject.Inject

class UserInteract @Inject constructor(private val userApi: UserApi /*, val userDao: UserDao*/) {
    suspend fun getUserInfo(): User {
        return userApi.getUserInfo()
    }
}