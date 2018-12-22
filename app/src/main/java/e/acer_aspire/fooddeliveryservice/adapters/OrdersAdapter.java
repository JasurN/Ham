package e.acer_aspire.fooddeliveryservice.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import e.acer_aspire.fooddeliveryservice.R;
import e.acer_aspire.fooddeliveryservice.models.Meals;
import e.acer_aspire.fooddeliveryservice.models.Orders;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.OrdersViewHolder> {
    private Context context;
    private ArrayList<Orders> orders;

    public OrdersAdapter(Context context, ArrayList<Orders>orders) {
        this.context = context;
        this.orders = orders;
    }

    @NonNull
    @Override
    public OrdersAdapter.OrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_fragment_favourite, parent, false);
        return new OrdersAdapter.OrdersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersAdapter.OrdersViewHolder holder, int position) {
        Orders order = orders.get(position);
        holder.setDetails(order);
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public static class OrdersViewHolder extends RecyclerView.ViewHolder {
        public View view;
        @BindView(R.id.view_fragment_orders_top) TextView meal_name;
        @BindView(R.id.view_fragment_orders_price) TextView price;
        @BindView(R.id.view_fragment_orders_amount) TextView amount;
        @BindView(R.id.view_fragment_orders_total_price) TextView totalPrice;
        @BindView(R.id.view_fragment_orders_date) TextView date;
        @BindView(R.id.orders_meal_rating) RatingBar rating;
        @BindView(R.id.orders_content_item) CardView cardView;
        @BindView(R.id.view_fragment_orders_status) ImageView status;

        public OrdersViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
        }

        public void setDetails(Orders orders) {
            meal_name.setText(orders.getMeal().getName());
            price.setText(orders.getMeal().getPrice());
            amount.setText(orders.getAmount() + "");
            date.setText(orders.getTime());
            rating.setRating(orders.getMeal().getReview().getRating());
//            switch (orders.getStatus()) {
//                case 0:
//                    status.setColorFilter(ContextCompat.getColor(context));
//            }
//            cardView.setBackground();
        }
    }
}
