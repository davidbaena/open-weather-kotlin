package davidbaena.com.kotlinbook.extensions

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.View

val View.ctx: Context
    get() = context

fun Context.color(res: Int): Int = ContextCompat.getColor(this, res)

fun View.slideExit() {
    if (translationY == 0f) animate().translationY(-height.toFloat())
}

fun View.slideEnter() {
    if (translationY < 0) animate().translationY(0f)
}