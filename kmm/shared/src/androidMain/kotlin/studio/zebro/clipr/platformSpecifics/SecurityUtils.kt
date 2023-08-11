package studio.zebro.clipr.platformSpecifics

import java.security.SecureRandom

actual fun generateSecureRandomKey(): ByteArray {
  val key = ByteArray(64)
  val secureRandom = SecureRandom()
  secureRandom.nextBytes(key)
  return key
}