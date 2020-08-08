package id.twynonymouse.core.utils

import android.content.SharedPreferences
import com.google.gson.Gson
import id.twynonymouse.core.model.response.LoginResponse
import javax.inject.Inject

class SharedPref @Inject constructor(
    private val pref: SharedPreferences,
private val editor: SharedPreferences.Editor){

    companion object{
        const val KEY_USER= "key_user"
    }

    fun saveUser(user: LoginResponse){
        editor.putString(KEY_USER, Gson().toJson(user))
        editor.apply()
    }

    fun getUser(): LoginResponse{
        val stringUser = pref.getString(KEY_USER,"{}")
        return Gson().fromJson(stringUser, LoginResponse::class.java)
    }
}