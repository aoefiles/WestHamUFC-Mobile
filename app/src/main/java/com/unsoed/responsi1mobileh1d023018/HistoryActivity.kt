package com.responsi.h1d023018

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.responsi.h1d023018.databinding.ActivityHistoryBinding

class HistoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHistoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Club History"
    }
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}

