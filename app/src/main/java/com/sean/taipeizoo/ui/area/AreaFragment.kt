package com.sean.taipeizoo.ui.area

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.sean.taipeizoo.databinding.FragmentAreaBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AreaFragment : Fragment() {
    private var _binding: FragmentAreaBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AreaViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAreaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.uiData.observe(viewLifecycleOwner) { uiData ->
            val adapter = AreaDetailAdapter(
                uiData,
                openLink = { url ->
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
                },
                onClick = { animal ->
                    findNavController().navigate(AreaFragmentDirections.actionAreaToAnimal(animal.id, animal.nameCh))
                }
            )
            binding.container.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}