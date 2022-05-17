package com.example.flashcardapp
class Game {

    // Initialize Member Variables
    private var practiceNum = 0
    private val operand = arrayOf<String>("+","-","*","/")
    private var generatedNum = 0
    private var answer = 0
    private var numsAlreadyUsed : Set<Int> = (0..0).toSet()
    private var currentProblem = arrayOf<String>("")

    // Initialize Methods
    fun setOperand() {}
    fun setPractice() {}
    fun generateProblem() {}
    fun getCurrentProblem() {}
    fun getNewProblem() {}
    fun isCorrect() {}
    fun getAnswer() {}


}

