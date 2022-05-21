package com.kayuzer0.eightball

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.*
import kotlin.random.Random
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class MainActivity : AppCompatActivity() {
    private var mInterstitialAd: InterstitialAd? = null
    private var TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        var showAnswer = true

        MobileAds.initialize(
            this
        ) { }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val askButton = findViewById<Button>(R.id.ask_button)
        val answerTextView = findViewById<TextView>(R.id.answerTextView)

        askButton.setOnClickListener {
            val answers = listOf("It is certain.", "It is decidedly so.", "Without a doubt.", "Yes, definitely.", "You may rely on it.", "As I see it, yes.", "Most likely.", "Outlook good.", "Yes.", "Sings point to yes.", "Reply hazy, try again.", "Ask again later.", "Better not tell you now.", "Cannot predict now.", "Concentrate and ask again.", "Don't count on it.", "My reply is no.", "My sources say no.", "Outlook not so good.", "Very doubtful.")
            val randomIndex = Random.nextInt(answers.size)
            val randomAnswer = answers[randomIndex]

            if (showAnswer) {
                showAnswer = false
                answerTextView.text = randomAnswer

                val adRequest = AdRequest.Builder().build()

                InterstitialAd.load(this,"ca-app-pub-3940256099942544/1033173712", adRequest, object : InterstitialAdLoadCallback() {
                    override fun onAdFailedToLoad(adError: LoadAdError) {
                        mInterstitialAd = null
                    }

                    override fun onAdLoaded(interstitialAd: InterstitialAd) {
                        mInterstitialAd = interstitialAd

                        if (mInterstitialAd != null) {
                            mInterstitialAd?.show(this@MainActivity)
                        } else {
                            Log.d("TAG", "The interstitial ad wasn't ready yet.")
                        }

                        mInterstitialAd?.fullScreenContentCallback = object: FullScreenContentCallback() {
                            override fun onAdDismissedFullScreenContent() {
                                Log.d(TAG, "Ad dismissed.")
                                showAnswer = true
                            }

                            override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                                showAnswer = true
                            }

                            override fun onAdShowedFullScreenContent() {
                                Log.d(TAG, "Ad shown.")
                                mInterstitialAd = null
                            }
                        }
                    }
                })
            }
        }
    }
}