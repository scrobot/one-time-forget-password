import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import java.awt.Toolkit
import java.awt.datatransfer.StringSelection

@OptIn(ExperimentalResourceApi::class)
@Composable
fun App() {
    var password by remember { mutableStateOf(PasswordGenerator.randomPassword(24, true, true)) }
    var passwordLength by remember { mutableStateOf(24) }
    var passwordLengthText by remember { mutableStateOf(TextFieldValue("24")) }
    var includeNumbers by remember { mutableStateOf(true) }
    var includeSpecialCharacters by remember { mutableStateOf(true) }
    val passwordGenerator = remember { PasswordGenerator() }
    var passwordVisibility by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier.padding(16.dp)
        ) {
            OutlinedTextField(
                value = TextFieldValue(if (passwordVisibility) password else password.replace(Regex("."), "*")),
                onValueChange = { password = it.text },
                singleLine = true,
                modifier = Modifier.weight(1f),
                readOnly = true,
            )
            Spacer(Modifier.width(10.dp))
            IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                Icon(
                    painter = if (passwordVisibility) painterResource("show-svgrepo-com.png") else painterResource("hide-svgrepo-com.png"),
                    contentDescription = "Toggle Password Visibility",
                    modifier = Modifier.size(24.dp)
                )
            }
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Size")
            Spacer(Modifier.width(10.dp))
            Slider(
                value = passwordLength.toFloat(),
                onValueChange = {
                    passwordLength = it.toInt()
                    passwordLengthText = TextFieldValue(it.toInt().toString())
                },
                valueRange = 8f..32f,
                steps = 24,
                modifier = Modifier.padding(16.dp).weight(1f)
            )
            OutlinedTextField(
                value = passwordLengthText,
                onValueChange = {
                    passwordLengthText = it
                    passwordLength = it.text.toIntOrNull() ?: passwordLength
                },
                singleLine = true,
                modifier = Modifier.width(50.dp)
            )
        }
        Row(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = includeNumbers,
                    onCheckedChange = { includeNumbers = it },
                )
                Text("Numbers")
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = includeSpecialCharacters,
                    onCheckedChange = { includeSpecialCharacters = it },
                    modifier = Modifier.padding(start = 16.dp)
                )
                Text("Special characters")
            }
        }
        Button(
            onClick = {
                val selection = StringSelection(password)
                val clipboard = Toolkit.getDefaultToolkit().systemClipboard
                clipboard.setContents(selection, selection)
                password = passwordGenerator.generateRandomPassword(
                    passwordLength,
                    includeNumbers,
                    includeSpecialCharacters
                )
            },
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Copy")
        }
    }
}