package com.bdc.struct

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bdc.blibrary.log.*

class LogDemoActivity : AppCompatActivity() {
    var viewPrinter:HiViewPrinter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_demo)
        viewPrinter= HiViewPrinter(this)
        findViewById<View>(R.id.print_log).setOnClickListener {
            printLog()
        }
        viewPrinter!!.viewProvider.showFloatingView()
    }

    private fun printLog(){
        HiLogManager.getInstance().addPrinters(viewPrinter)
        HiLog.log(object : HiLogConfig(){
            override fun includeThread(): Boolean {
                return true
            }

            override fun stackTraceDepth(): Int {
                return 0
            }
        },HiLogType.E,"-----",5566)
        HiLog.i(1114)
    }
}
