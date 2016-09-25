package se.markusmaga.jayway.weather.utils.kotlin.extensions

import android.app.Activity
import android.support.annotation.StringRes
import android.widget.Toast

/**
 * Created by Flydiverny on 25/09/16.
 */
fun Activity.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

fun Activity.toast(@StringRes message: Int, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}
