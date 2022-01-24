package com.example.appnhac.Service;

public class APIService {

    private static String base_url = "https://nghenhac29.000webhostapp.com/Server/";

    public static Dataservice getService(){
        return APIRetrofitClient.getClient(base_url).create(Dataservice.class);
    }
}
