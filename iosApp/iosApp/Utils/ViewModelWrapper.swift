import KMPNativeCoroutinesCore
import KMPNativeCoroutinesCombine
import shared
import Combine

class ViewModelWrapper: ObservableObject {

    var viewModel: PokedexViewModel

    init(viewModel: PokedexViewModel) {
        self.viewModel = viewModel
    }

    @Published public var pokemons: [Pokemon]? = []
    private var hasNextPage: Bool = false

    public var shouldDisplayNextPage: Bool {
        hasNextPage
    }

    func fetchPokemonsPaging() {
        viewModel.pokemons.watch { nullablePagingData in
            guard let list = nullablePagingData?.compactMap({ $0 as? Pokemon }) else {
                return
            }
            self.pokemons = list
            self.hasNextPage = self.viewModel.pager.hasNextPage
        }
    }

    func fetchNextData() {
        viewModel.pager.loadNext()
    }
}