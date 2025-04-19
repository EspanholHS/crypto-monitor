package carreiras.com.github.cryptomonitor

// Imports necessários para recursos de interface, formatação e chamadas de rede
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import carreiras.com.github.cryptomonitor.service.MercadoBitcoinServiceFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

// Classe principal que representa a tela inicial do app
class MainActivity : AppCompatActivity() {

    // Função que é executada quando a Activity é criada
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // Define o layout da tela

        // Referência da toolbar no layout e configuração dela
        val toolbarMain: Toolbar = findViewById(R.id.toolbar_main)
        configureToolbar(toolbarMain)

        // Referência do botão "Atualizar" e definição da ação ao clicar
        val btnRefresh: Button = findViewById(R.id.btn_refresh)
        btnRefresh.setOnClickListener {
            makeRestCall() // Ao clicar, chama a função que faz a requisição da cotação
        }
    }

    // Função que configura a toolbar do app (título e cor de fundo)
    private fun configureToolbar(toolbar: Toolbar) {
        setSupportActionBar(toolbar)
        toolbar.setTitleTextColor(getColor(R.color.white)) // Cor do título
        supportActionBar?.setTitle(getText(R.string.app_title)) // Define o título
        supportActionBar?.setBackgroundDrawable(getDrawable(R.color.primary)) // Cor do fundo
    }

    // Função que faz a chamada para buscar a cotação do Bitcoin
    private fun makeRestCall() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                // Criação do serviço usando o Factory
                val service = MercadoBitcoinServiceFactory().create()
                val response = service.getTicker() // Chamada à API

                if (response.isSuccessful) {
                    val tickerResponse = response.body() // Pega os dados retornados

                    // Referência aos campos de texto que serão atualizados
                    val lblValue: TextView = findViewById(R.id.lbl_value)
                    val lblDate: TextView = findViewById(R.id.lbl_date)

                    // Pega o valor da cotação e formata como moeda brasileira
                    val lastValue = tickerResponse?.ticker?.last?.toDoubleOrNull()
                    if (lastValue != null) {
                        val numberFormat = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
                        lblValue.text = numberFormat.format(lastValue)
                    }

                    // Converte o timestamp da API para uma data formatada
                    val date = tickerResponse?.ticker?.date?.let { Date(it * 1000L) }
                    val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
                    lblDate.text = sdf.format(date)

                } else {
                    // Se a chamada deu erro, mostra mensagem dependendo do código de erro
                    val errorMessage = when (response.code()) {
                        400 -> "Bad Request"
                        401 -> "Unauthorized"
                        403 -> "Forbidden"
                        404 -> "Not Found"
                        else -> "Unknown error"
                    }
                    Toast.makeText(this@MainActivity, errorMessage, Toast.LENGTH_LONG).show()
                }

            } catch (e: Exception) {
                // Se deu erro na requisição (ex: sem internet), mostra a mensagem de falha
                Toast.makeText(this@MainActivity, "Falha na chamada: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }
}
