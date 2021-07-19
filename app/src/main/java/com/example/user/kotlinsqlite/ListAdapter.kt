package com.example.user.kotlinsqlite

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.EditText
import kotlinx.android.synthetic.main.item_list.view.*

class ListAdapter  (var activity: Activity,
                    var listsiswi:List<Siswi>,
                    var edt_id:EditText,
                    var edt_nama:EditText,
                    var edt_email:EditText) : BaseAdapter() {

    var inflater:LayoutInflater
    init {
        inflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val itemView:View
        itemView = inflater.inflate(R.layout.item_list, null)
        itemView.id_item.text = listsiswi[position].id.toString()
        itemView.nama_item.text = listsiswi[position].name.toString()
        itemView.email_item.text = listsiswi[position].email.toString()

        itemView.setOnClickListener {
            edt_id.setText(itemView.id_item.text.toString())
            edt_nama.setText(itemView.nama_item.text.toString())
            edt_email.setText(itemView.email_item.text.toString())
        }
        return itemView
    }

    override fun getItem(position: Int): Any {
        return listsiswi[position]
    }

    override fun getItemId(position: Int): Long {
        return listsiswi[position].id.toLong()
    }

    override fun getCount(): Int {
        return listsiswi.size
    }
}