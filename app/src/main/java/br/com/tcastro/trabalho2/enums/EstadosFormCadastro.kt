package br.com.tcastro.trabalho2.enums

enum class EstadosFormCadastro(val valor: Int) {
    UsuarioVazio(0),
    SenhaVazio(1),
    ConfirmarDiferente(2),
    UsuarioJaCadastrado(3),
    Valido(4)
}