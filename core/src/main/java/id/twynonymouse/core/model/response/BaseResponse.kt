package id.twynonymouse.core.model.response

data class BaseResponse<T>(
    val message : String,
    val data : T? = null
)