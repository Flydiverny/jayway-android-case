package se.markusmaga.jayway.weather.utils.kotlin.extensions

import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.widget.Toast

/**
 * Created by Flydiverny on 25/09/16.
 */
fun Fragment.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    activity.toast(message, duration)
}

fun Fragment.toast(@StringRes message: Int, duration: Int = Toast.LENGTH_SHORT) {
    activity.toast(message, duration)
}