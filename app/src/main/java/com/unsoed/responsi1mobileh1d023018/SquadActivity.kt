package com.responsi.h1d023018

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.responsi.h1d023018.data.model.Player
import com.responsi.h1d023018.databinding.ActivitySquadBinding
import com.responsi.h1d023018.ui.adapter.PlayerAdapter
import com.responsi.h1d023018.ui.fragment.PlayerDetailFragment
import com.responsi.h1d023018.viewmodel.TeamViewModel

class SquadActivity : AppCompatActivity(), PlayerAdapter.OnPlayerClickListener {
    private lateinit var binding: ActivitySquadBinding
    private val teamViewModel: TeamViewModel by viewModels()
    private lateinit var playerAdapter: PlayerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySquadBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Team Squad"
        setupRecyclerView()
        observeSquadData()
    }

    private fun setupRecyclerView() {
        playerAdapter = PlayerAdapter(emptyList(), this)
        binding.rvSquad.apply {
            adapter = playerAdapter
            layoutManager = LinearLayoutManager(this@SquadActivity)
        }
    }

    private fun observeSquadData() {
        teamViewModel.squad.observe(this) { squad ->
            val sortedSquad = squad.sortedWith(compareBy {
                when (it.position) {
                    "Goalkeeper" -> 1
                    "Defence" -> 2
                    "Midfield" -> 3
                    "Attacker" -> 4
                    else -> 5
                }
            })
            playerAdapter.setData(sortedSquad)
        }
    }

    override fun onPlayerClick(player: Player) {
        PlayerDetailFragment(player).show(supportFragmentManager, "PlayerDetailFragmentTag")
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}

