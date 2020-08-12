package id.twynonymouse.core.api

import id.twynonymouse.core.model.request.LoginRequest
import id.twynonymouse.core.model.response.BaseResponse
import id.twynonymouse.core.model.response.LoginResponse
import id.twynonymouse.core.model.response.TweetResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface BaseApi {

    @POST("login")
    suspend fun postLogin(@Body request:LoginRequest): BaseResponse<LoginResponse>


    @POST("statuses/update.json")
    suspend fun postTweet(@Query(value = "status") tweet: String): TweetResponse

    @GET("statuses/user_timeline.json")
    suspend fun getListTweet(@Query(value = "user_id") idStr: String?): List<TweetResponse>
}