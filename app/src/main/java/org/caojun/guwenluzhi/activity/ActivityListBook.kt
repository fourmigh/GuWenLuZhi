package org.caojun.guwenluzhi.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.caojun.guwenluzhi.databinding.ActivityListBookBinding

class ActivityListBook: AppCompatActivity() {

    private lateinit var binding: ActivityListBookBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBookBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCancel.setOnClickListener {
            finish()
        }

        binding.btnAddBook.setOnClickListener {
            startActivity(Intent(this, ActivityBook::class.java))
        }

        binding.btnListAuthor.setOnClickListener {
            startActivity(Intent(this, ActivityListAuthor::class.java))
        }
    }
}