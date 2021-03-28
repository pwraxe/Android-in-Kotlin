
private void openSheet()
{
    BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext(),R.style.TransparentDialog);
    View bottomSheetView = LayoutInflater.from(getContext()).inflate(R.layout._raw_layout_bank_input,null);  // create layout file 

    LinearLayout bankInputContainer = (LinearLayout) bottomSheetView.findViewById(R.id.id_bank_input_container);
    ConstraintLayout bankDetailContainer = (ConstraintLayout) bottomSheetView.findViewById(R.id.id_bank_details_container);

    bottomSheetView.findViewById(R.id.id_button_add_bank_detail).setOnClickListener(view1 -> { bottomSheetDialog.dismiss(); });
    bottomSheetDialog.setContentView(bottomSheetView);
    bottomSheetDialog.show();
}



in style file 
_______________

   <style name="TransparentDialog" parent="Theme.Design.Light.BottomSheetDialog">
        <item name="android:windowCloseOnTouchOutside">false</item>
        <item name="android:windowIsTranslucent">true</item>
        <item name="android:windowContentOverlay">@null</item>
        <item name="android:colorBackground">     @android:color/transparent</item>
        <item name="android:backgroundDimEnabled">true</item>
        <item name="android:backgroundDimAmount">0.3</item>
        <item name="android:windowFrame">@null</item>
        <item name="android:windowIsFloating">true</item>
    </style>
