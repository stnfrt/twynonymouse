package id.twynonymouse.core.model.response

data class ErrorResponse(
    val errors: List<Error?>? = null
) {
    data class Error(
        val code: Int? = null,
        val message: String? = null
    )
}