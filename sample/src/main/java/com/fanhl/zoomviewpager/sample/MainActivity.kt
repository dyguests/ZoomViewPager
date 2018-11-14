package com.fanhl.zoomviewpager.sample

import android.os.Bundle
import android.support.transition.TransitionManager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_view.view.*

class MainActivity : AppCompatActivity() {

    private val adapter = MainAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        assignViews()
        initData()
        refreshData()
    }

    fun assignViews() {
        PagerSnapHelper().attachToRecyclerView(recycler_view)

        fab.setOnClickListener {
            if (recycler_view.layoutManager is GridLayoutManager) {
                TransitionManager.beginDelayedTransition(recycler_view)
                recycler_view.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            } else {
                TransitionManager.beginDelayedTransition(recycler_view)
                recycler_view.layoutManager = GridLayoutManager(this@MainActivity, 3)
            }
        }
    }

    fun initData() {
        recycler_view.adapter = adapter
    }

    fun refreshData() {
        adapter.setNewData(
            List(10) {
                "$it"
            }
        )
    }

    class MainAdapter : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_view) {
        override fun convert(helper: BaseViewHolder?, item: String?) {
            helper?.itemView?.apply {
                tv_1.text = item
            }
        }
    }
}
