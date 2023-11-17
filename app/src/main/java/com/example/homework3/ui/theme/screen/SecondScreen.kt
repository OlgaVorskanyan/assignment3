import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController

@Composable
fun SecondScreen(cityName: NavController, cityDescription: String) {
    Column {
        Text(text = "City: $cityName")
        Text(text = "Description: $cityDescription")
    }
}

@Preview
@Composable
fun PreviewSecondScreen() {
    SecondScreen(
        cityName = "Yerevan",
        cityDescription = "Yerevan is the capital and largest city of Armenia, situated on the Hrazdan River."
    )
}
