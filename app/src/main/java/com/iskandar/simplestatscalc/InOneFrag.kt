package com.iskandar.simplestatscalc

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class InOneFrag : Fragment()
{

    private lateinit var myView : View



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        myView = inflater.inflate(R.layout.frag_in_onebyone ,container,false)
        return myView
    }


}