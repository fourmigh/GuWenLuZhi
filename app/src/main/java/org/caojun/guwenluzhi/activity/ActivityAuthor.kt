package org.caojun.guwenluzhi.activity

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import org.caojun.guwenluzhi.databinding.ActivityAuthorBinding
import org.caojun.guwenluzhi.enums.ChinaDynasty
import org.caojun.guwenluzhi.realm.Author
import org.caojun.guwenluzhi.realm.RealmManager

class ActivityAuthor: AppCompatActivity() {

    companion object {
        var author: Author? = null
    }

    private lateinit var binding: ActivityAuthorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCancel.setOnClickListener {
            finish()
        }

        val dynastys = ChinaDynasty.values()
        val names = ArrayList<String>()
        for (dynasty in dynastys) {
            names.add(dynasty.chinese)
        }
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_item,
            names
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spDynasty.adapter = adapter

        binding.spDynasty.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                checkButton()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                checkButton()
            }

        }

        binding.etName.addTextChangedListener {
            checkButton()
        }

        binding.btnOK.setOnClickListener {
            if (!checkButton()) {
                return@setOnClickListener
            }
            val name = binding.etName.text.toString()
            val index = binding.spDynasty.selectedItemPosition
            val author = Author(name, ChinaDynasty.values()[index])
            author.save(RealmManager.getInstance().getRealm())
            finish()
        }

        if (author == null) {
            binding.btnDelete.visibility = View.GONE
        } else {
            binding.btnDelete.visibility = View.VISIBLE

            binding.etName.setText(author?.name)
            for (i in dynastys.indices) {
                if (dynastys[i].name == author?.dynasty) {
                    binding.spDynasty.setSelection(i)
                    break
                }
            }

            binding.btnDelete.setOnClickListener {
                author?.delete(RealmManager.getInstance().getRealm())
                finish()
            }
        }

        checkButton()
    }

    private fun checkButton(): Boolean {
        val name = binding.etName.text.toString()
        if (TextUtils.isEmpty(name)) {
            binding.btnOK.isEnabled = false
            return false
        }
        val index = binding.spDynasty.selectedItemPosition
        binding.btnOK.isEnabled = index > 0 && index < ChinaDynasty.values().size
        return binding.btnOK.isEnabled
    }
}