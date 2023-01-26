package br.eng.pedro_mendes.quotes.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Quote(
    var author: String,
    var quote: String,
) : Parcelable
