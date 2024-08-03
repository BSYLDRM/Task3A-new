package com.example.tasks3application.counter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.tasks3application.databinding.FragmentCounterBinding

class CounterFragment : Fragment() {
    private var _binding: FragmentCounterBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CounterViewModel by viewModels()
    private var isCheckSwitch = false
    private var counter = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCounterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    private fun initialize() {
        updateTextView()
        setListeners()
        setObserver()
    }

    private fun setListeners() {
        with(binding) {
            buttonAdd.setOnClickListener { increaseCounter() }
            switchV.setOnCheckedChangeListener { _, isChecked ->
                isCheckSwitch = isChecked
                updateTextView()
            }
        }
    }

    private fun increaseCounter() {
        if (isCheckSwitch) {
            viewModel.incrementCount()
        } else {
            counter++
            updateTextView()
        }
    }

    private fun setObserver() {
        viewModel.counterNum.observe(viewLifecycleOwner) { count ->
            updateCounterText(count)
        }
    }

    private fun updateCounterText(count: Int) {
        if (isCheckSwitch) {
            binding.tvFragmentSay.text = count.toString()
        }
    }

    private fun updateTextView() {
        val textToDisplay = if (isCheckSwitch) {
            viewModel.counterNum.value?.toString() ?: "0"
        } else {
            counter.toString()
        }
        binding.tvFragmentSay.text = textToDisplay
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("checkSwitch", isCheckSwitch)
        outState.putInt("counter", counter)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
