package com.example.flashcardapp

import android.text.Editable

/**********************************************************
 * GAME
 * A class to practice flash cards
 *********************************************************/
class Game {

    // Initialize Constructor

    // Initialize Member Variables
    private var practiceNum = 0
    private var operand = ""
    private var generatedNum = 0
    private var answer = 0
    private var numsAlreadyUsed = mutableSetOf<Int>(0)
    private var currentProblem = arrayOf<String>("", "", "")

    // Initialize Method
    /**********************************************************
     * A function to set the operand type.
     *********************************************************/
    fun setOperand(o : String) {
        when (o) {
            "x" -> operand = "x"
            "/" -> operand = "/"
            "+" -> operand = "+"
            "-" -> operand = "-"
        }
    }

    /**********************************************************
     * A function to get the operand type.
     *********************************************************/
    fun getOperand() : String {
        return operand
    }

    /**********************************************************
     * A function to set the practice number. Number must be
     * greater than 0 and less than 12
     *********************************************************/
    fun setPracticeNum(num: Int) {
        if (num in 1..12)
            practiceNum = num
    }

    /**********************************************************
     * A function to get the practice number.
     *********************************************************/
    fun getPracticeNum(): Int {
        return practiceNum
    }

    /**********************************************************
     * A function to get the generated number.
     *********************************************************/
    fun getGeneratedNum(): Int {
        return generatedNum
    }

    /**********************************************************
     * A function to generate a new problem
     *********************************************************/
    fun generateProblem() {
        while (generatedNum in numsAlreadyUsed) {
            generatedNum = (1..12).random()
        }
        when (operand) {
            "x" -> answer = practiceNum * generatedNum
            "/" -> {
                if (generatedNum > practiceNum) {
                    answer = generatedNum / practiceNum
                } else {
                    answer = practiceNum / generatedNum
                }
            }

            "+" -> answer = practiceNum + generatedNum
            "-" -> {
                if (generatedNum > practiceNum) {
                    answer = generatedNum / practiceNum
                } else {
                    answer = practiceNum / generatedNum
                }
            }
        }
        numsAlreadyUsed.add(generatedNum)

        // If the randomly selected number is greater than the user-chosen
        // practice number, AND the user is working with subtraction or division,
        // make the generated number first to prevent
        // having to deal with negatives
        if ((generatedNum > practiceNum) && (operand == "-" || operand == "/")) {
            currentProblem[0] = generatedNum.toString()
            currentProblem[1] = operand
            currentProblem[2] = practiceNum.toString()
        } else { // Otherwise keep it the same
            currentProblem[0] = practiceNum.toString()
            currentProblem[1] = operand
            currentProblem[2] = generatedNum.toString()
        }
    }

    /**********************************************************
     * A function to get the current problem.
     *********************************************************/
    fun getCurrentProblem() : String {
        return "${practiceNum} ${operand} ${generatedNum}"
    }

    /**********************************************************
     * A function to return a new problem
     *********************************************************/
    fun getNewProblem() : String {
        generateProblem()
        return getCurrentProblem()
    }

    /**********************************************************
     * A function to check if an user answer is correct
     *********************************************************/
    fun isCorrect(uAnswer : Int) : Boolean {
        return uAnswer == answer
    }

    /**********************************************************
     * A function to return the correct answer
     *********************************************************/
    fun getAnswer() : String {
        return answer.toString()
    }
    /**********************************************************
     * A function to return the numbers already used
     *********************************************************/
    fun getAlreadyUsedNumsLength() : Int {
        return numsAlreadyUsed.size
    }

}

