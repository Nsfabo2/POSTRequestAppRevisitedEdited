package com.example.postrequestapprevisited

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateDeleteUserActivity : AppCompatActivity() {

    lateinit var id_et: EditText
    lateinit var name_et: EditText
    lateinit var loc_et: EditText
    lateinit var update_button: Button
    lateinit var delete_button: Button

    var userID: Int =0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_delete_user)
        id_et = findViewById(R.id.id_editText)
        name_et = findViewById(R.id.name_editText)
        loc_et = findViewById(R.id.location_editText)

        if(id_et.text.isNotEmpty()){
            userID = id_et.text.toString()?.toInt()!!
        }


        update_button = findViewById(R.id.update_button)
        delete_button = findViewById(R.id.delete_button5)

        update_button.setOnClickListener{

            var f = Users.UsersInfo(name_et.text.toString(), loc_et.text.toString(), userID)

            updateUser(f, onResult = {
                id_et.setText("")
                name_et.setText("")
                loc_et.setText("")
                Toast.makeText(applicationContext, "Updated Successfuly!", Toast.LENGTH_SHORT).show();
            })
        }

        delete_button.setOnClickListener {
            var f = Users.UsersInfo(name_et.text.toString(), loc_et.text.toString(), userID)
            deleteUser(f, onResult = {
                id_et.setText("")
                name_et.setText("")
                loc_et.setText("")
                Toast.makeText(applicationContext, "Deleted Successfuly!", Toast.LENGTH_SHORT).show();
            })
        }

    }

    fun deleteUser(f: Users.UsersInfo, onResult: () -> Unit){
        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        apiInterface?.deleteUser(userID)?.enqueue(object :
            retrofit2.Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                onResult()
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                onResult()
                Toast.makeText(applicationContext, "Data are not deleted!", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun updateUser(f: Users.UsersInfo, onResult: () -> Unit){

        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        apiInterface?.updateUser(userID, Users.UsersInfo(
            name_et.text.toString(),
            loc_et.text.toString(),
            userID
        ))?.enqueue(object : retrofit2.Callback<Users.UsersInfo> {
            override fun onResponse(
                call: Call<Users.UsersInfo>,
                response: Response<Users.UsersInfo>
            ) {
                onResult()
            }

            override fun onFailure(call: Call<Users.UsersInfo>, t: Throwable) {
                onResult()
                Toast.makeText(applicationContext, "Data are not updated!", Toast.LENGTH_SHORT).show()
            }
        })
    }
    fun updel(view: android.view.View) {
        intent = Intent(applicationContext, UpdateDeleteUserActivity::class.java)
        startActivity(intent)
    }
    fun viewusers(view: android.view.View) {
        intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
    }
}