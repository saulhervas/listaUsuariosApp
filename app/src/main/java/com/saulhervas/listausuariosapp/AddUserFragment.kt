package com.saulhervas.listausuariosapp

import android.annotation.SuppressLint
import android.location.Geocoder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.saulhervas.listausuariosapp.data.model.User
import com.saulhervas.listausuariosapp.databinding.FragmentAddUserBinding
import com.saulhervas.listausuariosapp.viewmodel.UserViewModel
import java.util.Locale


class AddUserFragment : Fragment() {

    private var _binding: FragmentAddUserBinding? = null
    private val binding get() = _binding!!

    private val userViewModel: UserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSave.setOnClickListener {
            val name = binding.etName.text.toString()
            val favoriteColor = binding.etColor.text.toString()
            val birthDate = binding.etDate.text.toString()
            val favoriteCity = binding.etCity.text.toString()
            val favoriteNumber = binding.etNumber.text.toString().toIntOrNull() ?: 0
            val location = binding.etLocationText.text.toString()

            val user = User(name, favoriteColor, birthDate, favoriteCity, favoriteNumber, location)
            userViewModel.addUser(user)

            findNavController().popBackStack()
        }

        binding.btnLocation.setOnClickListener {
            val geocoder = Geocoder(requireContext(), Locale.getDefault())
            val addresses = geocoder.getFromLocationName(binding.etCity.text.toString(), 1)
            if (addresses != null) {
                if (addresses.isNotEmpty()) {
                    val address = addresses?.get(0)
                    binding.etLocationText.setText("${address?.latitude},${address?.longitude}")
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}