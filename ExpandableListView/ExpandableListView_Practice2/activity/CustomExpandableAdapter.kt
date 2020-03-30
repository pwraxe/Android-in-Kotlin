package com.example.expandablelistview_practice2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.ImageView
import android.widget.TextView

class CustomExpandableAdapter ( private var context: Context,
    private var parentChildGroup : ArrayList<ParentChildGroup>) : BaseExpandableListAdapter() {

    override fun getGroup(groupPosition: Int): Int = groupPosition

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean = true         // if set true then you can click child item's else you cannot select or click on them

    override fun hasStableIds(): Boolean = true

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup? ): View? {
        val view = LayoutInflater.from(context).inflate(R.layout.parent_layout,null,false)
        val image = view.findViewById<ImageView>(R.id.id_header_image)
        image?.setImageResource(parentChildGroup[groupPosition]._headerImage)
        return view
    }

    override fun getChildrenCount(groupPosition: Int): Int = parentChildGroup[groupPosition]._childList.size

    override fun getChild(groupPosition: Int, childPosition: Int): Any = parentChildGroup[groupPosition]._childList[childPosition]

    override fun getGroupId(groupPosition: Int): Long = groupPosition.toLong()

    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup?): View {

        val view = LayoutInflater.from(context).inflate(R.layout.child_layout,null,false)
        val child = view.findViewById<TextView>(R.id.id_childText)
        child?.text = parentChildGroup[groupPosition]._childList[childPosition]
        return view
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long = childPosition.toLong()

    override fun getGroupCount(): Int = parentChildGroup.size
}