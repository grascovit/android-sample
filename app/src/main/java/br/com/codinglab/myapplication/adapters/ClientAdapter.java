package br.com.codinglab.myapplication.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.codinglab.myapplication.R;
import br.com.codinglab.myapplication.models.Client;

public class ClientAdapter extends RecyclerView.Adapter<ClientAdapter.ViewHolder> {
    private ArrayList<Client> clients;

    public ClientAdapter(ArrayList<Client> clients) {
        this.clients = clients;
    }

    @Override
    public ClientAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_client, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ClientAdapter.ViewHolder viewHolder, int i) {
        viewHolder.textViewClientName.setText(clients.get(i).name);
        viewHolder.textViewClientPhone.setText(clients.get(i).phone);
    }

    @Override
    public int getItemCount() {
        return clients.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewClientName;
        private TextView textViewClientPhone;

        public ViewHolder(View view) {
            super(view);
            textViewClientName = view.findViewById(R.id.textViewClientName);
            textViewClientPhone = view.findViewById(R.id.textViewClientPhone);
        }
    }
}