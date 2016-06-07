package com.quankm.healthdiary;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentLogin extends Fragment implements Button.OnClickListener {

    private Button btnSignUp;
    private Button btnSignIn;
    private EditText edtEmail;
    private EditText edtPassword;
    private ActivityLogin activity;


    public FragmentLogin() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_login, container, false);

        activity = (ActivityLogin) getActivity();
        btnSignUp = (Button) root.findViewById(R.id.btnSignUp);
        btnSignIn = (Button) root.findViewById(R.id.btnSignIn);
        edtEmail = (EditText) root.findViewById(R.id.edtEmail);
        edtPassword = (EditText) root.findViewById(R.id.edtPassword);

        btnSignIn.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSignIn:
                break;
            case R.id.btnSignUp:
                activity.displaySignUp();
                break;
        }
    }
}
