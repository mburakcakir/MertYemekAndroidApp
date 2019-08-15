package com.example.mertyemek.ui.fragment;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.example.mertyemek.R;

public class MenuFragment extends Fragment {
    WebView webview;
    View menuView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        menuView=inflater.inflate(R.layout.fragment_menu, container, false);

        menuWebViewSet();

        return menuView;
    }

    public void menuWebViewSet() {
        // initialize
        webview = (WebView) menuView.findViewById(R.id.menuVW);

        // Enable Javascript
        webview.getSettings().setJavaScriptEnabled(true);

        // Force links and redirects to open in the WebView instead of in a browser
        webview.setWebViewClient(new WebViewClient());
        webview.clearCache(true);
        webview.getSettings().setDomStorageEnabled(true);
        webview.loadUrl("https://mertyemek.net/gunluk-menuler-2-3-2/");

    }


}
