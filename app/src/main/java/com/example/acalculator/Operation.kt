package com.example.acalculator

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Operation(val expressao: String,val resultado: String) : Parcelable {
    override fun toString(): String {
        return "Operation(expressao='$expressao', resultado='$resultado')"
    }


}