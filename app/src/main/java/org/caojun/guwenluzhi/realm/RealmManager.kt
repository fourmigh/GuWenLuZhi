package org.caojun.guwenluzhi.realm

import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query

class RealmManager {

    companion object {
        private var realmManager: RealmManager? = null

        fun getInstance(): RealmManager {
            if (realmManager == null) {
                synchronized(RealmManager::class.java) {
                    if (realmManager == null) {
                        realmManager = RealmManager()
                    }
                }
            }
            return realmManager!!
        }
    }

    private var realm: Realm? = null

    fun init() {
        val config = RealmConfiguration.create(
            // Pass object classes for the realm schema
            schema = setOf(Book::class, Author::class)
        )
        realm = Realm.open(config)
    }

    fun getRealm(): Realm? {
        return realm
    }

    fun readAuthors(): List<Author> {
        return realm?.query<Author>()?.find()?.toList() ?: emptyList()
    }

    fun readBooks(): List<Book> {
        return realm?.query<Book>()?.find()?.toList() ?: emptyList()
    }
}