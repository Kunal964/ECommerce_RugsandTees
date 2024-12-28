package com.example.ecommercerugsandtees.ui.theme.feature.summary

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.domain.di.model.CartItemModel
import com.example.domain.di.model.CartSummary
import org.koin.androidx.compose.koinViewModel


@Composable
fun CartSummaryScreen(
    navController: NavController,
    viewModel: CartSummaryViewModel = koinViewModel()
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp))
    {
        Box(modifier =  Modifier
            .fillMaxWidth()
            .height(60.dp)

        ) {
            Text(
                text = "Cart Summary",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.align(Alignment.Center)
            )
        }
        val uiState = viewModel.uiState.collectAsState()
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            when (val event = uiState.value) {
                is CartSummaryEvent.Loading -> {
                    // Show loading indicator
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.align(Alignment.Center)
                    ) {
                        CircularProgressIndicator()
                        Text(text = "Loading", style = MaterialTheme.typography.titleMedium)
                    }
                }
                is CartSummaryEvent.Success -> {
                    CartSummaryScreenContent(event.summary)


                }
                is CartSummaryEvent.Error -> {
                    Text(text = event.error,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.align(Alignment.Center))
                }
            }

        }
        Button(onClick = {}, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Checkout", style = MaterialTheme.typography.titleMedium)
        }


    }
}

@Composable
fun CartSummaryScreenContent(cartSummary: CartSummary) {
    LazyColumn {
        item {
            Text(
                text = "Products",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(vertical = 8.dp))
        }
        items(cartSummary.data.items) { cartItem ->
            ProductRow(cartItem)


        }
        item {
            Column {
                Text(
                    text = "Amount",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(vertical = 8.dp))
                AmountRow(title = "Total", amount = cartSummary.data.total)
                AmountRow(title = "Discount", amount = cartSummary.data.discount)
                AmountRow(title = "Shipping", amount = cartSummary.data.shipping)
                AmountRow(title = "SubTotal", amount = cartSummary.data.subtotal)
                AmountRow(title = "Tax", amount = cartSummary.data.tax)
            }

        }
    }
}
@Composable
fun ProductRow(cartItemModel: CartItemModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
           .padding(8.dp)
    ) {
        Text(
            text = cartItemModel.productName,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.bodyMedium
        )
        Text(text = "$${cartItemModel.price} x ${cartItemModel.quantity} $${cartItemModel.price * cartItemModel.quantity}",
             style = MaterialTheme.typography.titleMedium)
    }
}
@Composable
fun AmountRow(title: String, amount: Double) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            text = title,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.bodyMedium
        )
        Text(text = "$${amount}", style = MaterialTheme.typography.titleMedium)
    }
}