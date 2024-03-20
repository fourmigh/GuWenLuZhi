package org.caojun.guwenluzhi.realm

import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.types.RealmObject
import org.caojun.guwenluzhi.enums.ChinaDynasty

class Author: RealmObject {

    var name = ""
    var dynasty = ChinaDynasty.NA.name

    constructor()
    constructor(name: String, dynasty: ChinaDynasty) {
        this.name = name
        this.dynasty = dynasty.name
    }

    fun getDynastyEnum(): ChinaDynasty {
        val values = ChinaDynasty.values()
        for (cd in values) {
            if (cd.name == dynasty) {
                return cd
            }
        }
        return ChinaDynasty.NA
    }

    fun getInfo(): String {
        val dynasty = getDynastyEnum()
        return "【${dynasty.chinese}】 $name"
    }

    fun save(realm: Realm?): Boolean {
        return realm?.writeBlocking {
            val name = this@Author.name
            val dynasty = this@Author.dynasty
            val author = realm.query<Author>("name == $0 AND dynasty == $1", name, dynasty).first().find()
            if (author == null) {
                copyToRealm(this@Author)
                true
            } else {
                false
            }
        } ?: false
    }

    fun delete(realm: Realm?): Boolean {
        return realm?.writeBlocking {
            findLatest(this@Author)?.also {
                delete(it)
            }
            true
        } ?: false
    }
}