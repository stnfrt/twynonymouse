package id.twynonymouse.core.utils

import android.content.SharedPreferences
import com.google.gson.Gson
import id.twynonymouse.core.model.response.TwitterUserResponse
import javax.inject.Inject

class SharedPref @Inject constructor(
    private val pref: SharedPreferences,
private val editor: SharedPreferences.Editor){

    companion object{
        const val KEY_USER= "key_user"
    }

    fun saveUser(user: TwitterUserResponse){
        editor.putString(KEY_USER, Gson().toJson(user))
        editor.apply()
    }

    fun getUser(): TwitterUserResponse{
        val stringUser = pref.getString(KEY_USER,"{}")
        return Gson().fromJson(stringUser, TwitterUserResponse::class.java)
    }
}