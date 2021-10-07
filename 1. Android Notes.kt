- 1dp = 1/160 inch  or 0.15875 mm

- shadow to text => 
      android:shadowColor="@android:color/holo_blue_dark"
      android:shadowDx="10"
      android:shadowDy="0"
      android:shadowRadius="10"

- (1..100).random()
- All Layouts is type of viewgroup (Layouts like LL, RL, ConstrintLayout...)
- An "abstract" class is a class that cannot be instantiated because it is not fully implemented
      Ex.  You use a sketch (abstract class) to create a blueprint (class) from which you build the actual object instance.
      
- To Use Data Binding, step is
      > add dataBinding { enabled true } in build.gradle file 
      > add <layout>  tag as root in xml file 
      > declare binding variable in MainAtivity like " private lateinit var binding: ActivityMainBinding " 
      > initialized variable like > binding = DataBindingUtil.setContentView(this, R.layout.activity_main) > DONE

- To Use View Binding, Step is
      > buildFeatures { viewBinding = true } in build.gradle file
      > declare binding variable in MainActivity like " private lateinit var binding: ActivityMainBinding " 
      > initialized variable like " binding = ActivityMainBinding.inflate(layoutInflater); setContentView(binding.root) " > DONE
      
- mdpi     - medium-density screens (~160 dpi)
- hdpi     - high-density screens (~240 dpi)
- xhdpi    - extra-high-density screens (~320 dpi)
- xxhdpi   - extra-extra-high-density screens (~480dpi)
- xxxhdpi  - extra-extra-extra-high-density screens (~640dpi)
- nodpi    - resources that are not meant to be scaled, regardless of the screen pixel density
- anydpi   - resources that scale to any density

- Explicit Intent : An explicit intent is highly specific, where you know the exact activity to be launched
- Implicit Intent : In implicit intent , you have to tell the system the type of action, Ex. open link, send email, or make a call, and the system is responsible for fulfill the request.

- Call onClickListener of recyclerView from adapter
      fun onBindViewHolder(...){
            val context = holder.view.context
            val intent = Intent(context,XYZ_Activity::class.java)
            context.startActivity(intent)
      }
      
-  there's a handy Kotlin feature that can be used to separate your constants and make them usable without a particular instance of the class called companion objects
- only a single instance of a companion object will exist for the duration of your program, which is why this is sometimes called the singleton pattern.

- All URLs are URIs, but not all URIs are URLs.
- URI (Uniform Resource Identifier)
      1> URL(Uniform Resource Locator)
            https://example.com
            mailto:abc@gmail.com
      2> URN(Uniform Resource Name)
            tel:910000000000

- ACTION_VIEW is a generic intent that takes a URI,


- Intent.CATEGORY_APP_MAPS – launching the maps app
- Intent.CATEGORY_APP_EMAIL – launching the email app
- Intent.CATEGORY_APP_GALLERY – launching the gallery (photos) app
- Intent.ACTION_SET_ALARM – setting an alarm in the background
- Intent.ACTION_DIAL – initiating a phone call























