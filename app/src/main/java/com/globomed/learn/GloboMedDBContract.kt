package com.globomed.learn

import android.provider.BaseColumns

object GloboMedDBContract {

    object EmployeeEntry: BaseColumns {
        const val TABLE_NAME = "employee"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_DOB = "dob"
        const val COLUMN_DESIGNATION = "designation"
    }

}