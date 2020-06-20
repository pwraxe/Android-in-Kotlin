package com.example.swipeviewdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter
import com.example.swipeviewdemo.databinding.ActivityMainBinding
import com.example.swipeviewdemo.fragments.FragmentOne
import com.example.swipeviewdemo.fragments.FragmentThree
import com.example.swipeviewdemo.fragments.FragmentTow

class MainActivity : AppCompatActivity() {

    private var binding : ActivityMainBinding?  = null
    private var fragmentList = ArrayList<Fragment>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)


        fragmentList.add(FragmentOne())
        fragmentList.add(FragmentTow())
        fragmentList.add(FragmentThree())

        binding?.idViewFlipper?.adapter = SwipeAdapter(supportFragmentManager,fragmentList)
    }
}

class SwipeAdapter (fm : FragmentManager, var fragmentList : ArrayList<Fragment>) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment = when (position) {
            1 -> FragmentOne()
            2 -> FragmentTow()
            else -> FragmentThree()
        }

    override fun getCount(): Int = fragmentList.size
}