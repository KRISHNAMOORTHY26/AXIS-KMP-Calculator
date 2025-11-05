import Foundation
import shared

@MainActor
class CalculatorViewModelWrapper: ObservableObject {
    private let viewModel = SharedViewModel()

    @Published var result: Double? = nil
    @Published var userTitle: String? = nil

    init() {
        observeFlows()
    }

    private func observeFlows() {
        viewModel.calcResult.watch { [weak self] value in
            self?.result = value as? Double
        }

        viewModel.users.watch { [weak self] value in
            self?.userTitle = value as? String
        }
    }

    func calculate(op: String, a: String, b: String) {
        viewModel.calculate(operation: op, aStr: a, bStr: b)
    }

    func fetchUser() {
        viewModel.loadUsers()
    }
}
