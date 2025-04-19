package carreiras.com.github.cryptomonitor.service


import carreiras.com.github.cryptomonitor.model.TickerResponse
import retrofit2.Response
import retrofit2.http.GET

// Interface que define os endpoints da API do Mercado Bitcoin
interface MercadoBitcoinService {

    // Define o endpoint GET que busca a cotação atual do Bitcoin
    // A resposta será um objeto do tipo TickerResponse encapsulado em um Response
    @GET("api/BTC/ticker/")
    suspend fun getTicker(): Response<TickerResponse>
}
