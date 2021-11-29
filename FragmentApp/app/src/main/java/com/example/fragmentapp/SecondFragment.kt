package com.example.fragmentapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.fragmentapp.databinding.FragmentSecondBinding
import kotlinx.coroutines.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        val resultTextView = view.findViewById<TextView>(R.id.textViewRunComplexTask)
        binding.buttonRunComplexTask.setOnClickListener {

//            // launch - sequential /(IO)
//            val job: Job = CoroutineScope(Dispatchers.IO).launch {
////                val result = callApi()
////                val result = callApiWithCoroutines()
//                val currentTime = System.currentTimeMillis()
//                val result1 = downloadTask1()
//                val result2 = downloadTask2()
//                val result3 = downloadTask3()
//                withContext(Dispatchers.Main) {
//                    resultTextView.text =
//                        "" + ((System.currentTimeMillis() - currentTime) / 1000) + " seconds"
//                }
//            }

            // launch - parallel /(Main)
            val job2: Job = CoroutineScope(Dispatchers.Main).launch {
                val currentTime = System.currentTimeMillis()
                val result1 = async(Dispatchers.IO) { downloadTask1() }
                val result2 = async(Dispatchers.IO) { downloadTask2() }
                val result3 = async(Dispatchers.IO) { downloadTask3() }
                Toast.makeText(
                    activity,
                    "All task downloaded! ${result1.await()}, ${result2.await()}, ${result3.await()}",
                    Toast.LENGTH_LONG
                ).show()
                resultTextView.text =
                    "" + ((System.currentTimeMillis() - currentTime) / 1000) + " seconds"
            }

        }

    }

    private fun callApi(): String {
        val res = "This is a result from api request!"
        Thread.sleep(8000)
        return res
    }

    // suspend - async
    private suspend fun callApiWithCoroutines(): String {
        val res = "This is a result from api request!"
        Thread.sleep(8000)
        return res
    }

    private suspend fun downloadTask1(): String {
        kotlinx.coroutines.delay(5000)
        return "Task Completed"
    }

    private suspend fun downloadTask2(): Int {
        kotlinx.coroutines.delay(10000)
        return 100
    }

    private suspend fun downloadTask3(): Float {
        kotlinx.coroutines.delay(5000)
        return 5.0f
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}