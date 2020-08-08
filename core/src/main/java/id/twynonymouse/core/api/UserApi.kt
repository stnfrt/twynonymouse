package id.twynonymouse.core.api

import id.twynonymouse.core.model.User
import retrofit2.http.GET

interface UserApi {

    @GET("users/1")
    suspend fun getUserInfo(): User
}