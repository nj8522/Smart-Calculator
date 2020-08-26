package com.nash.calculator.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nash.calculator.R
import kotlinx.android.synthetic.main.fragment_display.*
import java.lang.Exception


/**
 * A simple [Fragment] subclass.
 * Use the [DisplayFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DisplayFragment : Fragment(){

    private var textFromFragment : String = ""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val  view = inflater.inflate(R.layout.fragment_display, container, false)
        return view
    }

    fun getDataFromKeyPadFragment(data : String){

        when(data) {

            "ac" -> clearAllNumber()
            "c"  -> clearLastNumber()
            "="  -> findSum()
            else -> { textFromFragment += data
                      numbers.text = textFromFragment }
        }
    }

    private fun clearLastNumber() {

       if(textFromFragment.length > 1) {

           textFromFragment = textFromFragment.dropLast(1)
           numbers.text = textFromFragment

       } else {
           textFromFragment = ""
           numbers.text = "0"
       }

    }

    private fun clearAllNumber() {

        textFromFragment = ""
        result_view.text = ""
        numbers.text = "0"

    }

    private fun findSum(){

        result_view.text = textFromFragment
        try {
            val actualValue = textFromFragment
            val valueInLong = actualValue.toLong()

            if(actualValue == valueInLong.toDouble().toString()){
                textFromFragment = valueInLong.toDouble().toString()
            }
            else {
                textFromFragment = valueInLong.toString()
            }

        }
        catch (e : Exception){
            Log.e("Error", e.message.toString())
        }

    }



}