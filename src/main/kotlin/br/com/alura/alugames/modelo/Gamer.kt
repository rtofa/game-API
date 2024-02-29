package br.com.alura.alugames.modelo

import java.lang.IllegalArgumentException
import java.util.*

data class Gamer(var nome: String, var email: String){
    var dataNascimento: String? = null
    var usuario: String? = null
        set(value) {
            field = value
            if(idInterno.isNullOrBlank())
                criarIdInterno()
        }
    var idInterno: String? = null
        private set

    val jogosBuscados = mutableListOf<Jogo>()

    // Tudo que houver a necessidade de receber um valor deve ter como utilização o var
    constructor(nome: String, email: String, dataNascimento: String, usuario: String) :  this(nome, email) {
        this.dataNascimento = dataNascimento
        this.usuario = usuario
        criarIdInterno()
    }


//    init {
//        this.email = validarEmail()
//        this.nome = validarNome()
//    }
//

    override fun toString(): String {
        return "Gamer(nome='$nome', email='$email', dataNascimento=$dataNascimento, usuario=$usuario, idInterno=$idInterno)"
    }


    fun criarIdInterno() {
       val id = kotlin.random.Random.nextInt(10000)
        val tag =  String.format("%04d", id)

            idInterno = "$usuario#$tag"
    }

    fun validarEmail(): String {
        val regex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")
        runCatching {
            require(email.matches(regex))
        }.onFailure {
            throw IllegalArgumentException("Email inválido")
        }
        return email
    }
    fun validarNome(): String {
        runCatching {
            if (nome.isBlank())
                throw IllegalArgumentException("Nome inválido")
            else
            require(nome.length > 3)
        }.onFailure {
            throw IllegalArgumentException("Digite um nome válido.")
        }
        return nome
    }

    companion object { // serve para criar métodos estáticos
        fun criarGamer(leitura: Scanner): Gamer {
            println("Bem vindos ao cadastro de gamers!")
            println("Digite o nome do gamer:")
            val nome = leitura.nextLine()
            println("Digite seu email:")
            val email = leitura.nextLine()
            println("Deseja completar seu cadastro de usuário e data de nascimento?")
            val opcao = leitura.nextLine()

            if (opcao.equals("S", true)) {
                println("Digite sua data de nascimento(DD/MM/AAAA):")
                val nascimento = leitura.nextLine()
                println("Digite seu nome de usuário:")
                val usuario = leitura.nextLine()
                return Gamer(nome, email, nascimento, usuario)
            }else{
                return Gamer(nome, email)
            }
        }
    }
}
