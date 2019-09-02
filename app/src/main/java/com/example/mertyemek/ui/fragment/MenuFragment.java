package com.example.mertyemek.ui.fragment;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.example.mertyemek.R;
import com.example.mertyemek.util.MenuUtils;
import java.io.IOException;


public class MenuFragment extends Fragment implements View.OnClickListener{
    public static String URL="https://www.w3.org/services/html2txt?url=https%3A%2F%2Fmertyemek.net%2Fgunluk-menuler-2-3-2%2F";
    ImageView imgPrice;
    Button btnPrice, btnOrder;
    LinearLayout linearLayout;
    RadioGroup rgOrder;
    View menuView;
    TextView txtDate;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        menuView=inflater.inflate(R.layout.fragment_menu, container, false);
        initAll();

        MenuUtils.sdkControl();
        MenuUtils.checkRadioGroup(getActivity(),rgOrder);
        MenuUtils.getCurrentDate(txtDate);

        try {
            if(MenuUtils.control==false)
            MenuUtils.getHtml(URL);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        linearLayout = menuView.findViewById(R.id.check);
        txtDate = menuView.findViewById(R.id.txtDate);
        rgOrder = menuView.findViewById(R.id.rgOrder);

        initListeners();
        initVisible();
    }

    public void initListeners() {
        btnPrice.setOnClickListener(this);
        btnOrder.setOnClickListener(this);
    }

    public void initVisible() {
        imgPrice.setVisibility(View.INVISIBLE);
        linearLayout.setVisibility(View.INVISIBLE);
    }
}