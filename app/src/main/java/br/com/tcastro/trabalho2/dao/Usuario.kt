package br.com.tcastro.trabalho2.dao

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Usuario: RealmObject() {
    @PrimaryKey
    open var email: String = ""
    open var senha: String = ""
}