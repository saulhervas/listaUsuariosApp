package com.saulhervas.listausuariosapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.saulhervas.listausuariosapp.R
import com.saulhervas.listausuariosapp.databinding.FragmentMainFragmentBinding
import com.saulhervas.listausuariosapp.ui.adapter.UserAdapter
import com.saulhervas.listausuariosapp.viewmodel.UserViewModel


class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainFragmentBinding
    private lateinit var viewModel: UserViewModel
    private val adapter = UserAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainFragmentBinding.inflate(inflater, container, false)

        binding.rvMain.adapter = adapter
        binding.rvMain.setHasFixedSize(true)
        binding.rvMain.layoutManager = LinearLayoutManager(requireContext())
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        viewModel.readAllData.observe(viewLifecycleOwner, Observer { user ->
            adapter.setDataset(user)
            binding.progressBar.visibility = View.GONE
        })

        val itemTouchHelper = ItemTouchHelper(object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val user = adapter.getUserAt(position)
                AlertDialog.Builder(requireContext()).apply {
                    setTitle("Eliminar usuario")
                    setMessage("¿Deseas eliminar el usuario ${user.name}?")
                    setPositiveButton("Si") { _, _ ->
                        viewModel.deleteUser(user)
                        adapter.notifyItemRemoved(position)
                    }
                    setNegativeButton("No") { _, _ -> adapter.notifyItemChanged(position) }
                    create()
                    show()
                }
            }
        })

        itemTouchHelper.attachToRecyclerView(binding.rvMain)



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_main_fragment2_to_add_user)
            binding.progressBar.visibility = View.VISIBLE
        }
    }
}




