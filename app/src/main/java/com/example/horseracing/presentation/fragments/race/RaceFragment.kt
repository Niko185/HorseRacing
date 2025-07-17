package com.example.horseracing.presentation.fragments.race

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope

import com.example.horseracing.databinding.FragmentRaceBinding
import kotlinx.coroutines.launch

class RaceFragment : Fragment() {
    private lateinit var binding: FragmentRaceBinding
    private val viewModel by lazy {
        ViewModelProvider(this)[RaceViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRaceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViewModel()
        onClickStartRace()
    }

    private fun onClickStartRace(){
        binding.bStart.setOnClickListener {
            viewModel.startRace()
        }
    }

    private fun bindViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.stateScreen.collect { state ->
                when(state) {
                    is RaceState.Idle -> {
                        binding.mainCardView.isVisible = false
                        binding.bStart.isEnabled = true
                        binding.bStart.isClickable = true
                        binding.bStart.text = "Старт"
                        binding.bSaveHistory.isEnabled = false
                        binding.bSaveHistory.isClickable = false
                        binding.tvActionHelper.isVisible = true
                    }
                    is RaceState.ProgressState -> {
                        binding.mainCardView.isVisible = true
                        binding.tvHeader.isVisible = false
                        binding.tvResultFinishGold.isVisible = false
                        binding.tvResultFinishSilver.isVisible = false
                        binding.tvResultFinishBronze.isVisible = false
                        binding.progressBar.isVisible = true
                        binding.bStart.isEnabled = false
                        binding.bStart.isClickable = false
                        binding.bSaveHistory.isEnabled = false
                        binding.bSaveHistory.isClickable = false
                        binding.tvActionHelper.isVisible = false
                    }
                    is RaceState.ResultState -> {
                        binding.progressBar.isVisible = false
                        binding.mainCardView.isVisible = true
                        binding.tvHeader.isVisible = true
                        binding.bStart.isEnabled = true
                        binding.bStart.isClickable = true
                        binding.bStart.text = "Рестарт"
                        binding.bSaveHistory.isEnabled = true
                        binding.bSaveHistory.isClickable = true
                        binding.tvActionHelper.isVisible = false


                        val sortedHorses = state.horses.sortedBy { it.position }


                        binding.tvResultFinishGold.apply {
                            isVisible = true
                            text = "${sortedHorses[0].horse.sportNumber} / ${sortedHorses[0].horse.finishTime} (1 место)"
                        }

                        binding.tvResultFinishSilver.apply {
                            isVisible = true
                            text = "${sortedHorses[1].horse.sportNumber} / ${sortedHorses[1].horse.finishTime} (2 место)"
                        }

                        binding.tvResultFinishBronze.apply {
                            isVisible = true
                            text = "${sortedHorses[2].horse.sportNumber} / ${sortedHorses[2].horse.finishTime} (3 место)"
                        }
                    }
                    is RaceState.ErrorState -> {
                        binding.mainCardView.isVisible = false
                        Toast.makeText(requireContext(), "Произошла ошибка", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}