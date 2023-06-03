package co.ssup.task.utils

import android.content.Context
import android.provider.Settings
import android.provider.Settings.SettingNotFoundException
import android.text.TextUtils.SimpleStringSplitter
import android.util.Log
import android.widget.Toast
import retrofit2.HttpException
import retrofit2.Response

suspend fun <T> Response<T>.parse(
  onSuccess: suspend (T) -> Unit
) {
  if (isSuccessful) {
    val responseBody = body()
    if (responseBody == null) {
      onSuccess(Unit as T)
    } else {
      onSuccess(responseBody)
    }
  } else {
    throw HttpException(this)
  }
}

fun isAccessibilityServiceEnabled(context: Context, accessibilityServiceName: String): Boolean {
  val accessibilityEnabled = try {
    Settings.Secure.getInt(context.contentResolver, Settings.Secure.ACCESSIBILITY_ENABLED)
  } catch (e: SettingNotFoundException) {
    0
  }

  val colonSplitter = SimpleStringSplitter(':')
  if (accessibilityEnabled == 1) {
    val settingValue = Settings.Secure.getString(context.contentResolver, Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES)
    Log.d("Vedant", "isAccessibilityServiceEnabled: $settingValue")
    if (settingValue != null) {
      colonSplitter.setString(settingValue)
      while (colonSplitter.hasNext()) {
        val accessibilityService = colonSplitter.next()
        if (accessibilityService.equals(accessibilityServiceName, ignoreCase = true)) {
          return true
        }
      }
    }
  }
  return false
}

fun showToast(context: Context, message: String) {
  Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}