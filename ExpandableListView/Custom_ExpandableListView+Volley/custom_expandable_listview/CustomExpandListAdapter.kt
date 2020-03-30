package com.example.custom_expandable_listview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView

class CustomExpandListAdapter (
    private var context: Context,
    private var singleGroupObj : ArrayList<SingleGroupObject>)
    : BaseExpandableListAdapter()
{
    override fun getGroup(groupPosition: Int): Any = singleGroupObj[groupPosition]

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean = false

    override fun hasStableIds(): Boolean = false

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup?): View {

        val view = LayoutInflater.from(context).inflate(R.layout.layout_parent,null,false)

        val profilePhoto = view.findViewById<CircleImageView>(R.id.id_parent_userImage)
        val profileName = view.findViewById<TextView>(R.id.id_parent_username)
        val arrow_icon = view.findViewById<ImageView>(R.id.id_parent_arrow_down)

        Glide.with(context).load(singleGroupObj[groupPosition]._profileImage).into(profilePhoto)
        profileName?.text = singleGroupObj[groupPosition]._profileName
        if(isExpanded){
            arrow_icon?.setImageResource(R.drawable.ic_arrow_up)
        }else{
            arrow_icon?.setImageResource(R.drawable.ic_arrow_down)
        }

        return view
    }

    override fun getChildrenCount(groupPosition: Int): Int = 1

    override fun getChild(groupPosition: Int, childPosition: Int): Any = singleGroupObj[groupPosition]

    override fun getGroupId(groupPosition: Int): Long = groupPosition.toLong()

    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup?): View {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_child,null,false)

        val viewCount = view.findViewById<TextView>(R.id.id_child_view_count)
        val likeCount = view.findViewById<TextView>(R.id.id_child_like_count)
        val commentCount = view.findViewById<TextView>(R.id.id_child_comment_count)
        val downloadCount = view.findViewById<TextView>(R.id.id_child_download_count)

        viewCount?.text = "${singleGroupObj[groupPosition]._viewCount}"
        likeCount?.text = "${singleGroupObj[groupPosition]._likeCount}"
        commentCount?.text = "${singleGroupObj[groupPosition]._commentCount}"
        downloadCount?.text = "${singleGroupObj[groupPosition]._downloadCount}"

        return view
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long = childPosition.toLong()

    override fun getGroupCount(): Int = singleGroupObj.size
}