package carreiras.com.github.cryptomonitor.model

// Classe que representa a resposta principal da API
class TickerResponse(
    val ticker: Ticker // A resposta da API vem com um objeto chamado "ticker" dentro, e aqui a gente mapeia ele
)

// Classe que representa os dados de cotação retornados pela API
class Ticker(
    val high: String, // Valor mais alto do dia
    val low: String,  // Valor mais baixo do dia
    val vol: String,  // Volume negociado
    val last: String, // Último valor da cotação
    val buy: String,  // Preço de compra
    val sell: String, // Preço de venda
    val date: Long    // Timestamp da última atualização
)
