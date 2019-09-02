package com.example.mertyemek.util;

import android.app.Activity;
import android.os.StrictMode;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mertyemek.R;
import com.example.mertyemek.di.Constants;
import com.example.mertyemek.di.DynamicConstants;
import com.example.mertyemek.model.MenuModel;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MenuUtils {

    public static String URL="https://www.w3.org/services/html2txt?url=https%3A%2F%2Fmertyemek.net%2Fgunluk-menuler-2-3-2%2F";
    public static boolean control = false;
    public static void sdkControl() {
       int SDK_INT = android.os.Build.VERSION.SDK_INT;
       if (SDK_INT > 8)
       {
           StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                   .permitAll().build();
           StrictMode.setThreadPolicy(policy);
       }
   }

    public static void getHtml(String url) throws ClientProtocolException, IOException
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
        control = true;
    }

    public static void clickButonItems(Activity activity, View view, ImageView imageView, LinearLayout linearLayout) {
        switch (view.getId()) {
            case R.id.btnOrder:
                orderButonClick(activity, imageView,linearLayout);
                break;
            case R.id.btnPrice:
                priceButonClick(activity, imageView,linearLayout);
                break;

        }
    }

    public static void checkRadioGroup(final Activity activity, RadioGroup radioGroup) {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.rbCall:
                        ContactUtils.makeCall(activity);
                        break;
                    case R.id.rbWhatsapp:
                        ContactUtils.connectWhatsapp(activity);
                        break;
                    case R.id.rbEmail:
                        ContactUtils.sendEmail(activity);
                        break;
                }
            }
        });
    }

    static void priceButonClick(Activity activity, ImageView imageView, LinearLayout linearLayout) {
        imageView.setVisibility(View.VISIBLE);
        linearLayout.setVisibility(View.INVISIBLE);
    }

    static void orderButonClick(Activity activity,ImageView imageView, LinearLayout linearLayout) {
        imageView.setVisibility(View.INVISIBLE);
        linearLayout.setVisibility(View.VISIBLE);
    }

    public static void getCurrentDate(TextView textView) {
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        textView.setText(currentDate);
    }
}
