package com.nash.calculator.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.nash.calculator.R
import kotlinx.android.synthetic.main.fragment_keypad.*
import java.lang.RuntimeException
import java.lang.StringBuilder
import kotlin.system.measureTimeMillis


class KeypadFragment : Fragment(), View.OnClickListener {


    private lateinit var listener: DataToDisplayFragment

    //Numbers
    private lateinit var numberOne: Button
    private lateinit var numberTwo: Button
    private lateinit var numberThree: Button
    private lateinit var numberFour: Button
    private lateinit var numberFive: Button
    private lateinit var numberSix: Button
    private lateinit var numberSeven: Button
    private lateinit var numberEight: Button
    private lateinit var numberNine: Button
    private lateinit var numberZero: Button
    private lateinit var dot : Button

    //Operators
    private lateinit var operatorAdd: Button
    private lateinit var operatorSubtract: Button
    private lateinit var operatorMultiply: Button
    private lateinit var operatorDivide: Button
    private lateinit var operatorOpenBracket: Button
    private lateinit var operatorCloseBracket: Button

    //Function
    private lateinit var equal: Button
    private lateinit var allClear: Button
    private lateinit var clear: Button


    lateinit var getNumData: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_keypad, container, false)

        //Number
        numberOne = view.findViewById(R.id.btn_one)
        numberTwo = view.findViewById(R.id.btn_two)
        numberThree = view.findViewById(R.id.btn_three)
        numberFour = view.findViewById(R.id.btn_four)
        numberFive = view.findViewById(R.id.btn_five)
        numberSix = view.findViewById(R.id.btn_six)
        numberSeven = view.findViewById(R.id.btn_seven)
        numberEight = view.findViewById(R.id.btn_eight)
        numberNine = view.findViewById(R.id.btn_nine)
        numberZero = view.findViewById(R.id.btn_zero)
        dot = view.findViewById(R.id.btn_dot)

        //Operators
        operatorAdd = view.findViewById(R.id.btn_addition)
        operatorSubtract = view.findViewById(R.id.btn_subtract)
        operatorDivide = view.findViewById(R.id.btn_divide)
        operatorMultiply = view.findViewById(R.id.btn_multiply)
        operatorOpenBracket = view.findViewById(R.id.btn_open_bracket)
        operatorCloseBracket = view.findViewById(R.id.btn_close_bracket)

        //Function
        equal = view.findViewById(R.id.btn_equal)
        clear = view.findViewById(R.id.btn_clear)
        allClear = view.findViewById(R.id.btn_all_clear)


        //SetOnClickListener Numbers
        numberOne.setOnClickListener(this)
        numberTwo.setOnClickListener(this)
        numberThree.setOnClickListener(this)
        numberFour.setOnClickListener(this)
        numberFive.setOnClickListener(this)
        numberSix.setOnClickListener(this)
        numberSeven.setOnClickListener(this)
        numberEight.setOnClickListener(this)
        numberNine.setOnClickListener(this)
        numberZero.setOnClickListener(this)
        dot.setOnClickListener(this)


        //SetOnClickListener Operators
        operatorAdd.setOnClickListener(this)
        operatorSubtract.setOnClickListener(this)
        operatorDivide.setOnClickListener(this)
        operatorMultiply.setOnClickListener(this)
        operatorDivide.setOnClickListener(this)
        operatorOpenBracket.setOnClickListener(this)
        operatorCloseBracket.setOnClickListener(this)


        //SetOnClickListener Function
        equal.setOnClickListener(this)
        clear.setOnClickListener(this)
        allClear.setOnClickListener(this)

        return view
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is DataToDisplayFragment) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + "Implement KeyPad Fragment")
        }


    }

    interface DataToDisplayFragment {
        fun displayTextFromKeyPad(num: String)
    }

    override fun onClick(v: View?) {
        when (v?.id) {

            //Numbers
            R.id.btn_one -> listener.displayTextFromKeyPad("1")
            R.id.btn_two -> listener.displayTextFromKeyPad("2")
            R.id.btn_three -> listener.displayTextFromKeyPad("3")
            R.id.btn_four -> listener.displayTextFromKeyPad("4")
            R.id.btn_five -> listener.displayTextFromKeyPad("5")
            R.id.btn_six -> listener.displayTextFromKeyPad("6")
            R.id.btn_seven -> listener.displayTextFromKeyPad("7")
            R.id.btn_eight -> listener.displayTextFromKeyPad("8")
            R.id.btn_nine -> listener.displayTextFromKeyPad("9")
            R.id.btn_zero -> listener.displayTextFromKeyPad("0")
            R.id.btn_dot -> listener.displayTextFromKeyPad(".")

            //Operators
            R.id.btn_addition -> listener.displayTextFromKeyPad("+")
            R.id.btn_subtract -> listener.displayTextFromKeyPad("-")
            R.id.btn_divide -> listener.displayTextFromKeyPad("/")
            R.id.btn_multiply -> listener.displayTextFromKeyPad("x")
            R.id.btn_open_bracket -> listener.displayTextFromKeyPad("(")
            R.id.btn_close_bracket -> listener.displayTextFromKeyPad(")")

            //Function
            R.id.btn_equal -> listener.displayTextFromKeyPad("=")
            R.id.btn_all_clear -> listener.displayTextFromKeyPad("ac")
            R.id.btn_clear -> listener.displayTextFromKeyPad("c")

        }
    }


}








