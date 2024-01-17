package com.example.mymatching

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.content.Context
import android.util.DisplayMetrics
import android.view.View
import android.view.WindowManager
import com.example.mymatching.databinding.AnswerItemBinding

class AnswerItemAdapter(
    private val context: Context,
    private val answer: List<Question>,
    private val onItemClickListener: (Question,View) -> Unit
) : RecyclerView.Adapter<AnswerItemAdapter.AnswerItemViewHolder>() {

    private var unit = 1
    var questionPlaceList =-1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerItemViewHolder {
        return AnswerItemViewHolder(
            AnswerItemBinding.inflate(LayoutInflater.from(parent.context)),
            onItemClickListener
        )

        /*val binding = AnswerItemBinding.inflate(LayoutInflater.from(parent.context))
        val layoutParams = binding.root.layoutParams
        layoutParams.width = getScreenWidth(context , 0.04) * unit
        binding.root.layoutParams = layoutParams

       return AnswerItemViewHolder(binding)*/
    }

    override fun onBindViewHolder(holder: AnswerItemViewHolder, position: Int) {
        val item = answer[position]
        holder.bind(context, item)
    }


    fun getBlankPlace() = questionPlaceList

    override fun getItemCount(): Int = answer.size

    class AnswerItemViewHolder(
        private var binding: AnswerItemBinding,
        private val onItemClickListener: (Question, View) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(context: Context, answer: Question) {
            binding.cbAnswer.text = context.resources.getString(answer.stringResourceId)

            binding.cbAnswer.setOnClickListener{
                onItemClickListener.invoke(answer,binding.cbAnswer)
            }
        }
    }
}