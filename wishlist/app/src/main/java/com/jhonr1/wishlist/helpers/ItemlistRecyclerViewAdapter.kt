package com.jhonr1.wishlist.helpers

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.jhonr1.wishlist.R
import com.jhonr1.wishlist.activities.MainActivity
import com.jhonr1.wishlist.interfaces.IItemCheckBoxClick
import com.jhonr1.wishlist.interfaces.IItemClick
import com.jhonr1.wishlist.interfaces.IItemDetailButtonClick
/**
 * Binding items on recycler view
 */
class ItemlistRecyclerViewAdapter(
    var listener: IItemClick,
    var checkboxListener: IItemCheckBoxClick,
    var detailButtonListener: IItemDetailButtonClick,
    private var items: ArrayList<Item>
) :
    RecyclerView.Adapter<ItemlistViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemlistViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.custom_item_list, parent, false)
        return ItemlistViewHolder(view)
    }
    /**
     * Getting item count in recyler view
     */
    override fun getItemCount(): Int {
        return if (items.isNotEmpty()) items.size else 0
    }
    /**
     * Notify items changes in recycler view and updates view
     */
    fun notifyData(newItems: ArrayList<Item>) {
        items = newItems
        notifyDataSetChanged()
    }
    /**
     * Binding items to view adapter
     */
    override fun onBindViewHolder(viewHolder: ItemlistViewHolder, position: Int) {
        val item: Item = items[position]

        viewHolder.txvName.text = item.name
        viewHolder.txvCategory.text = item.category
//        viewHolder.txvStore.text = item.store
        viewHolder.cbxButton.isChecked = item.purchasedOrNot == 1
        viewHolder.imvItem.setImageURI(Uri.parse(item.image))
        viewHolder.txvPrice.text = MainActivity._currency + " " + String.format(
            "%.2f", item.showPrice(MainActivity._currency)
        )

        viewHolder.imgBtnMenu.setOnClickListener(
            MenuOnButtonClickListener(
                item,
                viewHolder.imgBtnMenu
            )
        )
        viewHolder.cbxButton.setOnClickListener(CheckBoxButtonClickListener(item))
        viewHolder.btnDetail.setOnClickListener(DetailButtonClickListener(item))
    }
    /**
     * More menu on item. To update and delete item
     */
    inner class MenuOnButtonClickListener(var item: Item, var imgBtn: ImageButton) :
        View.OnClickListener {
        override fun onClick(v: View?) {
            listener.onItemClick(item, imgBtn)
        }
    }
    /**
     * Purchased checkbox click event handler
     */
    inner class CheckBoxButtonClickListener(var item: Item) : View.OnClickListener {
        override fun onClick(v: View?) {
            if (item.purchasedOrNot == 0)
                item.purchasedOrNot = 1
            else
                item.purchasedOrNot = 0
            checkboxListener.onCheckboxClick(item)
        }
    }
    /**
     * Detail button click event handler
     */
    inner class DetailButtonClickListener(var item: Item) : View.OnClickListener {
        override fun onClick(v: View?) {
            detailButtonListener.onDetailButtonClick(item)
        }
    }
}