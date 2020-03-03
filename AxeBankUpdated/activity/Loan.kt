package com.example.axebank

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import de.hdodenhof.circleimageview.CircleImageView

class Loan : AppCompatActivity() {

    var loanType = arrayOf("Bike Loan","Business Loan","Car Loan","Education Loan","Gold Loan","Home Loan","Personal Loan")
    var loanImages = arrayOf(R.drawable.bike_loan_icon,R.drawable.business_loan_icon,R.drawable.car_loan_icon,R.drawable.education_loan_icon,
        R.drawable.gold_loan_icon,R.drawable.home_loan_icon,R.drawable.personal_loan_icon)
    private var listView : ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loan)

        listView = findViewById(R.id.ListView_id)
        var customAdapter = CustomListViewAdapter(this,loanType,loanImages)
        listView?.adapter = customAdapter

        listView!!.setOnItemClickListener { parent, view, position, _ ->
            var loanTx = parent.getItemAtPosition(position).toString()
            val loanText = "Thanks for Showing interest in $loanTx, Your Application has been Submitted, Please Visit Bank within next 5 t 6 days "

            var intent = Intent(this,LoanDetails::class.java)
            intent.putExtra("LTT",loanText)
            intent.putExtra("LI",loanImages[position])
            startActivity(intent)
            this.finish()

        }



        }
}
class CustomListViewAdapter(var context: Context,var loanType : Array<String>,var loanImages : Array<Int>) : BaseAdapter()
{
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var view = inflater.inflate(R.layout.data_layout,parent,false)

        var loanIcon = view.findViewById<ImageView>(R.id.rawCircularSetterImage_id)
        var loanText = view.findViewById<TextView>(R.id.loanNameSetter_id)

        loanIcon.setImageResource(loanImages[position])
        loanText.text = loanType[position]

        return view

    }

    override fun getItem(position: Int): Any = loanType[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = loanImages.size

}