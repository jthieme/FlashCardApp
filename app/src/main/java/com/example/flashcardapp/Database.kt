import android.content.ContentValues.TAG
import android.util.Log
import com.example.flashcardapp.DatabaseUser
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

/**************************************
* DATABASE
* This class will not only connect to
* the database, it will also allow for
* reading and writing of data
* (user and problem)
***************************************/
class Database {

    // Initialize the database connection
    val db = Firebase.firestore

    /****************************************
    * ADD CORRECT PROBLEM
    * This method adds the current correct
    * problem to the user's document in Firebase
    *****************************************/
    fun addCorrectProblem(user : Map<String, Any>) {
        // Add a new document with a generated ID
        db.collection("users")
            .document(user["name"].toString()).set(user)
    }

    /****************************************
     * USER EXISTS
     * This method will check and return
     * true / false if the username entered
     * exists in the database
     *****************************************/
    fun userExists(userName : String, user : DatabaseUser) {
        // Initialize list to hold user data
        var userData : MutableList<String> = mutableListOf()

        // Check to see if the user exists
        db.collection("users").document(userName).get()
            .addOnSuccessListener { document ->

                // If the user does exist and their document is not empty
                if (document != null && document.exists()) {

                    // Get all of the relevant information
                    var pNum = document.getData()?.get("practiceNum").toString()
                    var operator = document.getData()?.get("operand").toString()
                    var gNum = document.getData()?.get("genNum").toString()
                    var alreadyUsed = document.getData()?.get("numsAlreadyUsed").toString()

                    // Save it to the list
                    userData.add(pNum)
                    userData.add(operator)
                    userData.add(gNum)
                    userData.add(alreadyUsed)

                    // Return true (user exists)
                    user.handleResult(true, userData)
                // Otherwise
                } else {
                    // Return false (user does not exist)
                    user.handleResult(false, userData)
                }
            } // If something went wrong
            .addOnFailureListener { exception ->
                // Throw and exception
                Log.d(TAG, "get failed with ", exception)

                // Return false
                user.handleResult(false, userData)
            }
    }

}