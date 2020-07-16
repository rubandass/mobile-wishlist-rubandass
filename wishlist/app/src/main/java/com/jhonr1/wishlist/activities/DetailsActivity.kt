package com.jhonr1.wishlist.activities

import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jhonr1.wishlist.R
import com.jhonr1.wishlist.helpers.BitmapProcessor
import kotlinx.android.synthetic.main.content_details.*

/**
 * Class to show the selected items details in a separate view
 */
class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        txvNameDetailView.text = "Name: " + intent.getStringExtra("item_name")
        txvPriceDetailView.text = "Price: " + intent.getStringExtra("item_price")
        txvCategoryDetailView.text = "Category: " + intent.getStringExtra("item_category")
        txvStoreDetailView.text = "Store: " + intent.getStringExtra("item_store")
        txvInfoDetailView.text = "Information: " + intent.getStringExtra("item_info")
        txvDateTimeDetailView.text = "Date-Time: " + intent.getStringExtra("item_datetime")
        var bitmapDrawable: BitmapDrawable? = null
        var imagePath = intent.getStringExtra("image")
        if (imagePath != "") {
            val bitmap = BitmapProcessor.process(imagePath)
            bitmapDrawable =
                BitmapProcessor.convertBitmapToDrawable(resources, bitmap)
        }
        imgDetailView.setImageDrawable(bitmapDrawable)
    }
    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }
}
