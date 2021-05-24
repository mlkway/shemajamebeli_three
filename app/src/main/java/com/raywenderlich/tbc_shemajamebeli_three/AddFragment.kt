package com.raywenderlich.tbc_shemajamebeli_three

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.raywenderlich.tbc_shemajamebeli_three.databinding.FragmentAddBinding


class AddFragment : Fragment() {


    private lateinit var binding: FragmentAddBinding


    private var currentPosition = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAddBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.saveButton.setOnClickListener {

            if (checkFields()){
                setFragmentResult("requestKey", bundleOf("customer" to createCustomerObject()))
                findNavController().popBackStack()

            }else{
                Toast.makeText(context,"fill all fields", Toast.LENGTH_SHORT).show()
            }

        }

    }

    private fun checkFields():Boolean{
        return (!binding.editTextPersonEmail.text.isNullOrBlank()
                && !binding.editeTextPersonName.text.isNullOrBlank()
                && !binding.editeTextPersonSurname.text.isNullOrBlank())
    }

    private fun createCustomerObject(): Customer {
        return Customer(
            binding.editeTextPersonName.text.toString(),
            binding.editeTextPersonSurname.text.toString(),
            binding.editTextPersonEmail.text.toString()
        )
    }



}