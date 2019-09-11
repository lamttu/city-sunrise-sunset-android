package au.edu.swin.sdmd.suncalculatorjava;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<AULocation> locations ;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.my_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.i("debugapp", String.valueOf(item.getItemId()));
        Log.i("debugapp", String.valueOf(R.id.addBtn));
        switch (item.getItemId()){
            case R.id.addBtn:
                Log.i("debugapp", "got btn clicked");
                Intent intent = new Intent(MainActivity.this, AddLocationActivity.class);
                startActivityForResult(intent, 1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        if(requestCode == 1){
            if(resultCode == RESULT_OK){
                AULocation location = intent.getParcelableExtra("result");
                Log.i("debugapp", location.toString());
                locations.add(location);
                initUI();
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_list);
        try {
            locations = getLocations();
            Log.i("debugapp", "locations size " + locations.size());
        } catch (IOException e) { // File doesn't exist yet
            locations = new ArrayList<>();
            Log.i("debugapp", "nofile found");
            e.printStackTrace();
        }
        initUI();

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("debugapp", "Go into pause, start persisting locations");
        persistLocations();

    }

    private void initUI(){
        RecyclerView recyclerView = findViewById(R.id.rvLocation);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        LocationRowAdapter adapter = new LocationRowAdapter(locations);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private ArrayList<AULocation> getLocations() throws IOException {
        FileInputStream fis = openFileInput("mylocations.txt");
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);
        String line;
        ArrayList<AULocation> locations = new ArrayList<>();
        try
        {
            while((line = br.readLine()) != null){
                String[] wordsOnLine = line.split(",");
                AULocation location = new AULocation(wordsOnLine[0],
                        Double.parseDouble(wordsOnLine[1]),
                        Double.parseDouble(wordsOnLine[2]),
                        Integer.parseInt(wordsOnLine[3]));
                locations.add(location);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return locations;
    }

    private void persistLocations(){
        String filename = "mylocations.txt";
        String fileContents = "";
        for(AULocation location : locations){
            fileContents += location.toString();
        }
        Log.i("debugapp", "IN persist Locations " + fileContents);
        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(fileContents.getBytes());
            Log.i("debugapp", "fisnished persisting");
            outputStream.close();
        } catch (Exception e) {
            Log.i("debugapp", "Something has gone wrong while persisting");
            e.printStackTrace();
        }

    }
}
