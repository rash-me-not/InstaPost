package android.example.com.assignment4

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth
    private lateinit var dbRef : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()
        dbRef = FirebaseDatabase.getInstance().getReference("USER_TABLE")
        loginButton.setOnClickListener {
            validateLogin(email.text.toString(), password.text.toString())
        }
    }

    private fun validateLogin(email: String, password: String) {

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) {task ->
            if (task.isSuccessful) {
                var loginIntent = Intent(this, InstaActivity::class.java)
                startActivity(loginIntent)
            } else {
                Toast.makeText(this, "Please check your Email ID or Password!!",Toast.LENGTH_SHORT).show()
            }
        }

    }
}
