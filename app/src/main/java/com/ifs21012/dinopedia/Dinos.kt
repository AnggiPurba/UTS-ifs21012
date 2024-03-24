package com.ifs21012.dinopedia

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class Dinos(

    var names: String,
    var icons: Int,
    var descriptions: String,
    var KarakteristikFisik: String,
    var HabitatdanLingkungan: String,
    var Perilaku: String,
    var periodehidup: String,
    var Klasifikasi: String,
    var startIndex: Int,
    var endIndex: Int,

) : Parcelable