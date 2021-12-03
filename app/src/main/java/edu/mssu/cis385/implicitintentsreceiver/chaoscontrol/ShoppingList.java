package edu.mssu.cis385.implicitintentsreceiver.chaoscontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ShoppingList extends AppCompatActivity {

    ListView shoppingList;
    ArrayList<String> items;
    ArrayAdapter<String> adapter;

    EditText input;
    ImageView enter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);

        shoppingList = findViewById(R.id.shoppingList);
        input=findViewById(R.id.input);
        enter=findViewById(R.id.addItem);

        items = new ArrayList<>();

        adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, items);
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
                    makeToast("Added" + text);
                }
            }
        });
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

}