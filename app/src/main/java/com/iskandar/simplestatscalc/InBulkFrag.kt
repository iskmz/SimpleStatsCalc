package com.iskandar.simplestatscalc

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


val legal_chars = listOf('-','0','1','2','3','4','5','6','7','8','9','.',',',' ') // to check when parsing text to num_list


class InBulkFrag : Fragment()
{

    private lateinit var myView : View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        myView = inflater.inflate(R.layout.frag_in_bulk ,container,false)
        return myView
    }
}