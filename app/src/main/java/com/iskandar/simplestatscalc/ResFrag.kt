package com.iskandar.simplestatscalc

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.DrawableContainer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.frag_result.*
import kotlinx.android.synthetic.main.item_result.view.*
import kotlin.math.pow
import kotlin.math.sqrt
import kotlin.math.truncate


data class Result(val title:String, var value:Double)

const val MAX_SIG_FIGS = 4

val resTitles = mutableListOf(
    "Sum"   // 0
    ,"Mean", // 1
    "Median", // 2
    "Min", // 3
    "Max", // 4
    "Range", // 5
    "1st Q.", // 6
    "3rd Q.", // 7
    "I.Q.R.", // 8
    "Variance", // 9
    "Standard Deviation") // 10

class ResFrag : Fragment()
{

    private lateinit var myView : View
    private lateinit var resList: MutableList<Result>
    private lateinit var sorted_list : List<Double>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        myView = inflater.inflate(R.layout.frag_result ,container,false)
        return myView
    }

    override fun onStart() {
        super.onStart()

        sorted_list = num_list.sorted()
        btnShowOriginalList.setOnClickListener { showOriginalListDialog() }
        btnShowOrderedList.setOnClickListener { showOrderedListDialog() }
        // initialization // calculation
        resList = MutableList(resTitles.size){ i-> Result(resTitles[i],calculate(i)) }
        refreshAdapter()
    }

    @SuppressLint("PrivateResource")
    private fun showOrderedListDialog() {
        val ordered = AlertDialog.Builder(ContextThemeWrapper(activity!!,R.style.AlertDialogCustom))
            .setTitle("Ordered Number List")
            .setMessage(sorted_list.joinToString (separator = " , ",prefix="",postfix = ""))
            .setIcon(R.drawable.ic_sorted)
            .setPositiveButton("OK"){ dialog,_ -> dialog.dismiss()}
            .setNegativeButton("MIN->max") { _,_ -> }
            .setNeutralButton("MAX->min"){ _,_-> }
            .create()
        ordered.setOnShowListener { dialog ->
            val btnNeg = (dialog as AlertDialog).getButton(AlertDialog.BUTTON_NEGATIVE)
            btnNeg.setOnClickListener {
                dialog.setMessage(sorted_list.joinToString (separator = " , ",prefix="",postfix = ""))
            }
            val btnNeutral = dialog.getButton(AlertDialog.BUTTON_NEUTRAL)
            btnNeutral.setOnClickListener {
                dialog.setMessage(sorted_list.sortedDescending()
                    .joinToString(separator=" , ",prefix = "",postfix = ""))
            }
        }
        ordered.setCanceledOnTouchOutside(false)
        ordered.show()
    }

    private fun showOriginalListDialog() {
        val original = AlertDialog.Builder(activity!!)
            .setTitle("Original Number List")
            .setMessage(num_list.joinToString (separator = " , ",prefix="",postfix = ""))
            .setIcon(R.drawable.ic_original)
            .setPositiveButton("OK"){ dialog,_ -> dialog.dismiss()}
            .create()
        original.setCanceledOnTouchOutside(false)
        original.show()
    }

    private fun calculate(i: Int): Double = when (i) {
            0-> num_list.sum()   // sum
            1-> (num_list.sum()) / (num_list.size.toDouble()) // mean (arithmetic)
            2-> getMedian(sorted_list)  // median
            3-> sorted_list[0]   // min
            4-> sorted_list[sorted_list.size-1] // max
            5-> sorted_list[sorted_list.size-1]-sorted_list[0] // range = max - min
            6-> getQ1()  // lower Q.
            7-> getQ3() // upper Q.
            8-> getQ3()-getQ1() // I.Q.R.
            9-> getVariance()
            10-> getStDev()
            else->0.0
        }

    private fun getStDev() = sqrt(getVariance())

    private fun getVariance():Double {
        val mean = (num_list.sum()) / (num_list.size.toDouble())
        val sumSqDiff = num_list.map { (it - mean).pow(2) }.sum()
        return (sumSqDiff / (num_list.size-1).toDouble())
    }

    private fun getQ3() = getMedian(sorted_list.subList(sorted_list.size-(sorted_list.size/2),sorted_list.size))
    private fun getQ1() = getMedian(sorted_list.subList(0,sorted_list.size/2))

    private fun getMedian(numbers: List<Double>):Double {
        val middle = numbers.size/2
        return if(numbers.size%2==1) numbers[middle] else (numbers[middle]+numbers[middle-1]) / 2.0
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
        v.txtResultItemValue.text = String.format("%.${MAX_SIG_FIGS}g",lst[position].value)
        return v
    }
    override fun getItem(position: Int) = lst[position]
    override fun getItemId(position: Int) = position.toLong()
    override fun getCount() = lst.size
}