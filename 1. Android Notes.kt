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
      
      
