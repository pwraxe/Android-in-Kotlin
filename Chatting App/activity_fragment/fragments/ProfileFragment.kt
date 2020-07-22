package com.example.chattingappdemo.fragments

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import com.example.chattingappdemo.NewUserInfo
import com.example.chattingappdemo.R
import com.example.chattingappdemo.activities.LoginActivity
import com.example.chattingappdemo.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*


class ProfileFragment : Fragment() {

    private lateinit var binding : FragmentProfileBinding

    private lateinit var fireRef : DatabaseReference
    private lateinit var fireUser : FirebaseUser

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_profile, container, false)

        fireUser = FirebaseAuth.getInstance().currentUser!!
        fireRef = FirebaseDatabase.getInstance().getReference("NewUser").child(fireUser.uid)
        fireRef.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(error: DatabaseError) { Log.e("AXE","Error to Fetch Data : $error") }

            override fun onDataChange(snapshot: DataSnapshot) {
                val userData = snapshot.getValue(NewUserInfo::class.java)
                binding.profileUserName.text = userData?.name
                binding.profileUserEmail.text = userData?.email
                binding.profileUserMobileNo.text = userData?.mobile
                binding.profileUserPassword.text = userData?.password
                binding.profileUserUID.text = userData?.uid
            }
        })


        binding.profileDelete.setOnClickListener {
            AlertDialog.Builder(context)
                .setTitle("Delete Profile?")
                .setMessage("Are you sure want to Delete Profile?")
                .setPositiveButton("Yes"){d,w ->
                    fireUser.delete()?.addOnCompleteListener {
                        fireRef.removeValue().addOnCompleteListener {task->
                            startActivity(Intent(context,LoginActivity::class.java))
                            activity?.finishAffinity()
                        }
                    }
                }
                .setNegativeButton("No"){d,w ->
                    d.dismiss()
                }
                .show()
        }



        return binding.root
    }

}