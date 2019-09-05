package com.iskandar.simplestatscalc

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v7.app.AlertDialog
import android.view.Menu
import android.view.MenuItem


val num_list = mutableListOf<Double>()


class MainActivity : AppCompatActivity() {

    private lateinit var fm : FragmentManager

    private lateinit var mainFrag: MainFrag
    private lateinit var inOneFrag: InOneFrag
    private lateinit var  inBulkFrag: InBulkFrag
    private lateinit var resFrag: ResFrag

    companion object {
        const val TAG_FRAG_IN_ONE = "IN_ONE"
        const val TAG_FRAG_IN_BULK = "IN_BULK"
        const val TAG_FRAG_MAIN = "MAIN"
        const val TAG_FRAG_RES = "RES"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initFragments()
    }

    private fun initFragments() {

        mainFrag = MainFrag()
        inOneFrag = InOneFrag()
        inBulkFrag = InBulkFrag()
        resFrag = ResFrag()

        fm = supportFragmentManager
        fm.beginTransaction()
            .add(R.id.layContainer, mainFrag, TAG_FRAG_MAIN)
            .add(R.id.layContainer,inOneFrag, TAG_FRAG_IN_ONE)
            .add(R.id.layContainer, inBulkFrag, TAG_FRAG_IN_BULK)
            .add(R.id.layContainer, resFrag, TAG_FRAG_RES)
            .replace(R.id.layContainer,mainFrag)
            .addToBackStack(null)
            .commit()
    }


    fun switchTo(tag: String) {
        val frg = fm.findFragmentByTag(tag)!!
        fm.beginTransaction()
            .replace(R.id.layContainer, frg,tag)
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId){
            R.id.itmAbout-> showAboutDialog()
            R.id.itmExit-> finish()
            R.id.itmMainScreen-> backToMain()
        }
        return true
    }

    private fun backToMain() { switchTo(TAG_FRAG_MAIN) }

    private fun showAboutDialog() {
        val about = AlertDialog.Builder(this@MainActivity)
            .setIcon(R.drawable.ic_bulb)
            .setTitle("Simple Statistics")
            .setMessage("by Iskandar Mazzawi \u00A9")
            .setPositiveButton("OK"){ dialog, _ -> dialog.dismiss() }
            .create()
        about.setCanceledOnTouchOutside(false)
        about.show()
    }

    override fun onBackPressed() {
        backToMain()
    }
}
