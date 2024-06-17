package com.saulhervas.listausuariosapp.ui.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.saulhervas.listausuariosapp.R
import com.saulhervas.listausuariosapp.data.model.User
import com.saulhervas.listausuariosapp.databinding.FragmentUserDetailBinding
import com.saulhervas.listausuariosapp.utils.DatePickerFragment
import com.saulhervas.listausuariosapp.viewmodel.UserViewModel


class UserDetailFragment : Fragment() {

    private val args by navArgs<UserDetailFragmentArgs>()
    private lateinit var binding: FragmentUserDetailBinding
    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserDetailBinding.inflate(inflater, container, false)

        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        binding.etUpdateName.setText(args.currentUser.name)
        binding.etUpdateColor.setText(args.currentUser.favoriteColor)
        binding.etUpdateDate.setText(args.currentUser.birthDate)
        binding.etUpdateCity.setText(args.currentUser.favoriteCity)
        binding.etUpdateNumber.setText(args.currentUser.favoriteNumber.toString())
        binding.etUpdateLocationText.setText(args.currentUser.location)

        binding.btnEdit.setOnClickListener {
            updateList()
        }

        binding.etUpdateDate.setOnClickListener {
            showDatePickerDialog()
        }

        binding.btnMap.setOnClickListener {
            val location = binding.etUpdateLocationText.text.toString()
            if (location.isNotEmpty()) {
                val coordinates = location.split(", ")
                if (coordinates.size == 2) {
                    try {
                        val latitude = coordinates[0].toDouble()
                        val longitude = coordinates[1].toDouble()
                        val gmmIntentUri = Uri.parse("geo:$latitude,$longitude")
                        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                        mapIntent.setPackage("com.google.android.apps.maps")
                        if (mapIntent.resolveActivity(requireActivity().packageManager) != null) {
                            startActivity(mapIntent)
                        } else {
                            Toast.makeText(
                                requireContext(),
                                "Google Maps app is not installed",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } catch (e: NumberFormatException) {
                        Toast.makeText(requireContext(), "Invalid coordinates", Toast.LENGTH_SHORT)
                            .show()
                    }
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Invalid coordinates format",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(requireContext(), "Please enter a location", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        binding.imageButton.setOnClickListener {
            findNavController().navigate(R.id.action_userDetail_to_main_fragment2)
            binding.progressBar.visibility = View.VISIBLE
        }

        return binding.root
    }

    private fun showDatePickerDialog() {
        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year) }
        datePicker.show(childFragmentManager, "datePicker")
    }

    @SuppressLint("SetTextI18n")
    fun onDateSelected(day: Int, month: Int, year: Int) {
        binding.etUpdateDate.setText("$day/$month/$year")
    }

    private fun updateList() {
        val name = binding.etUpdateName.text.toString()
        val favorite = binding.etUpdateColor.text.toString()
        val date = binding.etUpdateDate.text.toString()
        val city = binding.etUpdateCity.text.toString()
        val number = binding.etUpdateNumber.text.toString()
        val location = binding.etUpdateLocationText.text.toString()

        if (areFieldsComplete()) {

            val updateUser =
                User(args.currentUser.id, name, favorite, date, city, number.toInt(), location)
            userViewModel.updateUser(updateUser)

            Toast.makeText(requireContext(), "Usuario actualizado", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_userDetail_to_main_fragment2)
        } else {
            Toast.makeText(requireContext(), "Por favor llena todos los campos", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun areFieldsComplete(): Boolean {

        with(binding) {
            val name = etUpdateName.text.toString()
            val favorite = etUpdateColor.text.toString()
            val date = etUpdateDate.text.toString()
            val city = etUpdateCity.text.toString()
            val number = etUpdateNumber.text.toString()
            val location = etUpdateLocationText.text.toString()
            return name.isNotEmpty() && favorite.isNotEmpty() && date.isNotEmpty() && city.isNotEmpty() && number.isNotEmpty() && location.isNotEmpty()
        }
    }

}