package com.omnigpt.core

import android.app.ActivityManager
import android.content.Context

data class DeviceProfile(
    val ramTier: Int,
    val hasVoice: Boolean,
    val hasVision: Boolean,
    val maxEmbeddings: Int,
    val supportsRAG: Boolean
)

object DeviceAnalyzer {
    fun analyze(context: Context): DeviceProfile {
        val am = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val memMB = am.memoryInfo.totalMem / (1024 * 1024)
        val tier = when {
            memMB < 3000 -> 0 to 50
            memMB < 6000 -> 1 to 100
            memMB < 9000 -> 2 to 200
            else -> 3 to 400
        }
        return DeviceProfile(tier.first, memMB >= 4000, memMB >= 6000, tier.second, memMB >= 3000)
    }
    fun getModelName(tier: Int) = when (tier) {
        0 -> "tinyllama-1.1b-chat.Q4_K_M"
        1 -> "gemma-2-2b-it.Q4_K_M"
        2 -> "phi-3-mini-4k-instruct.Q4_K_M"
        else -> "qwen2-vl-2b.Q4_K_M"
    }
}
