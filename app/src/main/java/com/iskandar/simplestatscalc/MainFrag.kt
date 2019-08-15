package com.iskandar.simplestatscalc

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.frag_main.*
import com.iskandar.simplestatscalc.MainActivity.Companion.TAG_FRAG_IN_BULK
import com.iskandar.simplestatscalc.MainActivity.Companion.TAG_FRAG_IN_ONE

class MainFrag : Fragment()
{

    private lateinit var myView : View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        myView = inflater.inflate(R.layout.frag_main ,container,false)
        return myView
    }

    override fun onStart() {
        super.onStart()

        val mainAct = activity!! as MainActivity
        btnInBulk.setOnClickListener { mainAct.switchTo(TAG_FRAG_IN_BULK) }
        btnInOne.setOnClickListener { mainAct.switchTo(TAG_FRAG_IN_ONE) }
    }
}