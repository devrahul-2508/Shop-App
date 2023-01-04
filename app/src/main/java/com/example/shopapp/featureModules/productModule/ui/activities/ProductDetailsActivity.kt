package com.example.shopapp.featureModules.productModule.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.shopapp.R
import com.example.shopapp.application.ShopApplication
import com.example.shopapp.databinding.ActivityProductDetailsBinding
import com.example.shopapp.featureModules.cartModule.models.CartProductModel
import com.example.shopapp.featureModules.cartModule.viewModels.CartViewModel
import com.example.shopapp.featureModules.productModule.di.DaggerProductComponent
import com.example.shopapp.featureModules.productModule.viewModels.ProductViewModel
import com.example.shopapp.utility.Constants

class ProductDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductDetailsBinding
    private lateinit var productViewModel: ProductViewModel
    private lateinit var cartViewModel: CartViewModel
    private var productID: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_product_details)

        productViewModel = ViewModelProvider(this)[ProductViewModel::class.java]
        cartViewModel = ViewModelProvider(this)[CartViewModel::class.java]

        DaggerProductComponent.builder().appComponent((application as ShopApplication).applicationComponent()).build().also {
            it.inject(this)
            it.inject(productViewModel)
            it.inject(cartViewModel)
        }

        productID = intent.getStringExtra(Constants.INTENT_PARAMS.PRODUCT_ID).toString()

        getProductDetails()

        binding.btnAddToCart.setOnClickListener {
            addToCart()
        }
        binding.back.setOnClickListener {
            onBackPressed()
        }
    }

    private fun getProductDetails() {
        if (productID.isNotBlank()){

            productViewModel.getProductById(productID).observe(this){
                if (it.success){
                    with(binding){
                        it.response?.let { product ->
                            productTitle.text = product.title
                            productDescription.text = product.desc
                            productSize.text = product.size
                            productPrice.text = product.price.toString()
                            productPriceCart.text = product.price.toString()
                            Glide.with(this@ProductDetailsActivity).load(product.img).into(productImage)
                        }
                    }
                }
            }

        }
    }

    private fun addToCart(){
        val cartProductModel = CartProductModel(
            productId = productID,
            quantity = 1
        )

        cartViewModel.modifyCart(cartProductModel).observe(this){
            if (it.success){
                Toast.makeText(this,"Added to cart",Toast.LENGTH_SHORT).show()

            }
            else{
                Toast.makeText(this,"Some error occured",Toast.LENGTH_SHORT).show()

            }
        }
    }
}