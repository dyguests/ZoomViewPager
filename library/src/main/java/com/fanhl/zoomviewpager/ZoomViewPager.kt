package com.fanhl.zoomviewpager

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup

/**
 * 可以缩放的ViewPager
 */
class ZoomViewPager @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ViewGroup(context, attrs, defStyleAttr) {
    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {

        for (i in 0 until childCount) {
            val child = getChildAt(i)

            child.layout(l, t, r, b)
        }
    }
}