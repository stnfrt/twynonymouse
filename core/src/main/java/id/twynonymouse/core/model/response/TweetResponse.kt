package id.twynonymouse.core.model.response

data class TweetResponse(
    val created_at: String? = null,
    val id_str: String? = null,
    val text: String? = null,
    val is_quote_status: Boolean? = false,
    val extended_entities: ExtendedEntities? = null,
    val in_reply_to_status_id_str: String? = null,
    val entities: Entities? = null,
    val quoted_status: TweetResponse? = null,
    val retweeted_status: TweetResponse? = null,
    val user: TwitterUserResponse? = null
) {
    data class ExtendedEntities(
        val media: List<Media?>? = listOf()
    ) {
        data class Media(
            val media_url_https: String? = null
        )
    }

    data class Entities(
        val user_mentions: List<UserMention>? = null
    ) {
        data class UserMention(
            val screen_name: String
        )
    }

}