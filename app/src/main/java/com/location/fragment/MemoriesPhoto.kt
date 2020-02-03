package com.location.fragment

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MemoriesPhoto(val url: String) : Parcelable {

    companion object {
        fun getPhotos(): Array<MemoriesPhoto> {
            return arrayOf<MemoriesPhoto>(
                MemoriesPhoto("https://goo.gl/32YN2B"),
                MemoriesPhoto("https://goo.gl/Wqz4Ev"),
                MemoriesPhoto("https://goo.gl/U7XXdF"),
                MemoriesPhoto("https://goo.gl/ghVPFq"),
                MemoriesPhoto("https://goo.gl/qEaCWe"),
                MemoriesPhoto("https://goo.gl/vutGmM")
            )
        }
    }
}