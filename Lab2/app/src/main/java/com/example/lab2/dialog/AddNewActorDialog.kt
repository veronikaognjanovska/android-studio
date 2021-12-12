package com.example.lab2.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialogFragment
import com.example.lab2.R

class AddNewActorDialog : AppCompatDialogFragment() {

    interface AddNewActorDialogListener {
        fun setText(actorName: String, actorSurname: String, actorYear: Int)
    }

    private lateinit var listener: AddNewActorDialogListener
    private lateinit var addNewActorName: EditText
    private lateinit var addNewActorSurname: EditText
    private lateinit var addNewActorYear: EditText

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        super.onCreateDialog(savedInstanceState)

        val builder = AlertDialog.Builder(activity)
        val layoutInflater = activity?.layoutInflater
        val view = layoutInflater?.inflate(R.layout.dialog_add_new_actor, null)
        addNewActorName = view?.findViewById(R.id.addNewActorName)!!
        addNewActorSurname = view.findViewById(R.id.addNewActorSurname)!!
        addNewActorYear = view.findViewById(R.id.addNewActorYear)!!

        builder.setView(view)
            .setTitle("Add New Actor")
            .setPositiveButton("Save", DialogInterface.OnClickListener { dialog, id ->
                var addNewActorNameText: String = addNewActorName.text.toString()
                var addNewActorSurname: String = addNewActorSurname.text.toString()
                var addNewActorYear: String = addNewActorYear.text.toString()
                if (addNewActorNameText != "" && addNewActorSurname != "" && addNewActorYear != "" && addNewActorYear.toIntOrNull() != null
                ) {
                    listener.setText(
                        addNewActorNameText,
                        addNewActorSurname,
                        addNewActorYear.toInt()
                    )
                } else {
                    Toast.makeText(activity, "Enter the correct data!", Toast.LENGTH_LONG).show()
                }
            })
            .setNegativeButton("Exit", DialogInterface.OnClickListener { dialog, id ->

            })
        return builder.create()
    }


    fun setAddNewActorDialogListener(listener: AddNewActorDialogListener) {
        this.listener = listener
    }
}