package beretta.prajo.model;

import android.net.Uri;

import androidx.lifecycle.ViewModel;

public class NewItemActivityViewModel extends ViewModel {
    //guarda endereco Uri da foto escolhida, para que a imagem nao desapareca ao girar a tela de NewItemActivity
    Uri selectedPhotoLocation = null;

    //pega a uri da photo selecionada
    public Uri getSelectedPhotoLocation() {
        return selectedPhotoLocation;
    }

    //metodo que seta o endereco Uri dentro do ViewModel
    public void setSelectedPhotoLocation(Uri selectedPhotoLocation) {
        this.selectedPhotoLocation = selectedPhotoLocation;
    }
}
