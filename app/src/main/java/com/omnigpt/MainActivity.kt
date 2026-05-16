package com.omnigpt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.omnigpt.core.DeviceAnalyzer

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val profile = DeviceAnalyzer.analyze(this)
        println("Omni-GPT v2.0 on ${profile.ramTier}GB tier device")
    }
}
