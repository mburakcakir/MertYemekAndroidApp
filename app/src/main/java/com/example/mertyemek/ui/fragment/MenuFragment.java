package com.example.mertyemek.ui.fragment;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.mertyemek.R;
import com.example.mertyemek.util.MenuUtils;
import java.io.IOException;


public class MenuFragment extends Fragment implements View.OnClickListener,AdapterView.OnItemSelectedListener{
    ImageView imgPrice;
    Button btnPrice, btnOrder,btnSend;
    LinearLayout linearLayout;
    RadioGroup rgOrder;
    View menuView;
    TextView txtDate, txtPrice;
    double price;

    Spinner spinnerCorba, spinnerAna, spinnerYardimci, spinnerTatli,spinnerIcecek;
    ArrayAdapter<String> adapterCorba, adapterAna, adapterYardimci, adapterTatli,adapterIcecek;

    MenuUtils basicData = new MenuUtils();
    MenuUtils corbaData = new MenuUtils();
    MenuUtils anaData = new MenuUtils();
    MenuUtils tatliData = new MenuUtils();
    MenuUtils yardimciData = new MenuUtils();
    MenuUtils icecekData = new MenuUtils();

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        menuView=inflater.inflate(R.layout.fragment_menu, container, false);
        initAll();

        txtPrice.setText(Double.toString(price));
        MenuUtils.sdkControl();
      //  MenuUtils.checkRadioGroup(getActivity(),rgOrder);
       MenuUtils.getCurrentDate(txtDate);
        price -=20;

       btnSend.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               txtPrice.setText(Double.toString(price));
           }
       });

        getSpinnerHtmlData();

        setSpinnerAdapter();

        return menuView;
    }

    @Override
    public void onClick(View view) {
        MenuUtils.clickButonItems(getActivity(),view,imgPrice,linearLayout);
    }

    public void initAll() {

        imgPrice = menuView.findViewById(R.id.imgPrice);
        btnPrice = menuView.findViewById(R.id.btnPrice);
        btnOrder = menuView.findViewById(R.id.btnOrder);
        btnSend = menuView.findViewById(R.id.btnSend);
        linearLayout = menuView.findViewById(R.id.linearlayout);
        txtDate = menuView.findViewById(R.id.txtDate);
        txtPrice = menuView.findViewById(R.id.txtPrice);
        rgOrder = menuView.findViewById(R.id.rgOrder);

        spinnerCorba = menuView.findViewById(R.id.spinnerCorba);
        spinnerAna = menuView.findViewById(R.id.spinnerAna);
        spinnerYardimci = menuView.findViewById(R.id.spinnerYardimci);
        spinnerTatli = menuView.findViewById(R.id.spinnerTatli);
        spinnerIcecek = menuView.findViewById(R.id.spinnerIcecek);



        initListeners();
        initVisible();
    }

    public void setSpinnerAdapter() {
        adapterCorba = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, corbaData.menuData);
        spinnerCorba.setAdapter(adapterCorba);

        adapterAna = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, anaData.menuData);
        spinnerAna.setAdapter(adapterAna);

        adapterYardimci = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, yardimciData.menuData);
        spinnerYardimci.setAdapter(adapterYardimci);

        adapterTatli = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, tatliData.menuData);
        spinnerTatli.setAdapter(adapterTatli);

        adapterIcecek = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, icecekData.menuData);
        spinnerIcecek.setAdapter(adapterIcecek);

    }

    public void getSpinnerHtmlData() {
        try {
            if (MenuUtils.control == false)
                MenuUtils.loadData();

            corbaData.getHtml("ÇORBA", "ANA YEMEKLER");
            anaData.getHtml("ANA YEMEKLER", "YARDIMCILAR");
            yardimciData.getHtml("YARDIMCILAR", "TATLI ÇEŞİTLERİ");
            tatliData.getHtml("TATLI ÇEŞİTLERİ", "İÇECEKLER");
            icecekData.getHtml("İÇECEKLER", "[23]");
            basicData.getHtml("ÇORBA", "[23]");


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void initListeners() {
        btnPrice.setOnClickListener(this);
        btnOrder.setOnClickListener(this);
        btnSend.setOnClickListener(this);

        spinnerCorba.setOnItemSelectedListener(this);
        spinnerAna.setOnItemSelectedListener(this);
        spinnerYardimci.setOnItemSelectedListener(this);
        spinnerTatli.setOnItemSelectedListener(this);
        spinnerIcecek.setOnItemSelectedListener(this);
    }

    public void initVisible() {
        imgPrice.setVisibility(View.INVISIBLE);
        linearLayout.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        id = parent.getId();
    switch (parent.getId()) {
            case R.id.spinnerCorba:
                price +=5;
                //    ((TextView) parent.getChildAt(parent.getId())).setTextColor(getResources().getColor(R.color.colorSoup));
                break;
            case R.id.spinnerAna:
                price +=6;
            //    ((TextView) parent.getChildAt(position)).setTextColor(getResources().getColor(R.color.colorMain));
                break;
            case R.id.spinnerYardimci:
                price +=4;
                //   ((TextView) parent.getChildAt(position)).setTextColor(getResources().getColor(R.color.colorAux));
                break;
            case R.id.spinnerTatli:
                price +=2;
                //    ((TextView) parent.getChildAt(position)).setTextColor(getResources().getColor(R.color.colorDessert));
                break;
            case R.id.spinnerIcecek:
                price +=3;
                //    ((TextView) parent.getChildAt(position)).setTextColor(getResources().getColor(R.color.colorDrink));
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
            price =0;
    }
}