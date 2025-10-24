package com.responsi.h1d023018

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.responsi.h1d023018.databinding.ActivityCoachBinding
import com.responsi.h1d023018.viewmodel.TeamViewModel


class CoachActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCoachBinding
    private val teamViewModel: TeamViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoachBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Head Coach"
        observeCoachData()
    }

    private fun observeCoachData() {
        teamViewModel.coach.observe(this) { coach ->
            if (coach != null) {
                binding.tvCoachName.text = coach.name
                binding.tvCoachDob.text = coach.dateOfBirth
                binding.tvCoachNationality.text = coach.nationality
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}

