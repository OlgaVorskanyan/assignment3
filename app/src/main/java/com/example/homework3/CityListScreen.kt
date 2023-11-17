import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.homework3.ui.navigation.Screen // Import the Screen class

@Composable
fun CityListScreen(navController: NavController, weatherViewModel: WeatherViewModel = viewModel()) {
    BackHandler {
        navController.navigateBackOrToWelcome()
    }

    val cities = listOf("Yerevan", "Washington", "Madrid")

    LazyColumn {
        items(cities) { city ->
            CityItem(cityName = city)
            WeatherUI(weatherViewModel = weatherViewModel) // Display weather UI for each city
        }
    }
}

@Composable
fun CityItem(cityName: String) {
    Text(
        text = cityName,
    )
}

fun NavHostController.navigateBackOrToWelcome() {
    val currentRoute = currentDestination?.route
    if (currentRoute == Screen.CityListScreen.route) {
        navigate(Screen.WelcomeScreen.route)
    } else {
        popBackStack()
    }
}

@Composable
fun WeatherUI(weatherViewModel: WeatherViewModel = viewModel()) {
    val weatherData by weatherViewModel.weatherData.observeAsState(emptyList())

    // Use weatherData to display temperature under each city image
}

@Composable
fun PreviewCityListScreen() {
    val navController = rememberNavController() // Dummy NavController for preview
    CityListScreen(navController = navController)
}

@Composable
fun LocationPermissionRequired(
    onPermissionGranted: () -> Unit,
    onPermissionDenied: () -> Unit
) {
    val context = LocalContext.current
    val permissionState = rememberPermissionState(android.Manifest.permission.ACCESS_FINE_LOCATION)

    if (permissionState.hasPermission) {
        // Permission already granted
        onPermissionGranted()
    } else {
        // Permission not granted, request permission
        LaunchedEffect(Unit) {
            permissionState.launchPermissionRequest()
        }

        DisposableEffect(permissionState) {
            onDispose {
                when {
                    permissionState.hasPermission -> onPermissionGranted()
                    permissionState.shouldShowRationale -> onPermissionDenied()
                    else -> {
                        // Permission denied permanently, handle accordingly
                    }
                }
            }
        }
    }
}

