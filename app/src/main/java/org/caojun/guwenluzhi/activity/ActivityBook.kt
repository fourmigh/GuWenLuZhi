package org.caojun.guwenluzhi.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.caojun.guwenluzhi.databinding.ActivityBookBinding

class ActivityBook: AppCompatActivity() {

    private lateinit var binding: ActivityBookBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCancel.setOnClickListener {
            finish()
        }
    }
}