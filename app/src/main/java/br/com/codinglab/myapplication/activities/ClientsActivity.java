package br.com.codinglab.myapplication.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import br.com.codinglab.myapplication.R;
import br.com.codinglab.myapplication.requests.ApiClient;
import br.com.codinglab.myapplication.requests.ClientRequest;
import br.com.codinglab.myapplication.services.ClientService;
import butterknife.ButterKnife;

public class ClientsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clients);
        ButterKnife.bind(this);

        loadClients(getIntent());
    }

    private void loadClients(Intent intent) {
        int page = 1;
        int userId = intent.getIntExtra("userId", 0);
        String token = intent.getStringExtra("token");

        ProgressBar progressBar = findViewById(R.id.progressBarClients);
        progressBar.setVisibility(View.VISIBLE);

        ClientService clientService = ApiClient.getClientService();
        ClientRequest clientRequest = new ClientRequest(findViewById(R.id.clientsActivity), getApplicationContext(), progressBar);
        clientService.fetchClients(token, userId, page).enqueue(clientRequest);
    }
}
