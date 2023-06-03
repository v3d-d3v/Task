package co.ssup.task

import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityEvent
import co.ssup.task.utils.showToast

class WhatsAppAccessibilityService : AccessibilityService() {
  override fun onAccessibilityEvent(event: AccessibilityEvent?) {
    if (event?.eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
      val packageName = event.packageName?.toString()
      val className = event.className?.toString()

      // Check if WhatsApp is launched
      if (packageName == "com.whatsapp") {
        showToast(applicationContext, "WhatsApp Launched")
      }
    }
  }

  override fun onInterrupt() {
    // Not used
  }
}