package com.example.mertyemek.ui.fragment;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.mertyemek.di.DynamicConstants;
import com.example.mertyemek.model.UserModel;
import com.example.mertyemek.networking.Service;
import com.example.mertyemek.ui.adapter.ExpandableListAdapter;
import com.example.mertyemek.ui.adapter.MenuAdapter;
import com.example.mertyemek.ui.adapter.UserAdapter;
import com.example.mertyemek.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// object printer by Muhammed Çağatay
import com.cagataymuhammet.objectprinter.ObjectPrinter;
import com.example.mertyemek.util.MenuUtils;

// retrofit
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ListFragment extends Fragment {

    View listView;

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    MenuUtils menuUtils = new MenuUtils();


    List<UserModel> usersList=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

      listView = inflater.inflate(R.layout.expandable_listview, container, false);

        expListView = listView.findViewById(R.id.expandable_menu);

        prepareListData();

        listAdapter = new ExpandableListAdapter(getContext(), listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);


        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Toast.makeText(
                        getContext(),
                        listDataHeader.get(groupPosition)
                                + " : "
                                + listDataChild.get(
                                listDataHeader.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        });



      return listView;
    }




    void generateMenuList()
    {
        RecyclerView   recyclerView = listView.findViewById(R.id.recyclerView);
        MenuAdapter adapter = new MenuAdapter();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }


    void generateUserList(List<UserModel> empDataList)
    {
        RecyclerView   recyclerView = listView.findViewById(R.id.recyclerView);
        UserAdapter adapter = new UserAdapter(empDataList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }


    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        for(int i=0; i<8;i++) {
        listDataHeader.add(DynamicConstants.MENU_MODEL_LIST.get(i).getMenuName());
        listDataChild.put(listDataHeader.get(i), DynamicConstants.MENU_MODEL_LIST.get(i).getMenuList());
        }

    }



    void  getUsersList()
    {

        new Service().getServiceApi().getUsers().
                subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<UserModel>>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<UserModel> userModels) {
                        usersList=userModels;

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete()
                    {
                        if(usersList.size()>0)
                        {
                           ObjectPrinter.printJson(usersList);
                            generateUserList(usersList);
                        }

                    }
                });
    }

}
