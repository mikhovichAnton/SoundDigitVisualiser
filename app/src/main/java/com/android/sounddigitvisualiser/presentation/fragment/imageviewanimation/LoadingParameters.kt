package com.android.sounddigitvisualiser.presentation.fragment.imageviewanimation

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.sounddigitvisualiser.databinding.FragmentLoadingParametersBinding
import com.android.sounddigitvisualiser.domain.adapters.RecyclerAdapterImageAnimationHolder
import com.android.sounddigitvisualiser.presentation.viewmodel.AnimationViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoadingParameters : Fragment() {

    private lateinit var binding: FragmentLoadingParametersBinding

    private val adapter = RecyclerAdapterImageAnimationHolder()

    private lateinit var contextLate: Context

    private val animationViewModel: AnimationViewModel by viewModel<AnimationViewModel>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        contextLate = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoadingParametersBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerCreation(view)
    }

    private fun recyclerCreation(view: View){
            with(binding) {
                recyclerLoadingParams.layoutManager = LinearLayoutManager(contextLate)
                recyclerLoadingParams.adapter = adapter
                recyclerLoadingParams.setItemViewCacheSize(0)
                animationViewModel.showAllAnimations()
                animationViewModel.animationImageModelInit.observe(viewLifecycleOwner, Observer { animationImage ->
                    adapter.setData(animationImage,animationViewModel,view)})
        }
    }
}