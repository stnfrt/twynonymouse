package id.twynonymouse.core.interactor

import id.twynonymouse.core.api.BaseApi
import id.twynonymouse.core.model.response.TwitterUserResponse
import id.twynonymouse.core.utils.SharedPref
import id.twynonymouse.core.utils.default
import javax.inject.Inject

class UserInteract @Inject constructor(
    private val baseApi: BaseApi,
    private val pref: SharedPref/*, val userDao: UserDao*/
) {
    fun getUserInfo(): TwitterUserResponse {
        return pref.getUser()
    }

    suspend fun postTweet(newTweet: String)  = baseApi.postTweet(newTweet)
    suspend fun getListTweet() = baseApi.getListTweet(pref.getUser().id_str)
}