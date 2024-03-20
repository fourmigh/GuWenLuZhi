package org.caojun.guwenluzhi.realm

import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.types.RealmObject

class Book: RealmObject {

    var name = ""
    var author: Author? = null

    fun save(realm: Realm): Boolean {
        return realm.writeBlocking {
            val name = this@Book.name
            val author = this@Book.author
            val book = realm.query<Author>("name == $0 AND author == $1", name, author).first().find()
            if (book == null) {
                copyToRealm(this@Book)
                true
            } else {
                false
            }
        }
    }
}