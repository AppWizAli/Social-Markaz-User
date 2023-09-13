package com.socialmarkaz.app.Ui

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Switch
import androidx.appcompat.app.AppCompatDelegate
import com.socialmarkaz.app.R
import com.socialmarkaz.app.databinding.ActivityBuyBinding
import com.socialmarkaz.app.databinding.ActivitySettingBinding

class ActivitySetting : AppCompatActivity() {
    private lateinit var binidng: ActivitySettingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binidng = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binidng.root)


        binidng.tvThemeSetting.setOnClickListener {
            showDialog()
        }


        binidng.layPrivacyPolicy.setOnClickListener {
            startActivity(Intent(this@ActivitySetting,ActivityWebView::class.java))
        }
    }

    private fun showDialog() {
        var dialog = Dialog(this@ActivitySetting)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(R.layout.dialog_theme)
        val btn = dialog.findViewById<Switch>(R.id.switch1)
        btn.setOnCheckedChangeListener { _, isChecked ->
            if (btn.isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                btn.text = "Disable Dark Mode"
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                btn.text = "Enable dark Mode"
            }
        }

        dialog.show()
    }
}