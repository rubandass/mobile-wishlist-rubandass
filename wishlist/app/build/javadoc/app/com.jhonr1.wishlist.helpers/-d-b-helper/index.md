[app](../../index.md) / [com.jhonr1.wishlist.helpers](../index.md) / [DBHelper](./index.md)

# DBHelper

`class DBHelper : `[`SQLiteOpenHelper`](https://developer.android.com/reference/android/database/sqlite/SQLiteOpenHelper.html)

Database helper class

### Types

| Name | Summary |
|---|---|
| [DateTime](-date-time/index.md) | `object DateTime` |

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `DBHelper(context: `[`Context`](https://developer.android.com/reference/android/content/Context.html)`)`<br>Database helper class |

### Functions

| Name | Summary |
|---|---|
| [delete](delete.md) | `fun delete(id: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Delete item from database |
| [insert](insert.md) | `fun insert(itemName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, itemCategory: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, itemPrice: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, itemStore: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, itemInfo: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, itemPurchase: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, itemImage: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, currency: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)<br>Insert new item to sqlite database |
| [onCreate](on-create.md) | `fun onCreate(db: `[`SQLiteDatabase`](https://developer.android.com/reference/android/database/sqlite/SQLiteDatabase.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onUpgrade](on-upgrade.md) | `fun onUpgrade(db: `[`SQLiteDatabase`](https://developer.android.com/reference/android/database/sqlite/SQLiteDatabase.html)`, oldVersion: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, newVersion: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [selectAll](select-all.md) | `fun selectAll(): `[`ArrayList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-array-list/index.html)`<`[`Item`](../-item/index.md)`>`<br>Getting all values (item) from database |
| [update](update.md) | `fun update(id: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`, itemName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, itemCategory: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, itemPrice: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, itemStore: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, itemInfo: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, itemPurchase: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, itemImage: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, currency: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Updating item details |
| [updatePurchase](update-purchase.md) | `fun updatePurchase(id: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`, purchaseValue: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Updating item purchased or not |

### Companion Object Properties

| Name | Summary |
|---|---|
| [COLUMN_CATEGORY](-c-o-l-u-m-n_-c-a-t-e-g-o-r-y.md) | `const val COLUMN_CATEGORY: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [COLUMN_CURRENCY](-c-o-l-u-m-n_-c-u-r-r-e-n-c-y.md) | `const val COLUMN_CURRENCY: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [COLUMN_DATE_TIME](-c-o-l-u-m-n_-d-a-t-e_-t-i-m-e.md) | `const val COLUMN_DATE_TIME: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [COLUMN_ID](-c-o-l-u-m-n_-i-d.md) | `const val COLUMN_ID: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [COLUMN_IMAGE](-c-o-l-u-m-n_-i-m-a-g-e.md) | `const val COLUMN_IMAGE: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [COLUMN_INFO](-c-o-l-u-m-n_-i-n-f-o.md) | `const val COLUMN_INFO: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [COLUMN_NAME](-c-o-l-u-m-n_-n-a-m-e.md) | `const val COLUMN_NAME: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [COLUMN_PRICE](-c-o-l-u-m-n_-p-r-i-c-e.md) | `const val COLUMN_PRICE: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [COLUMN_PURCHASE](-c-o-l-u-m-n_-p-u-r-c-h-a-s-e.md) | `const val COLUMN_PURCHASE: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [COLUMN_STORE](-c-o-l-u-m-n_-s-t-o-r-e.md) | `const val COLUMN_STORE: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [DATABASE_CREATE](-d-a-t-a-b-a-s-e_-c-r-e-a-t-e.md) | `const val DATABASE_CREATE: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [DATABASE_NAME](-d-a-t-a-b-a-s-e_-n-a-m-e.md) | `const val DATABASE_NAME: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [DATABASE_VERSION](-d-a-t-a-b-a-s-e_-v-e-r-s-i-o-n.md) | `const val DATABASE_VERSION: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [TABLE_NAME](-t-a-b-l-e_-n-a-m-e.md) | `const val TABLE_NAME: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
