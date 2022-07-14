package com.example.flashcardapp

/**************************************
* DATABASE USER
* This Interface will house the callback
* function "handleResult" which will
* help while the app is checking if a user
* exists or not. This will prevent the app
* from moving forward in operation while
* this waits to be set to true / false
***************************************/
interface DatabaseUser {

    // Function prototype
    fun handleResult(result : Boolean, userResult : MutableList<String>)
}