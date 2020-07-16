package com.jhonr1.wishlist.helpers

import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.jhonr1.wishlist.R
import com.jhonr1.wishlist.activities.PrivacyPolicyActivity
import android.Manifest
import android.app.PendingIntent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.preference.PreferenceManager
import android.provider.Settings
import android.widget.Switch
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.TaskStackBuilder
import androidx.core.content.ContextCompat

class Utility {

    companion object {
        /**
         * Adding drawer menu to the activity layout
         */
        fun setNavDrawerMenu(activity: Activity, drawerLayout: DrawerLayout, toolbar: Toolbar) {
            val toggle = ActionBarDrawerToggle(
                activity, drawerLayout, toolbar, 0, 0
            )
            drawerLayout.addDrawerListener(toggle)
            toggle.syncState()
        }

        /**
         * Contains actions of the drawer menu
         */
        fun itemSelectedOnDrawerMenu(item: MenuItem, context: Context, activity: Activity, drawerLayout: DrawerLayout){
            when (item.itemId) {
                R.id.action_about -> {
                    val aboutUsAlertDialog = CustomAboutUsAlertDialog(activity)
                    aboutUsAlertDialog.show()
                }

                R.id.action_privacy -> {
                    val intent = Intent(activity, PrivacyPolicyActivity::class.java)
                    activity.startActivity(intent)
                }

                R.id.action_exit -> {
                    val exitAlertDialog = CustomExitAlertDialog(context, activity, R.layout.exit_dialog)
                    exitAlertDialog.show(context.getString(R.string.app_name))
                }
            }
            drawerLayout.closeDrawer(GravityCompat.START)
        }

    }
}