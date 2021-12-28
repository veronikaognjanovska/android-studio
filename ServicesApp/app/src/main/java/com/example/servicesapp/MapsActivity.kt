package com.example.servicesapp

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.servicesapp.databinding.ActivityMapsBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private lateinit var locationReceiver: BroadcastReceiver
    private lateinit var sessionService: SessionService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        val permission = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )

        sessionService = SessionService(applicationContext)

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
            && ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, permission, 1)
        } else {
            // start service
            startService(Intent(this, LocationService::class.java))
        }

        locationReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                if (mMap != null && intent?.extras != null) {
                    val bundle: Bundle = intent.extras!!
                    val lat: Double = bundle.getDouble("Lat")
                    val lng: Double = bundle.getDouble("Lng")
                    mMap.clear()
                    val currentLocation = LatLng(lat, lng)
                    mMap.addMarker(
                        MarkerOptions().position(currentLocation)
                            .title("Marker in Current Location")
                    )
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(currentLocation))
                    sessionService.saveData("lat", lat.toString())
                    sessionService.saveData("lng", lng.toString())
                }
            }
        }

    }

    override fun onResume() {
        super.onResume()
        LocalBroadcastManager.getInstance(this)
            .registerReceiver(locationReceiver, IntentFilter("location_event"))
    }

    override fun onPause() {
        super.onPause()
        LocalBroadcastManager.getInstance(this).unregisterReceiver(locationReceiver)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            1 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(
                            this,
                            Manifest.permission.ACCESS_FINE_LOCATION
                        ) == PackageManager.PERMISSION_GRANTED
                    ) {
                        // start service
                        startService(Intent(this, LocationService::class.java))
                    } else {
                        Toast.makeText(this, "Error!", Toast.LENGTH_LONG).show()
                    }
                } else {
                    Toast.makeText(this, "Error!", Toast.LENGTH_LONG).show()
                }
            }
        }
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
        mMap = googleMap
        val lat: String? = sessionService.getData("lat")
        val lng: String? = sessionService.getData("lng")
        if (lat != null && lng != null) {
            if (lat.isEmpty() || lng.isEmpty()) {
                return
            }
            // Add a marker and move the camera
            val lll = LatLng(lat.toDouble(), lng.toDouble())
            mMap.addMarker(MarkerOptions().position(lll).title("Marker in location"))
            mMap.moveCamera(CameraUpdateFactory.newLatLng(lll))

        }

    }
}