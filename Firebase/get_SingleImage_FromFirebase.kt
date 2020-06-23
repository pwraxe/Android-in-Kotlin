/*

  initialised firebase
  don't forget internet permission

*/


============XML=================
<layout>
  
  <ImageView  
      ...
  />

</layout>


==========Kt==========================
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private lateinit var storeRef : StorageReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
    }
    fun loadOnlineImages(view : View){
    
        storeRef = FirebaseStorage.getInstance().reference
        
        storeRef.child("UserImages/setting.webp").downloadUrl.addOnSuccessListener {
        
                Log.e("AXE","uri : $it")    // it:Uri => i.e Complete path of image (imageLink)
        
                Glide.with(this).load(it).into(binding.idImageView)
            }
            .addOnFailureListener {
                Toast.makeText(this,"Fail : $it",Toast.LENGTH_LONG).show()
            }
    }
}
