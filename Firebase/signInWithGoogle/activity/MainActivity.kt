package com.example.googlesignindemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.googlesignindemo.databinding.ActivityMainBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

private const val EXTRA_NAME = "userName"
private const val EXTRA_EMAIL = "userEmail"
private const val EXTRA_URL = "userUrl"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var client: GoogleSignInClient

    private lateinit var fireAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        fireAuth = FirebaseAuth.getInstance()

        val options =  GoogleSignInOptions.Builder()
            .requestEmail()
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestProfile()
            .build()

        client = GoogleSignIn.getClient(this,options)

        binding.idGoogleSignInButton.setOnClickListener {

            val signInIntent = client.signInIntent
            startActivityForResult(signInIntent, 1526)

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 1526) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                Log.e("AXE","Exception : ${task.exception}")
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        fireAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = fireAuth.currentUser


                    val providerData = user?.providerData?.get(0)

                    Log.e("AXE","Provider UID  :  ${providerData?.uid}")                                // Provider UID  :  sEam01zAo.....JCTNoAI6ym83
                    Log.e("AXE","Provider ID   : ${providerData?.providerId}")                          // Provider ID   : firebase
                    Log.e("AXE","Provider photo url : ${providerData?.photoUrl}")                       // Provider photo url : https://www.youWillHaveLinkHere/picName.png
                    Log.e("AXE","Provider phone number : ${providerData?.phoneNumber}")                 // Provider phone number : null   < Y it is Null > :(
                    Log.e("AXE","Provider isEmailVerified: ${providerData?.isEmailVerified}")           // Provider isEmailVerified: true
                    Log.e("AXE","Provider Email : ${providerData?.email}")                              // Provider Email : alexjoe@gmail.com
                    Log.e("AXE","Provider displayName  : ${providerData?.displayName}")                 // Provider displayName  : Alex Joe

                    Log.e("AXE","MultiFactor enrolledFactor   ::  ${user?.multiFactor?.enrolledFactors}")  // MultiFactor enrolledFactor   ::  []
                    Log.e("AXE","multiFactor session  :  ${user?.multiFactor?.session}")                // multiFactor session  :  com.google.android.gms.tasks.zzu@4e7fa29

                    Log.e("AXE","metadata lastSignInTime : ${user?.metadata?.lastSignInTimestamp?.toString()}")  // metadata lastSignInTime : 1593526728518
                    Log.e("AXE","Creation Time  : ${user?.metadata?.creationTimestamp?.toString()}")        // Creation Time  : 1593526728518

                    Log.e("AXE","isAnonymous  :  ${user?.isAnonymous}")                                 // isAnonymous  :  false
                    Log.e("AXE","ID Token  :  ${user?.getIdToken(true)}")                               // ID Token  :  com.google.android.gms.tasks.zzu@9b431ae
                    Log.e("AXE","UID  :  ${user?.uid}")                                                 // UID  :  sEam01zAo.....JCTNoAI6ym83
                    Log.e("AXE","providerId  :  ${user?.providerId}")                                   // providerId  :  firebase
                    Log.e("AXE","photoUrl  :  ${user?.photoUrl}")                                       // photoUrl  :  https://www.youWillHaveLinkHere/picName.png
                    Log.e("AXE","phoneNumber  : ${user?.phoneNumber}")                                  // phoneNumber  : null
                    Log.e("AXE","displayName  : ${user?.displayName}")                                  // displayName  : Alex Joe
                    Log.e("AXE","email  :  ${user?.email}")                                             // email  :  alexjoe@gmail.com
                    Log.e("AXE","isEmailVerified  :  ${user?.isEmailVerified}")                         // isEmailVerified  :  true


                    val intent = Intent(this,Welcome::class.java)
                    intent.putExtra(EXTRA_NAME,user?.displayName)
                    intent.putExtra(EXTRA_EMAIL,user?.email)
                    intent.putExtra(EXTRA_URL,user?.photoUrl)
                    startActivity(intent)
                    finish()
                } else {
                    Log.e("AXE", "signInWithCredential:failure", task.exception)
                }
            }
    }

    override fun onStart() {
        super.onStart()
        if (fireAuth.currentUser != null) {
            startActivity(Intent(this, Welcome::class.java))
            finish()
        }
    }
}