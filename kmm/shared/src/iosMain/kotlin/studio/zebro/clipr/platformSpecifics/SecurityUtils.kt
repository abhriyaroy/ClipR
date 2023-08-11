package studio.zebro.clipr.platformSpecifics

import kotlinx.cinterop.refTo
import platform.Security.SecRandomCopyBytes
import platform.Security.errSecSuccess
import platform.Security.kSecRandomDefault

actual fun generateSecureRandomKey(): ByteArray {
  val key = ByteArray(64)
  val status = SecRandomCopyBytes(kSecRandomDefault, 64, key.refTo(0))
  if (status != errSecSuccess) { // Always test the status.
    throw Error("Problem generating random bytes: $status")
  }
  return key
}
