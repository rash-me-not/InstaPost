package android.example.com.assignment4

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_register.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.*
import kotlin.collections.HashMap

class RegistrationActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
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

        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                Log.d("db", "createUserWithEmail:success")
                 val user = auth.currentUser
                 var user_id = user?.uid

                reference = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id.toString())

                lateinit var map : HashMap<String, String>

                map.put("id",user_id.toString())
                map.put("username",username)
                map.put("fullname",fullname)
                map.put("nickname",nickname)
                map.put("password",password)
                map.put("email",email)

                reference.setValue(map).addOnCompleteListener(this) {

                    if(task.isSuccessful) {
                        val intent = Intent(this, InstaActivity::class.java)
                        startActivity(intent)
                    }
                    else {
                        Toast.makeText(this, "You cannot be registered", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                // If sign in fails, display a message to the user.
                Log.w("db", "createUserWithEmail:failure", task.exception)
                Toast.makeText(baseContext, "Authentication failed.",
                    Toast.LENGTH_SHORT).show()

            }
        }
    }
}
