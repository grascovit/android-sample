package br.com.codinglab.myapplication.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import br.com.codinglab.myapplication.R;
import br.com.codinglab.myapplication.models.RegisterBody;
import br.com.codinglab.myapplication.models.User;
import br.com.codinglab.myapplication.requests.ApiClient;
import br.com.codinglab.myapplication.requests.UserRequest;
import br.com.codinglab.myapplication.services.UserService;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity {
    @BindView(R.id.editTextRegisterFirstName)
    EditText firstName;

    @BindView(R.id.editTextRegisterLastName)
    EditText lastName;

    @BindView(R.id.editTextRegisterEmail)
    EditText email;

    @BindView(R.id.editTextRegisterPassword)
    EditText password;

    @BindView(R.id.editTextRegisterCpf)
    EditText cpf;

    @BindView(R.id.editTextRegisterCnpj)
    EditText cnpj;

    @BindView(R.id.radioGroupFinancialDocument)
    RadioGroup financialDocumentGroup;

    @BindView(R.id.radioButtonPersonal)
    RadioButton personalRadioButton;

    @BindView(R.id.radioButtonCompany)
    RadioButton companyRadioButton;

    private String financialDocumentType;
    private String financialDocument;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.radioButtonPersonal)
    public void showCpfEditText() {
        cnpj.setVisibility(View.GONE);
        cpf.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.radioButtonCompany)
    public void showCnpjEditText() {
        cpf.setVisibility(View.GONE);
        cnpj.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.buttonRegister)
    public void registerUser() {
        ProgressBar progressBar = findViewById(R.id.progressBarRegister);
        progressBar.setVisibility(View.VISIBLE);

        setFinancialDocument();

        if (financialDocument == null) {
            progressBar.setVisibility(View.GONE);
        }

        User user = new User(firstName.getText().toString(), lastName.getText().toString(),
                email.getText().toString(), password.getText().toString(), financialDocument,
                financialDocumentType);
        RegisterBody registerBody = new RegisterBody(user);
        UserService userService = ApiClient.getUserService();
        UserRequest userRequest = new UserRequest(findViewById(R.id.registerActivity), getApplicationContext(), progressBar);
        userService.registerUser(registerBody).enqueue(userRequest);
    }

    private void setFinancialDocument() {
        if (financialDocumentGroup.getCheckedRadioButtonId() == R.id.radioButtonPersonal) {
            financialDocument = cpf.getText().toString();
            financialDocumentType = "cpf";
        } else if (financialDocumentGroup.getCheckedRadioButtonId() == R.id.radioButtonCompany) {
            financialDocument = cnpj.getText().toString();
            financialDocumentType = "cnpj";
        }
    }
}
