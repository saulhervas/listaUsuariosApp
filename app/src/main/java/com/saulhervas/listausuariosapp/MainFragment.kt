package com.saulhervas.listausuariosapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.saulhervas.listausuariosapp.databinding.FragmentMainFragmentBinding


class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAdd.setOnClickListener {
            it.findNavController().navigate(R.id.action_main_fragment2_to_add_user)
        }
    }


}