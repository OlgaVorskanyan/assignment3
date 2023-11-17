import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.homework3.ui.navigation.Screen
import kotlinx.coroutines.launch

@Composable
fun WelcomeScreen(
    navController: NavController
) {
    val coroutineScope = rememberCoroutineScope()

    Scaffold {
        WelcomeContent(navController, weatherViewModel, coroutineScope)
    }
}

@Composable
fun WelcomeContent(
    navController: NavHostController,
    weatherViewModel: WeatherViewModel,
    coroutineScope: CoroutineScope
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Integrated WelcomeMessage
        WelcomeMessage()

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                navController.navigate(Screen.CityListScreen.route)
            }
        ) {
            Text(text = "View City List")
        }

        // Weather UI section
        LocationPermissionRequired(
            onPermissionGranted = {
                coroutineScope.launch {
                    weatherViewModel.fetchWeatherForCurrentLocation()
                }
            },
            onPermissionDenied = {
                // Handle permission denied scenario
            }
        ) {
            // Display weather data if available
            WeatherUI(weatherViewModel = weatherViewModel)
        }
    }
}

@Composable
fun WelcomeMessage() {
    Text(
        text = "Welcome to My App!",
        style = MaterialTheme.typography.h4,
        color = Color.Black
    )
}

@Composable
fun WeatherUI(weatherViewModel: WeatherViewModel) {
    // Fetch weather data from weatherViewModel and display it
}
