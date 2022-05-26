package com.example.flashcardapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private var g = Game()
    private var sPN = SelectPracticeNumber()
    private lateinit var enterAnswer : EditText
    private lateinit var result : TextView
    /*private lateinit var leftHand : TextView
    private lateinit var operand : TextView
    private lateinit var rightHand : TextView*/
    private lateinit var problem : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val calculate = findViewById<Button>(R.id.calculate)

        calculate.setOnClickListener { calculate() }

        enterAnswer = findViewById(R.id.enterAnswer)
        problem = findViewById(R.id.problem)
        /*leftHand = findViewById(R.id.leftHand)
        operand = findViewById(R.id.operand)
        rightHand = findViewById(R.id.rightHand)*/
        result = findViewById(R.id.result)

        setProblem()

    }

    private fun calculate() {
        try {
            /*var lHand = leftHand.text.toString()
            var op = operand.text.toString()
            var rHand = leftHand.text.toString()*/
            var userAnswer  = enterAnswer.text.toString()
            //var problem = problem.text.toString()

            if (userAnswer == g.getAnswer()) {
                result.text = "Correct!"

                // Reset problem
                setProblem()

            } else {
                result.text = "Wrong!"
            }

        } catch (e : Exception) {
            Toast.makeText(this, "Invalid input", Toast.LENGTH_LONG).show()
            //result.text = g.getPracticeNum().toString()
        }
    }

    fun setProblem() {
        g.setPracticeNum(1).toString()
        g.setOperand("x").toString()
        problem.text = "${g.getNewProblem()}"
    }

}