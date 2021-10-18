package com.example.postrequestapprevisited

import com.google.gson.annotations.SerializedName


class Users {

        var data: List<UsersInfo>? = null

        class UsersInfo {

            @SerializedName("name")
            var name: String? = null

            @SerializedName("location")
            var location: String? = null

            @SerializedName("pk")
            var pk: Int? = null

            constructor(name: String?, location: String?, userID: Int?) {
                this.name = name
                this.location = location
                this.pk = pk
            }
        }
    }
