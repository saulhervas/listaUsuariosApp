package com.saulhervas.listausuariosapp.ui.fragments

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.saulhervas.listausuariosapp.R
import com.saulhervas.listausuariosapp.data.model.User
import com.saulhervas.listausuariosapp.databinding.FragmentAddUserBinding
import com.saulhervas.listausuariosapp.utils.DatePickerFragment
import com.saulhervas.listausuariosapp.viewmodel.UserViewModel
import kotlin.random.Random


class AddUserFragment : Fragment() {

    private lateinit var binding: FragmentAddUserBinding
    private val userViewModel by viewModels<UserViewModel>()
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    companion object {
        private const val PERMISSION_REQUEST_LOCATION = 1
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddUserBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())

        binding.btnSave.setOnClickListener {
            insertDataToDatabase()
        }
        binding.etDate.setOnClickListener { showDatePickerDialog() }

        binding.btnLocation.setOnClickListener {
            generateRandomLocation()
            hideKeyboard()
        }

        binding.imageButton.setOnClickListener {
            findNavController().navigate(R.id.action_add_user_to_main_fragment2)
            binding.progressBar.visibility = View.GONE
        }

    }
    private fun showDatePickerDialog() {
        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year) }
        datePicker.show(childFragmentManager, "datePicker")
    }

    @SuppressLint("SetTextI18n")
    fun onDateSelected(day: Int, month: Int, year: Int) {
        binding.etDate.setText("$day/$month/$year")
    }

    private fun insertDataToDatabase() {
        val name = binding.etName.text.toString()
        val favorite = binding.etColor.text.toString()
        val date = binding.etDate.text.toString()
        val city = binding.etCity.text.toString()
        val number = binding.etNumber.text.toString()
        val location = binding.etLocationText.text.toString()

        if (areFieldsComplete()) {
            val user = User(0, name, favorite, date, city, number.toInt(), location)
            userViewModel.addUser(user)
            Toast.makeText(requireContext(), "Usuario agregado", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_add_user_to_main_fragment2)
        } else {
            Toast.makeText(requireContext(), "Por favor llena todos los campos", Toast.LENGTH_SHORT)
                .show()
        }

    }

    private fun areFieldsComplete(): Boolean {

        with(binding) {
            val name = etName.text.toString()
            val favorite = etColor.text.toString()
            val date = etDate.text.toString()
            val city = etCity.text.toString()
            val number = etNumber.text.toString()
            val location = etLocationText.text.toString()
            return name.isNotEmpty() && favorite.isNotEmpty() && date.isNotEmpty() && city.isNotEmpty() && number.isNotEmpty() && location.isNotEmpty()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_LOCATION) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                generateRandomLocation()
            } else {
                Toast.makeText(requireContext(), "Permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun generateRandomLocation() {
        val latitude = Random.nextDouble(-90.0, 90.0)
        val longitude = Random.nextDouble(-180.0, 180.0)
        binding.etLocationText.setText("$latitude, $longitude")
    }

    private fun hideKeyboard() {
        val imm =
            activity?.getSystemService(android.content.Context.INPUT_METHOD_SERVICE) as? android.view.inputmethod.InputMethodManager
        imm?.hideSoftInputFromWindow(view?.windowToken, 0)
    }

}











