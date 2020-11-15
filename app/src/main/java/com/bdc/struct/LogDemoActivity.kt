package com.bdc.struct

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bdc.blibrary.log.HiLog

class LogDemoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_demo)
        findViewById<View>(R.id.print_log).setOnClickListener {
            printLog()
        }
    }

    private fun printLog(){
        android.util.Log.d("bdc","printlog")
        HiLog.i(1114)
    }
}
