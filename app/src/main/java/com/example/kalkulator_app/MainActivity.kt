package com.example.kalkulator_app

import android.icu.text.NumberFormat
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kalkulator_app.ui.theme.Kalkulator_AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Kalkulator_AppTheme {
                Scaffold (modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TaxLayout(modifier = Modifier.padding(innerPadding))
                    }
                }
            }
        }
    }

fun calculateTax(amount: Double, taxRate: Double = 10.0):String {
    val tax = amount * taxRate / 100
    return NumberFormat.getCurrencyInstance().format(tax);
}
@Composable
fun EditTextNumber(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(stringResource(R.string.bill_amount)) },
        modifier = Modifier
    )
}



@Composable
fun TaxLayout(modifier: Modifier = Modifier) {

    var amountInput by remember { mutableStateOf("") }

    val amount = amountInput.toDoubleOrNull() ?: 0.00
    val tax = calculateTax(amount)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(40.dp)
    ) {
        Text(
            text = stringResource(R.string.calculate_tax),
            modifier = Modifier
                .padding(bottom = 16.dp, top = 40.dp)
                .align(alignment = Alignment.Start)
        )
        EditTextNumber(
            value = amountInput,
            onValueChange = { amountInput = it },
            modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth()
        )

        Text(
            text = stringResource(R.string.tax_amount_0_00, ),
            style = MaterialTheme.typography.displaySmall
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Kalkulator_AppTheme {
        TaxLayout()
    }
}