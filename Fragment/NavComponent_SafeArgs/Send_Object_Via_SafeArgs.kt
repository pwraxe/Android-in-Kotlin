If you want to send object to next fragment by using safeArgs then,

Create 
data class UserData(...) : Serializable

create arguments attribute at that fragment where you need data (Ex. Here We need data in DisplayFragment)
<argument
	android:name="userdata"
        app:argType="com.example.navcomponent.UserData" />


Create variable in DisplayFragment: 
	private val args: DisplayFragmentArgs by navArgs()

access property
	args.userdata.name; args.userdata.mobile
