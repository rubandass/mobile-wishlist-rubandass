[app](../../index.md) / [com.jhonr1.wishlist.activities](../index.md) / [MainActivity](./index.md)

# MainActivity

`class MainActivity : AppCompatActivity, `[`IItemClick`](../../com.jhonr1.wishlist.interfaces/-i-item-click/index.md)`, `[`IItemCheckBoxClick`](../../com.jhonr1.wishlist.interfaces/-i-item-check-box-click/index.md)`, `[`IItemDetailButtonClick`](../../com.jhonr1.wishlist.interfaces/-i-item-detail-button-click/index.md)`, OnNavigationItemSelectedListener`

### Types

| Name | Summary |
|---|---|
| [DeleteAlertDialog](-delete-alert-dialog/index.md) | `inner class DeleteAlertDialog`<br>Delete item confirmation dialog |

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `MainActivity()` |

### Properties

| Name | Summary |
|---|---|
| [btnAddItem](btn-add-item.md) | `lateinit var btnAddItem: FloatingActionButton` |
| [toolbar](toolbar.md) | `lateinit var toolbar: Toolbar` |

### Functions

| Name | Summary |
|---|---|
| [dispatchTouchEvent](dispatch-touch-event.md) | `fun dispatchTouchEvent(ev: `[`MotionEvent`](https://developer.android.com/reference/android/view/MotionEvent.html)`?): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Hide beyboard after exiting edit text field |
| [filterItems](filter-items.md) | `fun filterItems(category: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, purchase: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, sorter: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`ArrayList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-array-list/index.html)`<`[`Item`](../../com.jhonr1.wishlist.helpers/-item/index.md)`>`<br>Based on the selected drop down value items are filtered |
| [getNotifications](get-notifications.md) | `fun getNotifications(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [onActivityResult](on-activity-result.md) | `fun onActivityResult(requestCode: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, resultCode: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, data: `[`Intent`](https://developer.android.com/reference/android/content/Intent.html)`?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Callback result activity for camera |
| [onBackPressed](on-back-pressed.md) | `fun onBackPressed(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Exit the app when back button pressed |
| [onCheckboxClick](on-checkbox-click.md) | `fun onCheckboxClick(item: `[`Item`](../../com.jhonr1.wishlist.helpers/-item/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Checkbox click to purchase or not purchase an item |
| [onCreate](on-create.md) | `fun onCreate(savedInstanceState: `[`Bundle`](https://developer.android.com/reference/android/os/Bundle.html)`?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Starting point of the application after splash screen activity |
| [onDetailButtonClick](on-detail-button-click.md) | `fun onDetailButtonClick(item: `[`Item`](../../com.jhonr1.wishlist.helpers/-item/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>To view full details of an item |
| [onItemClick](on-item-click.md) | `fun onItemClick(itemlist: `[`Item`](../../com.jhonr1.wishlist.helpers/-item/index.md)`, imgBtn: `[`ImageButton`](https://developer.android.com/reference/android/widget/ImageButton.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Menubutton click on the recycler view item |
| [onNavigationItemSelected](on-navigation-item-selected.md) | `fun onNavigationItemSelected(item: `[`MenuItem`](https://developer.android.com/reference/android/view/MenuItem.html)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>It takes menu item, current activity and the side drawer menu layout as input when an item is selected |
| [populateSpinner](populate-spinner.md) | `fun populateSpinner(spinner: `[`Spinner`](https://developer.android.com/reference/android/widget/Spinner.html)`, spinnerList: `[`ArrayList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-array-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Populate filter values |
| [toggleButtonStateSet](toggle-button-state-set.md) | `fun toggleButtonStateSet(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>toggle mode ON /OFF switch status |
| [toggleNotifications](toggle-notifications.md) | `fun toggleNotifications(view: `[`View`](https://developer.android.com/reference/android/view/View.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Toggle notifications on/off |

### Companion Object Properties

| Name | Summary |
|---|---|
| [REQUEST_CODE_PERMISSION](-r-e-q-u-e-s-t_-c-o-d-e_-p-e-r-m-i-s-s-i-o-n.md) | `const val REQUEST_CODE_PERMISSION: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [REQUEST_MAP_PERMISSION](-r-e-q-u-e-s-t_-m-a-p_-p-e-r-m-i-s-s-i-o-n.md) | `const val REQUEST_MAP_PERMISSION: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [_currency](_currency.md) | `var _currency: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
