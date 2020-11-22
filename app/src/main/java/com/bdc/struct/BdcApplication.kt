package com.bdc.struct

import android.app.Application
import com.bdc.blibrary.log.HiConsolePrinter
import com.bdc.blibrary.log.HiLogConfig
import com.bdc.blibrary.log.HiLogManager
import com.google.gson.Gson

class BdcApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        HiLogManager.getInstance().init(object :HiLogConfig(){
            override fun getGlobalTag(): String {
                return "BDCapplication"
            }

            override fun injectJsonParser(): JsonParser {
                return JsonParser { src -> Gson().toJson(src)}
            }
        },HiConsolePrinter())
    }
}