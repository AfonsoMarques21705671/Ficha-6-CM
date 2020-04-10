package com.example.acalculator

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
class Operation(val expressao: String,val resultado: String) : Parcelable {
    var uuid: String = UUID.randomUUID().toString()

    override fun toString(): String {
        return "Operation(expressao='$expressao', resultado='$resultado')"
    }


}