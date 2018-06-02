package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    private TextView originTv;
    private TextView alsoKnownAsTv;
    private TextView IngredientsTv;
    private TextView DescriptionTv;
    private ImageView ingredientsIv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //get the references
        ingredientsIv = findViewById(R.id.image_iv);
        originTv= findViewById(R.id.origin_tv);
        alsoKnownAsTv= findViewById(R.id.also_known_tv);
        IngredientsTv= findViewById(R.id.ingredients_tv);
        DescriptionTv= findViewById(R.id.description_tv);


        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {

     originTv.setText(sandwich.getPlaceOfOrigin());
        // Convert the ArrayList into a String.and set the text
        String res = TextUtils.join(",", sandwich.getAlsoKnownAs());
        alsoKnownAsTv.setText(res);
        String ingredientsString=TextUtils.join(",",sandwich.getIngredients());
        IngredientsTv.setText(ingredientsString);
        DescriptionTv.setText(sandwich.getDescription());

    }
}

