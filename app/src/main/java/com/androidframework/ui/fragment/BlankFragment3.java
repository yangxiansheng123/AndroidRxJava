package com.androidframework.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.androidframework.R;
import com.androidframework.ui.activity.ListActivity;

/**
 *
 */
public class BlankFragment3 extends Fragment {

    protected View rootView;
    protected TextView textView4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         rootView = inflater.inflate(R.layout.fragment_blank3, container, false);
        initView(rootView);
        textView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ListActivity.class));
            }
        });
        return rootView;
    }

    private void initView(View rootView) {
        textView4 = (TextView) rootView.findViewById(R.id.textView4);
    }
}
