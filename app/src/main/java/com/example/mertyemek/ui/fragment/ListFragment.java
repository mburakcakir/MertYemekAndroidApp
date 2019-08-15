package com.example.mertyemek.ui.fragment;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.mertyemek.model.UserModel;
import com.example.mertyemek.networking.Service;
import com.example.mertyemek.ui.adapter.UserAdapter;
import com.example.mertyemek.R;
import java.util.ArrayList;
import java.util.List;

// object printer by Muhammed Çağatay
import com.cagataymuhammet.objectprinter.ObjectPrinter;

// retrofit
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ListFragment extends Fragment {

    View listView;

    List<UserModel> usersList=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

      getUsersList();

      listView = inflater.inflate(R.layout.fragment_list, container, false);

      return listView;
    }

    void generateUserList(List<UserModel> empDataList)
    {
        RecyclerView   recyclerView = listView.findViewById(R.id.recyclerView);
        UserAdapter adapter = new UserAdapter(empDataList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
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
