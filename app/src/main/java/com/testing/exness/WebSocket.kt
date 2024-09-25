package com.testing.exness

import android.annotation.SuppressLint
import okhttp3.*
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.json.JSONObject


@SuppressLint("DefaultLocale")
@Composable
fun AssetCard(
    assetName: String,
    assetDescription: String,
    assetPrice: String,
    priceChange: Double
) {

    val changeColor = if (priceChange < 0.0) Color.Red else Color.Blue

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        contentColor = Color.White
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Row(
                    modifier = Modifier
                        .width(330.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(modifier = Modifier.width(2.dp))

                    // Asset Name and symbol display
                    Row(
                        modifier = Modifier
                            .width(120.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = assetName,
                            color = Color.Black,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.width(4.dp))

                        // Simple vertical bars (placeholder for future icon or visual elements)
                        Box(
                            modifier = Modifier
                                .width(4.dp)
                                .height(14.dp)
                                .background(Color.Red)
                        )
                        Spacer(modifier = Modifier.width(1.dp))
                        Box(
                            modifier = Modifier
                                .width(4.dp)
                                .height(14.dp)
                                .background(Color.Red)
                        )
                        Spacer(modifier = Modifier.width(1.dp))
                        Box(
                            modifier = Modifier
                                .width(4.dp)
                                .height(14.dp)
                                .background(Color.Red)
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))
                }
                // Asset Description
                Text(
                    text = assetDescription,
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            }
            // Column for asset price and price change
            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = assetPrice,
                    color = Color.Black,
                    style = MaterialTheme.typography.body1
                )
                Text(
                    text = "${String.format("%.2f", priceChange)}%",
                    color = changeColor,
                    style = MaterialTheme.typography.body1
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(2.dp))
}

@SuppressLint("DefaultLocale")
@Composable
fun BinanceWebSocket(viewModel: CryptoViewModel = viewModel()) {

    var btcPrice by remember { mutableStateOf("") }
    var ethPrice by remember { mutableStateOf("") }
    var solPrice by remember { mutableStateOf("") }
    var xrpPrice by remember { mutableStateOf("") }
    var trxPrice by remember { mutableStateOf("") }
    var adaPrice by remember { mutableStateOf("") }

    // Collect the percentage change data from the ViewModel
    val cryptoPrices by viewModel.cryptoPrices.collectAsState()

    val client = OkHttpClient()

    val listener = object : WebSocketListener() {
        override fun onMessage(webSocket: WebSocket, text: String) {
            // Handle the incoming message

            val jsonObject = JSONObject(text)
            val streamData = jsonObject.getJSONObject("data")
            val symbol = streamData.getString("s")
            val price = streamData.getString("p")

            // Update the state based on the symbol
            when (symbol) {
                "BTCUSDT" -> btcPrice = price
                "ETHUSDT" -> ethPrice = price
                "SOLUSDT" -> solPrice = price
                "XRPUSDT" -> xrpPrice = price
                "TRXUSDT" -> trxPrice = price
                "ADAUSDT" -> adaPrice = price
            }
        }

        override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
            t.printStackTrace()
        }
    }


    DisposableEffect(Unit) {
        val request = Request.Builder()
            .url("wss://stream.binance.com:9443/stream?streams=btcusdt@trade/ethusdt@trade/solusdt@trade/xrpusdt@trade/dogeusdt@trade/adausdt@trade/trxusdt@trade")
            .build()

        val webSocket = client.newWebSocket(request, listener)

        // Dispose WebSocket when the composable leaves the composition
        onDispose {
            webSocket.close(1000, "Closing WebSocket")
        }
    }

    if (cryptoPrices != null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
//            verticalArrangement = Arrangement.SpaceBetween
        ) {
            AssetCard(
                assetName = "BTC/USD",
                assetDescription = "Bitcoin",
                assetPrice = "${btcPrice.toDoubleOrNull() ?: 0.0}",
                priceChange = cryptoPrices!!["BTC"]?.quote?.usd?.percentChange24h ?: 0.0
            )
            AssetCard(
                assetName = "ETH/USD",
                assetDescription = "Ethereum",
                assetPrice = "${ethPrice.toDoubleOrNull() ?: 0.0}",
                priceChange = cryptoPrices!!["ETH"]?.quote?.usd?.percentChange24h ?: 0.0
            )
            AssetCard(
                assetName = "SOL/USD",
                assetDescription = "Solana",
                assetPrice = "${solPrice.toDoubleOrNull() ?: 0.0}",
                priceChange = cryptoPrices!!["SOL"]?.quote?.usd?.percentChange24h ?: 0.0
            )
            AssetCard(
                assetName = "XRP/USD",
                assetDescription = "XRP",
                assetPrice = "${xrpPrice.toDoubleOrNull() ?: 0.0}",
                priceChange = cryptoPrices!!["XRP"]?.quote?.usd?.percentChange24h ?: 0.0
            )
            AssetCard(
                assetName = "TRX/USD",
                assetDescription = "Doge-coin",
                assetPrice = "${trxPrice.toDoubleOrNull() ?: 0.0}",
                priceChange = cryptoPrices!!["TRX"]?.quote?.usd?.percentChange24h ?: 0.0
            )
            AssetCard(
                assetName = "ADA/USD",
                assetDescription = "Cardano",
                assetPrice = "${adaPrice.toDoubleOrNull() ?: 0.0}",
                priceChange = cryptoPrices!!["ADA"]?.quote?.usd?.percentChange24h ?: 0.0
            )
        }
    } else {
        Text(text = "Loading...")
    }
}

