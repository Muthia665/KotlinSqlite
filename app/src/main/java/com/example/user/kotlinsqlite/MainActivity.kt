package com.example.user.kotlinsqlite

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var db:DBHelper
    var listsiswi:List<Siswi> = arrayListOf<Siswi>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = DBHelper(this)

        refreshData()

        btnAdd.setOnClickListener {
            val siswi = Siswi(
                edt_id.text.toString().toInt(),
                edt_nama.text.toString(),
                edt_email.text.toString()
            )

            db.addsiswi(siswi)
            refreshData()
        }

        btnUpdate.setOnClickListener {
            val siswi = Siswi(
                edt_id.text.toString().toInt(),
                edt_nama.text.toString(),
                edt_email.text.toString()
            )

            db.updatesiswi(siswi)
            refreshData()
        }

        btnDelete.setOnClickListener {
            val siswi = Siswi(
                edt_id.text.toString().toInt(),
                edt_nama.text.toString(),
                edt_email.text.toString()
            )

            db.deletesiswi(siswi)
            refreshData()
        }
    }

    private fun refreshData() {
        listsiswi = db.allsiswi
        val adapter = ListAdapter(this, listsiswi, edt_id, edt_nama, edt_email)
        list_siswi.adapter = adapter
    }
}
