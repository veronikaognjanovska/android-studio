package com.example.firebaseapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.firebaseapp.databinding.FragmentFirstBinding
import com.example.firebaseapp.models.Post
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    var database = FirebaseDatabase.getInstance()
    var postsReference = database.getReference("posts")
    var mAuth = FirebaseAuth.getInstance()
    lateinit var uploadTitle: EditText
    lateinit var uploadDescription: EditText

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        uploadTitle = view.findViewById(R.id.uploadTitle)
        uploadDescription = view.findViewById(R.id.uploadDescription)

        binding.buttonUpload.setOnClickListener {
            val title: String = uploadTitle.text.toString()
            val description: String = uploadDescription.text.toString()
            if (title.isNullOrEmpty() || description.isNullOrEmpty()) {
                return@setOnClickListener
            }
            uploadData(title, description)
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun uploadData(title: String, description: String) {
        val currentPost: Post = Post(mAuth.uid!!, title, description)
        postsReference.push().setValue(currentPost)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(activity, "Success", Toast.LENGTH_LONG).show()
                    uploadTitle.text.clear()
                    uploadDescription.text.clear()
                } else {
                    Toast.makeText(activity, "Error", Toast.LENGTH_LONG).show()
                    uploadTitle.text.clear()
                    uploadDescription.text.clear()
                }
            }
    }

}