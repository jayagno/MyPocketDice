package com.mypocketdice

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.mypocketdice.databinding.ActivityMainBinding
import org.w3c.dom.Text
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.admanager.AdManagerAdRequest
import com.google.android.gms.ads.admanager.AdManagerAdView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var mAdManagerAdView : AdManagerAdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        MobileAds.initialize(this) {}

        /*
        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        */

        // -------------------- Code below is added.
        mAdManagerAdView = findViewById(R.id.adManagerAdView)
        val adRequest = AdManagerAdRequest.Builder().build()
        mAdManagerAdView.loadAd(adRequest)

        // -------------------- Code below is added.
        val btnResult = findViewById(R.id.btnRoll) as Button

        btnResult.setOnClickListener {
            var editTxtNumOfSides = findViewById(R.id.editTxtNumOfDiceSides) as EditText
            var intLength: Int = editTxtNumOfSides.text.toString().trim().length

            // Check null.
            if (intLength <= 0)
            {
                displayInvalidInput()
                return@setOnClickListener
            }

            // Check zero and negative numbers.
            var intTxtNumOfSides: Int = editTxtNumOfSides.text.toString().trim().toInt()
            if (intTxtNumOfSides <= 0)
            {
                displayInvalidNum(intTxtNumOfSides)
                return@setOnClickListener
            }

            // Proceed to random.
            var myRandomNum = (1..intTxtNumOfSides).random()
            displayRandomNum(myRandomNum)
        }

    }

    // -------------------- Code below is added.
    private fun displayInvalidInput() {
        val txtVwResult = findViewById(R.id.txtVwResult) as TextView
        val myTempStr: String = "Invalid. Pls enter a number!"
        Toast.makeText(this@MainActivity, myTempStr, Toast.LENGTH_SHORT).show()
        txtVwResult.text = myTempStr
    }

    private fun displayInvalidNum(myRandomNum: Int) {
        val txtVwResult = findViewById(R.id.txtVwResult) as TextView
        val myTempStr: String = "Invalid number: " + myRandomNum + "!"
        Toast.makeText(this@MainActivity, myTempStr, Toast.LENGTH_SHORT).show()
        txtVwResult.text = myTempStr
    }

    private fun displayRandomNum(myRandomNum: Int) {
        val txtVwResult = findViewById(R.id.txtVwResult) as TextView
        val myTempStr: String = "Rolled: " + myRandomNum + "!"
        Toast.makeText(this@MainActivity, myTempStr, Toast.LENGTH_SHORT).show()
        txtVwResult.text = myTempStr
    }
}