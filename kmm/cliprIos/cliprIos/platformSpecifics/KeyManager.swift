import Foundation
import shared

class KeyManagerImpl : KeyManager {
    // iOS-specific implementation using Swift
    private let keychainService = ""
    
    init() { }
    
    override func generateKey() -> String {
        let key = UUID().uuidString
        UserDefaults.standard.set(key, forKey: keychainService)
        return key
    }
    
    override func getKey() -> String {
        return UserDefaults.standard.string(forKey: keychainService) ?? ""
    }
}


