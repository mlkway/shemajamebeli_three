package com.raywenderlich.tbc_shemajamebeli_three

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.raywenderlich.tbc_shemajamebeli_three.databinding.FragmentEditCustomerBinding

class EditCustomerFragment : Fragment() {

    private lateinit var binding: FragmentEditCustomerBinding




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener("request"){ request, bundle ->

          val  beforeCheckcustomer = bundle.getParcelable<Customer>("editcustomer")

            binding.editTextTextPersonName.setText(beforeCheckcustomer?.name)
            binding.editTextPersonLastName.setText(beforeCheckcustomer?.lastName)
            binding.editTextPersonEmail.setText(beforeCheckcustomer?.email)


        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditCustomerBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button.setOnClickListener {

            if (checheker()){
                setFragmentResult("editedrequest", bundleOf("editedcustomer" to createCustomerObject()))
                findNavController().popBackStack()
            }else{
                Toast.makeText(context,"yvela veli shevsebuli unda iyves",Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun checheker():Boolean{

       return (!binding.editTextTextPersonName.text.isNullOrBlank()&&
                    !binding.editTextPersonLastName.text.isNullOrBlank()&&
                    !binding.editTextPersonEmail.text.isNullOrBlank())
    }


    private fun createCustomerObject(): Customer {
        return Customer(
            binding.editTextTextPersonName.text.toString(),
            binding.editTextPersonLastName.text.toString(),
            binding.editTextPersonEmail.text.toString()
        )
    }



}