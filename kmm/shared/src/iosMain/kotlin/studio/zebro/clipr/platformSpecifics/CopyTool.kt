package studio.zebro.clipr.platformSpecifics

actual class CopyTool {
  actual fun showTool() {
//    WidgetManager().showWidget()
    val button = PlatformButtonActual()
    button.show()
  }
}