package com.nash.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nash.calculator.ui.DisplayFragment
import com.nash.calculator.ui.KeypadFragment


class MainActivity : AppCompatActivity(), KeypadFragment.DataToDisplayFragment {

     lateinit var instanceOfClass : InstanceOfClass

    lateinit var displayFragment: DisplayFragment
    lateinit var keypadFragment: KeypadFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        instanceOfClass = InstanceOfClass

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.display_fragment, instanceOfClass.displayFragment)
            .commit()
    }

    override fun displayTextFromKeyPad(num: String) {
        instanceOfClass.displayFragment.getDataFromKeyPadFragment(num)
    }

}