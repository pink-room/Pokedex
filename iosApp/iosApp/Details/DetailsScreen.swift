import SwiftUI
import shared
import KMMViewModelSwiftUI

struct DetailsScreen: View {
    @StateViewModel var viewModel = DetailsViewModel()
    var name: String

    var body: some View {
        VStack {
            if (viewModel.state.loading) {
                ProgressView()
            } else if (viewModel.state.pokemon != nil) {
                PokemonDetailsView(pokemon: viewModel.state.pokemon!)
            }
        }
            .onAppear {
                viewModel.getDetails(name: name)
            }
    }
}

struct DetailsScreen_Previews: PreviewProvider {
    static var previews: some View {
        DetailsScreen(name: "charmander")
    }
}