//            cryptoPrices!!.forEach { (symbol, crypto) ->
//                Text(text = "$symbol: ${String.format("%.2f", crypto.quote.usd.price)}", fontSize = 18.sp)
//                Text(text = "$symbol: ${String.format("%.2f", crypto.quote.usd.percentChange24h)} %", fontSize = 18.sp)
//            }


/////////////////////////////////////////////////////////////////////////////////////////////
//@OptIn(ExperimentalMaterialApi::class)
//@Composable
//fun AssetCard(
//    assetName: String,
//    assetDescription: String,
//    assetPrice: String,
//    priceChange: Double,
//    onSwipeToFavorite: () -> Unit // Callback to handle the swipe action and move to favorites
//) {
//    // Calculate percentage change and assign color based on positive/negative change
//    val percentageChange =
//        if (priceChange >= 0) "↑ %.2f%%".format(priceChange) else "↓ %.2f%%".format(-priceChange)
//    val changeColor = if (priceChange >= 0) Color.Green else Color.Red
//
//    // Dismiss state to handle swipe gesture
//    val dismissState = rememberDismissState(
//        confirmStateChange = {
//            if (it == DismissValue.DismissedToEnd || it == DismissValue.DismissedToStart) {
//                onSwipeToFavorite() // Trigger when swipe is completed
//            }
//            true
//        }
//    )
//
//    SwipeToDismiss(
//        state = dismissState,
//        directions = setOf(
//            DismissDirection.StartToEnd,
//            DismissDirection.EndToStart
//        ), // Allow swipe in both directions
//        background = {
//            val color by animateColorAsState(
//                targetValue = if (dismissState.targetValue == DismissValue.DismissedToEnd || dismissState.targetValue == DismissValue.DismissedToStart) Color.Blue else Color.Transparent,
//                label = ""
//            )
//            // Background for swipe-to-favorite
//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .background(color)
//                    .padding(horizontal = 20.dp),
//                contentAlignment = if (dismissState.dismissDirection == DismissDirection.EndToStart) Alignment.CenterEnd else Alignment.CenterStart
//            ) {
//                Icon(Icons.Default.Star, contentDescription = "Favorite Icon", tint = Color.White)
//            }
//        },
//        dismissContent = {
//            Card(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(vertical = 4.dp), // Add some vertical spacing
//                shape = RoundedCornerShape(16.dp),
//                backgroundColor = Color.White,
//                elevation = 4.dp // Small shadow for better separation
//            ) {
//                Row(
//                    modifier = Modifier
//                        .padding(16.dp)
//                        .fillMaxWidth(),
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    // Asset Name and Symbol
//                    Column(modifier = Modifier.weight(1f)) {
//                        Row(verticalAlignment = Alignment.CenterVertically) {
//                            Text(
//                                text = assetName,
//                                color = Color.Black,
//                                fontSize = 16.sp,
//                                fontWeight = FontWeight.Bold
//                            )
//                        }
//                        Text(text = assetDescription, fontSize = 14.sp, color = Color.Gray)
//                    }
//                    // Asset Price and Percentage Change
//                    Column(horizontalAlignment = Alignment.End) {
//                        Text(
//                            text = "${assetPrice.toDoubleOrNull() ?: 0.0}",
//                            color = Color.Black,
//                            fontSize = 16.sp
//                        )
//                        Text(
//                            text = percentageChange,
//                            color = changeColor,
//                            fontSize = 16.sp
//                        )
//                    }
//                }
//            }
//        }
//    )
//    Spacer(modifier = Modifier.height(4.dp))
//}
//
//
//@Composable
//fun BinanceWebSocket() {
//    var assets by remember { mutableStateOf(listOf("BTC", "ETH", "SOL", "XRP", "DOGE", "ADA")) }
//    val favouriteCoins = remember { mutableStateOf(listOf<String>()) } // Favourite coins
//
//    var btcPrice by remember { mutableStateOf("") }
//    var ethPrice by remember { mutableStateOf("") }
//    var solPrice by remember { mutableStateOf("") }
//    var xrpPrice by remember { mutableStateOf("") }
//    var dogePrice by remember { mutableStateOf("") }
//    var adaPrice by remember { mutableStateOf("") }
//
//    val client = OkHttpClient()
//
//    val listener = object : WebSocketListener() {
//        override fun onMessage(webSocket: WebSocket, text: String) {
//            val jsonObject = JSONObject(text)
//            val streamData = jsonObject.getJSONObject("data")
//            val symbol = streamData.getString("s")
//            val price = streamData.getString("p")
//
//            when (symbol) {
//                "BTCUSDT" -> btcPrice = price
//                "ETHUSDT" -> ethPrice = price
//                "SOLUSDT" -> solPrice = price
//                "XRPUSDT" -> xrpPrice = price
//                "DOGEUSDT" -> dogePrice = price
//                "ADAUSDT" -> adaPrice = price
//            }
//        }
//
//        override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
//            t.printStackTrace()
//        }
//    }
//
//    DisposableEffect(Unit) {
//        val request = Request.Builder()
//            .url("wss://stream.binance.com:9443/stream?streams=btcusdt@trade/ethusdt@trade/solusdt@trade/xrpusdt@trade/dogeusdt@trade/adausdt@trade")
//            .build()
//
//        val webSocket = client.newWebSocket(request, listener)
//
//        onDispose {
//            webSocket.close(1000, "Closing WebSocket")
//        }
//    }
//
//    Column(modifier = Modifier.fillMaxSize()) {
//        // Main asset list
//        LazyColumn {
//            items(assets) { asset ->
//                val assetPrice = when (asset) {
//                    "BTC" -> btcPrice
//                    "ETH" -> ethPrice
//                    "SOL" -> solPrice
//                    "XRP" -> xrpPrice
//                    "DOGE" -> dogePrice
//                    "ADA" -> adaPrice
//                    else -> "0.0"
//                }
//
//                val priceChange = assetPrice.toDoubleOrNull() ?: 0.0
//
//                AssetCard(
//                    assetName = "$asset/USD",
//                    assetDescription = asset,
//                    assetPrice = assetPrice,
//                    priceChange = priceChange
//                ) {
//                    // Move coin to favouriteCoins instead of deleting
//                    assets = assets.filterNot { it == asset }
//                    favouriteCoins.value += asset
//                }
//            }
//        }
//
//        // Call FavouriteCoins composable
////        FavouriteCoins(favouriteCoins, btcPrice, ethPrice, solPrice, xrpPrice, dogePrice, adaPrice)
//
//    }
//}
//
//
//@Composable
//fun FavouriteCoins(
//    favouriteCoins: MutableState<List<String>>,
//    btcPrice: String,
//    ethPrice: String,
//    solPrice: String,
//    xrpPrice: String,
//    dogePrice: String,
//    adaPrice: String
//) {
//    Spacer(modifier = Modifier.height(16.dp))
//
//    Text(text = "Favourites", style = MaterialTheme.typography.h6)
//    LazyColumn {
//        items(favouriteCoins.value) { favourite ->
//            val assetPrice = when (favourite) {
//                "BTC" -> btcPrice
//                "ETH" -> ethPrice
//                "SOL" -> solPrice
//                "XRP" -> xrpPrice
//                "DOGE" -> dogePrice
//                "ADA" -> adaPrice
//                else -> "0.0"
//            }
//
//            val priceChange = assetPrice.toDoubleOrNull() ?: 0.0
//
//            AssetCard(
//                assetName = "$favourite/USD",
//                assetDescription = favourite,
//                assetPrice = assetPrice,
//                priceChange = priceChange,
//                onSwipeToFavorite = { favouriteCoins.value += favourite }
//            )
//        }
//    }
//}


