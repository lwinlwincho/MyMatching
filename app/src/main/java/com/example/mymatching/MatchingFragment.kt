package com.example.mymatching

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymatching.databinding.FragmentMatchingBinding
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.google.android.material.internal.ViewUtils.dpToPx


class MatchingFragment : Fragment() {

    private var _binding: FragmentMatchingBinding? = null
    private val binding get() = _binding!!

    private lateinit var answerItemAdapter : AnswerItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMatchingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val myQuestion = Datasource().loadQuestion()
        val itemAdapter = ItemAdapter(requireContext(), myQuestion)
        binding.rvQuestion.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = itemAdapter
        }

        val myAnswer = Datasource().loadAnswer()
        answerItemAdapter = AnswerItemAdapter(requireContext(), myAnswer,{question,view->
            animateWithBlank(view , true)

        } )

        /* binding.rvAnswer.apply {
             layoutManager =
                 GridLayoutManager(requireContext(), 3)
             adapter = answerItemAdapter
         }*/

        binding.rvAnswer.adapter = answerItemAdapter
        var layoutManager = FlexboxLayoutManager(context)
        layoutManager.flexDirection = FlexDirection.ROW
        layoutManager.justifyContent = JustifyContent.CENTER
        binding.rvAnswer.layoutManager = layoutManager
    }

    fun animateWithBlank(view : View , backward : Boolean) {
        if (answerItemAdapter.getBlankPlace() == -1) {
            Toast.makeText(requireContext(),"No Blank View To Animate! Check the response",Toast.LENGTH_LONG)
        } else {
            val location = ArrayList<Int>()
            Toast.makeText(requireContext(),"BLANK--------",Toast.LENGTH_LONG)

            getCenteredPositionofBlank(answerItemAdapter.getBlankPlace() , location)
            Toast.makeText(requireContext(),"animateWithBlank() location : $location",Toast.LENGTH_LONG)
            moveTo(view , binding.rvQuestion , location[0].toFloat() , location[1].toFloat() , backward)
        }
    }

    fun getCenteredPositionofBlank(index : Int , location : ArrayList<Int>) : ArrayList<Int> {
        location.clear()
        location.addAll(getCenteredLocation(binding.rvQuestion.getChildAt(index) , binding.rvQuestion).toList())
        Toast.makeText(requireContext()," blank view final location : $location",Toast.LENGTH_LONG)
        return location
    }

    fun getCenteredLocation(child : View , parent : View) : IntArray {
        val childLocation = IntArray(2)
        val parentLocation = IntArray(2)
        val finalLocation = IntArray(2)
        child.getLocationOnScreen(childLocation)
        parent.getLocationOnScreen(parentLocation)
        finalLocation[0] = childLocation[0] + parentLocation[0] + child.width / 2
        finalLocation[1] = childLocation[1] + parentLocation[1] + child.height / 2
        return finalLocation
    }

    fun moveTo(child : View , parent : View , toX : Float , toY : Float , backward : Boolean) {
       // showLogD("BLANK" , "current selected view in moveTo : ${(child as TextView).text}")
        val currentLocation = getCenteredLocation(child.parent as View , parent)

        val newX = if (backward) 0.0f else toX - (currentLocation[0])
        val newY =
            if (backward) dpToPx(12f , requireContext()).toFloat() else toY - (currentLocation[1])

       // showLogD("BLANK" , "initial [ toX , toY ] : [${toX} ,${toY}]")
      //  showLogD("BLANK" , "current choice view location : ${currentLocation.toIntArrayString()}")
      //  showLogD("BLANK" , "moving to new location : [${newX} ,${newY}]")
       // showLogD("BLANK" , "\n\n\n\n-----------------------------------------------------")
        child.animate().x(newX).y(newY).setDuration(400L).withEndAction {
            if (newX == 0.0f && newY == 0.0f) {
                //child.background =
                 //   ContextCompat.getDrawable(parentActivity , R.drawable.bg_quiz_choice_foreground_transparent)
                //child.setTextColor(ContextCompat.getColor(parentActivity , R.color.white))
            } else {
               // child.background =
                 //   ContextCompat.getDrawable(parentActivity , R.drawable.bg_quiz_choice_foreground_white)
                //child.setTextColor(ContextCompat.getColor(parentActivity , R.color.textColorBlack))
              //  parentActivity.vibrate(400L)
            }

        }
    }

}

