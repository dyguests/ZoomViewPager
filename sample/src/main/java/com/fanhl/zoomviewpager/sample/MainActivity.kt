package com.fanhl.zoomviewpager.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.PagerSnapHelper
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val adapter = MainAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        PagerSnapHelper().attachToRecyclerView(recycler_view)

        recycler_view.adapter = adapter

        adapter.setNewData(
            List(10) {
                "$it"
            }
        )
    }

    class MainAdapter : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_view) {
        override fun convert(helper: BaseViewHolder?, item: String?) {

        }
    }
}
