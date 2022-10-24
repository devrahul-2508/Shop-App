package com.example.shopapp.featureModules.cartModule.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shopapp.R
import com.example.shopapp.databinding.ItemCartProductLayoutBinding
import com.example.shopapp.featureModules.cartModule.models.CartProductModel

class CartAdapter(private val cartProductList: List<CartProductModel>,private val context: Context): RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    private var plusListener: OnPlusClickListener?=null
    private var minusListener: OnMinusClickListener?=null

    interface OnPlusClickListener{
        fun onPlusClicked(cartProductModel: CartProductModel)
    }
    interface OnMinusClickListener{
        fun onMinusClicked(cartProductModel: CartProductModel)
    }


    fun onPlusClickListener( mListener: OnPlusClickListener){
        plusListener = mListener
    }

    fun onMinusClickListener(mListener: OnMinusClickListener){
        minusListener = mListener
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_cart_product_layout,
                parent,
                false
            ),
            plusListener!!,
            minusListener!!
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       with(holder.binding){
           val item = cartProductList[position]
            productTitle.text = item.title
            productDescription.text = item.description
            productPrice.setText("$"+item.price)
           productQuantity.text = item.quantity.toString()
           Glide.with(context).load(item.img).into(productImage)
       }
    }

    override fun getItemCount(): Int {
        return cartProductList.size
    }





    inner class ViewHolder(
        val binding: ItemCartProductLayoutBinding,
        val pListener: OnPlusClickListener,
        val mListener:OnMinusClickListener): RecyclerView.ViewHolder(binding.root){
        init {
            with(binding){
                plusSwitch.setOnClickListener{
                    val position = absoluteAdapterPosition
                    val item = cartProductList[position]
                    pListener.onPlusClicked(item)
                }
                minusSwitch.setOnClickListener {
                    val position = absoluteAdapterPosition
                    val item = cartProductList[position]
                    mListener.onMinusClicked(item)
                }
            }
        }
    }


}