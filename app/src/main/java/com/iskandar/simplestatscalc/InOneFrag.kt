package com.iskandar.simplestatscalc

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.dialog_enter_num.view.*
import kotlinx.android.synthetic.main.frag_in_onebyone.*
import java.lang.StringBuilder
import com.iskandar.simplestatscalc.MainActivity.Companion.TAG_FRAG_RES as FRAG_RES
import android.view.inputmethod.InputMethodManager.SHOW_IMPLICIT
import android.content.Context.INPUT_METHOD_SERVICE
import android.graphics.Color.TRANSPARENT
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import kotlinx.android.synthetic.main.dialog_enter_num.view.txtNumberDialogValue
import kotlin.math.abs
import kotlin.math.truncate


class InOneFrag : Fragment()
{

    private lateinit var myView : View


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        myView = inflater.inflate(R.layout.frag_in_onebyone ,container,false)
        return myView
    }

    override fun onStart() {
        super.onStart()

        refreshList()
        btnEnterOneByOne.setOnClickListener { showNumInputDialog() }

        // for check //
        val mainAct = activity!! as MainActivity
        btnCalcOneByOne.setOnClickListener {
            if(num_list.isNotEmpty()) mainAct.switchTo(FRAG_RES)
            else Toast.makeText(activity!!,"Number List is EMPTY !",Toast.LENGTH_LONG).show()
        }
    }

    @SuppressLint("InflateParams")
    private fun showNumInputDialog() {
        val v = LayoutInflater.from(activity!!).inflate(R.layout.dialog_enter_num,null)

        val dialogEnterNum = Dialog(activity!!)

        dialogEnterNum.window?.decorView!!.setBackgroundColor(TRANSPARENT)

        with(dialogEnterNum){
            setContentView(v)
            setCanceledOnTouchOutside(true)
            setOnShowListener {
                val imm = activity!!.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(v.txtNumberDialogValue, SHOW_IMPLICIT)
            }
            create()
        }

        v.btnNumberDialogCancel.setOnClickListener { dialogEnterNum.dismiss() }
        v.btnNumberDialogAdd.setOnClickListener {
            if (v.txtNumberDialogValue.text.isNotBlank())
                addToNumList(v.txtNumberDialogValue.text.toString().toDouble())
            dialogEnterNum.dismiss() // close anyways
        }

        dialogEnterNum.show()
    }

    private fun addToNumList(num: Double) {
        num_list.add(num)
        refreshList()
    }

    private fun refreshList() {
        val sb = StringBuilder()
        for(n in num_list)
            sb.append(if(abs(n-truncate(n))==0.0) n.toLong() else n).append(" , ")
        txtOneByOne.text = sb.removeSuffix(", ")
    }
}