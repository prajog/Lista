package beretta.prajo.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import beretta.prajo.adapter.MyAdapter;
import beretta.prajo.lista.R;
import beretta.prajo.model.MyItem;

public class MainActivity extends AppCompatActivity {
    //identificador da chamada
    static int NEW_ITEM_REQUEST = 1;
    List<MyItem> itens = new ArrayList<>();

    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //obtem o botao flutuante
        FloatingActionButton fabAddItem = findViewById(R.id.fabAddNewItem);

        //ouvidor de cliques do botao flutuante
        fabAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //criando uma intent para navegar para NewItemActivity
                Intent i = new Intent(MainActivity.this, NewItemActivity.class);

                //esse metodo espera que a activity destino retorne com dados para a activity que iniciou a navegacao
                startActivityForResult(i, NEW_ITEM_REQUEST);
            }
        });

        RecyclerView rvItens = findViewById(R.id.rvItens);

        // Ensina o Recycler View a construir e preencher a lista
        myAdapter = new MyAdapter(this, itens);
        rvItens.setAdapter(myAdapter);

        rvItens.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvItens.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvItens.getContext(), DividerItemDecoration.VERTICAL);
        rvItens.addItemDecoration(dividerItemDecoration);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //verifica se as condicoes de retorno foram cumpridas
        if(requestCode == NEW_ITEM_REQUEST) {
            if(resultCode == Activity.RESULT_OK) {
                //cria um objeto de MyItem para guardar os dados do item
                MyItem myItem = new MyItem();

                //obtem os dados retornados por NewItemActivity (titulo e descricao e foto) e os guarda dentro de myItem
                myItem.title = data.getStringExtra("title");
                myItem.description = data.getStringExtra("description");
                myItem.photo = data.getData();

                //adiciona o item a uma lista de itens
                itens.add(myItem);
                myAdapter.notifyItemInserted(itens.size()-1);
            }
        }
    }
}