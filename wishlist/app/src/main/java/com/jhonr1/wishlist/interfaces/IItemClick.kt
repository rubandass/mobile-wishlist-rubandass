package com.jhonr1.wishlist.interfaces

import android.widget.ImageButton
import com.jhonr1.wishlist.helpers.Item
/**
 * Interface of item click
 */
interface IItemClick {
    fun onItemClick(item: Item, imgBtn: ImageButton)
}