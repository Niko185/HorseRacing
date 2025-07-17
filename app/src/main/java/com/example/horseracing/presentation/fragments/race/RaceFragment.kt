package com.example.horseracing.presentation.fragments.race

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.horseracing.data.local.race.repository.AppDatabase
import com.example.horseracing.data.local.race.repository.HistoryRepositoryImpl
import com.example.horseracing.data.local.race.repository.RaceDao

import com.example.horseracing.databinding.FragmentRaceBinding
import dagger.hilt.android.AndroidEntryPoint

import kotlinx.coroutines.launch

@AndroidEntryPoint
class RaceFragment : Fragment() {
    private lateinit var binding: FragmentRaceBinding
    private val viewModel: RaceViewModel by viewModels()

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
        onClickSaveRace()
    }

    private fun onClickStartRace(){
        binding.bStart.setOnClickListener {
            viewModel.startRace()
        }
    }



    private fun onClickSaveRace() {
        binding.bSaveHistory.setOnClickListener {
            viewModel.saveCurrentRace()
        }
    }

    @SuppressLint("SetTextI18n")
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
                    is RaceState.SavedState ->{
                        Toast.makeText(context, "Гонка сохранена!", Toast.LENGTH_SHORT).show()
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

                            val res = state.race


                            binding.tvResultFinishGold.apply {
                                isVisible = true
                                text = "${res.horses[0].name} / ${res.horses[0].finishTime} "
                            }

                            binding.tvResultFinishSilver.apply {
                                isVisible = true
                                text = "${res.horses[1].name} / ${res.horses[1].finishTime} "
                            }

                            binding.tvResultFinishBronze.apply {
                                isVisible = true
                                text = "${res.horses[2].name} / ${res.horses[2].finishTime} "
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