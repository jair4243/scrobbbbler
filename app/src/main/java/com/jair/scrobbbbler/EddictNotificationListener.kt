package com.jair.scrobbbbler

import android.app.Notification
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log

class EddictNotificationListener : NotificationListenerService() {

    override fun onNotificationPosted(sbn: StatusBarNotification) {
        val pkg = sbn.packageName ?: return

        if (!looksLikeEddict(pkg)) return

        val extras = sbn.notification.extras
        val title = extras?.getCharSequence(Notification.EXTRA_TITLE)?.toString().orEmpty()
        val text = extras?.getCharSequence(Notification.EXTRA_TEXT)?.toString().orEmpty()
        val subText = extras?.getCharSequence(Notification.EXTRA_SUB_TEXT)?.toString().orEmpty()
        val bigText = extras?.getCharSequence(Notification.EXTRA_BIG_TEXT)?.toString().orEmpty()

        val parsed = NotificationParser.parse(title, text, subText)

        val logText = buildString {
            appendLine("pkg=$pkg")
            appendLine("title=$title")
            appendLine("text=$text")
            appendLine("subText=$subText")
            appendLine("bigText=$bigText")
            appendLine("parsed=$parsed")
        }

        Log.d("scrobbbbler", logText)
        NotificationStore.saveLatestEntry(applicationContext, logText)
    }

    private fun looksLikeEddict(pkg: String): Boolean {
        val lower = pkg.lowercase()
        return lower.contains("eddict") || lower.contains("shanling")
    }
}