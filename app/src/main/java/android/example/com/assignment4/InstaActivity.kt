package android.example.com.assignment4

import android.example.com.assignment4.Fragments.UsersFragment
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_insta.*

class InstaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insta)

        users.setOnClickListener() {

            val userFragment = supportFragmentManager.findFragmentById(R.id.fragment_place) as UsersFragment
        }
    }
}
