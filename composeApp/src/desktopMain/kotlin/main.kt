import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.window.Tray
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import java.io.File
import javax.swing.text.StyleConstants.setIcon

@OptIn(ExperimentalResourceApi::class)
fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "OTFP",
        icon = painterResource("icon.png")
    ) {
        App()
    }

    Tray(
        icon = painterResource("icon.png"),
        tooltip = "Password Generator"
    ) {
        // Элементы меню трея
    }

}

@Preview
@Composable
fun AppDesktopPreview() {
    App()
}