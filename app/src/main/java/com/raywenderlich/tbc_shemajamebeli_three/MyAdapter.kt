package com.raywenderlich.tbc_shemajamebeli_three

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class MyAdapter
    (private val data: MutableList<Customer>,
     private val itemListener: ItemListener)
    :RecyclerView.Adapter<MyAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.customer_item, parent, false)

        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }



    override fun getItemCount() = data.size





    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view),View.OnClickListener{

        val nameText:TextView = view.findViewById(R.id.nameText)
        val lastNameText:TextView = view.findViewById(R.id.lastNameTx)
        val email: TextView = view.findViewById(R.id.emailTextView)
        val deleteItem: ImageView = view.findViewById(R.id.deleteImageView)
        val editItem: ImageView = view.findViewById(R.id.editImageView)




         fun bind(){

             nameText.text = data[adapterPosition].nmae
             lastNameText.text = data[adapterPosition].lastName
             email.text = data[adapterPosition].email
             deleteItem.setOnClickListener(this)
             editItem.setOnClickListener(this)

        }

        override fun onClick(v: View?) {

            if (v == deleteItem){
                itemListener.ItemOnClickListener(adapterPosition)
            }else{
                itemListener.editOnClick(adapterPosition)
            }

        }

    }

}