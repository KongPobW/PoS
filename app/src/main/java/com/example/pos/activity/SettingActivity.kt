package com.example.pos.activity

import android.Manifest
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.content.FileProvider.getUriForFile
import com.android.volley.AuthFailureError
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.pos.R
import com.example.pos.database.PoSAppDatabase
import com.example.pos.database.VolleySingleton
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.single.PermissionListener
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.io.ByteArrayOutputStream
import java.io.File

class SettingActivity : AppCompatActivity() {

    private var imgUri: Uri? = null

    var bitmap: Bitmap? = null
    var encodedImage: String? = null
    val apiUrl = "http://192.168.1.121/pos/images/fileupload.php"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        val takePic =
            registerForActivityResult(ActivityResultContracts.TakePicture()) { isSuccess ->

                if (isSuccess) {
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("Reminded Message")
                    builder.setMessage("Upload Picture Successful")

                    builder.setPositiveButton("yes") { dialog, which ->
                        Toast.makeText(applicationContext, android.R.string.yes, Toast.LENGTH_SHORT)
                            .show()

                        val linearLayout = findViewById(R.id.linear_layout_photo) as LinearLayout
                        val factor: Float = linearLayout.context.resources.displayMetrics.density
                        val width = (linearLayout.width * factor * 1)
                        val height = (linearLayout.height * factor * 1)

                        val imgView = ImageView(this)
                        imgView.layoutParams = LinearLayout.LayoutParams(width.toInt(), height.toInt())
                        imgView.setImageURI(imgUri)
                        linearLayout?.addView(imgView)
                    }
                    builder.show()
                } else {
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("Reminded Message")
                    builder.setMessage("Upload Picture Failed")

                    builder.setPositiveButton("yes") { dialog, which ->
                        Toast.makeText(applicationContext, android.R.string.yes, Toast.LENGTH_SHORT).show()
                    }
                    builder.show()
                }
            }

        val btnUploadReport = findViewById(R.id.btn_uploadReport) as Button

        btnUploadReport.setOnClickListener {

            val imgPath: File = File(getExternalFilesDir(null), "my_images")
            imgPath.mkdirs()
            val newFile = File(imgPath, "img_" + System.currentTimeMillis() + ".jpg")
            imgUri = getUriForFile(this@SettingActivity, "com.example.pos.fileprovider", newFile)
            takePic.launch(imgUri)
        }

        searchContactButton()

        setupBranchButton()

        retrieveRemoteButton()

        submitRemoteButton()

