package net.ivanvega.miclientehttpp87a;


import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by CHENAO on 6/08/2017.
 */

public class UsuariosImagenUrlAdapter extends RecyclerView.Adapter<UsuariosImagenUrlAdapter.UsuariosHolder>{

    List<Usuario> listaUsuarios;
    //  RequestQueue request;
    Context context;


    public UsuariosImagenUrlAdapter(List<Usuario> listaUsuarios, Context context) {
        this.listaUsuarios = listaUsuarios;
        this.context=context;
        //  request= Volley.newRequestQueue(context);
    }

    @Override
    public UsuariosHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.usuarios_list_image,parent,false);
        RecyclerView.LayoutParams layoutParams=new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);
        return new UsuariosHolder(vista);
    }

    @Override
    public void onBindViewHolder(UsuariosHolder holder, int position) {

        holder.txtName.setText(listaUsuarios.get(position).getName().toString());


        if (listaUsuarios.get(position).getImageurl()!=null){
            //
            cargarImagenWebService(listaUsuarios.get(position).getImageurl(),holder);
        }else{

        }
    }

    private void cargarImagenWebService(String rutaImagen, final UsuariosHolder holder) {

        String ip=context.getString(R.string.ip);

        String urlImagen=rutaImagen;

        ImageRequest imageRequest=new ImageRequest(urlImagen, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                holder.imagen.setImageBitmap(response);
            }
        }, 0, 0, ImageView.ScaleType.CENTER, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,"Error al cargar la imagen",Toast.LENGTH_SHORT).show();
            }
        });
        //request.add(imageRequest);
        VolleySingleton.getIntanciaVolley(context).addToRequestQueue(imageRequest);
    }

    @Override
    public int getItemCount() {
        return listaUsuarios.size();
    }

    public class UsuariosHolder extends RecyclerView.ViewHolder{

        TextView txtName;
        ImageView imagen;

        public UsuariosHolder(View itemView) {
            super(itemView);

            txtName= (TextView) itemView.findViewById(R.id.idName);

            imagen=(ImageView) itemView.findViewById(R.id.idImagen);
        }
    }
}
