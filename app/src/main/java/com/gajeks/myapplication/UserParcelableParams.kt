package com.gajeks.myapplication

import android.os.Parcel
import android.os.Parcelable

class UserParcelableParams(val someText: String) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.readString()!!)

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(someText)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserParcelableParams> {
        override fun createFromParcel(parcel: Parcel): UserParcelableParams {
            return UserParcelableParams(parcel)
        }

        override fun newArray(size: Int): Array<UserParcelableParams?> {
            return arrayOfNulls(size)
        }
    }
}