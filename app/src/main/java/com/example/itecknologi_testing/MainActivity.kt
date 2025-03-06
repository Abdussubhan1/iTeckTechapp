package com.example.itecknologi_testing

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var deviceNumber:EditText
    private lateinit var doneButton:LinearLayout
    private lateinit var deviceDetails:TextView
    private lateinit var deviceDetailsOutput:TextView
    private lateinit var cameraIcons:LinearLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        deviceNumber=findViewById(R.id.deviceNumber)
        doneButton=findViewById(R.id.doneButton)
        deviceDetails=findViewById(R.id.textView)
        deviceDetailsOutput=findViewById(R.id.outputDeviceDetails)
        cameraIcons=findViewById(R.id.cameraIcons)








        doneButton.setOnClickListener { check(deviceNumber.text.toString()) }


    }

    private fun check(deviceNumber: String) {
    if(deviceNumber.equals("1111"))
    {
        deviceDetails.visibility=View.VISIBLE
        deviceDetailsOutput.visibility=View.VISIBLE
        deviceDetailsOutput.text="Device Type : FMB-920 \nDevice IMEI: 11122233344455666 \nInstalled Sim : Telenor \nSim Number : 0300-1234567 "}
        cameraIcons.visibility=View.VISIBLE
    }




}