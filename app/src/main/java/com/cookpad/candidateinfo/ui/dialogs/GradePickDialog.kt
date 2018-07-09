package com.cookpad.candidateinfo.ui.dialogs

import android.content.Context
import android.support.v7.app.AlertDialog
import android.widget.ArrayAdapter
import com.cookpad.candidateinfo.R


object GradePickDialog {
    fun getDialog(context: Context, choices: List<String>, onItemChosen: (chosenGrade: String) -> Unit) {
        val builderSingle = AlertDialog.Builder(context)
        builderSingle.setTitle("Choose Grade")

        val arrayAdapter = ArrayAdapter<String>(context, android.R.layout.select_dialog_item)//select_dialog_singlechoice)

        arrayAdapter.addAll(choices)

        builderSingle.setAdapter(arrayAdapter) { _, which ->
            val strName = arrayAdapter.getItem(which)
            onItemChosen(strName)
        }
        builderSingle.show()
    }
}