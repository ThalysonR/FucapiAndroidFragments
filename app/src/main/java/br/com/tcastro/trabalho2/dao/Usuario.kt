package br.com.tcastro.trabalho2.dao

import io.realm.RealmObject

open class Usuario: RealmObject() {
    open var email: String = ""
    open var senha: String = ""
}