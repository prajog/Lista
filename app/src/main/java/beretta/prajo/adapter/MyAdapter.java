package beretta.prajo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import beretta.prajo.activity.MainActivity;
import beretta.prajo.lista.R;
import beretta.prajo.model.MyItem;

public class MyAdapter extends RecyclerView.Adapter{
    MainActivity mainActivity;
    List<MyItem> itens;

    public MyAdapter(MainActivity mainActivity, List<MyItem> itens){
        this.mainActivity = mainActivity;
        this.itens = itens;
    }

    //metodo que cria elementos de interface para um item
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflador de layout que le o arquivo xml do item
        LayoutInflater inflater = LayoutInflater.from(mainActivity);
        //o inflador cria os elementos de interface referentes a um item e os guarda dentro de um objeto do tipo View
        View v = inflater.inflate(R.layout.item_list, parent, false);
        //o objeto do tipo View eh guardado dentro de um objeto do tipo MyViewHolder, que eh retornado pela funcao
        return new MyViewHolder(v);
    }

    //metodo que preenche a interface de usuario com os dados de um item
    /* recebe dois parametros: holder (objeto do tipo ViewHolder qeu guarda os itens de interface criados na execucao
    do metodo anterior); position (indicador de qual elemento da lista deve ser usado para preencher o item)*/
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //obtem o item a ser usado para preencher a UI
        MyItem myItem = itens.get(position);

        //obtem o objeto do tipo View que esta guardado dentro do ViewHolder
        View v = holder.itemView;

        //preenche os dados do item (foto, titulo e descricao) dentro da interface
        ImageView imvfoto = v.findViewById(R.id.imvPhoto);
        imvfoto.setImageBitmap(myItem.photo);

        TextView tvTitle = v.findViewById(R.id.tvTitle);
        tvTitle.setText(myItem.title);

        TextView tvdesc = v.findViewById(R.id.tvDesc);
        tvdesc.setText(myItem.description);
    }

    public int getItemCount() {
        return itens.size();
    }


}
