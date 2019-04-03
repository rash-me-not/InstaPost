package android.example.com.assignment4

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegistrationActivity : AppCompatActivity() {

    var auth:FirebaseAuth?=null
    private lateinit var reference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        registerButton.setOnClickListener {

            if(TextUtils.isEmpty(username.text) || TextUtils.isEmpty(fullname.text)
                || TextUtils.isEmpty(password.text) || TextUtils.isEmpty(nickname.text) || TextUtils.isEmpty(email.text)) {
                Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show()
            }
            else {
                register(username.text.toString(), fullname.text.toString(), nickname.text.toString(),
                    password.text.toString(), email.text.toString())
            }
        }
    }

    fun register(username:String, fullname:String, nickname: String, password: String, email:String) {

        auth?.createUserWithEmailAndPassword(email,password)?.addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                Log.d("db", "createUserWithEmail:success")
                //val user = auth?

                reference = FirebaseDatabase.getInstance().getReference().child("Users").child(username)
            } else {
                // If sign in fails, display a message to the user.
                Log.w("db", "createUserWithEmail:failure", task.exception)
                Toast.makeText(baseContext, "Authentication failed.",
                    Toast.LENGTH_SHORT).show()

            }
        }
    }
}
