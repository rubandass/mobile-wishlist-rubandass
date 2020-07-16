[app](../../index.md) / [com.jhonr1.wishlist.helpers](../index.md) / [ItemlistRecyclerViewAdapter](./index.md)

# ItemlistRecyclerViewAdapter

`class ItemlistRecyclerViewAdapter : Adapter<`[`ItemlistViewHolder`](../-itemlist-view-holder/index.md)`>`

Binding items on recycler view

### Types

| Name | Summary |
|---|---|
| [CheckBoxButtonClickListener](-check-box-button-click-listener/index.md) | `inner class CheckBoxButtonClickListener : `[`OnClickListener`](https://developer.android.com/reference/android/view/View/OnClickListener.html)<br>Purchased checkbox click event handler |
| [DetailButtonClickListener](-detail-button-click-listener/index.md) | `inner class DetailButtonClickListener : `[`OnClickListener`](https://developer.android.com/reference/android/view/View/OnClickListener.html)<br>Detail button click event handler |
| [MenuOnButtonClickListener](-menu-on-button-click-listener/index.md) | `inner class MenuOnButtonClickListener : `[`OnClickListener`](https://developer.android.com/reference/android/view/View/OnClickListener.html)<br>More menu on item. To update and delete item |

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `ItemlistRecyclerViewAdapter(listener: `[`IItemClick`](../../com.jhonr1.wishlist.interfaces/-i-item-click/index.md)`, checkboxListener: `[`IItemCheckBoxClick`](../../com.jhonr1.wishlist.interfaces/-i-item-check-box-click/index.md)`, detailButtonListener: `[`IItemDetailButtonClick`](../../com.jhonr1.wishlist.interfaces/-i-item-detail-button-click/index.md)`, items: `[`ArrayList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-array-list/index.html)`<`[`Item`](../-item/index.md)`>)`<br>Binding items on recycler view |

### Properties

| Name | Summary |
|---|---|
| [checkboxListener](checkbox-listener.md) | `var checkboxListener: `[`IItemCheckBoxClick`](../../com.jhonr1.wishlist.interfaces/-i-item-check-box-click/index.md) |
| [detailButtonListener](detail-button-listener.md) | `var detailButtonListener: `[`IItemDetailButtonClick`](../../com.jhonr1.wishlist.interfaces/-i-item-detail-button-click/index.md) |
| [listener](listener.md) | `var listener: `[`IItemClick`](../../com.jhonr1.wishlist.interfaces/-i-item-click/index.md) |

### Functions

| Name | Summary |
|---|---|
| [getItemCount](get-item-count.md) | `fun getItemCount(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Getting item count in recyler view |
| [notifyData](notify-data.md) | `fun notifyData(newItems: `[`ArrayList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-array-list/index.html)`<`[`Item`](../-item/index.md)`>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Notify items changes in recycler view and updates view |
| [onBindViewHolder](on-bind-view-holder.md) | `fun onBindViewHolder(viewHolder: `[`ItemlistViewHolder`](../-itemlist-view-holder/index.md)`, position: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Binding items to view adapter |
| [onCreateViewHolder](on-create-view-holder.md) | `fun onCreateViewHolder(parent: `[`ViewGroup`](https://developer.android.com/reference/android/view/ViewGroup.html)`, viewType: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`ItemlistViewHolder`](../-itemlist-view-holder/index.md) |
