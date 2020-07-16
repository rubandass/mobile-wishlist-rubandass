package com.jhonr1.wishlist.activities

import android.Manifest
import android.app.*
import android.content.Context
import android.content.ContextWrapper
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.TaskStackBuilder
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.jhonr1.wishlist.R
import com.jhonr1.wishlist.enums.DatabaseStatus
import com.jhonr1.wishlist.helpers.*
import com.jhonr1.wishlist.interfaces.IItemCheckBoxClick
import com.jhonr1.wishlist.interfaces.IItemClick
import com.jhonr1.wishlist.interfaces.IItemDetailButtonClick
import kotlinx.android.synthetic.main.layout_switch.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), IItemClick, IItemCheckBoxClick, IItemDetailButtonClick,
    NavigationView.OnNavigationItemSelectedListener {

    lateinit var toolbar: Toolbar
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    lateinit var btnAddItem: FloatingActionButton
    private lateinit var dbHelper: DBHelper
    private lateinit var items: ArrayList<Item>
    private lateinit var imvItem: ImageView
    private lateinit var recyclerView: RecyclerView
    private lateinit var itemlistRecyclerViewAdapter: ItemlistRecyclerViewAdapter
    private lateinit var imgCapture: ImageCapture
    private lateinit var dialog: Dialog
    private lateinit var spinnerCurrency: Spinner
    private lateinit var spinnerCategory: Spinner
    private lateinit var spinnerPurchase: Spinner
    private lateinit var spinnerSorting: Spinner
    private var filePath = ""
    private lateinit var txvTotal: TextView
    private lateinit var txv_Total: TextView
    private lateinit var currencyList: ArrayList<String>

    private var currency: String = "NZD"
    private var category: String = "All categories"
    private var purchase: String = "All items"
    private var sorter: String = "Newest First"
    private var unpurchased_items = 0

    /**
     * Starting point of the application after splash screen activity
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar = findViewById(R.id.toolbarMain)
        setSupportActionBar(toolbar)

        /**
         * Setting Navigation Drawer to the toolbarMain
         */
        navView = findViewById(R.id.nav_view)
        drawerLayout = findViewById(R.id.drawer_layout)
        Utility.setNavDrawerMenu(this, drawerLayout, toolbar)
        navView.setNavigationItemSelectedListener(this)

        items = ArrayList()
        dbHelper = DBHelper(this@MainActivity)
        items = dbHelper.selectAll()
        items.filter { item -> item.purchasedOrNot == 0 }
        unpurchased_items = items.filter { item -> item.purchasedOrNot == 0 }.size

        spinnerCurrency = findViewById(R.id.spinner_Currency)
        spinnerCategory = findViewById(R.id.spinnerCategory)
        spinnerPurchase = findViewById(R.id.spinnerPurchase)
        spinnerSorting = findViewById(R.id.spinnerSorting)

        var purchaseList = arrayListOf(resources.getString(R.string.all_items), resources.getString(R.string.purchased), resources.getString(R.string.notpurchased))
        var sortingList = arrayListOf(resources.getString(R.string.newfirst), resources.getString(R.string.oldfirst), resources.getString(R.string.ass), resources.getString(R.string.des))
        currencyList = arrayListOf("NZD", "USD", "INR", "AUD")

        populateSpinner(spinnerCurrency, currencyList)
        populateSpinner(spinnerPurchase, purchaseList)
        populateSpinner(spinnerSorting, sortingList)
        populateSpinnerCategory(spinnerCategory)

    /*
    * Currency values change event listener
    * */
        spinnerCurrency.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                currency = spinnerCurrency.selectedItem.toString()
                _currency = currency
                itemlistRecyclerViewAdapter.notifyData(items)
                setTotal()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }
    /**
     * Category change event listener
     */
        spinnerCategory.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                category = spinnerCategory.selectedItem.toString()
                purchase = spinnerPurchase.selectedItem.toString()
                sorter = spinnerSorting.selectedItem.toString()
                var filteredItems = filterItems(category, purchase, sorter)
                items = filteredItems
                itemlistRecyclerViewAdapter.notifyData(filteredItems)
                setTotal()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }
    /**
     * Purchase change event listener
     */
        spinnerPurchase.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                category = spinnerCategory.selectedItem.toString()
                purchase = spinnerPurchase.selectedItem.toString()
                sorter = spinnerSorting.selectedItem.toString()
                var filteredItems = filterItems(category, purchase, sorter)
                items = filteredItems
                itemlistRecyclerViewAdapter.notifyData(filteredItems)
                setTotal()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }
    /**
     * Sorting change event listener
     */
        spinnerSorting.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                category = spinnerCategory.selectedItem.toString()
                purchase = spinnerPurchase.selectedItem.toString()
                sorter = spinnerSorting.selectedItem.toString()
                var filteredItems = filterItems(category, purchase, sorter)
                items = filteredItems
                itemlistRecyclerViewAdapter.notifyData(filteredItems)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }

