import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gson = Gson()
        val stud = Student("Akshay","pawarakshay13@gmail.com",1001)
        var jsonData = gson.toJson(stud)
        Log.e("AXE","$jsonData")

    }
}
class Student (@Transient private var fname : String, @Transient private var email : String,var id : Int)

// due to 'transient' keyword data is not serialized and id is not serialized hence we got output as -->  {"id":1001}