//@Composable
//fun BinanceWebSocket() {
//    var assets by remember { mutableStateOf(listOf("BTC", "ETH", "SOL", "XRP", "DOGE", "ADA")) }
//
//    var btcPrice by remember { mutableStateOf("") }
//    var ethPrice by remember { mutableStateOf("") }
//    var solPrice by remember { mutableStateOf("") }
//    var xrpPrice by remember { mutableStateOf("") }
//    var dogePrice by remember { mutableStateOf("") }
//    var adaPrice by remember { mutableStateOf("") }
//
//    val client = OkHttpClient()
//
//    val listener = object : WebSocketListener() {
//        override fun onMessage(webSocket: WebSocket, text: String) {
//            val jsonObject = JSONObject(text)
//            val streamData = jsonObject.getJSONObject("data")
//            val symbol = streamData.getString("s")
//            val price = streamData.getString("p")
//
//            when (symbol) {
//                "BTCUSDT" -> btcPrice = price
//                "ETHUSDT" -> ethPrice = price
//                "SOLUSDT" -> solPrice = price
//                "XRPUSDT" -> xrpPrice = price
//                "DOGEUSDT" -> dogePrice = price
//                "ADAUSDT" -> adaPrice = price
//            }
//        }
//
//        override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
//            t.printStackTrace()
//        }
//    }
//
//    DisposableEffect(Unit) {
//        val request = Request.Builder()
//            .url("wss://stream.binance.com:9443/stream?streams=btcusdt@trade/ethusdt@trade/solusdt@trade/xrpusdt@trade/dogeusdt@trade/adausdt@trade")
//            .build()
//
//        val webSocket = client.newWebSocket(request, listener)
//
//        onDispose {
//            webSocket.close(1000, "Closing WebSocket")
//        }
//    }
//
//    LazyColumn(modifier = Modifier.fillMaxSize()) {
//        items(assets) { asset ->
//            val assetPrice = when (asset) {
//                "BTC" -> btcPrice
//                "ETH" -> ethPrice
//                "SOL" -> solPrice
//                "XRP" -> xrpPrice
//                "DOGE" -> dogePrice
//                "ADA" -> adaPrice
//                else -> "0.0"
//            }
//
//            val priceChange = assetPrice.toDoubleOrNull() ?: 0.0
//
//            AssetCard(
//                assetName = "$asset/USD",
//                assetDescription = asset,
//                assetPrice = assetPrice,
//                priceChange = priceChange
//            ) {
//                // Handle delete
//                assets = assets.filterNot { it == asset }
//            }
//        }
//    }
//}
