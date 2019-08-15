package com.iskandar.simplestatscalc

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem


val legal_chars = listOf('-','0','1','2','3','4','5','6','7','8','9','.',',',' ')

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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

    private fun backToMain() {
        // TODO
        // back to Main Fragment
    }

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
}
