package edu.mssu.cis385.implicitintentsreceiver.chaoscontrol;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ShoppingList extends AppCompatActivity {

    @SuppressLint("StaticFieldLeak")
    static ListView shoppingList;
    static ArrayList<String> items;
    @SuppressLint("StaticFieldLeak")
    static ListViewAdapter adapter;

    EditText input;
    ImageView enter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);

        shoppingList = findViewById(R.id.shoppingList);
        input=findViewById(R.id.input);
        enter=findViewById(R.id.addItem);

        loadList();

        Button save = findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveList();
            }
        });

        adapter = new ListViewAdapter(getApplicationContext(), items);
        shoppingList.setAdapter(adapter);

        enter.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String text = input.getText().toString();
                if(text == null || text.length() == 0){
                    makeToast("Enter an item");
                }else{
                    addItem(text);
                    input.setText("");
                    makeToast("Added " + text);
                }
            }
        });
    }

    public static void removeItem(int remove){
        items.remove(remove);
        shoppingList.setAdapter(adapter);
    }

    private void saveList() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(items);
        editor.putString("shopping list", json);
        editor.apply();
    }

    private void loadList(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("shopping list", null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        items = gson.fromJson(json, type);

        if(items == null){
            items = new ArrayList<>();
        }
    }

    private void addItem(String item) {
        items.add(item);
        shoppingList.setAdapter(adapter);
    }

    Toast toast;

    private void makeToast(String s){
        if(toast != null) toast.cancel();
        toast = Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT);
        toast.show();
    }

    public void saveList(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(items);
        editor.putString("shopping list", json);
        editor.apply();
    }
}