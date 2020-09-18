package com.example.core.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

public class ConnectivityIntecepter implements Interceptor {

    Context context;

    public ConnectivityIntecepter(Context context){
        this.context = context;
    }

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Boolean isConnected = false;
        ConnectivityManager cm = (android.net.ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        Network networkCapabilities = cm.getActiveNetwork();
        NetworkCapabilities activeNetwork = cm.getNetworkCapabilities(networkCapabilities);
        if (activeNetwork != null) {
            if (activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)){
                isConnected = true;
            }else if (activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)){
                isConnected = true;
            }else if (activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)){
                isConnected = true;
            }else {
                isConnected = false;
            }
        }
        if (!isConnected){
            throw new NoConnectivityException();
        }else {
            return chain.proceed(chain.request());
        }
    }
}
