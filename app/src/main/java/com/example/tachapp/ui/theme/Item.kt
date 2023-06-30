package com.example.tachapp.ui.theme

import androidx.room.PrimaryKey
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmObject
import org.bson.types.ObjectId

class Item() : RealmObject() {
    @PrimaryKey
    var id: ObjectId = ObjectId()
    var isComplete: Boolean = false
    var summary: String = ""
    var owner_id: String = ""

    constructor(ownerId: String = "") : this() {
        owner_id = ownerId
    }

    val config = RealmConfiguration.Builder().build()
    val realm = Realm.getDefaultInstance()

    realm.executeTransactionAsync(Realm.Transaction { bgRealm ->
        bgRealm.copyToRealm(Item().apply {
            summary = "Do the laundry"
            isComplete = false
        })
    })
}
