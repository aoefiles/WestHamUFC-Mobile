package com.responsi.h1d023018.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.responsi.h1d023018.R
import com.responsi.h1d023018.data.model.Player
import com.responsi.h1d023018.databinding.ItemPlayerBinding


class PlayerAdapter(
    private var playerList: List<Player>,
    private val listener: OnPlayerClickListener
) : RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>() {


    interface OnPlayerClickListener {
        fun onPlayerClick(player: Player)
    }

    inner class PlayerViewHolder(val binding: ItemPlayerBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val binding = ItemPlayerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PlayerViewHolder(binding)
    }

    override fun getItemCount(): Int = playerList.size

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val player = playerList[position]

        holder.binding.tvPlayerName.text = player.name
        holder.binding.tvPlayerNationality.text = player.nationality

        val context = holder.itemView.context


        val colorRes = when (player.position) {
            // Goalkeepers
            "Goalkeeper" -> R.color.pos_goalkeeper
            // Defenders
            "Defence", "Centre-Back", "Right-Back", "Left-Back" -> R.color.pos_defender
            // Midfielders
            "Midfield", "Defensive Midfield", "Central Midfield", "Attacking Midfield" -> R.color.pos_midfielder
            // Forwards
            "Offence",
            "Attacker",
            "Centre-Forward",
            "Left Winger",
            "Right Winger",
            "Striker" -> R.color.pos_forward

            else -> R.color.white
        }

        holder.binding.cardPlayer.setCardBackgroundColor(
            ContextCompat.getColor(context, colorRes)
        )

        holder.itemView.setOnClickListener {
            listener.onPlayerClick(player)
        }
    }

    fun setData(newSquad: List<Player>) {
        playerList = newSquad
        notifyDataSetChanged()
    }
}

