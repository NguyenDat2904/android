package com.example.appnhac.Adpter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appnhac.Activity.PlayNhacActivity;
import com.example.appnhac.Model.Baihat;
import com.example.appnhac.R;
import com.example.appnhac.Service.APIService;
import com.example.appnhac.Service.Dataservice;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchAdapter extends  RecyclerView.Adapter<SearchAdapter.ViewHolder>{

    Context context; //gan phan khai bao vao` de no gan data vao
    ArrayList<Baihat> mangbaihat;

    public SearchAdapter(Context context, ArrayList<Baihat> mangbaihat) {
        this.context = context;
        this.mangbaihat = mangbaihat;
    }

    @NonNull
    @Override
    //Anh xa va gan layout cho moi dong items
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.dong_search, parent, false); //dinh nghia lai moi dong cua layout
        return new ViewHolder(view);
    }

    @Override
    //data gan cho moi view ben tren
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    Baihat baihat = mangbaihat.get(position);
    holder.txttensearch.setText(baihat.getTenbaihat());
    holder.txtcasisearch.setText(baihat.getCasi());
    Picasso.with(context).load(baihat.getHinhbaihat()).into(holder.imgbaihat);
    }

    @Override
    public int getItemCount() {
        return mangbaihat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txttensearch, txtcasisearch;
        ImageView imgbaihat, imgluothich;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txttensearch = itemView.findViewById(R.id.textviewsearchtenbaihat); //Khai bao
            txtcasisearch = itemView.findViewById(R.id.textviewsearchtencasi);
            imgbaihat = itemView.findViewById(R.id.imageviewsearch);
            imgluothich = itemView.findViewById(R.id.imageviewsearchluotthich);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, PlayNhacActivity.class); //chuyen qua man hinh Play
                    intent.putExtra("cakhuc", mangbaihat.get(getPosition()));
                    context.startActivity(intent);
                }
            });
            imgluothich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    imgluothich.setImageResource(R.drawable.iconloved);
                    Dataservice dataservice = APIService.getService();// tuong tac voi sever
                    Call<String> callback = dataservice.Updateluotthich("1",mangbaihat.get(getPosition()).getIdbaihat());
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String ketqua = response.body();
                            if (ketqua.equals("Success")){
                                Toast.makeText(context, "Bạn đã thích bài hát này", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(context, "Bạn chưa thích bài hát này", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                    imgluothich.setEnabled(false);
                }
            });
        }
    }
}
//Gan gia tri vao de thuc hien gan data vao moi dong item trong rycle view