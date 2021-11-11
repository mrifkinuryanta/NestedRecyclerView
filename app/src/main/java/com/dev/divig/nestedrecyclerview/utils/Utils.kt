package com.dev.divig.nestedrecyclerview.utils

import android.content.Context
import java.text.SimpleDateFormat
import java.util.*

object Utils {
    private const val MONTH_PATTERN = "yyyy-MM-dd"

    fun dpToPixels(context: Context?, dp: Int): Int {
        if (context != null) {
            val scale = context.resources.displayMetrics.density
            return (dp * scale + 0.5f).toInt()
        }
        return 0
    }

    fun dateToMillis(value: String?): Long {
        val date = value ?: "0000-00-00"
        val formatterPrev =
            SimpleDateFormat(MONTH_PATTERN, Locale.getDefault())
        val originalDate = formatterPrev.parse(date) as Date
        return originalDate.time
    }
}