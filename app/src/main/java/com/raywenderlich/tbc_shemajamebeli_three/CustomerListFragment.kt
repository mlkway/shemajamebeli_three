package com.raywenderlich.tbc_shemajamebeli_three

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.raywenderlich.tbc_shemajamebeli_three.databinding.FragmentCustomerListBinding


class CustomerListFragment : Fragment() {



    private lateinit var binding: FragmentCustomerListBinding

    private var myCustomerList = mutableListOf<Customer>()



    private lateinit var adapter: MyAdapter

    private var currentPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener("requestKey"){ requestKey, bundle ->
            val customer = bundle.getParcelable<Customer>("customer")
            myCustomerList.add(customer!!)

        }

        setFragmentResultListener("editedrequest"){ editedrequest, bundle ->

            val editedCustomer = bundle.getParcelable<Customer>("editedcustomer")
            myCustomerList[currentPosition] = editedCustomer!!

        }

    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCustomerListBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        binding.floatingActionButton.setOnClickListener {
            addCustomer()
        }

    }

    private fun addCustomer(){
        findNavController().navigate(R.id.action_customerListFragment_to_addFragment)
    }




    private fun initRecycler(){

        adapter = MyAdapter(myCustomerList, object :ItemListener{
            override fun ItemOnClickListener(position: Int) {
                myCustomerList.removeAt(position)
                adapter.notifyItemRemoved(position)

            }
            override fun editOnClick(position: Int) {

                currentPosition = position

                setFragmentResult("request", bundleOf("editcustomer" to myCustomerList[position]))
                findNavController().navigate(R.id.action_customerListFragment_to_editCustomerFragment)


            }
        })
        val recycler: RecyclerView = binding.recyclerviev
        recycler.layoutManager = GridLayoutManager(context,1)
        recycler.adapter = adapter



    }







}