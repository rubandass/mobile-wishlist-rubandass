package com.jhonr1.wishlist.helpers
/**
 * Item declaration class
 */
class Item {
    var id: Int = 0
    var name: String? = null
    var category: String? = null
    var price: Float = 0F
    var store: String? = null
    var additionalInfo: String? = null
    var purchasedOrNot: Int = 0
    var image: String? = null
    var dateTime: String? = null
    var currency: String = ""

    /**
     * Currency conversion
     */
    fun showPrice(toCurrency: String): Double {
        var _price: Double = this.price.toDouble()
        if (this.currency != "NZD") {
            when (this.currency) {
                "USD" -> _price /= 0.65
                "AUD" -> _price /= 0.93
                "INR" -> _price /= 49.32
            }
        }
        when (toCurrency) {
            "NZD" -> _price *= 1.00
            "USD" -> _price *= 0.65
            "AUD" -> _price *= 0.93
            "INR" -> _price *= 49.32
        }
        return _price
    }
}