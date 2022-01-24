package com.example.appnhac.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.appnhac.Activity.DanhsachbaihatActivity;
import com.example.appnhac.Activity.DanhsachtatcachudeActivity;
import com.example.appnhac.Activity.DanhsachtheloaitheochudeActivity;
import com.example.appnhac.Model.ChuDe;
import com.example.appnhac.Model.ChudevaTheloai;
import com.example.appnhac.Model.TheLoai;
import com.example.appnhac.R;
import com.example.appnhac.Service.APIService;
import com.example.appnhac.Service.Dataservice;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_ChudeTheloai extends Fragment {
    View view;
    HorizontalScrollView horizontalScrollView;
    TextView txtxemthemchude;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chudetheloai,container,false);
        horizontalScrollView = view.findViewById(R.id.horizontalStrollview);
        txtxemthemchude = view.findViewById((R.id.textviewxemthem));
        txtxemthemchude.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DanhsachtatcachudeActivity.class);
                startActivity(intent);
            }
        });
        GetData();
        return view;
    }

    private void GetData() {
    Dataservice dataservice = APIService.getService();
    Call<ChudevaTheloai> callback = dataservice.GetChudevaTheloai();
    callback.enqueue(new Callback<ChudevaTheloai>() {
        @Override
        public void onResponse(Call<ChudevaTheloai> call, Response<ChudevaTheloai> response) {
            ChudevaTheloai chudevaTheloai = response.body();

            final ArrayList<ChuDe> chuDeArrayList = new ArrayList<>();
            chuDeArrayList.addAll(chudevaTheloai.getChuDe());

            final ArrayList<TheLoai> theLoaiArrayList = new ArrayList<>();
            theLoaiArrayList.addAll(chudevaTheloai.getTheLoai());

            LinearLayout linearLayout = new LinearLayout(getActivity());
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);

            LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(580, 250);
            layout.setMargins(10, 20, 10, 30);
            for(int i = 0; i < (chuDeArrayList.size()); i++){
                CardView cardView = new CardView(getActivity());
                cardView.setRadius(10);
                ImageView imageView = new ImageView(getActivity());
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                if (chuDeArrayList.get(i).getHinhChuDe() != null){
                    Picasso.with(getActivity()).load(chuDeArrayList.get(i).getHinhChuDe()).into(imageView);
                }
                cardView.setLayoutParams(layout);
                cardView.addView(imageView);
                linearLayout.addView(cardView);
                int finalI = i;
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), DanhsachtheloaitheochudeActivity.class);
                        intent.putExtra("chude", chuDeArrayList.get(finalI));
                        startActivity(intent);
                    }
                });
            }
            for(int j = 0; j < (chuDeArrayList.size()); j++){
                CardView cardView = new CardView(getActivity());
                cardView.setRadius(10);
                ImageView imageView = new ImageView(getActivity());
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                if (theLoaiArrayList.get(j).getHinhTheLoai() != null){
                    Picasso.with(getActivity()).load(theLoaiArrayList.get(j).getHinhTheLoai()).into(imageView);
                }
                cardView.setLayoutParams(layout);
                cardView.addView(imageView);
                linearLayout.addView(cardView);
                int finalJ = j;
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), DanhsachbaihatActivity.class);
                        intent.putExtra("idtheloai", theLoaiArrayList.get(finalJ));
                        startActivity(intent);
                    }
                });
            }
            horizontalScrollView.addView(linearLayout);
        }

        @Override
        public void onFailure(Call<ChudevaTheloai> call, Throwable t) {

        }
    });
    }
}
