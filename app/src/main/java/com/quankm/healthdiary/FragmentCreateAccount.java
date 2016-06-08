package com.quankm.healthdiary;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.quankm.healthdiary.dao.UserDAO;
import com.quankm.healthdiary.pojo.User;
import com.quankm.healthdiary.utils.DateTimeUtil;
import com.quankm.healthdiary.utils.PasswordUtil;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentCreateAccount extends Fragment implements Button.OnClickListener {

    private Button btnSignUp;
    private EditText edtEmail;
    private EditText edtPassword;
    private EditText edtPasswordConfirm;
    private EditText edtFirstName;
    private EditText edtLastName;
    private EditText edtDOB;
    private RadioGroup rdgSex;
    private ActivityLogin activity;

    public FragmentCreateAccount() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_create_account, container, false);

        activity = (ActivityLogin) getActivity();
        btnSignUp = (Button) root.findViewById(R.id.btnSignUp);
        edtEmail = (EditText) root.findViewById(R.id.edtEmail);
        edtPassword = (EditText) root.findViewById(R.id.edtPassword);
        edtPasswordConfirm = (EditText) root.findViewById(R.id.edtPasswordConfirm);
        edtFirstName = (EditText) root.findViewById(R.id.edtFirstName);
        edtLastName = (EditText) root.findViewById(R.id.edtLastName);
        edtDOB = (EditText) root.findViewById(R.id.edtDOB);
        rdgSex = (RadioGroup) root.findViewById(R.id.rdgSex);

        btnSignUp.setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSignUp:

                String email = edtEmail.getText().toString();
                String password = PasswordUtil.hashPassword(edtPassword.getText().toString());
                String firstName = edtFirstName.getText().toString();
                String lastName = edtLastName.getText().toString();
                long dateOfBirth = DateTimeUtil.parseDateStringToMillisecs(edtDOB.getText().toString());
                byte sex = rdgSex.getCheckedRadioButtonId() == R.id.rdMale ?(byte) 1 :(byte) 0;

                User user = new User();
                user.setEmail(email);
                user.setPassword(password);
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setDateOfBirth(dateOfBirth);
                user.setSex(sex);

                UserDAO userDAO = new UserDAO();
                userDAO.insert(user);

                activity.finishSignUp();
                break;
        }
    }
}
