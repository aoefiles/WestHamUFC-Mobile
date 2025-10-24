package com.responsi.h1d023018.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.responsi.h1d023018.data.model.Player
import com.responsi.h1d023018.databinding.FragmentPlayerDetailBinding


class PlayerDetailFragment(
    private val player: Player
) : BottomSheetDialogFragment() {

    private var _binding: FragmentPlayerDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlayerDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
    }

    private fun loadData() {
        binding.tvPlayerNameDetail.text = player.name
        binding.tvPlayerDobDetail.text = player.dateOfBirth
        binding.tvPlayerNationalityDetail.text = player.nationality
        binding.tvPlayerPositionDetail.text = player.position
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

