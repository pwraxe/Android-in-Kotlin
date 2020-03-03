package com.example.swipe_tab_view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerTabStrip
import androidx.viewpager.widget.ViewPager

class MainActivity : AppCompatActivity() {


    var viewPager : ViewPager? = null
    var pageTab : PagerTabStrip? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.page_container)
        pageTab = findViewById(R.id.page_tab)

        val viewPageAdapter = CustomViewPagerAdapter(supportFragmentManager)
        viewPager?.adapter = viewPageAdapter

    }
}

class CustomViewPagerAdapter(fm : FragmentManager) : FragmentPagerAdapter(fm)
{
    override fun getItem(position: Int): Fragment {

        lateinit var fragment : Fragment
        if(position == 0)
            fragment = FragmentA()
        if(position == 1)
            fragment = FragmentB()
        if(position == 2)
            fragment = FragmentC()
        return fragment
    }

    override fun getCount(): Int = 3

    override fun getPageTitle(position: Int): CharSequence? {
        var title : String? = null
        if(position == 0)
            title = "TAB-1"
        if(position == 1)
            title = "TAB-2"
        if(position == 2)
            title = "TAB-3"
        return title
    }
}
