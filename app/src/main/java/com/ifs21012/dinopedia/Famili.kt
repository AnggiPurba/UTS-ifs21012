package com.ifs21012.dinopedia

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class Famili(
    var name: String,
    var icon: Int,
    var description: String,
    var periodehidup: String,
    var KarakteristikFisik: String,
    var HabitatdanLingkungan: String,
    var Perilaku: String,
    var Klasifikasi: String,
) : Parcelable