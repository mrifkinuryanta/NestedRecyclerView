package com.dev.divig.nestedrecyclerview.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SpacesItemDecoration(private val space: Int, private val isPaddingTop: Boolean) :
    RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        if (isPaddingTop) {
            if (parent.paddingTop != space) {
                parent.setPadding(0, space, 0, 0)
                parent.clipToPadding = false
            }
        } else {
            if (parent.paddingRight != space) {
                parent.setPadding(0, space, space, space)
                parent.clipToPadding = false
            }
        }
    }
}