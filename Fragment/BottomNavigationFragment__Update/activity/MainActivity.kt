package com.example.bottomnavifragment

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.bottomnavifragment.R.layout
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private var bottomNaviView : BottomNavigationView? = null
    private val homeFrag = Home()
    private val shopFrag = Shop()
    private val settingFrag = Setting()


    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)

        bottomNaviView = findViewById(R.id.bottom_view_id)
        bottomNaviView?.setOnNavigationItemSelectedListener {

            when(it.itemId)
            {
                R.id.home_id -> {
                    val homeFragment = supportFragmentManager.beginTransaction()
                    homeFragment.replace(R.id.homeFrag_id,homeFrag)
                    homeFragment.commit()
                }
                R.id.shop_id -> {
                    val shopFragment = supportFragmentManager.beginTransaction()
                    shopFragment.replace(R.id.homeFrag_id,shopFrag)
                    shopFragment.commit()
                }
                R.id.setting_id -> {
                    val settingFragment = supportFragmentManager.beginTransaction()
                    settingFragment.replace(R.id.homeFrag_id,settingFrag)
                    settingFragment.commit()
                }
                R.id.share_id ->{
                    val intent = Intent()
                    intent.action = Intent.ACTION_SEND
                    intent.putExtra(Intent.EXTRA_TEXT,"This is Sample Text")
                    intent.type = "text/plain"
                    startActivity(Intent.createChooser(intent,"Sample Text"))
                }
            }
            return@setOnNavigationItemSelectedListener true
        }

    }

    class Home : Fragment()
    {
        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            return LayoutInflater.from(container?.context).inflate(layout.fragment_home,container,false)
        }
    }


}


