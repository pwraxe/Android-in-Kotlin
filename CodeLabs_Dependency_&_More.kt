For All Dependencies 
https://maven.google.com/web/index.html

***************************************************************************************************************************

Hide Status Bar
	window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

***************************************************************************************************************************

Predefine Styling of Button (attrubute in xml)
	style="@style/Widgets.AppCompat.Button.colored"

***************************************************************************************************************************

Hide KeyBoard When Click on Button
	val hideKeyBoard = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    hideKeyBoard.hideSoftInputFromWindow(view.windowToken,0)

***************************************************************************************************************************

Show KeyBoard When Click on Button or textView
	val showKeyBoard = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
  	showKeyBoard.showSoftInput(et_nickname,0)
	

***************************************************************************************************************************

Add navigation components to the project
	Add navigation dependencies	
		1 : add navigation version code in build.gradle(Project) 
				ext.navigationVersion = 'see letest version @ https://developer.android.com/jetpack/androidx/releases/navigation#declaring_dependencies'	

		2 : add dependency in build.gradle(Module)
				implementation"android.arch.navigation:navigation-fragment-ktx:$navigationVersion"
    			implementation "android.arch.navigation:navigation-ui-ktx:$navigationVersion"

***************************************************************************************************************************
***************************************************************************************************************************
data class MySelfClass(var name: String = "", var nickname: String = "")

<layout>
	
	<data>

		<variable 
			name="classObj"
			type = "com.example.android.myapp.MySelfClass"
			/>

	</data>

	<TextView
		..
		android:text="@={classObj.nickname}"
		/>

</layout>
------------------------------------------------------------------------

	onCreate(){
		val mySelf = MySelfClass("Akshay","AXE")
		mySelf.name || classObj.name
	}

************************************************************************************************************************************

Add Navigation Dependency
	* add line @  build.gradle(Project)
		ext.navigationVersion = '2.2.2'

	* add Dependency in build.gradle (app)
		    implementation"android.arch.navigation:navigation-fragment-ktx:$navigationVersion"
    		implementation "android.arch.navigation:navigation-ui-ktx:$navigationVersion"


************************************************************************************************************************************

ADD Safe-Args in Project 

	add following line in build.gradle(Project) 
		classpath "androidx.navigation:navigation-safe-args-gradle-plugin:2.3.0-beta01"
	
	add following line in build.gradle(Module)
		apply plugin: 'androidx.navigation.safeargs'  // at the top 			// you will have Directions Classes

	dependency for fragment navigation
		implementation 'androidx.navigation:navigation-fragment-ktx:2.3.0-beta01'
	    	implementation 'androidx.navigation:navigation-ui-ktx:2.3.0-beta01'
	
	*********************=======*************************
	// if this !work then 
		def nav_version = "2.3.0-beta01"
	        classpath "androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version"

	// You can try this also 
		classpath "android.arch.navigation:navigation-safe-args-gradle-plugin:1.0.0"			// Recommand

************************************************************************************************************************************

Add Timber Library

	Timber has several advantages over the built-in Android Log class.	
	implementation 'com.jakewharton.timber:timber:4.7.1'			build.gradle(module)


*************************************************************************************************************************************

ViewModel Library
	implementation 'androidx.lifecycle:lifecycle-extensions:2.2.0'


*************************************************************************************************************************************

TrackMySleepingQuality 

build.gradle(Project)
	buildScript{
		ext.kotlin_version = "1.3.72"
    		ext.archLifecycleVersion = '1.1.1'
    		ext.room_version = '2.2.5'
    		ext.coroutine_version = '1.0.0'
    		ext.gradleVersion = '3.3.0'
    		ext.navigationVersion = '2.2.5'
    		ext.dataBindingCompilerVersion = gradleVersion
...		
		dependencies{
			classpath "android.arch.navigation:navigation-safe-args-gradle-plugin:1.0.0"
		}
	}

	------------------------------------------------------------------------------------------------------------------------------

	apply plugin: 'kotlin-kapt'
	apply plugin: 'androidx.navigation.safeargs'
	
	defaultConfig{
		vectorDrawables.useSupportLibrary = true
	}
	dataBinding {
        	enabled = true
    	}

	
	dependencies {
    		implementation fileTree(dir: "libs", include: ["*.jar"])
    		implementation "org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version"
    		implementation 'androidx.core:core-ktx:1.3.0'
    		implementation 'androidx.appcompat:appcompat:1.1.0'
    		implementation 'androidx.constraintlayout:constraintlayout:1.1.3'
    		
		// Testing
	    	testImplementation 'junit:junit:4.13'
    		androidTestImplementation 'androidx.test.ext:junit:1.1.1'
    		androidTestImplementation 'androidx.test.espresso:espresso-core:3.2.0'

    		// Room and Lifecycle dependencies
    		implementation "androidx.room:room-runtime:2.2.5"
    		implementation 'androidx.legacy:legacy-support-v4:1.0.0'
    		kapt "androidx.room:room-compiler:2.2.5"
    		implementation "androidx.lifecycle:lifecycle-extensions:2.2.0"

	    	// Coroutines
    		implementation "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.0"
    		implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.0"

	    	// Navigation
    		implementation "android.arch.navigation:navigation-fragment-ktx:1.0.0"
    		implementation "android.arch.navigation:navigation-ui-ktx:1.0.0"
	}


*************************************************************************************************************************************
