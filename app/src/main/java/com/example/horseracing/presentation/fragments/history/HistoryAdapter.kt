package com.example.horseracing.presentation.fragments.history

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.horseracing.R
import com.example.horseracing.databinding.ItemHistoryBinding
import com.example.horseracing.domain.model.Race

class HistoryAdapter : ListAdapter<Race, HistoryAdapter.ViewHolderHistory>(ItemComparator()) {

    class ViewHolderHistory(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemHistoryBinding.bind(view)
        @SuppressLint("SetTextI18n")

        fun binds(historyRace: Race) = with(binding) {
            val sortedHorses = historyRace.horses.sortedBy { it.finishTime }

            tvGoldMedalHorse.text = "${sortedHorses[0].name} / ${sortedHorses[0].finishTime}"
            tvSilverMedalHorse.text = "${sortedHorses[1].name} / ${sortedHorses[1].finishTime}"
            tvBronzeMedalHorse.text = "${sortedHorses[2].name} / ${sortedHorses[2].finishTime}"
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HistoryAdapter.ViewHolderHistory {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_history, parent, false)
        return ViewHolderHistory(view)
    }

    override fun onBindViewHolder(holder: HistoryAdapter.ViewHolderHistory, position: Int) {
        holder.binds(getItem(position))
    }



    class ItemComparator() : DiffUtil.ItemCallback<Race>() {
        override fun areItemsTheSame(oldItem: Race, newItem: Race): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Race, newItem: Race): Boolean {
            return oldItem == newItem
        }
    }

}