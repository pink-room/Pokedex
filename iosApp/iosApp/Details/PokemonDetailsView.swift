import SwiftUI
import shared

struct PokemonDetailsView: View {
    let pokemon: PokemonDetails

    var body: some View {
        VStack {
            if #available(iOS 15.0, *) {
                AsyncImage(url: URL(string: pokemon.getImageUrl())) { image in
                    image
                        .resizable()
                        .aspectRatio(contentMode: .fit)
                        .clipped()
                } placeholder: {
                    ProgressView()
                }
                    .frame(width: 80, height: 80)
                    .clipped()
            } else {
                // Fallback on earlier versions
            }
            Text(pokemon.getNameCapitalized()).font(.title3).foregroundColor(.accentColor)
            Text("Height: " + String(pokemon.height))
            Text("Weight: " + String(pokemon.weight))
            Text("Base experience: " + String(pokemon.experience))
        }
    }
}
