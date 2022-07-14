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

/**************************************
* MAIN ACTIVITY
* This class will be the main practice
* number page.
***************************************/
class MainActivity : AppCompatActivity() {

    // Initialize other classes used
    private var g = Game()
    private var db = Database()

    // Initialize member variables (attributes)
    private lateinit var enterAnswer: EditText
    private lateinit var result: TextView
    private lateinit var topNum: TextView
    private lateinit var bottomNum: TextView
    private lateinit var operand: TextView
    private lateinit var calculate : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Get the ids for all of the elements on the screen
        calculate = findViewById<Button>(R.id.calculate)
        enterAnswer = findViewById(R.id.enterAnswer)
        topNum = findViewById(R.id.topNum)
        operand = findViewById(R.id.operand)
        bottomNum = findViewById(R.id.bottomNum)
        result = findViewById(R.id.result)

        // Listen for the "Submit" button to be clicked
        calculate.setOnClickListener { determineResult() }

        // Set each new problem based on the user selected
        // practice number and operator with a randomly selected number
        setProblem()
    }

    /**************************************
    * CALCULATE
    * This method will display the result
    * as being "Correct" or "Wrong" based
    * on the users entered answer, and
    * the correct answer from the Game class
    ***************************************/
    private fun determineResult() {
        try {
            var topNum = topNum.text.toString()
            var bottomNum = bottomNum.text.toString()
            var userAnswer = enterAnswer.text.toString()
            val userName = intent.getStringExtra("userName")

            // If the correct answer is given
            if (g.isCorrect(userAnswer.toInt())) {
                // Set the result text color to green
                result.setTextColor(Color.GREEN)
                // Display the result
                result.text = "Correct"

                // Gather the information
                val user: MutableMap<String, Any> = HashMap()
                user["name"] = userName.toString()
                user["practiceNum"] = topNum
                user["operand"] = operand.text
                user["genNum"] = bottomNum
                user["numsAlreadyUsed"] = g.getAlreadyUsedNumsLength()

                // Send it to the database to be saved
                db.addCorrectProblem(user)

                // Practice all of the numbers until all are used
                if (g.getAlreadyUsedNumsLength() < 13)
                    setProblem()
                else {
                    // Then go back and choose a new number / operand
                    val intent = Intent(this, StartNewGame::class.java)
                    startActivity(intent)
                }
            // If the wrong answer was provided
            } else {
                // Set the result text color to red
                result.setTextColor(Color.RED)

                // Display message
                result.text = "Wrong"
            }
        // If anything goes wrong
        } catch (e: Exception) {
            // Display an error to the screen
            Toast.makeText(this, "Invalid input", Toast.LENGTH_LONG).show()

            // Log the problem
            Log.e("MainActivity", e.toString())
        }
    }

    /**************************************
    * SET PROBLEM
    * This method will set the problem to
    * the provided practice number and
    * operator for first time new users or
    * to the last saved practice number and
    * operator for returning users
    ***************************************/
    fun setProblem() {

        // For returning users:
        // Get the practice number and operator
        // that was passed from OpenPage
        val userPractice = intent.getStringExtra("userPractice")
        val userOperator = intent.getStringExtra("userOperator")

        // For first time users:
        // Get the practice number and operator
        // that was passed from StartNewGame
        val userNum = intent.getStringExtra("selectNumber")
        val userOperand = intent.getStringExtra("selectOperand")
        println("USER NUM ${userNum}")
        println("USER OPERAND ${userOperand}")

        // If this is a returning user
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
        // Otherwise for first time users
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

        // Set the practice number
        topNum.text = g.getPracticeNum().toString()

        // Set the operand text to the appropriate symbol
        when (g.getOperand()) {
            "+"->operand.text = "+"
            "-"->operand.text = "—"
            "x"->operand.text = "×"
            "/"->operand.text = "÷"
        }
        // Set the randomly generated number from the Game class
        bottomNum.text = g.getGeneratedNum().toString()
    }
}
