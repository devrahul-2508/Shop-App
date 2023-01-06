package com.example.shopapp.featureModules.productModule.ui.activities

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.shopapp.R
import com.example.shopapp.application.ShopApplication
import com.example.shopapp.databinding.ActivityAddProductBinding
import com.example.shopapp.featureModules.productModule.di.DaggerProductComponent
import com.example.shopapp.featureModules.productModule.viewModels.ProductViewModel
import java.io.IOException


class AddProductActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddProductBinding
    private lateinit var productViewModel: ProductViewModel
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_product)

        productViewModel = ViewModelProvider(this)[ProductViewModel::class.java]

        DaggerProductComponent.builder()
            .appComponent((application as ShopApplication).applicationComponent()).build().also {
            it.inject(productViewModel)
            it.inject(this)
        }

        binding.back.setOnClickListener {
            onBackPressed()
        }

        val requestPermissionLauncher =
            registerForActivityResult(
                ActivityResultContracts.RequestPermission()
            ) { isGranted: Boolean ->
                if (isGranted) {
                   chooseImageGallery()

                } else {

                                 }
            }

        binding.containerWithDottedBorder.setOnClickListener {
            when {
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_MEDIA_IMAGES
                ) == PackageManager.PERMISSION_GRANTED -> {
                    // You can use the API that requires the permission.
    chooseImageGallery()
                }


                else -> {
                    // You can directly ask for the permission.
                    requestPermissionLauncher.launch(
                        Manifest.permission.READ_MEDIA_IMAGES)
                }
            }
        }








    }
    private fun chooseImageGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        getContent.launch(intent)
    }

    private val getContent =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) {result->
            if(result.resultCode == Activity.RESULT_OK){

                val data = result.data
                // do your operation from here....
                // do your operation from here....
                if (data != null
                    && data.data != null
                ) {
                    val selectedImageUri = data.data
                    val selectedImageBitmap: Bitmap
                    try {
                        selectedImageBitmap = MediaStore.Images.Media.getBitmap(
                            this.contentResolver,
                            selectedImageUri
                        )
                        binding.imageView.visibility = View.VISIBLE

                        binding.imageView.setImageBitmap(
                            selectedImageBitmap
                        )
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }

                }





//                binding.imageView.visibility = View.VISIBLE
//                binding.imageView.setImageURI(data?.data)
            }
        }
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == IMAGE_CHOOSE && resultCode == Activity.RESULT_OK) {
//
//        }
//    }
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        when (requestCode) {
//            PERMISSION_CODE -> {
//                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    chooseImageGallery()
//                } else {
//                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
//                }
//            }
//        }
//    }
    companion object {
        private val IMAGE_CHOOSE = 1000;
        private val PERMISSION_CODE = 1001;
        private val REQUEST_CODE = 13
    }
}

