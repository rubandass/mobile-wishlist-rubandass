package com.jhonr1.wishlist.interfaces

import android.widget.ImageButton
import com.jhonr1.wishlist.helpers.Item
/**
 * Interface to item purchase checkbox click
 */
interface IItemCheckBoxClick {
    fun onCheckboxClick(item: Item)
}