package id.twynonymouse.ui.auth.viewmodel

import co.zsmb.rainbowcake.base.RainbowCakeViewModel
import com.google.firebase.auth.AuthResult
import id.twynonymouse.core.model.response.TwitterUserResponse
import id.twynonymouse.core.utils.SharedPref
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val pref : SharedPref
) : RainbowCakeViewModel<LoginViewState>(Initial) {

    fun saveUser(result: AuthResult?) {
        viewState = Loading
        result?.additionalUserInfo?.profile.apply {
            val user = TwitterUserResponse(
                verified = this?.get("verified") as Boolean?,
                description = this?.get("description") as String?,
                friends_count = this?.get("friends_count") as Int?,
                suspended = this?.get("suspended") as Boolean?,
                id_str = this?.get("id_str") as String?,
                profile_background_image_url = this?.get("profile_background_image_url").toString(),
                profile_image_url_https = this?.get("profile_image_url_https") as String?,
                followers_count = this?.get("followers_count") as Int?,
                default_profile = this?.get("default_profile") as Boolean?,
                profile_image_url = this?.get("profile_image_url") as String?,
                profile_use_background_image = this?.get("profile_use_background_image") as Boolean?,
                screen_name = this?.get("screen_name") as String?,
                name = this?.get("name") as String?,
                following = this?.get("following") as Boolean?,
                notifications = this?.get("notifications") as Boolean?,
                created_at = this?.get("created_at") as String?
            )
            pref.saveUser(user)
        }
        viewState = UserSaved
    }
}
