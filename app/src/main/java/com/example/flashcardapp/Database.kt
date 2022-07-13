import android.content.ContentValues.TAG
import android.util.Log
import com.example.flashcardapp.DatabaseUser
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Database {

    val db = Firebase.firestore

    fun addCorrectProblem(user : Map<String, Any>) {
        // Add a new document with a generated ID
        db.collection("users")
            .document(user["name"].toString()).set(user)
//            .addOnSuccessListener { documentReference ->
//                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
//            }
//            .addOnFailureListener { e ->
//                Log.w(TAG, "Error adding document", e)
//            }
    }

    fun userExists(userName : String, user : DatabaseUser) {
        var userData : MutableList<String> = mutableListOf()
        val collect = db.collection("users")
        val doc = collect.document(userName)

        doc.get()
            .addOnSuccessListener { document ->
                if (document != null && document.exists()) {
                    Log.d(TAG, "DocumentSnapshot data: ${document.data}")
//                    println("USER DATA BEFORE SETTING: ${userData.toList()}")

                    var pNum = document.getData()?.get("practiceNum").toString()
                    var operator = document.getData()?.get("operand").toString()
                    var gNum = document.getData()?.get("genNum").toString()
                    var alreadyUsed = document.getData()?.get("numsAlreadyUsed").toString()

                    userData.add(pNum)
                    userData.add(operator)
                    userData.add(gNum)
                    userData.add(alreadyUsed)

//                    println("USER DATA AFTER SETTING: ${userData.toList()}")
                    user.handleResult(true, userData)
                } else {
                    Log.d(TAG, "No such document")
                    user.handleResult(false, userData)
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
                user.handleResult(false, userData)
            }
    }

    fun read(userName : String) : Array<String> {

        var userData : Array<String> = emptyArray()

        db.collection("users").document(userName).get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    Log.d(TAG, "DocumentSnapshot data: ${document.data}")

                    var pNum = document.getData()?.get("practiceNum").toString()
                    var operator = document.getData()?.get("operand").toString()
                    var gNum = document.getData()?.get("genNum").toString()
                    var alreadyUsed = document.getData()?.get("alreadyUsedNums").toString()

                    userData.set(0, pNum)
                    userData.set(1, operator)
                    userData.set(2, gNum)
                    userData.set(3, alreadyUsed)

                } else {
                    Log.d(TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }
        return userData
    }

    fun addUser(userName : String) {
        var userData : MutableMap<String, Any> = mutableMapOf()
        userData["practiceNum"] = ""
        userData["operator"] = ""
        userData["genNum"] = ""
        userData["numsAlreadyUsed"] = ""
        db.collection("users").document(userName).set(userData)
    }
}