package com.globomed.learn

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val employeeListAdapter = EmployeeListAdapter(this)
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        databaseHelper = DatabaseHelper(this)

        recyclerView.adapter = employeeListAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        employeeListAdapter.setEmployees(DataManager.fetchAllEmployees(databaseHelper))

        fab.setOnClickListener {
            val addEmployee = Intent(this, AddEmployeeActivity::class.java)
            startActivityForResult(addEmployee, 1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) employeeListAdapter.setEmployees(DataManager.fetchAllEmployees(databaseHelper))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_deleteAll -> {
                val builder = AlertDialog.Builder(this)
                builder.setMessage(R.string.confirm_sure)
                    .setPositiveButton(R.string.yes) { _, _ ->
                        DataManager.deleteAllEmployees(databaseHelper)

                        Toast.makeText(applicationContext, "All Employees Deleted", Toast.LENGTH_SHORT).show()

                        setResult(Activity.RESULT_OK, Intent())
                        finish()
                    }
                    .setNegativeButton(R.string.no) { dialog, _ ->
                        dialog.dismiss()
                    }
                val dialog = builder.create()
                dialog.setTitle("Are you sure")
                dialog.show()

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
