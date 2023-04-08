package com.android.petsdetails.misc

import android.annotation.SuppressLint
import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.StringRes


class Util {
    object Strings {
        fun get(@StringRes stringRes: Int, vararg formatArgs: Any = emptyArray()): String {
            return App.instance.getString(stringRes, *formatArgs)
        }

        @SuppressLint("ResourceType")
        fun getArray(
            @StringRes stringRes: Int,
            vararg formatArgs: Any = emptyArray()
        ): MutableList<String> {
            return App.instance.resources.getStringArray(stringRes).toMutableList()
        }
    }

    object Keyboard {
        fun hideKeyboard(activity: Activity) {
            val imm: InputMethodManager =
                activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            var view: View? = activity.currentFocus
            if (view == null) {
                view = View(activity)
            }
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0)
        }
    }
}