package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.math.BigInteger

class MainActivity : AppCompatActivity() {

    private var current = ""
    private var total = BigInteger("0")
    private lateinit var display: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        setupGradeCalculator()
        setupCalculator()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setupGradeCalculator() {
        val etName = findViewById<EditText>(R.id.editTextText9)
        val etChinese = findViewById<EditText>(R.id.editTextText10)
        val etEnglish = findViewById<EditText>(R.id.editTextText11)
        val etMath = findViewById<EditText>(R.id.editTextText12)
        val btnCalculate = findViewById<Button>(R.id.button)
        val tvResult = findViewById<TextView>(R.id.textView6)

        btnCalculate.setOnClickListener {
            val name = etName.text.toString()
            val chinese = etChinese.text.toString().toDouble()
            val english = etEnglish.text.toString().toDouble()
            val math = etMath.text.toString().toDouble()

            val totalScore = chinese * 0.3 + english * 0.3 + math * 0.4

            tvResult.text = name + " 總成績 = " + String.format("%.2f", totalScore)
        }
    }

    private fun setupCalculator() {
        display = findViewById(R.id.editTextNumber3)

        val btn0 = findViewById<Button>(R.id.button2)
        val btn1 = findViewById<Button>(R.id.button3)
        val btn2 = findViewById<Button>(R.id.button4)
        val btn3 = findViewById<Button>(R.id.button5)
        val btn4 = findViewById<Button>(R.id.button6)
        val btn5 = findViewById<Button>(R.id.button7)
        val btn6 = findViewById<Button>(R.id.button8)
        val btn7 = findViewById<Button>(R.id.button9)
        val btn8 = findViewById<Button>(R.id.button10)
        val btn9 = findViewById<Button>(R.id.button11)
        val btnPlus = findViewById<Button>(R.id.button12)
        val btnEqual = findViewById<Button>(R.id.button13)

        var current = ""
        var total = BigInteger("0")
        var justPressedPlus = false

        fun input(num: String) {
            if (justPressedPlus) {

                current = num
                justPressedPlus = false
            } else {
                current += num
            }
            display.setText(current)
        }

        btn0.setOnClickListener { input("0") }
        btn1.setOnClickListener { input("1") }
        btn2.setOnClickListener { input("2") }
        btn3.setOnClickListener { input("3") }
        btn4.setOnClickListener { input("4") }
        btn5.setOnClickListener { input("5") }
        btn6.setOnClickListener { input("6") }
        btn7.setOnClickListener { input("7") }
        btn8.setOnClickListener { input("8") }
        btn9.setOnClickListener { input("9") }

        // +
        btnPlus.setOnClickListener {
            if (current != "") {
                total = total.add(BigInteger(current))
                current = ""
                display.setText("0")
                justPressedPlus = true
            }
        }

        // =
        btnEqual.setOnClickListener {
            if (current != "") {
                total = total.add(BigInteger(current))
            }
            display.setText(total.toString())


            current = total.toString()
            total = BigInteger("0")
            justPressedPlus = false
        }
    }
    }

