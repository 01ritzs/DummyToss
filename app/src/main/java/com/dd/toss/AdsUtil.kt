package com.dd.toss

import android.content.Context
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.initialization.InitializationStatus

public class AdsUtils private constructor() {

    fun initMobileAds(context: Context?) {
        MobileAds.initialize(context) { initializationStatus: InitializationStatus? -> isMobileAddInitialized = true }
    }

    companion object {
        @JvmField
        var instance: AdsUtils? = null
        private var isMobileAddInitialized = false

        @JvmStatic
        fun getInstance(): AdsUtils? {
            if (instance == null) {
                instance = AdsUtils()
            }
            return instance
        }
    }
}