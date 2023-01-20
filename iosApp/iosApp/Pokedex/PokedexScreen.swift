import SwiftUI
import shared
import KMMViewModelSwiftUI
import Combine

struct PokedexScreen: View {
    @StateObject private var viewModel = ViewModelWrapper(viewModel: PokedexViewModel())

    var body: some View {
        VStack {
            NavigationView {
                List {
                    ForEach(viewModel.pokemons ?? [], id: \.name) { pokemon in
                        VStack {
                            NavigationLink {
                                DetailsScreen(name: pokemon.name)
                            } label: {
                                PokedexRowView(pokemon: pokemon)
                            }
                        }
                    }
                        .navigationTitle("KMM Pokedex")
                    if viewModel.shouldDisplayNextPage {
                        loadingNextPage
                    }
                }
            }
        }
            .onAppear {
                viewModel.fetchPokemonsPaging()
            }
    }

    private var loadingNextPage: some View {
        HStack {
            Spacer()
            VStack {
                ProgressView()
                Text("Loading more Pokemons...")
            }
            Spacer()
        }
            .onAppear(perform: { viewModel.fetchNextData() })
    }
}

struct PokedexScreen_Previews: PreviewProvider {
    static var previews: some View {
        PokedexScreen()
    }
}
