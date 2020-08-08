package id.twynonymouse.core.api

import id.twynonymouse.core.model.request.LoginRequest
import id.twynonymouse.core.model.response.BaseResponse
import id.twynonymouse.core.model.response.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface BaseApi {

    @POST("login")
    suspend fun postLogin(@Body request:LoginRequest): BaseResponse<LoginResponse>
}