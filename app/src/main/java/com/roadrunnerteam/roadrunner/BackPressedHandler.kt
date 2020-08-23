package com.roadrunnerteam.roadrunner

import android.app.Activity
import android.widget.Toast

object BackPressedHandler {

    private var backKeyPressedTime: Long = 0
    private var toast: Toast? = null

    fun onBackPressedKillProcess(activity: Activity) {
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis()
            toast = Toast.makeText(activity, activity.getString(R.string.common_guide_finish), Toast.LENGTH_SHORT)
            toast!!.show()
            return
        }
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            toast!!.cancel()
            activity.moveTaskToBack(true)
            activity.finish()
            android.os.Process.killProcess(android.os.Process.myPid())
        }
    }

    fun onBackPressedKillProcess(activity: Activity, listener: BackPressedHandlerListener?) {
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis()
            toast = Toast.makeText(activity, activity.getString(R.string.common_guide_finish), Toast.LENGTH_SHORT)
            toast!!.show()
            return
        }
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            listener?.exitEvent()
            toast!!.cancel()
            activity.moveTaskToBack(true)
            activity.finish()
            android.os.Process.killProcess(android.os.Process.myPid())
        }
    }
}


interface BackPressedHandlerListener {
    fun exitEvent()
}