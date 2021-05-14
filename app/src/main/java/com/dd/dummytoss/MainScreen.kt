package com.dd.dummytoss

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.graphics.drawable.AnimationDrawable
import com.google.android.gms.ads.*
import kotlinx.android.synthetic.main.activity_main.*

class MainScreen : AppCompatActivity() {

    private lateinit var mAdView: AdView
    private final var TAG = "MainScreen"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AdsUtils.getInstance()?.loadInterstitial(this)
        mAdView = findViewById(R.id.adBanner)
        adBannerLoading()

        val animDrawable = rlBackground.background as AnimationDrawable
        animDrawable.setEnterFadeDuration(4000)
        animDrawable.setExitFadeDuration(2000)
        animDrawable.start()
        init()
    }

    private fun adBannerLoading() {
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
    }

    override fun onResume() {
        super.onResume()
        AdsUtils.getInstance()?.loadInterstitial(this)
    }


    private fun init() {
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



