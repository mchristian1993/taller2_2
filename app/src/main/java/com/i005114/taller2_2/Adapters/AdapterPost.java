package com.i005114.taller2_2.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.i005114.taller2_2.Comment;
import com.i005114.taller2_2.MainActivity;
import com.i005114.taller2_2.Models.ModelPosts;
import com.i005114.taller2_2.Models.Modeluser;
import com.i005114.taller2_2.Posts;
import com.i005114.taller2_2.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CHRISTIAN on 14/10/2017.
 */

public class AdapterPost extends RecyclerView.Adapter<AdapterPost.ViewHolder> {

    List<ModelPosts> modelPostses = new ArrayList<>();
    Context context;

    public AdapterPost(List<ModelPosts> modelPostses, Context context) {
        this.modelPostses = modelPostses;
        this.context = context;
    }

    @Override
    public AdapterPost.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.itempost, parent, false);
        ViewHolder viewHolder = new ViewHolder(item);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(AdapterPost.ViewHolder holder, int position) {
        String i[] = new String[10];
        i[0] = "https://t2.ea.ltmcdn.com/es/images/7/7/2/img_los_10_animales_mas_rapidos_del_mundo_21277_600.jpg";
        i[1] = "https://t1.ea.ltmcdn.com/es/images/6/0/0/img_5_cosas_graciosas_que_hacen_los_perros_22006_600.jpg";
        i[2] = "https://t1.ea.ltmcdn.com/es/images/8/8/2/img_remedios_caseros_para_la_gastritis_en_perros_21288_600.jpg";
        i[3] = "https://t1.ea.ltmcdn.com/es/images/8/4/3/img_recetas_de_tartas_para_perros_22348_600.jpg";
        i[4] = "https://i.pinimg.com/736x/f0/b6/3c/f0b63ca95176f0bb86c81e96cc1643c7--lion-blanc-tatoo.jpg";
        i[5] = "https://i.pinimg.com/originals/ef/f7/2e/eff72e6fa4dbf14eca6211e3e75aebc8.jpg";
        i[6] = "https://animalesmascotas.com/wp-content/uploads/2013/10/Gatos-Azul-Ruso-comportamiento-600x600.jpg";
        i[7] = "http://bncdn02.mundotkm.com/2016/08/escudo4.jpg";
        i[8] = "https://t1.ea.ltmcdn.com/es/images/3/6/9/img_como_educar_a_un_gato_desde_que_es_pequeno_20963_600.jpg";
        i[9] = "https://t1.ea.ltmcdn.com/es/images/1/5/1/img_los_10_gatos_mas_raros_del_mundo_22151_600.jpg";
        String b = i[(int) (Math.random() * 9)];
        String a;

        holder.id.setText(modelPostses.get(position).getId());
        holder.idusuario.setText(modelPostses.get(position).getIdusuario());
        holder.contenido.setText(modelPostses.get(position).getContenido());

        holder.titulo.setText(modelPostses.get(position).getTitulo());


        a = modelPostses.get(position).getUrl();
        Picasso.with(context).load(b).into(holder.url);
    }

    @Override
    public int getItemCount() {
        return modelPostses.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView url;
        TextView id;
        TextView idusuario;
        TextView titulo;
        TextView contenido;


        public ViewHolder(View item) {
            super(item);
            item.setOnClickListener(this);
            url = (ImageView) item.findViewById(R.id.img1);
            id = (TextView) item.findViewById(R.id.txtide2);
            idusuario = (TextView) item.findViewById(R.id.idusuario);
            titulo = (TextView) item.findViewById(R.id.txtide3);
            contenido = (TextView) item.findViewById(R.id.txtide4);

        }
        public void onClick(View view) {
            Context contextItem = view.getContext();
            Intent intent = new Intent(context, Comment.class);
           intent.putExtra("postId", modelPostses.get(getLayoutPosition()).getId());
            contextItem.startActivity(intent);


            //String valor = Integer.toString(albumModelList.get(getLayoutPosition()).getId());
            //Toast.makeText(contextItem, valor, Toast.LENGTH_SHORT).show();
        }
    }
}
