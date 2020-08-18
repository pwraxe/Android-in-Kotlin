package com.codexdroid.googlemapstudy

import android.Manifest
import android.content.pm.PackageManager
import android.content.res.Resources
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import java.util.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap
    private var mapZoom : Float = 1f
    private val sydney = LatLng(19.842912, 73.992051)
    private val pune = LatLng(18.520259, 73.855388)
    private val mumbai = LatLng(19.045599, 73.039068)
    private val chennai = LatLng(13.051431, 80.261216)
    private val chennai2 = LatLng(13.234075, 80.302643)
    private val delhi = LatLng(28.648772, 77.214137)


    private var mapStyles = arrayListOf(
        R.raw.map_aubergine,        //0
        R.raw.map_dark,             //1
        R.raw.map_night,            //2
        R.raw.map_retro,            //3
        R.raw.map_silver,           //4
        R.raw.map_style_practice)   //5


    private val REQUEST_LOCATION_PERMISSION = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        // Add a marker in Sydney and move the camera

        val overlay = GroundOverlayOptions()
            .image(BitmapDescriptorFactory
            .fromResource(R.drawable.android_img)).position(sydney,20f)        // 100f is size of image on map , it may in meter // it occupied the size of ground

        map.addGroundOverlay(overlay)

//        map.addMarker(MarkerOptions().position(sydney).title("My Home"))
//        map.addMarker(MarkerOptions().position(pune).title("Pune"))
//        map.addMarker(MarkerOptions().position(mumbai).title("Mumbai"))
//        map.addMarker(MarkerOptions().position(delhi).title("Delhi"))
//        map.addMarker(MarkerOptions().position(chennai).title("Chennai"))

//        map.addCircle(CircleOptions().center(pune).clickable(true).radius(10000.0))    //Radius is in meter
//        map.addCircle(CircleOptions().center(mumbai).clickable(true).radius(20000.0))
//        map.addCircle(CircleOptions().center(chennai).clickable(true).radius(30000.0))
//        map.addCircle(CircleOptions().center(delhi).clickable(true).radius(40000.0))

//        map.addPolygon(PolygonOptions().add(mumbai,chennai,delhi).visible(true).strokeColor(Color.RED).fillColor(Color.GRAY))

        //map.addPolyline(PolylineOptions().add(chennai,delhi).add(mumbai,pune))         // line connect as like = [chennai -to-> delhi -to-> mumbai -to-> pune]
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,mapZoom))


        map.setOnMapLongClickListener {
           val snippet = String.format(Locale.getDefault(),
                   "Lat: ${it.longitude}, Long: ${it.longitude}",       // lat & lng is 2.13 digit long i.e. 00.0000000000000
                    it.latitude,it.longitude)

            /**
             *
             * latitude = 00.0000000000000
             * longitude = 00.0000000000000
             *
             * if you want to show 5 digit after decimal(point)
             *      "Lat: %1$.5f, Long: %2$.5f",
             *
             * */
            map.addMarker(MarkerOptions().position(it)
                    .title("Your Location")
                    .snippet("${it.latitude}, ${it.longitude}")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
            )

            enableMyLocation()

        }

        
        map.setOnPoiClickListener {
            val poiMarker = map.addMarker(MarkerOptions().position(it.latLng)
                    .title(it.name))
            poiMarker.showInfoWindow()
        }

        // customized map style
        setMapStyle(map,mapStyles[5])

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_map,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when(item.itemId){
            R.id.normal_map -> {
                map.mapType = GoogleMap.MAP_TYPE_NORMAL
                true
            }
            R.id.hybrid_map -> {
                map.mapType = GoogleMap.MAP_TYPE_HYBRID
                true
            }
            R.id.satellite_map -> {
                map.mapType = GoogleMap.MAP_TYPE_SATELLITE
                true
            }
            R.id.terrain_map -> {
                map.mapType = GoogleMap.MAP_TYPE_TERRAIN
                true
            }

            R.id.id_zoomIn -> {
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,mapZoom++))
                Toast.makeText(this, "zoomIn : $mapZoom", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.id_zoomOut -> {
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,mapZoom--))
                Toast.makeText(this, "zoomOut : $mapZoom", Toast.LENGTH_SHORT).show()
                true
            }

            R.id.id_map_aubergine -> { setMapStyle(map,mapStyles[0]); true }

            R.id.id_map_dark -> { setMapStyle(map,mapStyles[1]); true }

            R.id.id_map_night -> { setMapStyle(map,mapStyles[2]); true }

            R.id.id_map_retro -> { setMapStyle(map,mapStyles[3]); true }

            R.id.id_map_silver -> { setMapStyle(map,mapStyles[4]); true }

            else -> super.onOptionsItemSelected(item)
        }

    private fun setMapStyle(map : GoogleMap, mapStyle : Int){
        try {

            if(!map.setMapStyle(MapStyleOptions.loadRawResourceStyle(this,mapStyle))){
                Log.e("AXE","Style parsing fail")
            }else Log.e("AXE","Map Successfully Loaded")

        }catch (ex : Resources.NotFoundException){
            Log.e("AXE","resource not found exception : ",ex)
        }
    }

    private fun isPermissionGranted() : Boolean{
        return ContextCompat.checkSelfPermission(this,
            Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
    }

    private fun enableMyLocation(){
        if(isPermissionGranted()){
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                &&  ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                map.isMyLocationEnabled = true
                map.isTrafficEnabled = true
                return
            }
        }else{
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),REQUEST_LOCATION_PERMISSION)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == REQUEST_LOCATION_PERMISSION ){
            if(grantResults.contains(PackageManager.PERMISSION_GRANTED)){
                enableMyLocation()
            }
        }
    }

}