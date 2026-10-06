package com.example.roomdatabasetest;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShowAllScoresFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShowAllScoresFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    UserAdapter adapter;

    AppRoomDataBase dataBase;

    public ShowAllScoresFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ShowAllScoresFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ShowAllScoresFragment newInstance(String param1, String param2) {
        ShowAllScoresFragment fragment = new ShowAllScoresFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        dataBase = AppRoomDataBase.getDatabase(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_show_all_scores, container, false);
        List<User> users = dataBase.userDao().getAllUsers();
        adapter = new UserAdapter(getContext(),users);
        RecyclerView recyclerView = v.findViewById(R.id.rv_users_scores);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        return v;
    }
}