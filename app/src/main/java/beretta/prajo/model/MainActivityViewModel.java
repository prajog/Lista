package beretta.prajo.model;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivityViewModel extends ViewModel {
    // note: um ViewModel eh um pacote que guarda os dados de uma Activity*/

    //lista de itens cadastrados
    List<MyItem> itens = new ArrayList<>();

    //metodo para obter a lista de itens
    public List<MyItem> getItens() {
        return itens;
    }
}
