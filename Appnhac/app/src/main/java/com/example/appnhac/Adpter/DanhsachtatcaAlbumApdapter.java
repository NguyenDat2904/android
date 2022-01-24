package com.example.appnhac.Adpter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appnhac.Activity.DanhsachbaihatActivity;
import com.example.appnhac.Model.Album;
import com.example.appnhac.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhsachtatcaAlbumApdapter extends RecyclerView.Adapter<DanhsachtatcaAlbumApdapter.ViewHolder>{

    Context context;
    ArrayList<Album> albumArrayList;

    public DanhsachtatcaAlbumApdapter(Context context, ArrayList<Album> albumArrayList) {
        this.context = context;
        this.albumArrayList = albumArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_tat_ca_album, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    Album album = albumArrayList.get(position);
        Picasso.with(context).load(album.getHinhAlbum()).into(holder.imgtatcaalbum);
        holder.txttatcaalbum.setText(album.getTenAlbum());
        holder.txtcasitatcaalbum.setText(album.getTencasiAlbum());
    }

    @Override
    public int getItemCount() {
        return albumArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgtatcaalbum;
        TextView txttatcaalbum, txtcasitatcaalbum;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgtatcaalbum = itemView.findViewById(R.id.imageviewdanhsachtatcaalbum);
            txttatcaalbum = itemView.findViewById(R.id.textviewtendanhsachtatcaalbum);
            txtcasitatcaalbum = itemView.findViewById(R.id.textviewtencasitatcaalbum);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DanhsachbaihatActivity.class);
                    intent.putExtra("album", albumArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
