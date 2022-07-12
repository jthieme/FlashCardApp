package com.example.flashcardapp

interface DatabaseUser {
    fun handleResult(result : Boolean, userResult : MutableList<String>)
}