package com.example.passdata_frm_1frg2other

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.loginfragment.Communicater

class MainActivity : AppCompatActivity(), Communicater {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val frag1 = LoginPage()
        val fm = supportFragmentManager
        var ft = fm.beginTransaction()
        ft.add(R.id.mainPlace_id,frag1,"F1")

        ft.commit()
    }

    override fun shareData(username: String?, password: String?) {
        val f2 = UserData()
        val fm = supportFragmentManager
        var ft = fm.beginTransaction()
        val b = Bundle()
        b.putString("U",username)
        b.putString("P",password)
        f2.arguments = b

        val f1 = fm.findFragmentByTag("F1") as LoginPage

        if(f1 != null) {
            ft.replace(R.id.mainPlace_id, f2, "F2")
            ft.addToBackStack("LoginFrame")
            ft.commit()
        }
        else
            Toast.makeText(this,"fragment 2 is not load ", Toast.LENGTH_LONG).show()



    }

}
