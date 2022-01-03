package com.example.lab4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab4.adapters.StudentAdapter
import com.example.lab4.databinding.FragmentSecondBinding
import com.example.lab4.dialog.EditStudentDialog
import com.example.lab4.models.Student
import com.example.lab4.viewmodel.StudentViewModel
import com.google.firebase.database.FirebaseDatabase

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment(), EditStudentDialog.EditStudentDialogListener {

    private var _binding: FragmentSecondBinding? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewAdapter: StudentAdapter
    private lateinit var studentViewModel: StudentViewModel
    var studentsReference = FirebaseDatabase.getInstance().getReference("students")

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

        this.studentViewModel =
            ViewModelProvider(this).get(StudentViewModel::class.java)
        this.studentViewModel.getStudentsListMutableLiveData()
            .observe(viewLifecycleOwner, object : Observer<MutableList<Student>?> {
                override fun onChanged(t: MutableList<Student>?) {
                    if (t != null) {
                        recyclerViewAdapter.updateData(t)
                    } else {
                        Toast.makeText(activity, "Error!", Toast.LENGTH_LONG).show()
                    }
                }
            })

        this.recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewStudentList)
        this.recyclerView.layoutManager = LinearLayoutManager(activity)
        this.recyclerViewAdapter =
            StudentAdapter(
                requireActivity().applicationContext,
                this.studentViewModel.getStudentsListMutableLiveData().value!!
            )
        this.recyclerView.adapter = this.recyclerViewAdapter
        this.recyclerViewAdapter.onEditClick = { student: Student ->
            val bundle: Bundle = Bundle()
            bundle.putSerializable("student", student) // Serializable Object
            this.openDialog(bundle)
        }
        this.recyclerViewAdapter.onDeleteClick = { student ->
            var id = student.id
            studentsReference.child(id).removeValue()
        }

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun editStudent(
        id: String,
        index: String,
        name: String,
        surname: String,
        phoneNumber: String,
        address: String
    ) {
        var student: Student = Student(id, index, name, surname, phoneNumber, address)
        studentsReference.child(id).setValue(student)
        // will update recycler view data using the change in the model
        Toast.makeText(activity, "Student edited!", Toast.LENGTH_LONG).show()
    }

    private fun openDialog(bundle: Bundle) {
        var dialogInstance = EditStudentDialog()
        dialogInstance.arguments = bundle
        dialogInstance.setEditStudentDialogListener(this)
        fragmentManager?.let { dialogInstance.show(it, "") }
    }

}