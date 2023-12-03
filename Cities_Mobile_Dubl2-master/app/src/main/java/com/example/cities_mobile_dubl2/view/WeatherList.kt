package com.example.cities_mobile_dubl2.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cities_mobile_dubl2.data.cities
import com.example.cities_mobile_dubl2.model.City
import com.example.cities_mobile_dubl2.model.WeatherData
import com.example.cities_mobile_dubl2.ui.TemperatureUnit
import com.example.cities_mobile_dubl2.ui.celsiusToFahrenheit
import com.example.cities_mobile_dubl2.viewmodel.WeatherViewModel

@Composable
fun WeatherList(weatherList: List<WeatherData>, viewModel: WeatherViewModel) {
    val selectedTemperatureUnit = viewModel.selectedTemperatureUnit.value
    val weatherData = viewModel.weatherData.value

    if (weatherData != null) {
        LazyColumn {
            itemsIndexed(weatherList) { index, weather ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    CityItem(
                        city = City(
                            name = cities[index].name,
                            description = cities[index].description,
                            imageRes = cities[index].imageRes
                        )
                    )

                    val temperatureValue = when (selectedTemperatureUnit) {
                        TemperatureUnit.CELSIUS -> weatherData[index].current.temp_c
                        TemperatureUnit.FAHRENHEIT -> celsiusToFahrenheit(weatherData[index].current.temp_c)
                        else -> weatherData[index].current.temp_c // Handle other cases as needed
                    }

                    Text(
                        text = "Temperature: ${temperatureValue}",
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

