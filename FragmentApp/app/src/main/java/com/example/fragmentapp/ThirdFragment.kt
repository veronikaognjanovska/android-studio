package com.example.fragmentapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.fragmentapp.dialog.ExampleDialog

class ThirdFragment : Fragment(), ExampleDialog.ExampleDialogListener {

    private lateinit var firstNameTextView: TextView
    private lateinit var secondNameTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_third, container, false)
        val dialogButton = view.findViewById<Button>(R.id.openDialogButton)

        dialogButton.setOnClickListener {
            openDialog()
        }

        firstNameTextView = view.findViewById(R.id.dialogFirstName)
        secondNameTextView = view.findViewById(R.id.dialogSecondName)

        return view
    }

    fun openDialog() {
        var dialogInstance = ExampleDialog()
        dialogInstance.setExampleDialogListener(this)
        fragmentManager?.let { dialogInstance.show(it, "") }
    }

    override fun setText(firstName: String, secondName: String) {
        firstNameTextView.text = firstName
        secondNameTextView.text = secondName
    }

}