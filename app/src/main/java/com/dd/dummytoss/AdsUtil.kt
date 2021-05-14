package com.dd.dummytoss

import android.app.Activity
import android.content.Context
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.initialization.InitializationStatus
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class AdsUtils private constructor() {
    private val adRequest = AdRequest.Builder().build()
    private var adInterstitial: InterstitialAd? = null

    fun initMobileAds(context: Context?) {
        MobileAds.initialize(context) { initializationStatus: InitializationStatus? ->
            isMobileAddInitialized = true
        }
    }

    fun loadInterstitial(context: Context) {
        if (isMobileAddInitialized) {
            val adInterstitialId = context.resources.getString(R.string.interstitial_id)
            InterstitialAd.load(
                context,
                adInterstitialId,
                adRequest,
                object : InterstitialAdLoadCallback() {
                    override fun onAdLoaded(interstitialAd: InterstitialAd) {
                        adInterstitial = interstitialAd
                    }

                    override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                        adInterstitial = null
                    }
                })
        }
    }

    fun showInterstitialAd(activity: Activity) {
        if (adInterstitial != null) {
            adInterstitial!!.show(activity)
        } else {
            loadInterstitial(activity)
        }
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