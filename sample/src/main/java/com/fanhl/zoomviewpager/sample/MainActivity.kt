package com.fanhl.zoomviewpager.sample

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ViewPager::clearOnPageChangeListeners

        zvp_1.setOnClickListener {
            zvp_1.progress = if (zvp_1.progress == 0f) 1f else 0f
        }
    }
}
