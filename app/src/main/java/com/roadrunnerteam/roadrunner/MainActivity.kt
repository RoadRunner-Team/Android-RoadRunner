package com.roadrunnerteam.roadrunner

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)
        setContentView(R.layout.activity_main)


        webMain.setNetworkAvailable(true)
        webMain.settings.javaScriptEnabled = true
        webMain.settings.domStorageEnabled = true
        webMain.settings.setSupportZoom(false)

        webMain.webChromeClient = object: WebChromeClient() {}
        webMain.webViewClient = object: WebViewClient() {}

        webMain.loadUrl("http://roadrunner-front.s3-website.ap-northeast-2.amazonaws.com")

        val timer = object: CountDownTimer(2000, 2000) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {

                imgLaunchBG.animate()
                    .translationY(0F)
                    .alpha(0.0f)
                    .setDuration(500)
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator) {
                            super.onAnimationEnd(animation)
                            imgLaunchBG.visibility = View.GONE
                        }

                        override fun onAnimationStart(animation: Animator?) {
                            super.onAnimationStart(animation)
                            progressMain.visibility = View.GONE
                        }
                    })
            }
        }
        timer.start()
    }

    override fun onBackPressed() {
        if (webMain != null && webMain.canGoBack())
            webMain.goBack()
        else
            BackPressedHandler.onBackPressedKillProcess(this)
    }

}
