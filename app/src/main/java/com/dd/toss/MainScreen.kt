package com.dd.toss

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.graphics.drawable.AnimationDrawable
import android.widget.ImageView
import android.widget.RelativeLayout
import com.google.android.gms.ads.*

public class MainScreen : AppCompatActivity() {

    private lateinit var mAdView: AdView
    private lateinit var rlBackground: RelativeLayout
    private lateinit var ivCoin: ImageView

    override
    fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rlBackground = findViewById(R.id.rlBackground)
        ivCoin = findViewById(R.id.ivCoin)
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

    private fun onCoinToss() {
        ivCoin.setOnClickListener {
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



