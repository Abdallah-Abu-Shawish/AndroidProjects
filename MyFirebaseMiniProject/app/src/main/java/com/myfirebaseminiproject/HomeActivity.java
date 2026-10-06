package com.myfirebaseminiproject;

import static android.os.Build.VERSION_CODES.R;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
//import com.google.firebase.firestore.CollectionReference;
//import com.google.firebase.firestore.EventListener;
//import com.google.firebase.firestore.FirebaseFirestore;
//import com.google.firebase.firestore.FirebaseFirestoreException;
//import com.google.firebase.firestore.QueryDocumentSnapshot;
//import com.google.firebase.firestore.QuerySnapshot;
import com.myfirebaseminiproject.adapters.RecipeAdapter;
import com.myfirebaseminiproject.models.Recipe;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
//import javax.annotation.Nullable;

public class HomeActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private SearchView searchView;
    private RecyclerView recipesRecyclerView;
    private FloatingActionButton addRecipeFab;
    private ImageView profileIcon;
    private RecipeAdapter adapter;
    private List<Recipe> allRecipes = new ArrayList<>();
    private List<Recipe> filteredRecipes = new ArrayList<>();
    private List<String> categories = new ArrayList<>();
    private String selectedCategory = "All";
//    private FirebaseFirestore db;
    private TextView emptyStateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tabLayout = findViewById(R.id.tabLayout);
        searchView = findViewById(R.id.searchView);
        recipesRecyclerView = findViewById(R.id.recipesRecyclerView);
        addRecipeFab = findViewById(R.id.addRecipeFab);
        profileIcon = findViewById(R.id.profileIcon);
        emptyStateTextView = findViewById(R.id.emptyStateTextView);

//        db = FirebaseFirestore.getInstance();

        adapter = new RecipeAdapter(this, filteredRecipes, recipe -> {
            Intent intent = new Intent(HomeActivity.this, RecipeDetailsActivity.class);
            intent.putExtra("recipeId", recipe.getId());
            startActivity(intent);
        });
        recipesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        recipesRecyclerView.setAdapter(adapter);

        addRecipeFab.setOnClickListener(v -> {
            startActivity(new Intent(HomeActivity.this, AddRecipeActivity.class));
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        });

        profileIcon.setOnClickListener(v -> {
            startActivity(new Intent(HomeActivity.this, ProfileActivity.class));
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        });

        loadRecipesAndCategories();
        setupSearchView();
        setupTabLayoutListener();
    }

    private void loadRecipesAndCategories() {
        CollectionReference recipesRef = db.collection("recipes");
        recipesRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    Toast.makeText(HomeActivity.this, "Error loading recipes", Toast.LENGTH_SHORT).show();
                    return;
                }
                allRecipes.clear();
                Set<String> categorySet = new HashSet<>();
                for (QueryDocumentSnapshot doc : value) {
                    Recipe recipe = doc.toObject(Recipe.class);
                    recipe.setId(doc.getId());
                    allRecipes.add(recipe);
                    if (!TextUtils.isEmpty(recipe.getCategory())) {
                        categorySet.add(recipe.getCategory());
                    }
                }
                categories.clear();
                categories.add("All");
                categories.addAll(categorySet);
                setupTabs();
                filterAndShowRecipes();
            }
        });
    }

    private void setupTabs() {
        tabLayout.removeAllTabs();
        for (String category : categories) {
            tabLayout.addTab(tabLayout.newTab().setText(category));
        }
        // Set selected tab
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).equals(selectedCategory)) {
                tabLayout.getTabAt(i).select();
                break;
            }
        }
    }

    private void setupTabLayoutListener() {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                selectedCategory = tab.getText().toString();
                filterAndShowRecipes();
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}
            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
    }

    private void setupSearchView() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterAndShowRecipes();
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                filterAndShowRecipes();
                return true;
            }
        });
        // Add clear (cancel) icon functionality
        searchView.setOnCloseListener(() -> {
            filterAndShowRecipes();
            return false;
        });
    }

    private void filterAndShowRecipes() {
        String query = searchView.getQuery() != null ? searchView.getQuery().toString().toLowerCase().trim() : "";
        filteredRecipes.clear();
        for (Recipe recipe : allRecipes) {
            boolean matchesCategory = selectedCategory.equals("All") || recipe.getCategory().equalsIgnoreCase(selectedCategory);
            boolean matchesQuery = TextUtils.isEmpty(query) || recipe.getTitle().toLowerCase().contains(query);
            if (matchesCategory && matchesQuery) {
                filteredRecipes.add(recipe);
            }
        }
        adapter.notifyDataSetChanged();
        if (filteredRecipes.isEmpty()) {
            emptyStateTextView.setVisibility(View.VISIBLE);
            recipesRecyclerView.setVisibility(View.GONE);
        } else {
            emptyStateTextView.setVisibility(View.GONE);
            recipesRecyclerView.setVisibility(View.VISIBLE);
        }
    }
} 