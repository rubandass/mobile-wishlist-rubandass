[app](../../index.md) / [com.jhonr1.wishlist.activities](../index.md) / [MapsActivity](./index.md)

# MapsActivity

`class MapsActivity : AppCompatActivity, OnMapReadyCallback`

Opens google map activity

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `MapsActivity()`<br>Opens google map activity |

### Functions

| Name | Summary |
|---|---|
| [finish](finish.md) | `fun finish(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onCreate](on-create.md) | `fun onCreate(savedInstanceState: `[`Bundle`](https://developer.android.com/reference/android/os/Bundle.html)`?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onMapReady](on-map-ready.md) | `fun onMapReady(googleMap: GoogleMap): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Set markers (hard coded locations) |
| [onRequestPermissionsResult](on-request-permissions-result.md) | `fun onRequestPermissionsResult(requestCode: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, permissions: `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>, grantResults: `[`IntArray`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int-array/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Prompt user to ON location if it is OFF |

### Companion Object Properties

| Name | Summary |
|---|---|
| [REQUEST_CODE_PERMISSION](-r-e-q-u-e-s-t_-c-o-d-e_-p-e-r-m-i-s-s-i-o-n.md) | `const val REQUEST_CODE_PERMISSION: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [REQUEST_LOCATION_PERMISSION](-r-e-q-u-e-s-t_-l-o-c-a-t-i-o-n_-p-e-r-m-i-s-s-i-o-n.md) | `const val REQUEST_LOCATION_PERMISSION: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
