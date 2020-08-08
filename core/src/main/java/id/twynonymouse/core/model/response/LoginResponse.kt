package id.twynonymouse.core.model.response

import id.twynonymouse.core.utils.default

data class LoginResponse(
    val username : String? = "",
    val name: String? = "",
    val token: String? = ""
)

fun LoginResponse.toUser() = User(0,this.name.default(), "")