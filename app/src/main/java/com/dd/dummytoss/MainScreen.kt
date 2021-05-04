package com.dd.dummytoss

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.graphics.drawable.AnimationDrawable
import android.util.Log
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import kotlinx.android.synthetic.main.activity_main.*

class MainScreen : AppCompatActivity() {

    private var mInterstitialAd: InterstitialAd? = null
    private lateinit var mAdView: AdView
    private final var TAG = "MainScreen"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MobileAds.initialize(this) {}
        mAdView = findViewById(R.id.adBanner)
        adBannerLoading()
        adInterstitialLoading()

        val animDrawable = rlBackground.background as AnimationDrawable
        animDrawable.setEnterFadeDuration(4000)
        animDrawable.setExitFadeDuration(2000)
        animDrawable.start()
        onCoinToss()
    }

    private fun adBannerLoading() {
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
    }

    private fun adInterstitialLoading() {
        var adRequest = AdRequest.Builder().build()
        InterstitialAd.load(this, "ca-app-pub-3949256999942544/1033173712", adRequest, object :
            InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(p0: LoadAdError) {
                Log.d(TAG, p0?.message)
                mInterstitialAd = null
            }

            override fun onAdLoaded(p0: InterstitialAd) {
                Log.d(TAG, "Ad was Loaded.")
                mInterstitialAd = p0
            }
        })
    }

    private fun onCoinToss() {
        ivCoin.setOnClickListener {
            if (mInterstitialAd != null) {
                mInterstitialAd?.show(this)
            } else {
                Log.d(TAG, "The interstitial ad wasn't ready yet.")
            }
            val randomNumber = (1..2).random()

            if (randomNumber == 1) {
                flipTheCoin(R.drawable.ic_head, "Heads")
            } else {
                flipTheCoin(R.drawable.ic_tail, "Tails")
            }
        }
    }

    private fun flipTheCoin(imageId: Int, coinSide: String) {
        ivCoin.animate().apply {
            duration = 1000
            rotationBy(1800f)
            ivCoin.isClickable = false
        }.withEndAction {
            ivCoin.setImageResource(imageId)
            Toast.makeText(this, coinSide, Toast.LENGTH_SHORT).show()
            ivCoin.isClickable = true
        }.start()
    }

}

//    private fun adUtils() {
//        if (mInterstitialAd.isLoaded) {
//            mInterstitialAd.show()
//        } else {
//            onCoinToss()
//        }
//    }


