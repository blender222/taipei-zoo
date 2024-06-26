package com.sean.taipeizoo.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.sean.taipeizoo.R
import com.sean.taipeizoo.common.Status
import com.sean.taipeizoo.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = AreaListAdapter { area ->
            findNavController().navigate(HomeFragmentDirections.actionHomeToArea(area.id, area.name))
        }
        with(binding) {
            viewModel.status.observe(viewLifecycleOwner) {
                if (it == Status.NetworkError) {
                    Toast.makeText(
                        context,
                        getString(R.string.network_error),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            areaList.adapter = adapter
            areaList.addItemDecoration(DividerItemDecoration(areaList.context, DividerItemDecoration.VERTICAL))
            viewModel.areaList.observe(viewLifecycleOwner) {
                adapter.areaList = it
                adapter.notifyDataSetChanged()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}