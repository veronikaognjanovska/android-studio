package com.example.lab4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.lab4.databinding.FragmentFirstBinding
import com.example.lab4.models.Student
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    var database = FirebaseDatabase.getInstance()
    var studentsReference = database.getReference("students")
    var mAuth = FirebaseAuth.getInstance()
    lateinit var uploadNewStudentIndex: EditText
    lateinit var uploadNewStudentName: EditText
    lateinit var uploadNewStudentSurname: EditText
    lateinit var uploadNewStudentPhoneNumber: EditText
    lateinit var uploadNewStudentAddress: EditText

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

        uploadNewStudentIndex = view.findViewById(R.id.uploadNewStudentIndex)
        uploadNewStudentName = view.findViewById(R.id.uploadNewStudentName)
        uploadNewStudentSurname = view.findViewById(R.id.uploadNewStudentSurname)
        uploadNewStudentPhoneNumber = view.findViewById(R.id.uploadNewStudentPhoneNumber)
        uploadNewStudentAddress = view.findViewById(R.id.uploadNewStudentAddress)

        binding.buttonUpload.setOnClickListener {
            val index: String = uploadNewStudentIndex.text.toString()
            val name: String = uploadNewStudentName.text.toString()
            val surname: String = uploadNewStudentSurname.text.toString()
            val phoneNumber: String = uploadNewStudentPhoneNumber.text.toString()
            val address: String = uploadNewStudentAddress.text.toString()
            if (index.isNullOrEmpty() || name.isNullOrEmpty() || surname.isNullOrEmpty()
                || phoneNumber.isNullOrEmpty() || address.isNullOrEmpty()
            ) {
                return@setOnClickListener
            }
            uploadData(index, name.trim(), surname.trim(), phoneNumber, address.trim())
        }

        binding.buttonToList.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun uploadData(
        index: String,
        name: String,
        surname: String,
        phoneNumber: String,
        address: String
    ) {
        val currentPost: Student = Student(mAuth.uid!!, index, name, surname, phoneNumber, address)
        studentsReference.push().setValue(currentPost)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(activity, "Success", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(activity, "Error", Toast.LENGTH_LONG).show()
                }
                clearFields()
            }
    }

    private fun clearFields() {
        uploadNewStudentIndex.text.clear()
        uploadNewStudentName.text.clear()
        uploadNewStudentSurname.text.clear()
        uploadNewStudentPhoneNumber.text.clear()
        uploadNewStudentAddress.text.clear()
    }

}