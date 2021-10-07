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

- Change recycler view layout dynamically 
      if(isLinearLayoutManager){
          recyclerView.layoutManager = LinearLayoutManager(this)
      }else{
           recyclerView.layoutManager = GridLayoutManager(this,4)
      }
      //call adapter here
      
      // set menu icon dynamically
        - create fun which takes MenuItem as Parameter
        - if variable of MenuItem is null return it
        - menuItem.icon = set icon by condition

- onCreate() - When Activity Initialized(i.e new Activity Obj created in memory) onCreate() method call once,
- onStart()  - After just onCreate(), onStart() call which visible activity to user, onStart() call often  //pair(onStart, onStop)
- onResume() - It call after onStart and this state allo user to interact  with UI/Screen
- onCreate() and onDestroy() called only once during the lifetime of a single activity instance

- onPause() state activity no longer focus, and onStop() state Activity no longer visible (i.e Activity has been stop) but activiy obj still in memory at BG
- open app , onCreate(), onStart(), onResume() call, move next activity, prev activity goes onPause(), onStop(), then /** ready exact above line */
      //When App open > onCreate() > onStart() > onResume() 
      //When app close > onPause() > onStop()
      //When Reopen App > onRestart() > onStart() > onResume()
      //When App Close completly > onPause() > onStop() > onDestroy()
      //When Any dialog open over activity, then it goes to onPause() only ,bcoz activity is partially visible
      
      
 - Log.d() = debug message
 - Log.i() = informational messages
 - Log.e() = errors
 - Log.w() = warnings
 - Log.v() = verbose messages.

- const is compile-time constant which won't change.
- When Activity Orientation change app call onDestroy() for potrait view and when screen changes with landscape app call onCreate()

- onSaveInstanceState(Bundle)
- onSaveInstanceState(Bundle, PersistableBundle)

- A Bundle is a collection of key-value pairs, where the keys are always strings. (we can put data in bundle coz system keeps these bundle in memory)
- Max Size of bundle is 500KB //Src from stackoverflow  (the size varies from device to device)
- if try to save too much data in bundle then may be TransactionTooLargeException occur
- Each Activity and Fragment have there own lifecycle and each manage there own and can initialized and remove from memory

- Fragment also have 5 states
      - INITIALIZED : A new instance of the fragment has been instantiated.
      - CREATED     : The first fragment lifecycle methods are called. During this state, the view associated with the fragment is also created.
      - STARTED     : The fragment is visible onscreen but does not have "focus", meaning it cant respond to user input.
      - RESUMED     : The fragment is visible and has focus.
      - DESTROYED   : The fragment object has been de-instantiated.

onCreate()        :     Fragment is initialized 
onCreateView()    :     In this method layout is inflated
onViewCreated()   :     This method call after view created, typically bind views to properties
onStart()         :     Fragment enter in onStart state    
onResume()        :     Fragment enter in onResume and have focus
onPause()         :     Fragment re-enter started state
onStop()          :     The fragment has re-entered the CREATED state.
onDestroyView()   :     Call before destroy state, The view already remove from memory but object still exists
onDestroy()       :     The fragment enters the DESTROYED state.















