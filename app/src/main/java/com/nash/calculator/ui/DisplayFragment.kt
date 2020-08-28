package com.nash.calculator.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.isDigitsOnly
import com.nash.calculator.InstanceOfClass
import com.nash.calculator.R
import kotlinx.android.synthetic.main.fragment_display.*
import kotlinx.android.synthetic.main.fragment_keypad.*
import java.lang.Exception
import java.text.DecimalFormat


/**
 * A simple [Fragment] subclass.
 * Use the [DisplayFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DisplayFragment : Fragment(){

    private var textFromFragment : String = ""
    private  var doubleValueFromString : Double = 0.0
    private val integerNumberFormat = DecimalFormat("###############")
    private val doubleNumberFormat = DecimalFormat("########.#######")
    private var resultView : String = ""
    private lateinit var instanceOfClass : InstanceOfClass


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val  view = inflater.inflate(R.layout.fragment_display, container, false)
        instanceOfClass = InstanceOfClass
        return view
    }

    fun getDataFromKeyPadFragment(data : String){

        when(data) {

            "ac" -> clearAllNumber()
            "c"  -> clearLastNumber()
            "="  -> findSum()

            else -> {

                if(textFromFragment.isEmpty() && (data == "x" || data == "/")) {
                    textFromFragment += "0$data"
                }  else {
                    textFromFragment += data
                }

                numbers.text = textFromFragment

            }
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

        if(textFromFragment.isNotEmpty() && !textFromFragment.equals("Division by Zero")) {

            resultView = textFromFragment
            result_view.text = textFromFragment

            try {

                val actualValue = instanceOfClass.expressionProcessor.inputProcessor(textFromFragment)
                val isStringOrNumber = checkIfStringOrNumber(actualValue)
                var resultText : String = ""
                if(isStringOrNumber){
                    doubleValueFromString = actualValue.toDouble()

                    if((doubleValueFromString % 1).equals(0)){
                        resultText = integerNumberFormat.format(doubleValueFromString.toInt())
                        numbers.text = resultText
                        textFromFragment = resultText
                    } else {
                        resultText = doubleNumberFormat.format(doubleValueFromString)
                        numbers.text = resultText
                        textFromFragment = resultText
                    }

                } else {
                    numbers.text = actualValue
                    textFromFragment = actualValue
                }
            }
            catch (e : NumberFormatException){
                Log.e("Error", e.message.toString())
            }
        }
    }


    private fun checkIfStringOrNumber(expressionValue : String) : Boolean{

         try{
             val isNumberOrNot = expressionValue.toDouble()

         } catch (e : java.lang.NumberFormatException) {
             return false
         }
        return true
    }



}