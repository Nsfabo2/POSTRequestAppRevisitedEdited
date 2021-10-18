package com.example.postrequestapprevisited

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/*
Add update and delete functionality to your POST Request app.

Your app should already have the following functionality:

Retrieving API data and displaying it in a Recycler View
Adding new users with a POST request
For this assignment, you need to add another activity that will allow users to update existing posts
with the PUT request, and delete unwanted posts with the DELETE request.
 */

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val responseText = findViewById<View>(R.id.textView) as TextView
        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        val progressDialog = ProgressDialog(this@MainActivity)
        progressDialog.setMessage("Please wait")
        progressDialog.show()

        //Get User
        if (apiInterface != null) {
            apiInterface.getUser()?.enqueue(object :Callback<List<Users.UsersInfo>> {
                override fun onResponse(
                    call: Call<List<Users.UsersInfo>>,
                    response: Response<List<Users.UsersInfo>>) {
                    progressDialog.dismiss()
                    var stringToBePritined:String? = "";
                    for(User in response.body()!!){
                        stringToBePritined = stringToBePritined +User.name+ "\n"+User.location + "\n"+"\n"
                    }
                    responseText.text= stringToBePritined
                }
                override fun onFailure(call: Call<List<Users.UsersInfo>>, t: Throwable) {
                    //  onResult(null)
                    progressDialog.dismiss()
                    Toast.makeText(applicationContext, ""+t.message, Toast.LENGTH_SHORT).show();
                }
            })
        }
    }

    fun updel(view: android.view.View) {
        intent = Intent(applicationContext, UpdateDeleteUserActivity::class.java)
        startActivity(intent)
    }

    fun addnew(view: android.view.View) {
        intent = Intent(applicationContext, NewUserActivity::class.java)
        startActivity(intent)
    }
}