        uploadDailyRemoteButton()
    }

    fun deleteAllRecord() = GlobalScope.async {
        val db = PoSAppDatabase.getInstance(applicationContext)
        db.orderLineDao().deleteAll()
        db.orderDao().deleteAll()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 111 && resultCode == RESULT_OK) {
            bitmap = data!!.extras!!["data"] as Bitmap?
            encodeBitMap(bitmap!!)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    fun encodeBitMap(bitmap: Bitmap) {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)

        val byteOfImages = byteArrayOutputStream.toByteArray()
        encodedImage = Base64.encodeToString(byteOfImages, Base64.DEFAULT)
    }

    fun uploadToServer() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Do you need to upload image to remote?")

            .setPositiveButton("Yes") { dialog, which ->

                Toast.makeText(applicationContext, "Uploaded", Toast.LENGTH_SHORT).show()

                val request: StringRequest = object: StringRequest(
                    Method.POST, this.apiUrl, Response.Listener {
                        Toast.makeText(applicationContext, "FileUploaded Successfully", Toast.LENGTH_SHORT).show()
                    }, Response.ErrorListener { error ->
                        Toast.makeText(applicationContext, error.toString(), Toast.LENGTH_SHORT).show()
                    })
                {
                    @Throws(AuthFailureError::class)
                    override fun getParams(): Map<String, String>? {
                        val map: MutableMap<String, String> = HashMap()
                        map["upload"] = encodedImage!!
                        return map
                    }
                }

                val queue = Volley.newRequestQueue(applicationContext)
                queue.add(request)
            }

            .setNegativeButton("No") { dialog, which ->
                Toast.makeText(applicationContext, "Failed", Toast.LENGTH_SHORT).show()
            }

        builder.create().show()
    }

    fun getOrdersFromLocalDBAsync() = GlobalScope.async {
        val db = PoSAppDatabase.getInstance(applicationContext)
        db.orderDao().getAll()
    }

    fun getOrderLinesFromLocalDBAsync() = GlobalScope.async {
        val db = PoSAppDatabase.getInstance(applicationContext)
        db.orderLineDao().getAll()
    }

    fun searchContactButton() {

        val btnSearchContact = findViewById(R.id.btn_searchContact) as Button

        btnSearchContact.setOnClickListener {

            val intent = Intent(this, ContactActivity::class.java).apply {
            }
            startActivity(intent)
        }
    }

    fun setupBranchButton() {

        val btnSetupBranch = findViewById(R.id.btn_setupBranch) as Button

        btnSetupBranch.setOnClickListener {

            val intent = Intent(this, SetUpActivity::class.java).apply {
            }
            startActivity(intent)
        }
    }

    fun retrieveRemoteButton() {

        val btnRetrieveOrderRemote = findViewById(R.id.btn_retrieveRemote) as Button

        btnRetrieveOrderRemote.setOnClickListener {

            val intent = Intent(this, OrderManagerActivity::class.java).apply {
            }
            startActivity(intent)

            Log.i("SettingActivity", "Retrieve Remote Button Clicked!!")

            GlobalScope.launch {

                var url = "http://192.168.1.121/pos/pos_api/public/orders"
                var jsonRequest = JsonArrayRequest(Request.Method.GET, url, null,
                    { response ->

                        for (i in 0 until response.length()) {
                            val order = response.getJSONObject(i)
                            Log.i("SettingActivity", "Response: User ID = ${order.get("user_id")}, Branch ID = ${order.get("branch_id")}, Staff ID = ${order.get("staff_id")}")
                        }

                    }, {
                        Log.i("SettingActivity", "Error: $it")
                    }
                )

                jsonRequest.retryPolicy = DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, 0, 1f)
                VolleySingleton.getInstance(applicationContext).addToRequestQueue(jsonRequest)

                url = "http://192.168.1.121/pos/pos_api/public/orderlines"
                jsonRequest = JsonArrayRequest(Request.Method.GET, url, null,
                    { response ->

                        for (i in 0 until response.length()) {
                            val orderline = response.getJSONObject(i)
                            Log.i("SettingActivity", "Response: Order ID = ${orderline.get("order_id")}, User ID = ${orderline.get("user_id")}, Product ID = ${orderline.get("product_id")}, Product Name = ${orderline.get("product_name")}, Quantity = ${orderline.get("quantity")}, Subtotal = ${orderline.get("subtotal")}")
                        }

                    }, {
                        Log.i("SettingActivity", "Error: $it")
                    }
                )

                jsonRequest.retryPolicy = DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, 0, 1f)
                VolleySingleton.getInstance(applicationContext).addToRequestQueue(jsonRequest)
            }

            Toast.makeText(applicationContext, "Retrieve Order Remote Clicked", Toast.LENGTH_SHORT).show()
        }
    }

    fun submitRemoteButton() {

        val btnSubmitOrderRemote = findViewById(R.id.btn_submitRemote) as Button

        btnSubmitOrderRemote.setOnClickListener {

            Log.i("SettingActivity", "Submit Remote Button Clicked!!")

            GlobalScope.launch {

                val localOrders = getOrdersFromLocalDBAsync()
                val localOrderLines = getOrderLinesFromLocalDBAsync()

                for (order in localOrders.await()) {
                    val url = "http://192.168.1.121/pos/pos_api/public/order"

                    val params = JSONObject()
                    params.put("user_id", "${order.userID}")
                    params.put("branch_id", "${order.branchID}")
                    params.put("staff_id", "${order.staffID}")

                    val jsonRequest = JsonObjectRequest(Request.Method.POST, url, params,
                        { response ->
                            Log.i("SettingActivity", "Response: $response")
                        }, {
                            Log.i("SettingActivity", "Error: $it")
                        }
                    )

                    jsonRequest.retryPolicy = DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, 0, 1f)
                    VolleySingleton.getInstance(applicationContext).addToRequestQueue(jsonRequest)
                }

                for (orderLine in localOrderLines.await()) {
                    val url = "http://192.168.1.121/pos/pos_api/public/orderline"

                    val params = JSONObject()
                    params.put("order_id", "${orderLine.orderID}")
                    params.put("user_id", "${orderLine.userID}")
                    params.put("product_id", "${orderLine.productID}")
                    params.put("product_name", orderLine.productName)
                    params.put("quantity", "${orderLine.quantity}")
                    params.put("subtotal", "${orderLine.sTotal}")

                    val jsonRequest = JsonObjectRequest(Request.Method.POST, url, params,
                        { response ->
                            Log.i("SettingActivity", "Response: $response")
                        }, {
                            Log.i("SettingActivity", "Error: $it")
                        }
                    )

                    jsonRequest.retryPolicy = DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, 0, 1f)
                    VolleySingleton.getInstance(applicationContext).addToRequestQueue(jsonRequest)
                }
            }

            Thread.sleep(1000)
            deleteAllRecord()

            Toast.makeText(applicationContext, "Submit Order Remote Clicked", Toast.LENGTH_SHORT).show()
        }
    }

    fun uploadDailyRemoteButton() {

        val btnUploadRemote = findViewById(R.id.btn_uploadRemote) as Button

        btnUploadRemote.setOnClickListener {

            Dexter.withContext(applicationContext).withPermission(Manifest.permission.CAMERA).withListener(object: PermissionListener {

                override fun onPermissionGranted(permissionGrantedResponse: PermissionGrantedResponse) {
                    val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

                    startActivityForResult(intent, 111)

                    Thread.sleep(1000)

                    val check = true

                    if (check == true) {
                        uploadToServer()
                    }
                }

                override fun onPermissionDenied(permissionDeniedResponse: PermissionDeniedResponse) {}

                override fun onPermissionRationaleShouldBeShown(permissionRequest: com.karumi.dexter.listener.PermissionRequest?, permissionToken: PermissionToken) {
                    permissionToken.continuePermissionRequest()
                }
            }).check()
        }
    }
}