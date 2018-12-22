package e.acer_aspire.fooddeliveryservice.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import e.acer_aspire.fooddeliveryservice.R;
import e.acer_aspire.fooddeliveryservice.models.Meals;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {

    private Context context;
    private ArrayList<Meals>meals;

    public MenuAdapter(Context context, int resource, ArrayList<Meals>meals) {

    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_fragment_favourite, parent, false);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public static class MenuViewHolder extends RecyclerView.ViewHolder {
        public View view;
//        @BindView(R.id.menu_meal_rating) RatingBar rating;
//        @BindView(R.id.view_fragment_favourite_name) TextView namePrice;
//        @BindView(R.id.view_fragment_favourite_description) TextView description;



        public MenuViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
        }
    }
}
