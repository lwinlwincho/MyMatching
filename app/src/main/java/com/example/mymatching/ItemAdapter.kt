package com.example.mymatching

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.TypedArrayUtils.getString
import androidx.recyclerview.widget.RecyclerView
import android.content.Context
import com.example.mymatching.databinding.QuestionItemBinding

class ItemAdapter( private val context: Context,
                   private val question: List<Question>):RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            QuestionItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = question[position]
        holder.bind(context,item)
    }

    override fun getItemCount(): Int = question.size

    class ItemViewHolder(private var binding: QuestionItemBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(context: Context,question:Question){
            binding.tvQuestion.text= context.resources.getString(question.stringResourceId)
        }
    }
}