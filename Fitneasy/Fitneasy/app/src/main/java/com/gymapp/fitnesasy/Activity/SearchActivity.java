package com.gymapp.fitnesasy.Activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.gymapp.fitnesasy.Model.DTO.Exercise;
import com.gymapp.fitnesasy.R;

import static com.gymapp.fitnesasy.Fragment.MuscleTypeFragment.exerciseArrayList;



public class SearchActivity extends AppCompatActivity implements  SearchView.OnQueryTextListener{

    Toolbar toolbar;
    RecyclerView recyclerView;
    
    SimpleCursorAdapter simpleCursorAdapter;
    SearchView searchView;
    long back_pressed;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_timkiem);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        toolbar = (Toolbar) findViewById(R.id.toolbarTK);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerTimKiem);
        setSupportActionBar(toolbar);
        
        getSupportActionBar().setIcon(R.mipmap.logo);
        

        final String[] from = new String[]{"songName"};
        final int[] to = new int[]{R.id.item1custom};
        simpleCursorAdapter = new SimpleCursorAdapter(this, R.layout.simple_list_item_1_custom,null,from,to, CursorAdapter.IGNORE_ITEM_VIEW_TYPE );

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_timkiem,menu);
        MenuItem itemSearch =menu.findItem(R.id.itSearch);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) MenuItemCompat.getActionView(itemSearch);

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        searchView.setIconified(false);
        searchView.setOnQueryTextListener(this);

        searchView.setSuggestionsAdapter(simpleCursorAdapter);
        searchView.setOnSuggestionListener(new SearchView.OnSuggestionListener() {
            @Override
            public boolean onSuggestionSelect(int position) {

                return true;
            }

            @Override
            public boolean onSuggestionClick(int position) {

                simpleCursorAdapter.getCursor().moveToPosition(position);
                
                Intent intent = new Intent(SearchActivity.this, ExerciseActivity.class);
                intent.putExtra("exercise",(Exercise)exerciseArrayList.get(position));
                
                
                startActivity(intent);
                return true;
            }
        });
        getSuggestion("ng");
        return true;

    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            Toast.makeText(this,"Từ khóa: " + query+" đang tìm kiếm...", Toast.LENGTH_SHORT).show();

        }
    }





    private void getSuggestion(String text){
        MatrixCursor c = new MatrixCursor(new String[]{BaseColumns._ID,"songName"});

        int count=0;
        for(int i=0; i<exerciseArrayList.size(); i++){
            if(exerciseArrayList.get(i).getName().toLowerCase().contains(text.toLowerCase())){
                c.addRow(new Object[]{i,exerciseArrayList.get(i).getName()});
                count++;
            }
            if(count>5)
                break;
        }

        simpleCursorAdapter.changeCursor(c);


    }



    @Override
    public boolean onQueryTextSubmit(String query) {

        
        
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        getSuggestion(newText);

        return false;
    }



















}