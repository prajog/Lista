package beretta.prajo.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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

public class NewItemActivity extends AppCompatActivity {
    //identificador de chamada
    static int PHOTO_PICKER_REQUEST = 1;

    //guarda o endereco da foto selecionada pelo usuario, e nao a foto em si.
    Uri photoSelected = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);


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

        Button btnAddItem = findViewById(R.id.btnAddItem);

        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (photoSelected == null) {
                    Toast.makeText(NewItemActivity.this, "É necessário selecionar uma imagem!", Toast.LENGTH_LONG).show();
                    return;
                }

                EditText etTitle = findViewById(R.id.etTitle);
                String title = etTitle.getText().toString();
                if (title.isEmpty()){
                    Toast.makeText(NewItemActivity.this, "É necessário inserir um título", Toast.LENGTH_LONG).show();
                    return;
                }

                EditText etDesc = findViewById(R.id.etDesc);
                String desc = etDesc.getText().toString();
                if (desc.isEmpty()){
                    Toast.makeText(NewItemActivity.this, "É necessário inserir uma descrição", Toast.LENGTH_LONG).show();
                    return;
                }

                Intent i = new Intent();
                i.setData(photoSelected);
                i.putExtra("title", title);
                i.putExtra("description", desc);
                setResult(Activity.RESULT_OK, i);
                finish();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PHOTO_PICKER_REQUEST){
            if(resultCode == Activity.RESULT_OK){
                photoSelected = data.getData();
                ImageView imvfotoPreview = findViewById(R.id.imvPhotoPreview);
                imvfotoPreview.setImageURI(photoSelected);
            }
        }
    }


}