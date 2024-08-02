package com.example.tasks3application.game

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.tasks3application.R
import com.example.tasks3application.databinding.FragmentGameBinding

class GameFragment : Fragment() {
    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!
    private val viewModel: GameViewModel by activityViewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private var currentGuess: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState != null) {
            currentGuess = savedInstanceState.getInt("currentGuess", -1).takeIf { it != -1 }
        }
        setObservers()
        setListeners()
        updateUI()
    }

    private fun setObservers() {
        viewModel.randomChar.observe(viewLifecycleOwner) { char ->
            binding.tvResult.text = char.toString()
        }

        viewModel.resultMessage.observe(viewLifecycleOwner) { message ->
            binding.editText.setText(message)
        }

        viewModel.turnsCount.observe(viewLifecycleOwner) { turns ->
            binding.texCount.text = getString(R.string.turns_count, turns)

        }
    }

    private fun setListeners() {
        binding.buttonGuess.setOnClickListener {
            val guess = binding.editText.text.toString().toIntOrNull()
            if (guess != null) {
                val winMessage = getString(R.string.win_message)
                val againMessage = getString(R.string.again_message)
                viewModel.checkGuess(guess, winMessage, againMessage)
                if (viewModel.resultMessage.value == winMessage) {
                    val charToUpdate = viewModel.randomChar.value ?: 'A'
                    sharedViewModel.updateChar(charToUpdate)
                }
            }
        }
        val buttons = listOf(
            binding.button0, binding.button1, binding.button2, binding.button3,
            binding.button4, binding.button5, binding.button6, binding.button7,
            binding.button8, binding.button9
        )

        buttons.forEach { button ->
            button.setOnClickListener {
                val number = button.text.toString()
                binding.editText.setText(number)
            }
        }
        binding.buttonClear.setOnClickListener {
            viewModel.startGame()
            binding.editText.text.clear()
            binding.tvResult.text = viewModel.randomChar.value.toString()
        }
    }

    private fun updateUI() {
        binding.editText.setText(currentGuess?.toString() ?: "")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // Save the current guess
        outState.putInt("currentGuess", currentGuess ?: -1)
    }
}