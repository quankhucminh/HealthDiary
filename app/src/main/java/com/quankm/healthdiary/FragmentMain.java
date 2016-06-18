package com.quankm.healthdiary;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.quankm.healthdiary.animations.CollapseExpandPanel;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMain extends Fragment implements View.OnClickListener {

    LinearLayout content_panel;

    public FragmentMain() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        ImageButton btnExpandCollapse = (ImageButton) root.findViewById(R.id.btnExpandCollapse);
        btnExpandCollapse.setOnClickListener(this);
        content_panel = (LinearLayout) root.findViewById(R.id.content_panel);

        return root;
    }

    @Override
    public void onClick(View v) {
        CollapseExpandPanel.expand(content_panel);
    }
}
