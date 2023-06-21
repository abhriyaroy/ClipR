import SwiftUI
import shared
import UIKit

struct ComposeView: UIViewControllerRepresentable {

    func updateUIViewController(_ uiViewController: UIViewControllerType, context: Context) {}

    func makeUIViewController(context: Context) -> some UIViewController {
        IosMainAppKt.MainViewController()
    }
}

struct ContentView: View {

	var body: some View {
        ComposeView()
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
