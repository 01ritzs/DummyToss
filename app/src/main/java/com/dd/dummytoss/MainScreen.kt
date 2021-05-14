package com.dd.dummytoss

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.graphics.drawable.AnimationDrawable
import android.util.Log
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainScreen : AppCompatActivity() {

//    private val primaryLocale: Locale = this.resources.configuration.locales[0]
//    private val locale: String = primaryLocale.displayName
    private var mInterstitialAd: InterstitialAd? = null
    private lateinit var mAdView: AdView
    private final var TAG = "MainScreen"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAdView = findViewById(R.id.adBanner)
        AdsUtils.getInstance()?.initMobileAds(this)
        adBannerLoading()

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

    override fun onResume() {
        super.onResume()
        AdsUtils.getInstance()?.loadInterstitial(this)
    }



    private fun onCoinToss() {
        ivCoin.setOnClickListener {
            AdsUtils.getInstance()?.showInterstitialAd(this)
            val randomNumber = (1..2).random()

            if (randomNumber == 1) {
                flipTheCoin(R.raw.ic_head, getString(R.string.text_heads))
            } else {
                flipTheCoin(R.raw.ic_tail, getString(R.string.text_tails))
            }
        }
    }

    private fun flipTheCoin(imageId: Int, coinSide: String) {
        ivCoin.animate().apply {
            duration = 1000
            rotationYBy(1800f)
            ivCoin.isClickable = false
        }.withEndAction {
            ivCoin.setImageResource(imageId)
            Toast.makeText(this, coinSide, Toast.LENGTH_SHORT).show()
            ivCoin.isClickable = true
        }.start()
    }
}



