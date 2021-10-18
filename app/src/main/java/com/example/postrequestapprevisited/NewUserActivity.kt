package com.example.postrequestapprevisited

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewUserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_user)
        val name = findViewById<View>(R.id.editTextTextPersonName) as EditText
        val location = findViewById<View>(R.id.editTextTextPersonName2) as EditText
        val savebtn = findViewById<View>(R.id.button) as Button

        savebtn.setOnClickListener {

            var f = Users.UsersInfo(name.text.toString(), location.text.toString(), 0)

            addSingleuser(f, onResult = {
                name.setText("")
                location.setText("")
                Toast.makeText(applicationContext, "Save Success!", Toast.LENGTH_SHORT).show();
            })
        }
    }

    private fun addSingleuser(f: Users.UsersInfo, onResult: () -> Unit) {

        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        val progressDialog = ProgressDialog(this@NewUserActivity)
        progressDialog.setMessage("Please wait")
        progressDialog.show()

        if (apiInterface != null) {
            apiInterface.addUser(f).enqueue(object : Callback<Users.UsersInfo> {
                override fun onResponse(
                    call: Call<Users.UsersInfo>,
                    response: Response<Users.UsersInfo>
                ) {

                    onResult()
                    progressDialog.dismiss()
                }

                override fun onFailure(call: Call<Users.UsersInfo>, t: Throwable) {
                    onResult()
                    Toast.makeText(applicationContext, "Error!", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss()

                }
            })
        }
    }

    fun addnew(view: android.view.View) {
        intent = Intent(applicationContext, NewUserActivity::class.java)
        startActivity(intent)
    }

    fun viewusers(view: android.view.View) {
        intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
    }
}