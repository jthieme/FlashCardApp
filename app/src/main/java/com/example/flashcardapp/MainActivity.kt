package com.example.flashcardapp

import Database
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity() {

    private var g = Game()
    private var db = Database()

    private lateinit var enterAnswer: EditText
    private lateinit var result: TextView
    private lateinit var topNum: TextView
    private lateinit var bottomNum: TextView
    private lateinit var operand: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val calculate = findViewById<Button>(R.id.calculate)

        calculate.setOnClickListener { calculate() }

        enterAnswer = findViewById(R.id.enterAnswer)
        topNum = findViewById(R.id.topNum)
        operand = findViewById(R.id.operand)
        bottomNum = findViewById(R.id.bottomNum)
        result = findViewById(R.id.result)

//        val userOperand = intent.getStringExtra("selectOperand")
//        println("USER OPERAND IS THIS: " + userOperand.toString())

        // Practice all of the numbers until all are used
        if (g.getAlreadyUsedNums().length <= 13)
//            userOperand?.let { setProblem(it) }
            setProblem()
        else {
            // Disable the Calculate button
            calculate?.isEnabled = false
            calculate?.setTextColor(Color.WHITE)
            calculate?.setBackgroundColor(Color.LTGRAY)

            // Then go back and choose a new number / operand
            val intent = Intent(this, Number::class.java)
            startActivity(intent)
        }


    }

    private fun calculate() {
        try {
            var topNum = topNum.text.toString()
            var op = operand.text.toString()
            var bottomNum = bottomNum.text.toString()
            var userAnswer = enterAnswer.text.toString()


            if (userAnswer == g.getAnswer()) {
                result.setTextColor(Color.GREEN)
                result.text = "Correct"
                // Testing Database
                val users: MutableMap<String, Any> = HashMap()
                users["practiceNum"] = topNum
                users["operand"] = operand.text
                users["genNum"] = bottomNum
                users["numsAlreadyUsed"] = g.getAlreadyUsedNums()

                db.add(users)

                // Reset problem
                setProblem()

            } else {
                result.setTextColor(Color.RED)
                result.text = "Wrong"
            }

        } catch (e: Exception) {
            Toast.makeText(this, "Invalid input", Toast.LENGTH_LONG).show()
            //result.text = g.getPracticeNum().toString()
        }
    }

    fun setProblem() {
        // Set the practice number
        g.setPracticeNum(1).toString()

        // Set the operand
        g.setOperand("x").toString()

        // Generate the problem
        g.generateProblem()

        // This will set the practice number,
        // operand, and the generated number
        // to individual text boxes
        topNum.text = g.getPracticeNum().toString()
        operand.text = g.getOperand()
        bottomNum.text = g.getGeneratedNum().toString()
//        result.text = "answer"



    }
}
