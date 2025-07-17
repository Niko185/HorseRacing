package com.example.horseracing.presentation.fragments.race

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.horseracing.R
import com.example.horseracing.databinding.FragmentRaceBinding
import com.example.horseracing.presentation.utils.RaceErrors
import com.example.horseracing.presentation.utils.millisToSecondsString
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

    private fun onClickStartRace() {
        binding.bStart.setOnClickListener {
            viewModel.startRace()
        }
    }

    private fun onClickSaveRace() {
        binding.bSaveHistory.setOnClickListener {
            viewModel.saveCurrentRace()
        }
    }

    private fun bindViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.stateScreen.collect { state ->
                    when (state) {
                        is RaceState.Idle -> showIdleState()
                        is RaceState.ProgressState -> showProgressState()
                        is RaceState.ResultState -> showResultState(state)
                        is RaceState.ErrorState -> showErrorState()
                    }
                }
            }
        }
    }

    private fun showIdleState() {
        with(binding) {
            bStart.apply {
                text = getString(R.string.start_race)
                isEnabled = true
                isClickable = true
            }
            bSaveHistory.apply {
                isEnabled = false
                isClickable = false
            }
            mainCardView.isVisible = false
            tvActionHelper.isVisible = true
        }
    }

    private fun showProgressState() {
        with(binding) {
            mainCardView.isVisible = true
            tvHeader.isVisible = false
            listOf(tvResultFinishGold, tvResultFinishSilver, tvResultFinishBronze).forEach {
                it.isVisible = false
            }
            progressBar.isVisible = true
            bStart.isEnabled = false
            bStart.isClickable = false
            bSaveHistory.isEnabled = false
            bSaveHistory.isClickable = false
            tvActionHelper.isVisible = false
        }
    }

    private fun showResultState(state: RaceState.ResultState) {
        with(binding) {
            bStart.apply {
                text = getString(R.string.restart_race)
                isEnabled = true
                isClickable = true
            }
            bSaveHistory.apply {
                isEnabled = !state.isSaved
                isClickable = !state.isSaved
            }
            progressBar.isVisible = false
            mainCardView.isVisible = true
            tvHeader.isVisible = true
            tvActionHelper.isVisible = false

            val horses = state.race.horses
            listOf(tvResultFinishGold, tvResultFinishSilver, tvResultFinishBronze).forEachIndexed { index, view ->
                view.apply {
                    isVisible = true
                    text = "${horses[index].name} / ${horses[index].finishTime.millisToSecondsString()}"
                }
            }
        }
    }

    private fun showErrorState() {
        binding.mainCardView.isVisible = false
        Toast.makeText(requireContext(), RaceErrors.ERROR, Toast.LENGTH_SHORT).show()
    }
}