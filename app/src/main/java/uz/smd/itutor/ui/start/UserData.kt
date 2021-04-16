package uz.smd.itutor.ui.start

import java.io.Serializable

data class UserData(
    var id: Int,
    var title: String,
    var name: String,
    var desc: String,
    var fav: Int,
    var location: String
): Serializable
