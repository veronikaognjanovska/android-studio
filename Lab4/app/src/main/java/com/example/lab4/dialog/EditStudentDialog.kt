package com.example.lab4.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialogFragment
import com.example.lab4.R
import com.example.lab4.models.Student

class EditStudentDialog : AppCompatDialogFragment() {

    interface EditStudentDialogListener {
        fun editStudent(
            id: String,
            index: String,
            name: String,
            surname: String,
            phoneNumber: String,
            address: String
        )
    }

    private lateinit var listener: EditStudentDialogListener
    private lateinit var uploadEditStudentIndex: EditText
    private lateinit var uploadEditStudentName: EditText
    private lateinit var uploadEditStudentSurname: EditText
    private lateinit var uploadEditStudentPhoneNumber: EditText
    private lateinit var uploadEditStudentAddress: EditText

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        super.onCreateDialog(savedInstanceState)

        val builder = AlertDialog.Builder(activity)
        val layoutInflater = activity?.layoutInflater
        val view = layoutInflater?.inflate(R.layout.edit_student_dialog, null)
        uploadEditStudentIndex = view?.findViewById(R.id.uploadEditStudentIndex)!!
        uploadEditStudentName = view.findViewById(R.id.uploadEditStudentName)!!
        uploadEditStudentSurname = view.findViewById(R.id.uploadEditStudentSurname)!!
        uploadEditStudentPhoneNumber = view.findViewById(R.id.uploadEditStudentPhoneNumber)!!
        uploadEditStudentAddress = view.findViewById(R.id.uploadEditStudentAddress)!!

        val student: Student = arguments?.getSerializable("student") as Student

        uploadEditStudentIndex.setText(student.index)
        uploadEditStudentName.setText(student.name)
        uploadEditStudentSurname.setText(student.surname)
        uploadEditStudentPhoneNumber.setText(student.phoneNumber)
        uploadEditStudentAddress.setText(student.address)

        builder.setView(view)
            .setTitle("Edit Student")
            .setPositiveButton("Save", DialogInterface.OnClickListener { dialog, id ->
                val indexText: String = uploadEditStudentIndex.text.toString()
                val nameText: String = uploadEditStudentName.text.toString()
                val surnameText: String = uploadEditStudentSurname.text.toString()
                val phoneNumberText: String = uploadEditStudentPhoneNumber.text.toString()
                val addressText: String = uploadEditStudentAddress.text.toString()
                if (!indexText.isNullOrEmpty() && !nameText.isNullOrEmpty() && !surnameText.isNullOrEmpty() && !phoneNumberText.isNullOrEmpty() && !addressText.isNullOrEmpty()) {
                    listener.editStudent(
                        student.id,
                        indexText.trim(),
                        nameText.trim(),
                        surnameText.trim(),
                        phoneNumberText.trim(),
                        addressText.trim()
                    )
                } else {
                    Toast.makeText(activity, "Enter the correct data!", Toast.LENGTH_LONG).show()
                }
            })
            .setNegativeButton("Exit", DialogInterface.OnClickListener { dialog, id ->

            })
        return builder.create()
    }


    fun setEditStudentDialogListener(listener: EditStudentDialogListener) {
        this.listener = listener
    }
}