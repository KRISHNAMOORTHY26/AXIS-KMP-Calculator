import SwiftUI
import shared

struct ContentView: View {
    @StateObject private var vm = CalculatorViewModelWrapper()
    @State private var a = ""
    @State private var b = ""

    var body: some View {
        VStack(spacing: 16) {
            Text("KMM Calculator")
                .font(.title)
                .padding(.top, 40)

            HStack {
                TextField("Enter A", text: $a)
                    .keyboardType(.decimalPad)
                    .textFieldStyle(RoundedBorderTextFieldStyle())
                TextField("Enter B", text: $b)
                    .keyboardType(.decimalPad)
                    .textFieldStyle(RoundedBorderTextFieldStyle())
            }

            HStack(spacing: 12) {
                Button("+") { vm.calculate(op: "add", a: a, b: b) }
                Button("-") { vm.calculate(op: "subtract", a: a, b: b) }
                Button("×") { vm.calculate(op: "multiply", a: a, b: b) }
                Button("÷") { vm.calculate(op: "divide", a: a, b: b) }
            }

            if let result = vm.result {
                Text("Result: \(result)")
                    .font(.headline)
            }

            Divider().padding(.vertical, 20)

            Button("Fetch User Data") {
                vm.fetchUser()
            }

            if let user = vm.userTitle {
                Text("User Title: \(user)")
            }
        }
        .padding()
    }
}


struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}