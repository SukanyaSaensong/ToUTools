package com.toutools.me.toutools.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.toutools.me.toutools.R;
import com.toutools.me.toutools.activity.MenuFiveActivity;
import com.toutools.me.toutools.activity.MenuFourActivity;
import com.toutools.me.toutools.activity.MenuOneActivity;
import com.toutools.me.toutools.activity.MenuSixActivity;
import com.toutools.me.toutools.activity.MenuTwoActivity;
import com.toutools.me.toutools.activity.MenuThreeActivity;

public class CategoriesFragment extends Fragment implements View.OnClickListener {

    Button btn1, btn2, btn3, btn4, btn5, btn6;

    public CategoriesFragment() {
        super();

    }

    public static CategoriesFragment newInstance() {
        CategoriesFragment fragment = new CategoriesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_category, container, false);
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {
        btn1 = (Button) rootView.findViewById(R.id.btn1);
        btn2 = (Button) rootView.findViewById(R.id.btn2);
        btn3 = (Button) rootView.findViewById(R.id.btn3);
        btn4 = (Button) rootView.findViewById(R.id.btn4);
        btn5 = (Button) rootView.findViewById(R.id.btn5);
        btn6 = (Button) rootView.findViewById(R.id.btn6);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
    }


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    /*
     * Save Instance State Here
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Instance State here
    }

    /*
     * Restore Instance State Here
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            // Restore Instance State here
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                Intent intent = new Intent(getContext(), MenuOneActivity.class);
                startActivity(intent);
                break;
            case R.id.btn2:
                Intent intent2 = new Intent(getContext(), MenuTwoActivity.class);
                startActivity(intent2);
                break;
            case R.id.btn3:
                Intent intent3 = new Intent(getContext(), MenuThreeActivity.class);
                startActivity(intent3);
                break;
            case R.id.btn4:
                Intent intent4 = new Intent(getContext(),MenuFourActivity.class);
                startActivity(intent4);
                break;
            case R.id.btn5:
                Intent intent5 = new Intent(getContext(),MenuFiveActivity.class);
                startActivity(intent5);
                break;
            case R.id.btn6:
                Intent intent6 = new Intent(getContext(),MenuSixActivity.class);
                startActivity(intent6);
                break;
            default:
        }
    }

}

