package com.example.flashcardapp

import Database
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var g = Game()
    private var db = Database()

    private lateinit var enterAnswer: EditText
    private lateinit var result: TextView
    private lateinit var topNum: TextView
    private lateinit var bottomNum: TextView
    private lateinit var operand: TextView
    private lateinit var calculate : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        calculate = findViewById<Button>(R.id.calculate)
        calculate.setOnClickListener { calculate() }

        enterAnswer = findViewById(R.id.enterAnswer)
        topNum = findViewById(R.id.topNum)
        operand = findViewById(R.id.operand)
        bottomNum = findViewById(R.id.bottomNum)
        result = findViewById(R.id.result)

        setProblem()

    }

    private fun calculate() {
        try {
            var topNum = topNum.text.toString()
            var op = operand.text.toString()
            var bottomNum = bottomNum.text.toString()
            var userAnswer = enterAnswer.text.toString()
            val userName = intent.getStringExtra("userName")

            println("USER NAME IS: ${userName}")


            if (g.isCorrect(userAnswer.toInt())) {
                result.setTextColor(Color.GREEN)
                result.text = "Correct"

                // Testing Database
                val user: MutableMap<String, Any> = HashMap()
                user["name"] = userName.toString()
                user["practiceNum"] = topNum
                user["operand"] = operand.text
                user["genNum"] = bottomNum
                user["numsAlreadyUsed"] = g.getAlreadyUsedNumsLength()

                db.addCorrectProblem(user)

                // Reset problem

                // Practice all of the numbers until all are used
                if (g.getAlreadyUsedNumsLength() < 13)
                    setProblem()
                else {
                    // Then go back and choose a new number / operand
                    val intent = Intent(this, StartNewGame::class.java)
                    startActivity(intent)
                }

            } else {
                result.setTextColor(Color.RED)
                result.text = "Wrong"
            }

        } catch (e: Exception) {
            Toast.makeText(this, "Invalid input", Toast.LENGTH_LONG).show()
            Log.e("MainActivity", e.toString())
        }
    }

    fun setProblem() {

        val userPractice = intent.getStringExtra("userPractice")
        val userOperator = intent.getStringExtra("userOperator")
        val userGenNumber = intent.getStringExtra("userGenNumber")
//        val userPractice = intent.getStringExtra("userPractice")
//        println("SET PROBLEM - USER DATA IS: ${userData}")

        val userNum = intent.getStringExtra("selectNumber")
        val userOperand = intent.getStringExtra("selectOperand")
        println("USER NUM ${userNum}")
        println("USER OPERAND ${userOperand}")

        if (userPractice != null) {
            // Set the operand
            when (userOperator) {
                "+"-> g.setOperand("+")
                "-"-> g.setOperand("-")
                "×"-> g.setOperand("x")
                "÷"-> g.setOperand("/")
            }

            // Set the practice number
            when (userPractice) {
                "1"-> g.setPracticeNum(1)
                "2"-> g.setPracticeNum(2)
                "3"-> g.setPracticeNum(3)
                "4"-> g.setPracticeNum(4)
                "5"-> g.setPracticeNum(5)
                "6"-> g.setPracticeNum(6)
                "7"-> g.setPracticeNum(7)
                "8"-> g.setPracticeNum(8)
                "9"-> g.setPracticeNum(9)
                "10"-> g.setPracticeNum(10)
                "11"-> g.setPracticeNum(11)
                "12"-> g.setPracticeNum(12)
            }
        } else {

            // Set the operand
            when (userOperand) {
                "Add (+)" -> g.setOperand("+")
                "Subtract (-)" -> g.setOperand("-")
                "Multiply (×)" -> g.setOperand("x")
                "Divide (÷)" -> g.setOperand("/")
            }

            // Set the practice number
            when (userNum) {
                "Ones (1)" -> g.setPracticeNum(1)
                "Twos (2)" -> g.setPracticeNum(2)
                "Threes (3)" -> g.setPracticeNum(3)
                "Fours (4)" -> g.setPracticeNum(4)
                "Fives (5)" -> g.setPracticeNum(5)
                "Sixes (6)" -> g.setPracticeNum(6)
                "Sevens (7)" -> g.setPracticeNum(7)
                "Eights (8)" -> g.setPracticeNum(8)
                "Nines (9)" -> g.setPracticeNum(9)
                "Tens (10)" -> g.setPracticeNum(10)
                "Elevens (11)" -> g.setPracticeNum(11)
                "Twelves (12)" -> g.setPracticeNum(12)
            }
        }

        // Generate the problem
        g.generateProblem()

        // This will set the practice number,
        // operand, and the generated number
        // to individual text boxes
        topNum.text = g.getPracticeNum().toString()

        // Set the operand text to the appropriate symbol
        when (g.getOperand()) {
            "+"->operand.text = "+"
            "-"->operand.text = "—"
            "x"->operand.text = "×"
            "/"->operand.text = "÷"
        }
        bottomNum.text = g.getGeneratedNum().toString()




    }
}
