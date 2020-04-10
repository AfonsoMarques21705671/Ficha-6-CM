package com.example.acalculator

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class User(val name: String,val password: String,val email: String) : Parcelable {
    override fun toString(): String {
        return "User(name='$name', password='$password', email='$email')"
    }


}