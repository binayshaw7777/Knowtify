import SwiftUI
import ComposeApp

@main
struct iOSApp: App {

    init() {
        MainViewControllerKt.initialize()
    }

	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}