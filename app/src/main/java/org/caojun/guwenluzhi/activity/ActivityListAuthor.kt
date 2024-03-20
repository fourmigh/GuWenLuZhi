package org.caojun.guwenluzhi.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import org.caojun.guwenluzhi.databinding.ActivityListAuthorBinding
import org.caojun.guwenluzhi.realm.RealmManager

class ActivityListAuthor: AppCompatActivity() {

    private lateinit var binding: ActivityListAuthorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListAuthorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCancel.setOnClickListener {
            finish()
        }

        binding.btnAddAuthor.setOnClickListener {
            ActivityAuthor.author = null
            startActivity(Intent(this, ActivityAuthor::class.java))
        }

        binding.btnEditAuthor.setOnClickListener {
            try {
                val index = binding.spAuthor.selectedItemPosition
                val authors = RealmManager.getInstance().readAuthors()
                ActivityAuthor.author = authors[index]
            } catch (e: Exception) {
            }
            startActivity(Intent(this, ActivityAuthor::class.java))
        }
    }

    override fun onResume() {
        super.onResume()

        val authors = RealmManager.getInstance().readAuthors()
        val names = ArrayList<String>()
        for (author in authors) {
            names.add(author.getInfo())
        }
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_item,
            names
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spAuthor.adapter = adapter

        binding.btnEditAuthor.visibility = if (authors.isEmpty()) View.GONE else View.VISIBLE
    }
}