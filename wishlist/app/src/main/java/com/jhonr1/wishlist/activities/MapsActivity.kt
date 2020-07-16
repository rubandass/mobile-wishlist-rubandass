package com.jhonr1.wishlist.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jhonr1.wishlist.R
import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.content.res.Resources
import android.location.LocationManager
import android.os.Build
import android.provider.MediaStore
import android.provider.Settings
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
import com.jhonr1.wishlist.helpers.MapsData
/**
 * Opens google map activity
 */
class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap
    private lateinit var data: ArrayList<MapsData>
    private var storeAvailable = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        val mapFragment: SupportMapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this@MapsActivity)

    }
    /**
     * Set markers (hard coded locations)
     */
    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        data = arrayListOf(
            MapsData(
                "Kmart",
                LatLng(-45.870213, 170.5048105),
                R.drawable.kmart,
                resources.getString(R.string.url_kmart)
            ),
            MapsData(
                "Warehouse",
                LatLng(-45.8936398, 170.4899039),
                R.drawable.warehouse,
                resources.getString(R.string.url_warehouse)
            ),
            MapsData(
                "Warehouse",
                LatLng(-45.877994, 170.4990423),
                R.drawable.warehouse,
                resources.getString(R.string.url_warehouse)
            ),
            MapsData(
                "Noel Leeming",
                LatLng(-45.8831084, 170.4993026),
                R.drawable.noel_leeming,
                resources.getString(R.string.url_noel_leeming)
            )
        )

        val zoomLevel = 15f
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(data[1].location, zoomLevel))

        for (d: MapsData in data) {
            map.addMarker(
                MarkerOptions().position(d.location).title(d.name).snippet(d.url)
            )
            val overlaySize = 100f
            val googleOverlay: GroundOverlayOptions = GroundOverlayOptions()
                .image(BitmapDescriptorFactory.fromResource(d.drawable))
                .position(d.location, overlaySize)
            map.addGroundOverlay(googleOverlay)
        }
        setMapMarkerClick(map)
        setMapOnClick(map)
        enableMyLocation()
        Toast.makeText(this@MapsActivity, "Click on marker to add store", Toast.LENGTH_LONG)
            .show()
    }

    /**
     * Request user to enable location
     */
    private fun enableMyLocation() {
        if (isPermissionGranted()) {
            map.isMyLocationEnabled = true
        } else {
            ActivityCompat.requestPermissions(
                this@MapsActivity,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_LOCATION_PERMISSION
            )
        }
    }
    /**
     * Checking the user has given permission or not
     */
    private fun isPermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            this@MapsActivity,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    companion object {
        const val REQUEST_CODE_PERMISSION = 1
        const val REQUEST_LOCATION_PERMISSION = 1
    }
    /**
     * Prompt user to ON location if it is OFF
     */
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_CODE_PERMISSION) {
            if (grantResults.contains(PackageManager.PERMISSION_GRANTED)) {
                enableMyLocation()
                if (!isLocationEnabled()) {
                    Toast.makeText(this, "Turn on location", Toast.LENGTH_SHORT).show()
                    val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                    startActivity(intent)
                    recreate()
                }
            }
        }

    }
    /**
     * Checking location is enable or not
     */
    private fun isLocationEnabled(): Boolean {
        var locationManager: LocationManager =
            getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }
    /**
     * Give message to user that store is not selected if user clicks other than the red marker
     */
    private fun setMapOnClick(map: GoogleMap) {
        map.setOnMapClickListener {
            if (storeAvailable != 1) {
                Toast.makeText(this@MapsActivity, "No store selected", Toast.LENGTH_SHORT).show()
            }
        }
    }
    /**
     * Select the store based on the marker click
     */
    private fun setMapMarkerClick(map: GoogleMap) {

        map.setOnMarkerClickListener { latLng ->
            val latitude = latLng.position.latitude
            val longitude = latLng.position.longitude
            var store = ""
            for (d in data) {
                if (latitude == d.location.latitude && longitude == d.location.longitude) {
                    store = d.name
                    storeAvailable = 1
                    break
                }
            }
            val intent = Intent(this@MapsActivity, MainActivity::class.java)
            intent.putExtra("store", store)
            setResult(10, intent)
            finish()
            return@setOnMarkerClickListener true
        }
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }
}
