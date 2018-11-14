package com.fanhl.zoomviewpager

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Context
import android.support.annotation.FloatRange
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

    /**
     * 在0到1之间，0为缩放成一个点，1为完全不缩放
     */
    @FloatRange(from = 0.0, to = 1.0)
    var zoom = 1f

    /**
     * 在0到1之间，0为未缩放，1为完全缩放
     */
    @FloatRange(from = 0.0, to = 1.0)
    var progress = 0f
        set(value) {
            if (field == value) {
                return
            }

            beforeAnimationProgress = field
            field = value

            animateZoom(beforeAnimationProgress, field)
        }

    /** 动画前的progress */
    private var beforeAnimationProgress = progress
    /**
     * 存放当前的动画进度
     *
     * 为0时当前进度
     */
    private var animatingProgress = progress

    private var zoomAnimator: ValueAnimator? = null

    init {
        zoom = .8f

//        layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

//        val specSizeWidth = View.MeasureSpec.getSize(widthMeasureSpec)
//        val specSizeHeight = View.MeasureSpec.getSize(heightMeasureSpec)
//        setMeasuredDimension(specSizeWidth, specSizeHeight)

        for (i in 0 until childCount) {
            val child = getChildAt(i)
            this.measureChild(child, widthMeasureSpec, heightMeasureSpec)
        }
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        val paddedWidth = width - paddingLeft - paddingRight
        val paddedHeight = height - paddingTop - paddingBottom


        for (i in 0 until childCount) {
            val child = getChildAt(i)

            child.layout(paddingLeft, paddingTop, paddingLeft + paddedWidth, paddingTop + paddedHeight)
            child.scaleX = getZoomProgress()
            child.scaleY = getZoomProgress()
        }
    }

    private fun getZoomProgress(): Float {
        val zoomProgress = 1f * (1f - animatingProgress) + zoom * animatingProgress
        println("zoomProgress$:$zoomProgress")
        return zoomProgress
    }

    private fun animateZoom(start: Float, end: Float) {
        zoomAnimator?.cancel()
        zoomAnimator = ValueAnimator.ofFloat(start, end).apply {
            duration = 1000
            addUpdateListener {
                animatingProgress = it.animatedValue as Float
                println("animatingProgress:$animatingProgress")
                requestLayout()
            }
            addListener(object : Animator.AnimatorListener {
                override fun onAnimationRepeat(animation: Animator?) {
                }

                override fun onAnimationEnd(animation: Animator?) {
                    beforeAnimationProgress = progress
                }

                override fun onAnimationCancel(animation: Animator?) {
                }

                override fun onAnimationStart(animation: Animator?) {
                }
            })
            start()
        }
    }
}