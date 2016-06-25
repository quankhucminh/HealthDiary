package com.quankm.healthdiary;


import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
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

    private LinearLayout content_panel_bp;
    private LinearLayout title_panel_bp;
    private ImageButton btnExpandBP;
    private LinearLayout content_panel_weight;
    private LinearLayout title_panel_weight;
    private ImageButton btnExpandWeight;
    private LinearLayout content_panel_bs;
    private LinearLayout title_panel_bs;
    private ImageButton btnExpandBS;
    private LinearLayout content_panel_pres;
    private LinearLayout title_panel_pres;
    private ImageButton btnExpandPres;

    public FragmentMain() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        btnExpandBP = (ImageButton) root.findViewById(R.id.btnExpandBP);
        btnExpandBP.setOnClickListener(this);
        content_panel_bp = (LinearLayout) root.findViewById(R.id.content_panel_bp);
        title_panel_bp = (LinearLayout) root.findViewById(R.id.title_panel_bp);
        content_panel_bp.setTag("expanded");

        btnExpandWeight = (ImageButton) root.findViewById(R.id.btnExpandWeight);
        btnExpandWeight.setOnClickListener(this);
        content_panel_weight = (LinearLayout) root.findViewById(R.id.content_panel_weight);
        title_panel_weight = (LinearLayout) root.findViewById(R.id.title_panel_weight);
        content_panel_weight.setTag("expanded");

        btnExpandBS = (ImageButton) root.findViewById(R.id.btnExpandBS);
        btnExpandBS.setOnClickListener(this);
        content_panel_bs = (LinearLayout) root.findViewById(R.id.content_panel_bs);
        title_panel_bs = (LinearLayout) root.findViewById(R.id.title_panel_bs);
        content_panel_bs.setTag("expanded");

        btnExpandPres = (ImageButton) root.findViewById(R.id.btnExpandPres);
        btnExpandPres.setOnClickListener(this);
        content_panel_pres = (LinearLayout) root.findViewById(R.id.content_panel_pres);
        title_panel_pres = (LinearLayout) root.findViewById(R.id.title_panel_pres);
        content_panel_pres.setTag("expanded");

        return root;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnExpandBP:
                togglePanel(title_panel_bp,content_panel_bp,btnExpandBP);
                break;
            case R.id.btnExpandWeight:
                togglePanel(title_panel_weight,content_panel_weight,btnExpandWeight);
                break;
            case R.id.btnExpandBS:
                togglePanel(title_panel_bs,content_panel_bs,btnExpandBS);
                break;
            case R.id.btnExpandPres:
                togglePanel(title_panel_pres,content_panel_pres,btnExpandPres);
                break;
        }

    }

    private void togglePanel(LinearLayout title_panel, LinearLayout content_panel, ImageButton btnExpandCollapse) {
        if(content_panel.getTag().toString().equalsIgnoreCase("collapsed")){
            CollapseExpandPanel.expand(content_panel);
            content_panel.setTag("expanded");
            title_panel.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.background_rect_half_rounded));
            btnExpandCollapse.setImageResource(R.drawable.ic_arrow_up_drop_circle_white_24dp);
        }
        else if(content_panel.getTag().toString().equalsIgnoreCase("expanded")) {
            CollapseExpandPanel.collapse(content_panel);
            content_panel.setTag("collapsed");
            title_panel.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.background_rect_rounded));
            btnExpandCollapse.setImageResource(R.drawable.ic_arrow_down_drop_circle_white_24dp);
        }
    }
}
