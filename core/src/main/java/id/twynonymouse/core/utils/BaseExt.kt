package id.twynonymouse.core.utils

import android.view.View

fun String?.default(): String = this ?: ""
fun Int?.default(): Int = this ?: 0
fun View.visibille() {
    this.visibility = View.VISIBLE
}
fun View.gone() {
    this.visibility = View.GONE
}