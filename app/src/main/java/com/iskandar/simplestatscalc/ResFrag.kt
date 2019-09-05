package com.iskandar.simplestatscalc

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.DrawableContainer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.frag_result.*
import kotlinx.android.synthetic.main.item_result.view.*

data class Result(val title:String, var value:Double)
val resTitles = mutableListOf("Sum" ,"Mean","Median","Min","Max","Range","1st Q.","3rd Q.","I.Q.R.","Variance","Standard Deviation")

class ResFrag : Fragment()
{

    private lateinit var myView : View
    private lateinit var resList: MutableList<Result>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        myView = inflater.inflate(R.layout.frag_result ,container,false)
        return myView
    }

    override fun onStart() {
        super.onStart()

        resList = MutableList(resTitles.size){ i-> Result(resTitles[i],0.0) }  // initialization
        refreshAdapter()
    }

    private fun refreshAdapter() {
        lstResults.adapter = ResultAdapter(activity!!,resList)
    }
}

class ResultAdapter(private val context: Context, private val lst:List<Result>) : BaseAdapter(){
    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val v = LayoutInflater.from(context).inflate(R.layout.item_result,null)
        if(position%2==1) v.setBackgroundColor(Color.BLACK)
        v.txtResultItemTitle.text = lst[position].title
        v.txtResultItemValue.text = lst[position].value.toString()
        return v
    }
    override fun getItem(position: Int) = lst[position]
    override fun getItemId(position: Int) = position.toLong()
    override fun getCount() = lst.size
}