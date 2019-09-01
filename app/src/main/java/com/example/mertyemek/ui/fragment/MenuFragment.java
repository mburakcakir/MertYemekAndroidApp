package com.example.mertyemek.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.cagataymuhammet.objectprinter.ObjectPrinter;
import com.example.mertyemek.R;
import com.example.mertyemek.di.Constants;
import com.example.mertyemek.di.DynamicConstants;
import com.example.mertyemek.model.MenuModel;
import com.google.gson.JsonObject;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;

public class MenuFragment extends Fragment {

    String URL="https://www.w3.org/services/html2txt?url=https%3A%2F%2Fmertyemek.net%2Fgunluk-menuler-2-3-2%2F";


    WebView webview;
    View menuView;
    TextView txtWebMenu;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        menuView=inflater.inflate(R.layout.fragment_menu, container, false);
        txtWebMenu=(TextView)menuView.findViewById(R.id.txtWebMenu);

        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }


        try {
            getHtml(URL);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //txtWebMenu.setText(s);
        return menuView;
    }


    void   getHtml(String url) throws ClientProtocolException, IOException
    {
        HttpClient httpClient = new DefaultHttpClient();
        HttpContext localContext = new BasicHttpContext();
        HttpGet httpGet = new HttpGet(url);
        HttpResponse response = httpClient.execute(httpGet, localContext);

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        response.getEntity().getContent()
                )
        );


        String result = "";
        String line = null;

        while ((line = reader.readLine()) != null){

            result += line + "\n";
        }
/*
        result=result.replace(Constants.start,"");
        result= result.replace(Constants.stop,"");
        result= result.replace("\n ","");
*/

        int indexOfNew = result.indexOf("Çorbalar");
        int indexOfIn = result.indexOf("[23]");
        result = result.substring(indexOfNew,indexOfIn-5);
        result= result.replace("\n ","");
        System.out.println(result);

        String finalStr=result;

      //  Log.e("CAGATAY_FINAL_STR",finalStr);

        BufferedReader reader2 = new BufferedReader(new StringReader(finalStr));
        BufferedReader reader3 = new BufferedReader(new StringReader(finalStr));


        ArrayList<MenuModel> tumListe = new ArrayList<>();
        String  line2=null;

        String  line3=null;
        while ((line3 = reader3.readLine()) != null){
            System.out.println("------"+line3+"----");
        }

        while ((line = reader2.readLine()) != null && !line.startsWith(" ")&& line.length()>3) {

            MenuModel menuModel = new MenuModel();
            menuModel.setMenuName(line);

            ArrayList<String> menus = new ArrayList<>();
            while ((line = reader2.readLine()) != null && line.startsWith("  ")) {
                menus.add(line);
            }
            menuModel.setMenuList(menus);
            tumListe.add(menuModel);
        }

        DynamicConstants.MENU_MODEL_LIST =tumListe;

    }

}


