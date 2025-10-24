package com.responsi.h1d023018

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.responsi.h1d023018.databinding.ActivityMainBinding
import com.responsi.h1d023018.viewmodel.TeamViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val teamViewModel: TeamViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observeViewModel()
        setupNavigation()
    }

    private fun observeViewModel() {
        teamViewModel.teamData.observe(this) { team ->
            if (team != null) {
                binding.tvClubName.text = team.name
            }
        }
    }

    private fun setupNavigation() {
        binding.cardMenuHistory.setOnClickListener {
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
        }
        binding.cardMenuCoach.setOnClickListener {
            val intent = Intent(this, CoachActivity::class.java)
            startActivity(intent)
        }
        binding.cardMenuSquad.setOnClickListener {
            val intent = Intent(this, SquadActivity::class.java)
            startActivity(intent)
        }
    }
}

