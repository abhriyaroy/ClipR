package studio.zebro.clipr.platformSpecifics

actual interface CopyTool {
  actual fun showTool() {
    println("CopyTool ios")
  }
}