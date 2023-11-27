What is an Activity?
Activity is an core building block within an android app
It Provides a window to display composable of your users interface and handle interaction with the users such as receiving touch input on the screen
Activity is an entry point to the users interaction with the app


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
      > add dataBinding { enabled true } and plugin{ id `kotlin-kapt` } in build.gradle file 
      > add <layout>  tag as root in xml file 
      > declare binding variable in MainAtivity like " private lateinit var binding: ActivityMainBinding " 
      > initialized variable like > binding = DataBindingUtil.setContentView(this, R.layout.activity_main) > DONE

Advantages of DataBinding : 
      - Data binding = binding data (from code) to views + view binding (binding views to code)
      - it lets you remove many UI framework calls in your activities, make them simple and easy to maintain.
      - Improve app`s performance and help prevent memory leaks and NPE
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
- To Use View Binding, Step is
      > buildFeatures { viewBinding = true } in build.gradle file
      > declare binding variable in MainActivity like " private lateinit var binding: ActivityMainBinding " 
      > initialized variable like " binding = ActivityMainBinding.inflate(layoutInflater); setContentView(binding.root) " > DONE
      
DisAdvantage of ViewBinding: 
      - It is a one-way binding. You can bind views to code but not vice versa.
      - Using view binding you can`t reference the app data in the views (layout files)



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
      
-  there`s a handy Kotlin feature that can be used to separate your constants and make them usable without a particular instance of the class called companion objects
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

- const is compile-time constant which wont change.
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

- Some Difference in onCreateOptionMenu() method working for activity or fragment
      - Activity Class has Globla Property "menuInflater" where fragment class dont have
      - onCreateOptionsMenu() return Boolean Value in Activity , and return Unit in Fragment

- NavHost : A NavHost is used to display destinations form a navigation graph within an activity. 
            When navigate between fragments, the destination shown in NavHost is updated. You will use a built-in implementation, called NavHostFragment

- NavController : This object lets you control the navigation between destinations displayed in the NavHost
                  For navigate > call navigate() method of NavController

- Navigation Component : It simply refers to the collection of tools for implementing navigation, particularly between fragments.

To Use NavigationComponent add following Dependency
      - implementation "androidx.navigation:navigation-fragment-ktx:2.3.5"
      - implementation "androidx.navigation:navigation-ui-ktx:2.3.5"

- To Use Safe Args in navigation Component (build.gradle > module)
      - classpath "androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version"
      - Add PlugIn > build.gradle(:app) > plugin { id 'androidx.navigation.safeargs' }  //It generates Direction Classes

- add container in activity_main.xml file to set/add fragment init for navigation
      <FrameLayout .....>
              <androidx.fragment.app.FragmentContainerView
                  android:id="@+id/id_nav_host_fragment"
                  android:layout_width="match_parent"
                  android:layout_height="match_parent"
                  android:name="androidx.navigation.fragment.NavHostFragment"
                  app:defaultNavHost="true"
                  app:navGraph="@navigation/nav_graph"  />
      </FrameLayout>
      
      
============================================ BACK BUTTON in Navigation Component from MainActivity ====================================================
      
      onCreate(..){
            val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            val navController = navHostFragment.navController
            setupActionBarWithNavController(navController)
      }
      
      override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment).navigateUp() || super.onSupportNavigateUp()
    }
      
==========================================================================================================================================================
      
- Android Jetpack libraries are a collection of libraries to make it to develop app easier
- Android Architecture Components are part of Android Jetpack libraries, to help you design apps with good architecture.
- The ViewModel stores the app related data that is not destroyed when activity or fragment is destroyed and recreated by the Android framework.
- Object of view model not destroy like activity or fragment they can automtically retain in case config changes or restart app

- //  Syntax for property delegation
      var <property-name> : <property-type> by <delegate-class>()

- The ViewModel is destroyed when the associated fragment is detached, or when the activity is finished, Right before the ViewModel is destroyed, the onCleared() callback is called.

- val viewModel : ViewModelFileName by viewModels()  <---------- This viewModel scope is only for perticular fragment where it writter
- viewModels() gives you the ViewModel instance scoped to the current fragment,This will be different for different fragments.
- activityViewModels() gives you the ViewModel instance scoped to the current activity. Therefore the instance will remain the same across multiple fragments in the same activity.

____________________________ NEW and VERY IMP _____________________________________


class MainActivity : AppCompatActivity(R.layout.activity_main) <-----------this is shortcut of following code

class MainActivity : AppCompatActivity() {
   override fun onCreate(savedInstanceState: Bundle?) {
       super.onCreate(savedInstanceState)
       setContentView(R.layout.main_activity)
   }
}

____________________________________________________________________________________________________________________________

- The Android framework provides a class called SimpleDateFormat, which is a class for formatting and parsing dates in a locale-sensitive manner
- In SimpleDateFormat  following represent
      d = day in month
      y = year
      M = Month
- January 4 in 2018, the pattern string "EEE, MMM d" parses to "Wed, Jul 4". 

- 
      - In SimpleDateF following representormat 
      d = day in month
      y = year
      M = Month

- January 4 in 2018, the pattern string "EEE, MMM d" parses to "Wed, Jul 4". 
- LifecycleOwner is a class that has an Android lifecycle, such as an activity or a fragment
- A LiveData observer observes the changes to the app`s data only if the lifecycle owner is in active states (STARTED or RESUMED)

- popUpTo : remove all fragment except mention fragment name
- popUpInclusive : remove all fragment include mention fragment name
- A thread is a bit of an abstract concept, it as a single path of execution for code in your app, Each line of code that`s to be executed by same thread.

- REST : REpresentational State Transfer
- According to the Material design guidelines, you should have 8dp of space at the top, bottom, and sides of the list, and 4dp of space between the items.


=============================== Room Database ================================
      
- Add following Dependency 
      implementation "androidx.room:room-runtime:2.3.0"
      kapt "androidx.room:room-compiler:2.3.0"
      // optional - Kotlin Extensions and Coroutines support for Room
      implementation "androidx.room:room-ktx:2.3.0"


------------------------------------------------------------------  WORK MANAGER
- Add following Dependency
    implementation "androidx.work:work-runtime-ktx:$versions.work"

Few WorkManager Classes
      - Worker : This is where you put the code for the actual work you want to perform in the background. You`ll extend this class and override the doWork() method.
      - WorkRequest : This represents a request to do some work. You`ll pass in your Worker as part of creating your WorkRequest. 
                        When making the WorkRequest you can also specify things like Constraints on when the Worker should run.

      - WorkManager: This class actually schedules your WorkRequest and makes it run. 
                        It schedules WorkRequests in a way that spreads out the load on system resources, while honoring the constraints you specify.

 
SAM Conversion : Single Abstract Method
      - any click event can listen by implementing onClickListener interface, 
        this interface has one abstract method `onClick()` and this method can be represent anonymously, this process known as SAM Conversion


Property Delegation
      private val viewModel: FragmentViewModel by viewModels()   //initialised viewmodel directly

NotificationManager : 
      - It is responsible for sending a notification, updating its contents, and canceling the notification
      - Once a channel is created, it can be used to deliver any number of notifications.


ApplicationClass : If you think some variables, functions, classes will need in whole app then define those in custom Application class with extending Application Class itself and define those custom application class in manifest file with `name` attrib






