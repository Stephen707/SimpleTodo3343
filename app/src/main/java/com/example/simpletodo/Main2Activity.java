package com.example.simpletodo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.simpletodo.MainActivity.positionitem;
import static com.example.simpletodo.MainActivity.text;

public class Main2Activity extends AppCompatActivity {
    EditText editText;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Edit Item");
        editText = (EditText)findViewById(R.id.editItem);

        editText.setText(getIntent().getStringExtra(text));
        position = getIntent().getIntExtra(positionitem,0);

    }

    public void save(View view){
        String item = editText.getText().toString();
        if (item.equals("")){
            Toast.makeText(this, "Enter a new Item to Edit                                                                                                                                             ", Toast.LENGTH_SHORT).show();
        }else {
            Intent intent = new Intent();
            intent.putExtra(text, item);
            intent.putExtra(positionitem, position);
            setResult(RESULT_OK, intent);
            finish();
        }

    }
}
