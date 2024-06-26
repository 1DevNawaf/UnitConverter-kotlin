package com.example.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}


@Composable
fun UnitConverter() {

    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Meters") }
    var outputUnit by remember { mutableStateOf("Meters") }
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }
    val iConversionFactor = remember { mutableDoubleStateOf(1.0) }
    val oConversionFactor = remember { mutableDoubleStateOf(1.0) }

    fun conversionUnit() {
        // ?: this is elvis operator, means if it's null take the after colon value
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result =
            (inputValueDouble * iConversionFactor.doubleValue * 100.0 / oConversionFactor.doubleValue).roundToInt() / 100
        outputValue = result.toString()
    }



    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            Icons.Default.Create,
            contentDescription = "Create",
            Modifier.size(width = 64.dp, height = 64.dp)
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text("Unit converter", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = inputValue,
            onValueChange = {
                inputValue = it
                conversionUnit()
            },
            label = { Text(text = "Enter value here", textAlign = TextAlign.Center) }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            //Input Box
            Box {
                //Input Button
                Button(onClick = { iExpanded = !iExpanded }) {
                    Text(text = inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Select")
                }
                DropdownMenu(
                    expanded = iExpanded,
                    onDismissRequest = { iExpanded = !iExpanded }) {
                    DropdownMenuItem(
                        text = { Text("Centimeters") },
                        onClick = {
                            iExpanded = !iExpanded
                            inputUnit = "Centimeters"
                            iConversionFactor.doubleValue = 0.01
                            conversionUnit()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Meters") },
                        onClick = {
                            iExpanded = !iExpanded
                            inputUnit = "Meters"
                            iConversionFactor.doubleValue = 1.00
                            conversionUnit()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Feet") },
                        onClick = {
                            iExpanded = !iExpanded
                            inputUnit = "Feet"
                            iConversionFactor.doubleValue = 0.3048
                            conversionUnit()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Millimeters") },
                        onClick = {
                            iExpanded = !iExpanded
                            inputUnit = "Millimeters"
                            iConversionFactor.doubleValue = 0.001
                            conversionUnit()
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            //Output Box
            Box {
                //Output Button
                Button(onClick = { oExpanded = !oExpanded }) {
                    Text(text = outputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Select")
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded = !oExpanded }) {
                    DropdownMenuItem(
                        text = { Text("Centimeters") },
                        onClick = {
                            oExpanded = !iExpanded
                            outputUnit = "Centimeters"
                            oConversionFactor.doubleValue = 0.01
                            conversionUnit()
                        }

                    )
                    DropdownMenuItem(
                        text = { Text("Meters") },
                        onClick = {
                            oExpanded = !iExpanded
                            outputUnit = "Meters"
                            oConversionFactor.doubleValue = 1.00
                            conversionUnit()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Feet") },
                        onClick = {
                            oExpanded = !iExpanded
                            outputUnit = "Feet"
                            oConversionFactor.doubleValue = 0.3048
                            conversionUnit()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Millimeters") },
                        onClick = {
                            oExpanded = !iExpanded
                            outputUnit = "Millimeters"
                            oConversionFactor.doubleValue = 0.001
                            conversionUnit()
                        }
                    )
                }
            }

        }
        Spacer(modifier = Modifier.height(16.dp))
        //result text
        Text(
            text = "Results: $inputValue $inputUnit = $outputValue $outputUnit",
            style = MaterialTheme.typography.headlineMedium
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun UnitConverterPreview() {
    UnitConverter()
}