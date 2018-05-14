package com.toutools.me.toutools.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.toutools.me.toutools.R;

import static com.facebook.AccessTokenManager.TAG;

/**
 * Created by User on 18/1/2561.
 */

public class NotiFragment extends Fragment implements View.OnClickListener {

Button subscribe;
Button unsubscribe;
Button logtoken;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_noti, container, false);
        initInstances(rootView);
        return rootView;

    }

    private void initInstances(View rootView) {

        rootView.findViewById(R.id.subscribeButton).setOnClickListener(this);
        rootView.findViewById(R.id.unsubscribeButton).setOnClickListener(this);
        rootView.findViewById(R.id.logTokenButton).setOnClickListener(this);

    }

    @SuppressLint("LongLogTag")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.subscribeButton:
                FirebaseMessaging.getInstance().subscribeToTopic("droiddev/news");
                Log.d(TAG, "SubscribeToTopic");
                Toast.makeText(getActivity(), "SubscribeToTopic", Toast.LENGTH_SHORT).show();
                break;
            case R.id.unsubscribeButton:
                FirebaseMessaging.getInstance().unsubscribeFromTopic("droiddev/news");
                Log.d(TAG, "UnsubscribeFromTopic");
                Toast.makeText(getActivity(), "UnsubscribeFromTopic", Toast.LENGTH_SHORT).show();
                break;
            case R.id.logTokenButton:
                String token = FirebaseInstanceId.getInstance().getToken();
                Log.d(TAG, "Token : " + token);
                Toast.makeText(getActivity(), "Token : " + token, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
