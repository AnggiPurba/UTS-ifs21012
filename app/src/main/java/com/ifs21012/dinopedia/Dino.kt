package com.ifs21012.dinopedia

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class Dino(
    var name: String,
    var icon: Int,
    var description: String,
    var Karakteristik: String,
    var Kelompok: String,
    var Habitat: String,
    var PanjangTinggiBobot: String,
    var Kelemahan: String,
    var process: String,
) : Parcelable