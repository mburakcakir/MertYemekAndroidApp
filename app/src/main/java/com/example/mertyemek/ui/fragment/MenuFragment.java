package com.example.mertyemek.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mertyemek.R;
import com.example.mertyemek.di.DynamicConstants;
import com.example.mertyemek.model.MenuModel;
import com.example.mertyemek.util.ContactUtils;

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
    ImageView imgPrice;
    Button btnPrice, btnOrder;
    LinearLayout linearLayout;
    RadioGroup rgOrder;

    ContactUtils contactUtils;

    WebView webview;
    View menuView;
    TextView txtWebMenu;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        menuView=inflater.inflate(R.layout.fragment_menu, container, false);
        imgPrice = menuView.findViewById(R.id.imgPrice);
        btnPrice = menuView.findViewById(R.id.btnPrice);
        btnOrder = menuView.findViewById(R.id.btnOrder);
        imgPrice.setVisibility(View.INVISIBLE);
        linearLayout = menuView.findViewById(R.id.check);
        linearLayout.setVisibility(View.INVISIBLE);
        rgOrder = menuView.findViewById(R.id.rgOrder);



        btnPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    imgPrice.setVisibility(View.VISIBLE);
                linearLayout.setVisibility(View.INVISIBLE);

            }
        });

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout.setVisibility(View.VISIBLE);
                imgPrice.setVisibility(View.INVISIBLE);
            }
        });
/*
        int call=rgOrder.getCheckedRadioButtonId();
        Toast.makeText(getContext(), Integer.toString(call), Toast.LENGTH_SHORT).show();
        switch(call)

        {
            case R.id.rbCall: { contactUtils.makeCall(getActivity()); break; }

            case R.id.rbWhatsapp: { contactUtils.connectWhatsapp(getActivity()); break;}
        }
*/

        rgOrder.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.rbCall:
                        contactUtils.makeCall(getActivity());
                        break;
                    case R.id.rbWhatsapp:
                        contactUtils.connectWhatsapp(getActivity());
                        break;
                }
            }
        });





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


