package com.example.datatransferfragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.datatransferfragment.databinding.Fragment1Binding
import kotlinx.android.synthetic.main.activity_main.*


class Fragment1 : Fragment() {

    private var binding : Fragment1Binding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_1,container,false)

        setHasOptionsMenu(true)
        binding?.idWelcomeNextButton?.setOnClickListener {
            val userText = binding?.idEtInput?.text.toString().trim()
            if(userText != ""){

                //*****************************METHOD-1*************************************

                // val bundle = Bundle()
                // bundle.putString("uData",userText)
                // Navigation.findNavController(it).navigate(R.id.action_fragment_1_to_fragment_2,bundle)

                //*****************************METHOD-2*************************************
                // add classpath "android.arch.navigation:navigation-safe-args-gradle-plugin:1.0.0"  in build.gradle(Project..)
                // add apply plugin: 'androidx.navigation.safeargs'  in build.gradle(app) at the TOP
                Navigation.findNavController(it).navigate(Fragment1Directions.actionFragment1ToFragment2(userText))

            }else{
                Toast.makeText(context,"Please Enter Something..!",Toast.LENGTH_SHORT).show()
            }

        }


        return binding?.root
    }





}
