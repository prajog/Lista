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

        //obtemos o RecyclerView (ele serve para criar uma lista generica)
        RecyclerView rvItens = findViewById(R.id.rvItens);

        // Ensina o RecycleView a construir e preencher a lista

        //cria o myAdapter
        myAdapter = new MyAdapter(this, itens);
        //seta o myAdapter dentro do RecycleView
        rvItens.setAdapter(myAdapter);

        //metodo que indica ao rv que nao ha variacao de tamanho entre os itens da lista
        //Isso faz com que a construcao da lista seja mais rapida
        rvItens.setHasFixedSize(true);

        //cria um gerenciador de layout do tipo linear
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        //seta o gerenciador de layout no RecycleView
        rvItens.setLayoutManager(layoutManager);

        //cria um decorador para a lista, que consiste apenas em uma linha separando cada item
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvItens.getContext(), DividerItemDecoration.VERTICAL);
        //seta o decorador no rv
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

                //adiciona o item a uma lista de itens que eh repassada para o Adapter
                itens.add(myItem);
                //notifica o Adapter para que o RecycleView se atualize e exiba o novo item
                myAdapter.notifyItemInserted(itens.size()-1);
            }
        }
    }
}