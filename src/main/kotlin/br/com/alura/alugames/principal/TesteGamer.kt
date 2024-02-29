import br.com.alura.alugames.modelo.Gamer

fun main() {
    val gamer1 = Gamer("etfre", "  ")
    println(gamer1)

    val gamer2 = Gamer(
        "Matheus",
        "matheus12@gmail.com",
        "12/12/1990",
        "matheus12")

    println(gamer2)

    //  Alterando o valor de um atributo utilizando o Scope Function
    gamer1.let {
        it.dataNascimento = "18/09/2004"
        it.usuario = "ryantofa"

    }.also{
        println(gamer1.idInterno)
    }
}