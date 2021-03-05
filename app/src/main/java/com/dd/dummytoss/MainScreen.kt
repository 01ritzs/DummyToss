package com.dd.dummytoss

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.ads.InterstitialAd
import android.graphics.drawable.Animatable
import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.DrawableContainer
import kotlinx.android.synthetic.main.activity_main.*

class MainScreen : AppCompatActivity() {

//    private lateinit var mInterstitialAd: InterstitialAd

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val animDrawable = rlBackground.background as AnimationDrawable
        animDrawable.setEnterFadeDuration(4000)
        animDrawable.setExitFadeDuration(2000)
        animDrawable.start()
//        adLoading()
        onCoinToss()
    }

//    private fun adLoading() {
//        mInterstitialAd = InterstitialAd(this)
//        mInterstitialAd.adUnitId = "ca-app-pub-3940256099942544/1033173712"
//        mInterstitialAd.loadAd(AdRequest.Builder().build())
//
//    }

//    private fun adUtils() {
//        if (mInterstitialAd.isLoaded) {
//            mInterstitialAd.show()
//        } else {
//            onCoinToss()
//        }
//    }

    private fun onCoinToss() {
        ivCoin.setOnClickListener {
//            adUtils()
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
