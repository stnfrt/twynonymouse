package id.twynonymouse.core.interactor

import id.twynonymouse.core.api.BaseApi
import id.twynonymouse.core.model.request.LoginRequest
import id.twynonymouse.core.model.response.BaseResponse
import id.twynonymouse.core.model.response.LoginResponse
import javax.inject.Inject

class LoginInteract @Inject constructor(private val baseApi: BaseApi /*, val userDao: UserDao*/) {
    suspend fun postLogin(username:String): BaseResponse<LoginResponse> {
        return baseApi.postLogin(LoginRequest(username))
    }
}