package com.jhonr1.wishlist.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.jhonr1.wishlist.R
import com.jhonr1.wishlist.fragments.PrivacyFragment
/**
 * Opens up privacy policy in web view
 */
class PrivacyPolicyActivity : AppCompatActivity() {

    private val detailFrag = PrivacyFragment()
    private val fragManager: FragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_privacy_policy)
        //bundle the url using 'putstring' method and pass it to the fragment class 'arguments'
        val urlBundle = Bundle()
        urlBundle.putString("url", resources.getString(R.string.privacy_url))
        detailFrag.arguments = urlBundle
        //Calling new fragment and replace the existing 'framelayout' to 'detailFragment'
        fragManager.beginTransaction().replace(R.id.frameLayout, detailFrag).commit()
    }
    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }
}
