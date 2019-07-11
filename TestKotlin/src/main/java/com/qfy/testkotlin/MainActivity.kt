package com.qfy.testkotlin

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity(), View.OnClickListener {
    override fun onClick(viewID: View?) {
        if (viewID == tv1) {
            Toast.makeText(this, "点击了" + tv1.toString(), Toast.LENGTH_LONG).show()
        } else if (viewID == tv2) {
            Toast.makeText(this, "点击了" + tv2.toString(), Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tv1 = findViewById<View>(R.id.tv1)
        val tv2 = findViewById<View>(R.id.tv2)
        tv1.setOnClickListener(this)
        tv2.setOnClickListener(this)
    }
}
