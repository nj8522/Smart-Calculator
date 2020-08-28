package com.nash.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nash.calculator.ui.DisplayFragment
import com.nash.calculator.ui.KeypadFragment


class MainActivity : AppCompatActivity(), KeypadFragment.DataToDisplayFragment {

    private lateinit var displayFragment : DisplayFragment



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        displayFragment = DisplayFragment()

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.display_fragment, displayFragment)
            .commit()
    }

    override fun displayTextFromKeyPad(num: String) {
        displayFragment.getDataFromKeyPadFragment(num)
    }

}