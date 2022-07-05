package com.example.flashcardapp

import Database
import android.os.Bundle
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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_best_ui)


        val calculate = findViewById<Button>(R.id.calculate)

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


            if (userAnswer == g.getAnswer()) {
                result.text = "Correct!"

                // Reset problem
                setProblem()

            } else {
                result.text = "Wrong!"
            }

        } catch (e: Exception) {
            Toast.makeText(this, "Invalid input", Toast.LENGTH_LONG).show()
            //result.text = g.getPracticeNum().toString()
        }
    }

    fun setProblem() {
        g.setPracticeNum(1).toString()
        g.setOperand("x").toString()
//        problem.text = "${g.getNewProblem()}"
        g.generateProblem()
        topNum.text = g.getPracticeNum().toString()
        operand.text = g.getOperand().toString()
        bottomNum.text = g.getGeneratedNum().toString()

        // Testing Database
        val users: MutableMap<String, Any> = HashMap()
        users["firstName"] = "TEST"
        users["lastName"] = "TEST AGAIN"
        users["desc"] = "LAST TEST"

        db.add(users)

    }
}
