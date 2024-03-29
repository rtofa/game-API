package br.com.alura.alugames.servicos

import br.com.alura.alugames.modelo.InfoJogo
import com.google.gson.Gson
import com.google.gson.JsonElement
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

class ConsumoApi { // classe que consome a API e busca o jogo pelo ID

    fun buscaJogo(id: String): InfoJogo? {
        val endereco = "https://www.cheapshark.com/api/1.0/games?id=$id"

        val client: HttpClient = HttpClient.newHttpClient()
        val request = HttpRequest.newBuilder()
            .uri(URI.create(endereco))
            .build()
        val response = client
            .send(request, HttpResponse.BodyHandlers.ofString())

        val json = response.body()

        val gson = Gson()
        val jsonElement: JsonElement = gson.fromJson(json, JsonElement::class.java)

        if (jsonElement.isJsonObject) {
            val meuInfoJogo = gson.fromJson(jsonElement, InfoJogo::class.java)
            return meuInfoJogo
        } else {
            return null
        }


    }
}