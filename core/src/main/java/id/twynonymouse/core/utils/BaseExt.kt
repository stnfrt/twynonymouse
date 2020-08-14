package id.twynonymouse.core.utils

import android.view.View
import android.widget.ImageView
import coil.api.load
import coil.transform.RoundedCornersTransformation

fun String?.default(): String = this ?: ""
fun Int?.default(): Int = this ?: 0
fun Long?.default(): Long = this ?: 0
fun Boolean?.default(): Boolean = this ?: false
fun View.visibille() {
    this.visibility = View.VISIBLE
}
fun View.gone() {
    this.visibility = View.GONE
}

fun ImageView.loadWithCrossFade(url:String?, placeHolder:Int? = null) = this.load(url){
    crossfade(true)
    if (placeHolder!=null) placeholder(placeHolder)
    transformations(RoundedCornersTransformation())
}

fun getTwitterPage(idStr: String?): String? {
    return "https://twitter.com/twynony/status/$idStr"
}