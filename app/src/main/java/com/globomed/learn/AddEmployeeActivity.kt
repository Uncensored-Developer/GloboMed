package com.globomed.learn

import android.app.Activity
import android.app.DatePickerDialog
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_add.*
import java.text.SimpleDateFormat
import java.util.*

class AddEmployeeActivity : Activity() {

    private val myCalendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        // on clicking ok on the calender dialog
        val date = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, monthOfYear)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            etDOB.setText(getFormattedDate(myCalendar.timeInMillis))
        }

        etDOB.setOnClickListener {
            setUpCalender(date)
        }

        bCancel.setOnClickListener { finish() }

        bSave.setOnClickListener {
            saveEmployee()
        }
    }

    private fun saveEmployee() {
        var isValid = true
        etEmpName.error = if (etEmpName.text.toString().isEmpty()) {
            isValid = false
            "Required"
        } else null

        etDesignation.error = if (etDesignation.text.toString().isEmpty()) {
            isValid = false
            "Required"
        } else null

        if (isValid) {
            val name = etEmpName.text.toString()
            val designation = etDesignation.text.toString()
            val dob: Long = myCalendar.timeInMillis
        }
    }

    private fun setUpCalender(date: DatePickerDialog.OnDateSetListener) {

        DatePickerDialog(
            this, date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
            myCalendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun getFormattedDate(dobInMilis: Long?): String {

        return dobInMilis?.let {
            val sdf = SimpleDateFormat("d MMM, yyyy", Locale.getDefault())
            sdf.format(dobInMilis)
        } ?: "Not Found"
    }
}