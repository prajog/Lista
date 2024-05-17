package beretta.prajo.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import beretta.prajo.lista.R;
import beretta.prajo.model.NewItemActivityViewModel;

public class NewItemActivity extends AppCompatActivity {
    //identificador de chamada
    static int PHOTO_PICKER_REQUEST = 1;

    Uri photoSelected = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);

        //obtem o ViewModel referente a NewItemActivity
        NewItemActivityViewModel vm = new ViewModelProvider(this).get(NewItemActivityViewModel.class);

        //obtem o enereco URI guardado dentro do ViewModel
        Uri selectPhotoLocation = vm.getSelectedPhotoLocation();

        //verifica se o endereco URI nao eh nulo
        if(selectPhotoLocation != null){
            //caso nao seja nulo:
            //obtem a imageView
            ImageView imvPhotoPreview = findViewById(R.id.imvPhotoPreview);
            //seta a imagem selecionada pelo usuario no ImageView da tela
            imvPhotoPreview.setImageURI(selectPhotoLocation);
        }

        //obtendo o ImageButton
        ImageButton imgCI = findViewById(R.id.imbCl);

        //ouvidor de cliques do imageButton
        imgCI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ao clicar havera a abertura da galeria para que uma foto seja escolhida

                //criando uma intencao implícito que abre um documento
                Intent photoPickerIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);

                //definindo o tipo de documento esperado como tipo imagem
                photoPickerIntent.setType("image/*");

                //executa a Intent
                startActivityForResult(photoPickerIntent, PHOTO_PICKER_REQUEST);
            }
        });

        //obtem botao de adicionar item
        Button btnAddItem = findViewById(R.id.btnAddItem);

        //setando ouvidor de cliques do botao de adicionar item
        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //verifica se nenhuma foto foi selecionada
                if (photoSelected == null) {
                    //exibe mensagem de erro
                    Toast.makeText(NewItemActivity.this, "É necessário selecionar uma imagem!", Toast.LENGTH_LONG).show();
                    return;
                }

                //obtem a editText com o titulo
                EditText etTitle = findViewById(R.id.etTitle);
                //converte o texto do titulo em string
                String title = etTitle.getText().toString();

                //verifica se o titulo esta vazio
                if (title.isEmpty()){
                    //exibe mensagem de erro
                    Toast.makeText(NewItemActivity.this, "É necessário inserir um título", Toast.LENGTH_LONG).show();
                    return;
                }

                //obtem a editText com a descricao
                EditText etDesc = findViewById(R.id.etDesc);
                //converte o texto da descricao em string
                String desc = etDesc.getText().toString();

                //verifica se o campo de descricao esta vaziop
                if (desc.isEmpty()){
                    //exibe mensagem de erro
                    Toast.makeText(NewItemActivity.this, "É necessário inserir uma descrição", Toast.LENGTH_LONG).show();
                    return;
                }

                //cria uma intent para guardar os dados a serem retornados a MainActivity
                Intent i = new Intent();

                //seta o Uri da imagem selecionada dentro do Intent
                i.setData(photoSelected);
                //seta o titulo dentro da Intent
                i.putExtra("title", title);
                //seta o descricao dentro da Intent
                i.putExtra("description", desc);
                //metodo que indica o resultado da Activity
                setResult(Activity.RESULT_OK, i);
                //finaliza a activity
                finish();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //verifica se requestCode eh referente ao fornecido na chamada de startActivityForResult com id PHOTO_PICKER_REQUEST
        if(requestCode == PHOTO_PICKER_REQUEST){
            //verifica se o resultCode eh um codigo de sucesso
            if(resultCode == Activity.RESULT_OK){

                //obtem o Uri da imagem escolhida e guarda dentro do atributo de classe photoSelected
                photoSelected = data.getData();
                //obtem a ImageView
                ImageView imvPhotoPreview = findViewById(R.id.imvPhotoPreview);
                //seta o Uri na ImageView para que a foto seja exibida na app
                imvPhotoPreview.setImageURI(photoSelected);

                //obtem o ViewModel de NewItemActivity
                NewItemActivityViewModel vm = new ViewModelProvider(this).get(NewItemActivityViewModel.class);
                //seta o endereco URI da imagem selecionada pelo usuario dentro do ViewModel
                vm.setSelectedPhotoLocation(photoSelected);



            }
        }
    }


}