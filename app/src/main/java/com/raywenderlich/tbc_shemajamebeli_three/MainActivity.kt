package com.raywenderlich.tbc_shemajamebeli_three

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.raywenderlich.tbc_shemajamebeli_three.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private lateinit var adapter: MyAdapter

    private var customerList = mutableListOf<Customer>()

    private var currentPosition = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_main)

        makeData()
        initRecycler()

    }




    private fun initRecycler(){

        adapter = MyAdapter(customerList, object :ItemListener{
            override fun ItemOnClickListener(position: Int) {
                customerList.removeAt(position)
                adapter.notifyItemRemoved(position)

            }

            override fun editOnClick(position: Int) {

                currentPosition = position
                val intent = Intent(this@MainActivity, UserActivity::class.java)

                intent.putExtra("customer", customerList[position])


                startActivityForResult(intent, REQUEST_CODE)

            }
        })
        val recycler: RecyclerView = findViewById(R.id.recyclerviev)
        recycler.layoutManager = GridLayoutManager(this,1)
        recycler.adapter = adapter




    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK){
            val customer = data?.getParcelableExtra<Customer>("customer")

            customerList.set(currentPosition,customer!!)
            adapter.notifyItemChanged(currentPosition)
        }else{
            Toast.makeText(this,"nothing will change", Toast.LENGTH_SHORT).show()
        }


    }

    companion object{
        const val REQUEST_CODE = 0
    }


    private fun makeData(){
        customerList.add(Customer("temo", "khatiashvili","temu@gmail.com"))
        customerList.add(Customer("giorgi", "khatiashvili","temu@gmail.com"))
        customerList.add(Customer("lado", "khatiashvili","temu@gmail.com"))
        customerList.add(Customer("dato", "khatiashvili","temu@gmail.com"))
        customerList.add(Customer("zaza", "khatiashvili","temu@gmail.com"))
    }


}