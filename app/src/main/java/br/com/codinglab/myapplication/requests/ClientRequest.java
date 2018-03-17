package br.com.codinglab.myapplication.requests;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import br.com.codinglab.myapplication.R;
import br.com.codinglab.myapplication.adapters.ClientAdapter;
import br.com.codinglab.myapplication.models.Client;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClientRequest implements Callback<List<Client>> {
    private View view;
    private ProgressBar progressBar;
    private Context context;
    private RecyclerView recyclerView;

    public ClientRequest(View view, Context context, ProgressBar progressBar) {
        this.view = view;
        this.progressBar = progressBar;
        this.context = context;

        recyclerView = view.findViewById(R.id.recyclerViewClients);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
    }

    @Override
    public void onResponse(@NonNull Call<List<Client>> call, @NonNull Response<List<Client>> response) {
        progressBar.setVisibility(View.GONE);

        int statusCode = response.code();

        if (statusCode == 200) {
            ArrayList<Client> clients = new ArrayList<>(response.body());
            ClientAdapter clientAdapter = new ClientAdapter(clients);
            recyclerView.setAdapter(clientAdapter);
        } else {
            Snackbar.make(view, context.getString(R.string.wrong_user_or_password), Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(@NonNull Call<List<Client>> call, @NonNull Throwable t) {
        progressBar.setVisibility(View.GONE);
        Snackbar.make(view, context.getString(R.string.request_error), Snackbar.LENGTH_LONG).show();
    }
}
