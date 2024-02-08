import java.awt.Toolkit
import java.awt.datatransfer.StringSelection

fun copyToClipboard(str: String) {
    val selection = StringSelection(str)
    val clipboard = Toolkit.getDefaultToolkit().systemClipboard
    clipboard.setContents(selection, selection)
}