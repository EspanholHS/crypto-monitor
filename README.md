# 📱 Crypto Monitor - Android App

Aplicativo Android desenvolvido em Kotlin com o objetivo de monitorar em tempo real a cotação da criptomoeda **Bitcoin**, utilizando requisições HTTP com Retrofit.

---

## 🗂️ Arquivos principais

###  `MainActivity.kt` (main)
Arquivo principal da aplicação e **ponto de entrada do app**.  
Responsável por:
- Configurar o layout da tela principal
- Definir a `Toolbar` com o título
- Gerenciar o botão "ATUALIZAR"
- Fazer a requisição de cotação chamando a função `makeRestCall()` com uso de corrotinas

---

###  `MercadoBitcoinService.kt` (service)
Interface Kotlin usada com Retrofit para definir o serviço de rede.  
Contém o endpoint da API pública do Mercado Bitcoin, que retorna as informações da criptomoeda.

```kotlin
@GET("BTC/ticker")
suspend fun getTicker(): Response<TicketResponse>
```

---

###  `MercadoBitcoinServiceFactory.kt` (factory)
Responsável por criar e configurar o **Retrofit**.  
Retorna uma instância da interface `MercadoBitcoinService` já pronta para uso com a base URL e conversor JSON configurados.

---

###  `TicketResponse.kt` (model extra)
Modelo de dados que representa a resposta da API com os dados da cotação.  
Usado para extrair o preço da moeda e o timestamp do momento da cotação.

---

##  Como Executar

1. Abra o projeto no Android Studio.
2. Conecte um celular físico ou utilize um emulador (API 30+).
3. Clique no botão **Run ▶️**.
4. O app será instalado e exibirá a cotação do Bitcoin.
5. Clique em **"ATUALIZAR"** para buscar o valor mais recente.

---

## ⚙️ Como o aplicativo funciona

### 1. Quando o app é iniciado

Assim que o aplicativo é aberto, a `MainActivity` é carregada.  
Nela, a barra superior (Toolbar) é personalizada com um título e as cores definidas no projeto.  
Além disso, o botão de atualização já fica preparado para reagir a interações do usuário.

### 2. Interação do usuário: clicando em “ATUALIZAR”

Ao clicar no botão, o app executa a função `makeRestCall()`.  
Essa função usa uma **coroutine** com o `Dispatchers.Main`, que permite fazer chamadas de rede sem travar a interface.

### 3. Fazendo a requisição para a API

A chamada é feita para a API pública do **Mercado Bitcoin**.  
Isso acontece por meio da `MercadoBitcoinServiceFactory`, que configura uma instância do Retrofit apontando para a URL base `https://www.mercadobitcoin.net/`.

O endpoint chamado é `api/BTC/ticker/`, definido na interface `MercadoBitcoinService`, e ele retorna um JSON com os dados da moeda.

### 4. Exibindo o resultado

Se a resposta da API for válida e bem-sucedida:
- O valor da última cotação (`last`) é extraído e convertido para o formato monetário do Brasil (R$), usando `NumberFormat`.
- A data (que vem como um timestamp Unix) é transformada para um formato de data legível (`dd/MM/yyyy HH:mm:ss`) e exibida na tela.
- Os dois valores são atualizados diretamente nas `TextViews` (`lbl_value` e `lbl_date`).

### 5. Caso ocorra algum erro

Se a API retornar um erro (como 400 ou 404), o app mostra uma **Toast** com uma mensagem correspondente ao código recebido, ajudando o usuário a entender o que aconteceu.

Além disso, se houver uma falha inesperada durante a chamada (como falta de conexão ou erro de conversão dos dados), o app também trata isso e exibe uma mensagem genérica informando o problema.




##  Evidências

### Tela inicial do app
![image](https://github.com/user-attachments/assets/714dc936-1b6e-4908-b2c9-abc573cfbbf6)

![Captura de tela 2025-04-19 114622](https://github.com/user-attachments/assets/856ec393-4952-49ed-9156-e28bf1ea4cca)


### Após atualização
![Captura de tela 2025-04-19 114807](https://github.com/user-attachments/assets/0536195b-ec00-476a-abce-76892a79d30d)

---

