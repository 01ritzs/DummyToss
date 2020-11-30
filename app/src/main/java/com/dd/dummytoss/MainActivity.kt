package com.dd.dummytoss

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        onCoinToss()
    }

    private fun onCoinToss() {
        ivCoin.setOnClickListener {
            val randomNumber = (1..2).random()

            if (randomNumber == 1) {
                flipTheCoin(R.drawable.heads, "Heads")
            } else {
                flipTheCoin(R.drawable.tails, "Tails")
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
