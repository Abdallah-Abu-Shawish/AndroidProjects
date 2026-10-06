package com.example.roomdatabasetest;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    boolean b = true;
   SharedPreferences preferences;

    boolean flag;
    AppRoomDataBase dataBase;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        preferences  = getContext().getSharedPreferences("remember",MODE_PRIVATE);
        flag  = preferences.getBoolean("isRemember",false);
        dataBase = AppRoomDataBase.getDatabase(getContext());

    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        User user = dataBase.userDao().getUser(dataBase.userInDao().getAllUsersIn().getUserIn_id());

        TextView tvName = v.findViewById(R.id.profile_name);
        TextView tvEmail = v.findViewById(R.id.profile_email);
        TextView tvUsername = v.findViewById(R.id.profile_username);
        EditText tvPassword = v.findViewById(R.id.profile_password);
        ImageView userImage = v.findViewById(R.id.profile_img);





//        try {
//            ContentResolver resolver = getContext().getContentResolver();
//            resolver.openInputStream(user.getImage()).close();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        if (user.getImage() != null){
//            userImage.setImageURI(user.getImage());
//        }
        tvName.setText(user.getName());
        tvEmail.setText(user.getEmail());
        tvUsername.setText(user.getUsername());
        tvPassword.setText(user.getPassword());


        if (flag){
            v.findViewById(R.id.btn_logout).setVisibility(View.VISIBLE);
        }

        v.findViewById(R.id.btn_logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.remove("isRemember");
                editor.apply();
                startActivity(new Intent(getActivity(), SignInActivity.class));
                getActivity().finish();
                onDestroyView();

            }
        });

        tvPassword.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    Drawable drawableEnd = tvPassword.getCompoundDrawables()[2];
                    if (drawableEnd != null && motionEvent.getRawX() >= (tvPassword.getRight() - drawableEnd.getBounds().width())){
                        if (b){
                            tvPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                            tvPassword.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_baseline_remove_red_eye_24,0);
                            b = false;
                        }else {
                            tvPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                            tvPassword.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.baseline_visibility_off_24,0);
                            b = true;
                        }
                        tvPassword.setCursorVisible(false);
                        tvPassword.setFocusable(false);
                        tvPassword.setFocusableInTouchMode(false);
                        return true;
                    }
                }
                return false;
            }
        });


        v.findViewById(R.id.btn_show_my_scores).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ShowUserScoresActivity.class);
//                intent.putExtra("user_id_",user.getId());
                startActivity(intent);
            }
        });
        return v;
    }
}