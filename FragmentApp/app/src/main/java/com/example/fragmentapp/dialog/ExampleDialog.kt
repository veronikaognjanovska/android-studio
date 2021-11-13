package com.example.fragmentapp.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatDialogFragment
import com.example.fragmentapp.R

class ExampleDialog : AppCompatDialogFragment() {

    private lateinit var listener: ExampleDialogListener

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        super.onCreateDialog(savedInstanceState)

        val builder = AlertDialog.Builder(activity)
        val layoutInflater = activity?.layoutInflater
        val view = layoutInflater?.inflate(R.layout.dialog_layout, null)
        var firstName: EditText? = view?.findViewById(R.id.editTextFirstName)
        var secondName: EditText? = view?.findViewById(R.id.editTextSecondName)

        builder.setView(view)
            .setTitle("Title")
            .setPositiveButton("Save", DialogInterface.OnClickListener { dialog, id ->
                var firstNameText: String = firstName?.text.toString()
                var secondNameText: String = secondName?.text.toString()
                listener.setText(firstNameText, secondNameText)
            })
            .setNegativeButton("Exit", DialogInterface.OnClickListener { dialog, id ->

            })
        return builder.create()
    }

    interface ExampleDialogListener {
        fun setText(firstName: String, secondName: String)
    }

    fun setExampleDialogListener(listener: ExampleDialogListener) {
        this.listener = listener
    }
}