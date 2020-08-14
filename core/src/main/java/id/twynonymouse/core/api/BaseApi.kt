package id.twynonymouse.core.api

import id.twynonymouse.core.model.response.TweetResponse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface BaseApi {

    @POST("statuses/update.json")
    suspend fun postTweet(@Query(value = "status") tweet: String): TweetResponse

    @GET("statuses/user_timeline.json")
    suspend fun getListTweet(@Query(value = "user_id") idStr: String?): List<TweetResponse>
}