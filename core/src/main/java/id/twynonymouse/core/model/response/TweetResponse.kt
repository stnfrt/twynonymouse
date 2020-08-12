package id.twynonymouse.core.model.response

data class TweetResponse(
    val created_at: String? = null,
    val id_str: String? = null,
    val text: String? = null,
    val truncated: Boolean? = null,
    val extended_entities: ExtendedEntities? = null
) {
    data class ExtendedEntities(
        val media : List<Media?>? = listOf()
    ) {
        data class Media(
            val media_url_https : String? = null
        )

    }
}