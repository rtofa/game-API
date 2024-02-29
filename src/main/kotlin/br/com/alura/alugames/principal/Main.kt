package br.com.alura.alugames.principal

import br.com.alura.alugames.modelo.Gamer
import br.com.alura.alugames.modelo.Jogo
import br.com.alura.alugames.servicos.ConsumoApi
import java.util.*


fun main() {
    val leitura = Scanner(System.`in`)
    val cadastroGamer = Gamer.criarGamer(leitura)
    println("Cadastro realizado com sucesso! Dados do gamer:")
    println(cadastroGamer)
    do {
        println("Digite um código de jogo para buscar:")
        val busca = leitura.nextLine()

        val buscaApi = ConsumoApi()
        val informacaoJogo = buscaApi.buscaJogo(busca)

        var meuJogo: Jogo? = null


        val resultado = runCatching {
            if (informacaoJogo == null) {
                throw NullPointerException("Título não encontrado")
            }
            meuJogo = Jogo(
                informacaoJogo.info.title,
                informacaoJogo.info.thumb
            )
        }

        resultado.onFailure {
            println("Jogo não encontrado. Tente outro ID.")
        }
        resultado.onSuccess {
            println("Deseja inserir uma descrição personalizada? (S/N)")
            val opcao = leitura.nextLine()
            if (opcao.equals("S" , true)){
                println("Insira a descrição personalizada do jogo:")
                val descricaoPersonalizada = leitura.nextLine()
                meuJogo?.descricao = descricaoPersonalizada // "?" permite que a variável seja nula
            }else{
                meuJogo?.descricao = meuJogo?.titulo
                println("")
            }
           cadastroGamer.jogosBuscados.add(meuJogo!!)
        }
        println("Deseja buscar um novo jogo? (S/N)")
        val resposta = leitura.nextLine()
    }while (resposta.equals("S", true))

    println("Jogos Buscados: ")
    println(cadastroGamer.jogosBuscados)

    println("\n Jogos ordenados por título: ")
    cadastroGamer.jogosBuscados.sortBy { it?.titulo }

    cadastroGamer.jogosBuscados.forEach {
        println("Título: " + it?.titulo)
    }

    println("Busca realizada com sucesso!")
}

