package com.example.swipetabview

import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter

        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)

    }
}
class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        lateinit var fragment : Fragment
        if(position == 0)
            fragment = Fragment_Chat()
        if(position == 1)
            fragment = Fragment_Status()
        if(position == 2)
            fragment = Fragment_Calls()
        return fragment

    }

    override fun getPageTitle(position: Int): CharSequence? {
        var title : String? = null
        if(position == 0)
            title = "CHAT"
        if(position == 1)
            title = "STATUS"
        if(position == 2)
            title = "CALLS"
        return title

    }

    override fun getCount(): Int = 3
}



