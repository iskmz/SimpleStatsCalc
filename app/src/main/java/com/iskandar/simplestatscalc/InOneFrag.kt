package com.iskandar.simplestatscalc

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.frag_in_onebyone.*
import kotlinx.android.synthetic.main.frag_main.*
import com.iskandar.simplestatscalc.MainActivity.Companion.TAG_FRAG_RES as FRAG_RES

class InOneFrag : Fragment()
{

    private lateinit var myView : View



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        myView = inflater.inflate(R.layout.frag_in_onebyone ,container,false)
        return myView
    }

    override fun onStart() {
        super.onStart()


        // for check //
        val mainAct = activity!! as MainActivity
        btnCalcOneByOne.setOnClickListener { mainAct.switchTo(FRAG_RES) }
    }
}