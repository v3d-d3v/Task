package co.ssup.task

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import co.ssup.task.utils.isAccessibilityServiceEnabled
import co.ssup.task.utils.showToast
import dagger.hilt.android.AndroidEntryPoint

private const val REQUEST_ACCESSIBILITY = 1
private const val SERVICE_NAME = "co.ssup.task/co.ssup.task.WhatsAppAccessibilityService"

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    //if accessibility service is not enabled, request permission
    val isServiceEnabled = isAccessibilityServiceEnabled(this, SERVICE_NAME)
    if (!isServiceEnabled) {
      showToast(applicationContext, "Please enable accessibility service")
      requestAccessibilityPermission()
    }
  }

  private fun requestAccessibilityPermission() {
    val intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
    startActivityForResult(intent, REQUEST_ACCESSIBILITY)
  }
}