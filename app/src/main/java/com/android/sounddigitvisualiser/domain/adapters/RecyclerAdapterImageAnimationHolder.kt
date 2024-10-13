package com.android.sounddigitvisualiser.domain.adapters
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.android.sounddigitvisualiser.databinding.AnimationDataContainerBinding
import com.android.sounddigitvisualiser.domain.model.AnimationImageModel

import com.android.sounddigitvisualiser.presentation.fragment.imageviewanimation.LoadingParametersDirections
import com.android.sounddigitvisualiser.presentation.viewmodel.AnimationViewModel


class RecyclerAdapterImageAnimationHolder :
    RecyclerView.Adapter<RecyclerAdapterImageAnimationHolder.MyHolder>() {

    private lateinit var contextLate: Context
    private lateinit var viewModel: AnimationViewModel
    private var animationParamsList = emptyList<AnimationImageModel>()
    private lateinit var view: View

     inner class MyHolder(private val animationDataContainerBinding: AnimationDataContainerBinding) :
        RecyclerView.ViewHolder(animationDataContainerBinding.root){
            fun bind(
                animationImageModel: AnimationImageModel,
                viewModel: AnimationViewModel,
                view: View,
                holder: MyHolder
            ){
                animationDataContainerBinding.parameterName.text = animationImageModel.parameterName

                animationDataContainerBinding.parameterAnimation.setOnClickListener {
                    val action = LoadingParametersDirections.
                    actionLoadingParametersToViewAnimatorFragment(animationImageModel)
                    view.findNavController().navigate(action)
                    Toast.makeText(contextLate,"Loaded successfully ${animationImageModel.parameterName}",Toast.LENGTH_SHORT).show()
                }
                animationDataContainerBinding.deleteIcon.setOnClickListener {
                    deleteRecyclerItem(holder.bindingAdapterPosition,animationImageModel)
                    val action = LoadingParametersDirections.
                    actionLoadingParametersToViewAnimatorFragment(animationImageModel)
                    view.findNavController().navigate(action)
                }
                animationDataContainerBinding.deleteAll.setOnClickListener {
                    viewModel.deleteAllAnimations()
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val animImageBind = AnimationDataContainerBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        contextLate = parent.context
        return MyHolder(animImageBind)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.bind(animationParamsList[position],viewModel,view,holder)
    }

    override fun getItemCount(): Int {
        return animationParamsList.size
    }

    fun setData(
        animationImageModel: List<AnimationImageModel>, viewModel: AnimationViewModel, view: View
    ){
        this.animationParamsList = animationImageModel
        this.viewModel = viewModel
        this.view = view
        notifyDataSetChanged()
    }
    private fun deleteRecyclerItem(itemPosition:Int,animationImageModel: AnimationImageModel){
        viewModel.deleteAnimation(animationParamsList[itemPosition])
        notifyItemRemoved(itemPosition)
        Toast.makeText(contextLate,"Successfully deleted ${animationImageModel.parameterName}",Toast.LENGTH_SHORT).show()
    }
}