package e.acer_aspire.fooddeliveryservice.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import e.acer_aspire.fooddeliveryservice.R;
import e.acer_aspire.fooddeliveryservice.models.Meal;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.MainViewHolder> {
    private Context context;
    private ArrayList<Meal> meals;

    public FavouriteAdapter(Context context, ArrayList<Meal>meals) {
        this.context = context;
        this.meals = meals;
    }

    @NonNull
    @Override
    public FavouriteAdapter.MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_fragment_favourite, parent, false);
        return new FavouriteAdapter.MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavouriteAdapter.MainViewHolder holder, int position) {
        Meal meal = meals.get(position);
        holder.setDetails(meal);
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public static class MainViewHolder extends RecyclerView.ViewHolder {
        public View view;
        @BindView(R.id.main_meal_rating) RatingBar rating;
        @BindView(R.id.view_fragment_favourite_name) TextView namePrice;
        @BindView(R.id.view_fragment_favourite_description) TextView description;
        @BindView(R.id.main_content_item) CardView cardView;


        public MainViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
        }

        public void setDetails(Meal meal) {
            rating.setRating(meal.getRating());
            namePrice.setText(String.valueOf(meal.getPrice()));
            description.setText(meal.getDescription());
//            cardView.setBackground();
        }
    }
}
