package com.example.simplebooksapi_project_withvolley;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simplebooksapi_project_withvolley.databinding.OrderItemBinding;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderItemHolder>{

    Context context;
    ArrayList<Order> orders;
    ClickItem clickItem;

    public OrderAdapter(Context context, ArrayList<Order> orders, ClickItem clickItem) {
        this.context = context;
        this.orders = orders;
        this.clickItem = clickItem;
    }

    @NonNull
    @Override
    public OrderItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        OrderItemBinding binding = OrderItemBinding.inflate(LayoutInflater.from(context), parent, false);
        return new OrderItemHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderItemHolder holder, int position) {




        holder.binding.getRoot().setOnClickListener(view -> {
            clickItem.onItemClick("");
        });
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    class OrderItemHolder extends RecyclerView.ViewHolder {

        OrderItemBinding binding;
        public OrderItemHolder(OrderItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    interface ClickItem {
        void onItemClick(String orderId);
    }
}
