<?xml version="1.0" encoding="utf-8"?>

<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto">

    <com.google.android.material.chip.Chip
        android:id="@+id/id_chip_dist_name"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="District"
        android:textColorHighlight="@drawable/chip_selector_back"
        android:fontFamily="@font/gotham_book"
        android:textSize="@dimen/text_size_12_sp"
        style="@style/chipTextStyle"
        app:chipStrokeColor="@color/blue_lite_trans_66"
        app:chipStrokeWidth="1dp"
        />
</layout>

_________________________________________________________________________________________________________________
//chip background drawable file 

<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">

    <item android:color="@color/blue_medium"
        android:state_checked="true" />
    
    <item android:color="@color/white"
        android:state_checked="false" />

</selector>
___________________________________________________________________________________________________________________

//chip text color drawable

<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">

    <item android:color="@color/black"
        android:state_checked="false" />

    <item android:color="@color/white"
        android:state_checked="true" />

</selector>

___________________________________________________________________________________________________________________

<style name="chipTextStyle" parent="Widget.MaterialComponents.Chip.Choice">

    <item name="chipBackgroundColor">@drawable/chip_selector_backgrd</item>
    <item name="android:textColor">@drawable/chip_text_color</item>

</style>
___________________________________________________________________________________________________________________

onCreate(..){
  addSourceDestChips(...);
}

 private void addSourceDestChips(String[] distList, ChipGroup group) 
 {
    LayoutInflater layoutInflater =  LayoutInflater.from(requireContext());

    for (String dist : distList){
        chip = layoutInflater.inflate(R.layout._raw_layout_chip,group,false).findViewById(R.id.id_chip_dist_name);
        chip.setText(dist);
        group.addView(chip);
    }
}

___________________________________________________________________________________________________________________




___________________________________________________________________________________________________________________
