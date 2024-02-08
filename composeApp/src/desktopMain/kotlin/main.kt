import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Tray
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import java.awt.Desktop


@OptIn(ExperimentalResourceApi::class)
fun main() = application {
    val windowState = rememberWindowState(
        size = DpSize(600.dp, 300.dp),
    )

    Window(
        onCloseRequest = { windowState.isMinimized = windowState.isMinimized.not() }, // вместо ::exitApplication
        state = windowState,
        title = "OTFP",
        icon = painterResource("icon_big.png")
    ) {
        App()
    }

    Tray(
        icon = painterResource("icon_tray.png"),
        tooltip = "Password Generator",
    ) {
        Item("Generate Password", onClick = {
            val password = PasswordGenerator.randomPassword()
            copyToClipboard(password)
            if (Desktop.isDesktopSupported()) {
                val desktop = Desktop.getDesktop()
                if (desktop.isSupported(Desktop.Action.BROWSE)) {
                    val script = "display notification \"Password copied to clipboard\" with title \"Password Generator\""
                    val command = arrayOf("osascript", "-e", script)
                    Runtime.getRuntime().exec(command)
                }
            }
        })
        Item("Exit", onClick = ::exitApplication)
    }

}

@Preview
@Composable
fun AppDesktopPreview() {
    App()
}