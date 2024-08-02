package com.example.tasks3application.counter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.tasks3application.databinding.FragmentCounterBinding
import androidx.lifecycle.Observer
import kotlin.random.Random

class CounterFragment : Fragment() {
    private var _binding: FragmentCounterBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CounterViewModel by viewModels()
    private var checkSwitch = false
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

        updateTextView()
        setListeners()
        setObserver()
    }

    private fun setListeners() {
        with(binding) {
            buttonAdd.setOnClickListener {
                increaseCounter()
            }
            switchV.setOnCheckedChangeListener { _, isChecked ->
                checkSwitch = isChecked
                updateTextView()
            }
        }
    }

    private fun increaseCounter() {
        if (checkSwitch) {
            viewModel.incrementCount()
        } else {
            counter++
            updateTextView()
        }
    }

    private fun setObserver() {
        viewModel.counterNum.observe(viewLifecycleOwner, Observer { count ->
            if (checkSwitch) {
                binding.tvFragmentSay.text = count.toString()
            }
        })
    }

    private fun updateTextView() {
        if (checkSwitch) {
            binding.tvFragmentSay.text = viewModel.counterNum.value?.toString() ?: "0"
        } else {
            binding.tvFragmentSay.text = counter.toString()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("checkSwitch", checkSwitch)
        outState.putInt("counter", counter)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
