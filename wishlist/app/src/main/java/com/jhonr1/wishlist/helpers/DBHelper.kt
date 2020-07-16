package com.jhonr1.wishlist.helpers

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.ImageView
import de.hdodenhof.circleimageview.CircleImageView
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
/**
 * Database helper class
 */
class DBHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(DATABASE_CREATE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }
    /**
     * Insert new item to sqlite database
     */
    fun insert(
        itemName: String, itemCategory: String, itemPrice: String, itemStore: String,
        itemInfo: String, itemPurchase: Int, itemImage: String, currency: String
    ): Long {
        val db: SQLiteDatabase = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_NAME, itemName)
        values.put(COLUMN_CATEGORY, itemCategory)
        values.put(COLUMN_PRICE, itemPrice)
        values.put(COLUMN_STORE, itemStore)
        values.put(COLUMN_INFO, itemInfo)
        values.put(COLUMN_PURCHASE, itemPurchase)
        values.put(COLUMN_IMAGE, itemImage)
        values.put(COLUMN_CURRENCY, currency)
        values.put(COLUMN_DATE_TIME, DateTime.currentDateTime())
        val id: Long = db.insert(TABLE_NAME, null, values)
        db.close()
        return id
    }
    /**
     * Getting all values (item) from database
     */
    fun selectAll(): ArrayList<Item> {
        val itemlists = ArrayList<Item>()

        val selectQuery = "SELECT * FROM $TABLE_NAME ORDER BY $COLUMN_DATE_TIME ASC"
        val db: SQLiteDatabase = this.writableDatabase
        val cursor: Cursor = db.rawQuery(selectQuery, null)
        if (cursor.moveToFirst()) {
            do {
                val itemlist = Item()
                itemlist.id = cursor.getInt((cursor.getColumnIndex(COLUMN_ID)))
                itemlist.name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME))
                itemlist.category = cursor.getString(cursor.getColumnIndex(COLUMN_CATEGORY))
                itemlist.price = cursor.getFloat(cursor.getColumnIndex(COLUMN_PRICE))
                itemlist.store = cursor.getString(cursor.getColumnIndex(COLUMN_STORE))
                itemlist.additionalInfo = cursor.getString(cursor.getColumnIndex(COLUMN_INFO))
                itemlist.purchasedOrNot = cursor.getInt(cursor.getColumnIndex(COLUMN_PURCHASE))
                itemlist.image = cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE))
                itemlist.currency = cursor.getString(cursor.getColumnIndex(COLUMN_CURRENCY))
                itemlist.dateTime = cursor.getString(cursor.getColumnIndex(COLUMN_DATE_TIME))
                itemlists.add(itemlist)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return itemlists
    }
    /**
     * Updating item details
     */
    fun update(
        id: Long, itemName: String, itemCategory: String, itemPrice: String, itemStore: String,
        itemInfo: String, itemPurchase: Int, itemImage: String, currency: String
    ): Int {
        val db: SQLiteDatabase = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_NAME, itemName)
        values.put(COLUMN_CATEGORY, itemCategory)
        values.put(COLUMN_PRICE, itemPrice)
        values.put(COLUMN_STORE, itemStore)
        values.put(COLUMN_INFO, itemInfo)
        values.put(COLUMN_PURCHASE, itemPurchase)
        values.put(COLUMN_IMAGE, itemImage)
        values.put(COLUMN_CURRENCY, currency)
        values.put(COLUMN_DATE_TIME, DateTime.currentDateTime())
        return db.update(
            TABLE_NAME, values, "$COLUMN_ID = ?",
            arrayOf(id.toString())
        )
    }
    /**
     * Updating item purchased or not
     */
    fun updatePurchase(id: Long, purchaseValue: Int): Int{
        val db: SQLiteDatabase = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_PURCHASE, purchaseValue)
        return db.update(
            TABLE_NAME, values, "$COLUMN_ID = ?",
            arrayOf(id.toString())
        )
    }
    /**
     * Delete item from database
     */
    fun delete(id: Long) {
        val db: SQLiteDatabase = this.writableDatabase
        db.delete(
            TABLE_NAME, "$COLUMN_ID = ?",
            arrayOf(id.toString())
        )
        db.close()
    }

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "db_itemlist"
        const val TABLE_NAME = "itemlists"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_CATEGORY = "category"
        const val COLUMN_PRICE = "price"
        const val COLUMN_STORE = "store"
        const val COLUMN_INFO = "info"
        const val COLUMN_PURCHASE = "purchase"
        const val COLUMN_IMAGE = "image"
        const val COLUMN_CURRENCY = "currency"
        const val COLUMN_DATE_TIME = "date_time"
        const val DATABASE_CREATE: String =
            "CREATE TABLE $TABLE_NAME($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_NAME TEXT, $COLUMN_CATEGORY TEXT, $COLUMN_PRICE REAL, $COLUMN_STORE TEXT, $COLUMN_INFO TEXT, $COLUMN_PURCHASE INT, $COLUMN_IMAGE TEXT, $COLUMN_CURRENCY TEXT, $COLUMN_DATE_TIME TEXT)"
    }

    object DateTime {
        fun currentDateTime(): String {
            val date: Date = Calendar.getInstance().time
            val outputPattern = "dd/MM/yyyy kk:mm:ss"
            val outputFormat = SimpleDateFormat(outputPattern, Locale.ENGLISH)
            return outputFormat.format(date)
        }
    }
}
