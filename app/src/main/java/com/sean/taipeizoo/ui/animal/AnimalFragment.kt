package com.sean.taipeizoo.ui.animal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.sean.taipeizoo.R
import com.sean.taipeizoo.common.toHttps
import com.sean.taipeizoo.common.trimNewLineStart
import com.sean.taipeizoo.databinding.FragmentAnimalBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnimalFragment : Fragment() {
    private var _binding: FragmentAnimalBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AnimalViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAnimalBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val noneText = getString(R.string.none)
        with(binding) {
            viewModel.animal.observe(viewLifecycleOwner) {
                nameCh.text = it.nameCh
                nameEn.text = it.nameEn
                alsoKnown.text = it.alsoKnown.ifEmpty { noneText }
                distribution.text = it.distribution.ifEmpty { noneText }
                habitat.text = it.habitat.ifEmpty { noneText }
                feature.text = it.feature
                    .ifEmpty { noneText }
                    .trimNewLineStart()
                behavior.text = it.behavior
                    .ifEmpty { noneText }
                    .trimNewLineStart()
                update.text = getString(
                    R.string.last_updated,
                    it.update.ifEmpty { getString(R.string.no_data) }
                )
                Glide.with(image)
                    .load(it.imageUrl.toHttps())
                    .into(image)
                root.visibility = View.VISIBLE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}