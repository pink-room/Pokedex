import SwiftUI
import shared

struct PokedexRowView: View {
    let pokemon: Pokemon

    var body: some View {
        HStack {
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
        }
    }
}