package com.example.shopapp.featureModules.productModule.ui.activities

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.IntegerRes
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.applex.utsavadmin.utility.utilManager.RealPathUtil
import com.example.shopapp.R
import com.example.shopapp.application.ShopApplication
import com.example.shopapp.databinding.ActivityAddProductBinding
import com.example.shopapp.featureModules.productModule.di.DaggerProductComponent
import com.example.shopapp.featureModules.productModule.viewModels.ProductViewModel
import java.io.IOException


class AddProductActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddProductBinding
    private lateinit var productViewModel: ProductViewModel
    private var imagePath = ""
    private var title: String = ""
    private var description: String = ""
    private var categories: String = ""
    private var price: String = ""
    private var colour: String = ""
    private var size: String = ""

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

        with(binding){
            back.setOnClickListener {
                onBackPressed()
            }

            btnAddToCart.setOnClickListener {
                Log.d("BAMPATH", imagePath)
                title = productTitle.text.toString()
                description = productDescription.text.toString()
                categories = productCategories.text.toString()
                price = productPrice.text.toString()
                colour =productColor.text.toString()
                size = productSize.text.toString()


                addProduct()
            }
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
                        Manifest.permission.READ_MEDIA_IMAGES
                    )
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
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {

                val data = result.data
                // do your operation from here....
                // do your operation from here....
                if (data != null
                    && data.data != null
                ) {
                    val selectedImageUri = data.data
                    imagePath = RealPathUtil.getRealPath(this, selectedImageUri!!).toString()
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

    private fun addProduct(){
        productViewModel.addProduct(title,description,categories,size,colour,price,imagePath).observe(this){
            if (it.success){
                Toast.makeText(this,"Successfully added Product",Toast.LENGTH_SHORT).show()
                finish()
            }
            else{
                Toast.makeText(this,it.message,Toast.LENGTH_SHORT).show()

                Log.d("BAM",it.message.toString())
            }
        }
    }

    companion object {
        private val IMAGE_CHOOSE = 1000;
        private val PERMISSION_CODE = 1001;
        private val REQUEST_CODE = 13
    }
}

