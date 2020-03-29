package com.example.expandablelistviewdemo

import android.content.Context
import android.database.DataSetObserver
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.ListAdapter
import android.widget.TextView
import android.widget.Toast

class CustomExpandListAdapter(
    private var context: Context,
    private var singleObject : ArrayList<HeaderChildData> ) : BaseExpandableListAdapter(){


    override fun getGroup(groupPosition: Int): Any = groupPosition

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean = true

    override fun hasStableIds(): Boolean = false

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val view = LayoutInflater.from(context).inflate(R.layout.custom_header_list, null, false)

        val headerName = view.findViewById<TextView>(R.id.id_custom_header)
        headerName?.text = singleObject[groupPosition]._header

        return view

    }

    override fun getChildrenCount(groupPosition: Int): Int =
        singleObject[groupPosition]._childs.size

    override fun getChild(groupPosition: Int, childPosition: Int): Any? =
        singleObject[groupPosition]._childs[childPosition]

    override fun getGroupId(groupPosition: Int): Long = groupPosition.toLong()

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val view = LayoutInflater.from(context).inflate(R.layout.custom_child_list, null, false)
        val childNames = view.findViewById<TextView>(R.id.id_custom_child)
        childNames?.text = singleObject[groupPosition]._childs[childPosition]
        return view
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long = childPosition.toLong()

    override fun getGroupCount(): Int = singleObject.size
}