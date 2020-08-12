package id.twynonymouse.core.model.response

data class TweetResponse(
    val created_at: String? = null,
    val id_str: String? = null,
    val text: String? = null,
    val truncated: Boolean? = null,
    val source: String? = null
)