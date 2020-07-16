package com.jhonr1.wishlist.helpers

import android.view.View
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.jhonr1.wishlist.R
import de.hdodenhof.circleimageview.CircleImageView
/**
 * Item view holder
 */
class ItemlistViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var txvName: TextView = view.findViewById(R.id.txvItemName)
    var txvCategory: TextView = view.findViewById(R.id.txvItemCategory)
    var txvPrice: TextView = view.findViewById(R.id.txvItemPrice)
//    var txvStore: TextView = view.findViewById(R.id.txvItemStore)
    var imvItem: ImageView = view.findViewById(R.id.imvItemList)
    var imgBtnMenu: ImageButton = view.findViewById(R.id.imgBtnItemlistMenu)
    var cbxButton: CheckBox = view.findViewById(R.id.cbxPurchase)
    var btnDetail: Button = view.findViewById(R.id.btnDetail)
}