//        txvTotal = findViewById(R.id.total_amount)
        txvTotal = findViewById(R.id.txv_Total)
        btnAddItem = findViewById(R.id.btnAddItem)
        btnAddItem.setOnClickListener {
            addNewItemDialog(DatabaseStatus.INSERT, 0, "", "", 0F, "", "", "", 0, "")
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }


        imgCapture = ImageCapture(this@MainActivity)
        recyclerView = findViewById(R.id.rcvItems)
        val layoutManager = LinearLayoutManager(this@MainActivity)
        recyclerView.layoutManager = layoutManager
        itemlistRecyclerViewAdapter = ItemlistRecyclerViewAdapter(this, this, this, items)
        recyclerView.adapter = itemlistRecyclerViewAdapter
//        To set Unpurchased items as default filter
        spinnerPurchase.setSelection(2)
        createNotificationChannel()
        toggleButtonStateSet()
        if(getNotifications())
            showNotification()
    }
    /**
     * Populate filter values
     */
    fun populateSpinner(spinner: Spinner, spinnerList: ArrayList<String>) {
        val adapter =
            ArrayAdapter(
                this@MainActivity,
                android.R.layout.simple_spinner_item,
                spinnerList
            )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.setAdapter(adapter)
    }
    /**
     * Hide beyboard after exiting edit text field
     */
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }

    /**
     * It takes menu item, current activity and the side drawer menu layout as input when an item is selected
     * @param[item] Menu item selected on the left drawer menu as input.
     * @return Boolean value as True.
     */
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        Utility.itemSelectedOnDrawerMenu(item, this, this, drawerLayout)
        return true
    }

    /**
     * Toggle notifications on/off
     */
    fun toggleNotifications(view: View){
        val notifications = drawer_switch.isChecked
        val editor = getSharedPreferences("Settings", Activity.MODE_PRIVATE).edit()
        editor.putBoolean("notify", notifications)
        editor.apply()
    }

    fun getNotifications(): Boolean{
        val sharedPreferences = getSharedPreferences("Settings", Activity.MODE_PRIVATE)
        val isNotificationOn = sharedPreferences.getBoolean("notify", true)
        return isNotificationOn
    }

    /**
     * toggle mode ON /OFF switch status
     */
    fun toggleButtonStateSet(){
        val menuItem: MenuItem = navView.menu.findItem(R.id.action_switch) // This is the menu item that contains switch icon
        val switch = menuItem.actionView.findViewById<View>(R.id.drawer_switch) as Switch
        // Setting switch button state
        if(getNotifications()){
            switch.isChecked = true
        }
    }

    /**
     * Based on the selected drop down value items are filtered
     */
    fun filterItems(category: String, purchase: String, sorter: String): ArrayList<Item> {
        var filteredItems: ArrayList<Item> = dbHelper.selectAll()
        if (category != resources.getString(R.string.category)) {
            filteredItems = filteredItems.filter { i -> i.category == category } as ArrayList<Item>
        }

        if (purchase == resources.getString(R.string.notpurchased)) {
            filteredItems = filteredItems.filter { i -> i.purchasedOrNot == 0 } as ArrayList<Item>
        } else if (purchase == resources.getString(R.string.purchased)) {
            filteredItems = filteredItems.filter { i -> i.purchasedOrNot == 1 } as ArrayList<Item>
        }

        if (sorter == resources.getString(R.string.ass)) {
            filteredItems.sortBy { i -> i.name }
        }

        if (sorter == resources.getString(R.string.des)) {
            filteredItems.sortByDescending { i -> i.name }
        }

        if (sorter == resources.getString(R.string.newfirst)) {
            filteredItems.sortByDescending { item -> item.dateTime }
        }
        return filteredItems
    }

    private fun populateSpinnerCategory(spinner: Spinner) {
        var categoryList = ArrayList<String>()
        categoryList.add(resources.getString(R.string.category))
        for (item in items) {
            categoryList.add(item.category.toString())
        }
        android.R.layout.simple_spinner_item
        if (categoryList.size > 1) {
            categoryList = categoryList.distinct() as ArrayList<String>
        }
        val adapter =
            ArrayAdapter(
                this@MainActivity,
                android.R.layout.simple_spinner_item,
                categoryList
            )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.setAdapter(adapter)
    }

    private fun getCurrencyIndex(currency: String): Int {
        return when (currency) {
            "NZD" -> 0
            "USD" -> 1
            "INR" -> 2
            "AUD" -> 3
            else -> 0
        }
    }
    /**
     * Add/Update new item dialog
     */
    private fun addNewItemDialog(
        status: DatabaseStatus,
        id: Int,
        txtName: String,
        txtCategory: String,
        txtPrice: Float,
        txtStore: String,
        txtInfo: String,
        imageView: String,
        purchase: Int,
        currency: String
    ) {
        dialog = Dialog(this@MainActivity, R.style.DialogFullScreen)
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setContentView(R.layout.fragment_add_itemlist)
        imvItem = dialog.findViewById(R.id.imvItem)
        imgCapture.prepare()

        val edtAddItem: EditText = dialog.findViewById(R.id.edtItemNameAdd)
        val edtAddItemCategory: EditText = dialog.findViewById(R.id.edtItemCategoryAdd)
        val edtAddItemPrice: EditText = dialog.findViewById(R.id.edtItemPriceAdd)
        val edtAddItemStore: EditText = dialog.findViewById(R.id.edtItemStoreAdd)
        val edtAddItemInfo: EditText = dialog.findViewById(R.id.edtItemInfoAdd)
        val txvAddItem: TextView = dialog.findViewById(R.id.txvItemlistHeader)
        val btnAddItem: Button = dialog.findViewById(R.id.btnAdd)
        val btnCloseItem: Button = dialog.findViewById(R.id.btnClose)
        val imgChangeItem: ImageButton = dialog.findViewById(R.id.imgBtnAddItem)
        val btnMap: Button = dialog.findViewById(R.id.btnMap)
        val currencySpinnerDialog: Spinner = dialog.findViewById(R.id.spinnerCurrencyDialog)
        populateSpinner(currencySpinnerDialog, currencyList)

        var bitmapDrawable: BitmapDrawable? = null
        if (imageView != "") {
            val bitmap = BitmapProcessor.process(imageView)
            bitmapDrawable =
                BitmapProcessor.convertBitmapToDrawable(resources, bitmap)
            filePath = imageView
        }

        btnMap.setOnClickListener {
            val intent = Intent(this@MainActivity, MapsActivity::class.java)
            val newIntent: Intent = intent.putExtra("map_code", 10)
            startActivityForResult(newIntent, REQUEST_MAP_PERMISSION)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)

        }

        imgChangeItem.setOnClickListener {
            openCamera()
        }

        if (status == DatabaseStatus.UPDATE) {
            edtAddItem.setText(txtName)
            edtAddItemCategory.setText(txtCategory)
            edtAddItemPrice.setText(txtPrice.toString())
            edtAddItemStore.setText(txtStore)
            edtAddItemInfo.setText(txtInfo)
            imvItem.setImageDrawable(bitmapDrawable)
            currencySpinnerDialog.setSelection(getCurrencyIndex(currency))
            btnAddItem.text = resources.getString(R.string.update_btn_text)
            txvAddItem.text = resources.getString(R.string.update_item_header)
        } else {
            edtAddItem.setText("")
            btnAddItem.text = resources.getString(R.string.add_btn_text)
            txvAddItem.text = resources.getString(R.string.add_item_header)
        }

        btnAddItem.setOnClickListener {
            var isEmpty = false
            if (filePath == "") {
                filePath =
                    saveImageToInternalStorage(R.drawable.default_item_image).encodedPath.toString()
            }

            var fields = arrayListOf(
                edtAddItem,
                edtAddItemCategory,
                edtAddItemPrice,
                edtAddItemStore
            )
            for (field in fields) {
                if (field.text.toString() == "") {
                    isEmpty = true
                }
            }
            if (!isEmpty) {
                if (status == DatabaseStatus.UPDATE) {
                    dbHelper.update(
                        id.toLong(),
                        edtAddItem.text.toString().trim(),
                        edtAddItemCategory.text.toString().trim(),
                        edtAddItemPrice.text.toString().trim(),
                        edtAddItemStore.text.toString().trim(),
                        edtAddItemInfo.text.toString().trim(),
                        purchase,
                        filePath,
                        currencySpinnerDialog.selectedItem.toString()
                    )
                    readDatabase()
                    populateSpinnerCategory(spinnerCategory)
                    itemlistRecyclerViewAdapter.notifyData(items)
                    filePath = ""
                } else if (status == DatabaseStatus.INSERT) {
                    dbHelper.insert(
                        edtAddItem.text.toString().trim(),
                        edtAddItemCategory.text.toString().trim(),
                        edtAddItemPrice.text.toString().trim(),
                        edtAddItemStore.text.toString().trim(),
                        edtAddItemInfo.text.toString().trim(),
                        purchase,
                        filePath,
                        currencySpinnerDialog.selectedItem.toString()
                    )
                    readDatabase()
                    populateSpinnerCategory(spinnerCategory)
                    filePath = ""
                }
                dialog.dismiss()
            } else {
                Toast.makeText(
                    this@MainActivity,
                    resources.getString(R.string.error_item), Toast.LENGTH_SHORT
                ).show()
            }
        }
        btnCloseItem.setOnClickListener { dialog.dismiss() }
        dialog.show()
    }
    /**
     * convert image to bitmap and save to phone storage
     */
    private fun saveImageToInternalStorage(drawableId: Int): Uri {
        // Get the image from drawable resource as drawable object
        val drawable = ContextCompat.getDrawable(applicationContext, drawableId)

        // Get the bitmap from drawable object
        val bitmap = (drawable as BitmapDrawable).bitmap

        // Get the context wrapper instance
        val wrapper = ContextWrapper(applicationContext)

        // Initializing a new file
        // The bellow line return a directory in internal storage
        var file = wrapper.getDir("images", Context.MODE_PRIVATE)


        // Create a file to save the image
        file = File(file, "${UUID.randomUUID()}.jpg")

        try {
            // Get the file output stream
            val stream: OutputStream = FileOutputStream(file)

            // Compress bitmap
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)

            // Flush the stream
            stream.flush()
            // Close stream
            stream.close()
        } catch (e: IOException) { // Catch the exception
            e.printStackTrace()
        }

        // Return the saved image uri
        return Uri.parse(file.absolutePath)
    }

    /**
     * Menubutton click on the recycler view item
     */
    override fun onItemClick(itemlist: Item, imgBtn: ImageButton) {
        val popup = PopupMenu(this, imgBtn)
        popup.menuInflater.inflate(R.menu.menu_itemlist, popup.menu)
        popup.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.update -> addNewItemDialog(
                    DatabaseStatus.UPDATE,
                    itemlist.id,
                    itemlist.name!!,
                    itemlist.category!!,
                    itemlist.price,
                    itemlist.store!!,
                    itemlist.additionalInfo!!,
                    itemlist.image!!,
                    itemlist.purchasedOrNot,
                    itemlist.currency!!
                )
                R.id.delete -> {
                    val deleteDialog =
                        DeleteAlertDialog(this@MainActivity, R.layout.delete_itemlist, itemlist)
                    deleteDialog.show("Delete Item")
                }
            }
            true
        }
        popup.show()
    }
    /**
     * Checkbox click to purchase or not purchase an item
     */
    override fun onCheckboxClick(item: Item) {
        dbHelper.updatePurchase(
            item.id.toLong(), item.purchasedOrNot
        )
        items = filterItems(category, purchase, sorter)
        itemlistRecyclerViewAdapter.notifyData(items)
        setTotal()
        Toast.makeText(
            this@MainActivity,
            if (item.purchasedOrNot == 1) "Item purchased" else "Not purchased", Toast.LENGTH_SHORT
        ).show()
    }
    /**
     * To view full details of an item
     */
    override fun onDetailButtonClick(item: Item) {
        if(item != null){
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra("item_name", item.name)
            intent.putExtra("item_category", item.category)
            intent.putExtra("item_price", item.price.toString())
            intent.putExtra("item_purchase", item.purchasedOrNot)
            intent.putExtra("item_store", item.store)
            intent.putExtra("item_info", item.additionalInfo)
            intent.putExtra("image", item.image)
            intent.putExtra("item_datetime", item.dateTime.toString())
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }
    }

    /**
     * Delete item confirmation dialog
     */
    inner class DeleteAlertDialog(
        context: Context,
        layout: Int,
        private val itemlist: Item
    ) {
        private lateinit var alertDialog: AlertDialog
        private val inflater: LayoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        private val view: View = inflater.inflate(layout, null)
        private val builder = AlertDialog.Builder(context)

        fun show(title: String) {
            builder.setTitle(title)
            builder.setCancelable(true)
            builder.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, id ->
                dbHelper.delete(itemlist.id.toLong())
                readDatabase()
                populateSpinnerCategory(spinnerCategory)
                dialog.cancel()
            })
            builder.setNegativeButton("CANCEL", DialogInterface.OnClickListener { dialog, id ->
                dialog.cancel()
            })
            alertDialog = builder.setView(view).create()
            builder.show()
        }
    }
    /**
     * Get user permission for camera
     */
    private fun isPermissionGranted(): Boolean {
        if (ActivityCompat.checkSelfPermission(
                this@MainActivity, Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                this@MainActivity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
    }
    /**
     * Opens camera based on user permission
     */
    private fun openCamera() {
        if (isPermissionGranted()) {
            val uri: Uri = imgCapture.prepare()
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            val newIntent: Intent = intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
            startActivityForResult(newIntent, REQUEST_CODE_PERMISSION)
        } else {
            requestPermissions()
            openCamera()
        }

    }

    /**
     * Callback result activity for camera
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_PERMISSION && (resultCode == RESULT_OK)) {
            filePath = imgCapture.imgFile.path
            val bitmap: Bitmap = BitmapProcessor.process(filePath)
            val bitmapDrawable: BitmapDrawable =
                BitmapProcessor.convertBitmapToDrawable(resources, bitmap)
            imvItem.setImageDrawable(bitmapDrawable)
        } else if (requestCode == REQUEST_MAP_PERMISSION) {
            val store = data?.getStringExtra("store")
            val edtAddItemStore: EditText = dialog.findViewById(R.id.edtItemStoreAdd)
            edtAddItemStore.setText(store)
        }
    }

    /**
     * Request user permission for camera
     */
    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            this@MainActivity,
            arrayOf(
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ),
            REQUEST_CODE_PERMISSION
        )
    }

    /**
     * Read all values from database
     */
    private fun readDatabase() {
        items = dbHelper.selectAll()
        itemlistRecyclerViewAdapter.notifyData(items)
    }

    /**
     * Set recycler view items total
     */
    private fun setTotal() {
        var allprice = items.map { it.showPrice(_currency) }
        txvTotal.setText(String.format("%.2f", allprice.sum()))
//        txvTotal.setText(resources.getString(R.string.total) + ": " + String.format("%.2f", allprice.sum()))
    }

    /**
     * Create notifications channel
     */
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Wish"
            val descriptionText = "Notifications"
            val importance: Int = NotificationManager.IMPORTANCE_DEFAULT
            val channel: NotificationChannel =
                NotificationChannel(CHANNEL_ID, name, importance).apply {
                    description = descriptionText
                }
            val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
    /**
     * Send notifications for the un purchased items
     */
    private fun showNotification() {
        val tag = "myNotification"
        val notificationId = 1
        val resultIntent = Intent(this@MainActivity, MainActivity::class.java)
        val resultPendingIntent: PendingIntent? = TaskStackBuilder.create(this).run {
            addNextIntentWithParentStack(resultIntent)
            getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
        }

        if (unpurchased_items > 0) {
            val builder = NotificationCompat.Builder(this@MainActivity, CHANNEL_ID)
                .setContentIntent(resultPendingIntent)
                .setContentTitle("Wishlist alert")
                .setSmallIcon(R.drawable.wishlist_logo)
                .setContentText("Currently, you have ${unpurchased_items} unpurchased items")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)
            with(NotificationManagerCompat.from(this)) {
                notify(tag, notificationId, builder.build())
            }
        }
    }
    /**
     * Exit the app when back button pressed
     */
    override fun onBackPressed() {
        val exitAlertDialog = CustomExitAlertDialog(this, this, R.layout.exit_dialog)
        exitAlertDialog.show(resources.getString(R.string.app_name))
    }

    companion object {
        const val REQUEST_CODE_PERMISSION = 1
        const val REQUEST_MAP_PERMISSION = 100
        var _currency: String = "NZD"
        private const val CHANNEL_ID = "com.jhonr1.wishlist"
    }

}
