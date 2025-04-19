package carreiras.com.github.cryptomonitor.service


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Classe responsável por construir e configurar o Retrofit
class MercadoBitcoinServiceFactory {

    // Função que cria e retorna uma instância do serviço da API
    fun create(): MercadoBitcoinService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.mercadobitcoin.net/") // URL base da API do Mercado Bitcoin
            .addConverterFactory(GsonConverterFactory.create()) // Conversor de JSON para objetos Kotlin
            .build()

        // Cria uma implementação da interface MercadoBitcoinService com base no Retrofit configurado
        return retrofit.create(MercadoBitcoinService::class.java)
    }
}
