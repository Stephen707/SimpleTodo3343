package com.example.simpletodo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<String> arrayList;
    ArrayAdapter arrayAdapter;
    EditText editText;
    public static int code = 0;
    public static String text = "edittext";
    public static String positionitem = "position";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("SimpleTodo");
        editText = (EditText)findViewById(R.id.editText);
        listView = (ListView)findViewById(R.id.listview);

        Readitem();

        arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                intent.putExtra(text,arrayList.get(position));
                intent.putExtra(positionitem,position);
                startActivityForResult(intent,code);

            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == code){
            String update = data.getExtras().getString(text);
            int postion = data.getExtras().getInt(positionitem);
            arrayList.set(postion,update);
            arrayAdapter.notifyDataSetChanged();
            Writeitem();
            Toast.makeText(this, "Item Updated successfully", Toast.LENGTH_SHORT).show();
        }
    }

    public void save(View view){
        String item = editText.getText().toString();
        if(item.equals("")){
            Toast.makeText(this, "Enter a new item", Toast.LENGTH_SHORT).show();
        }else{
            arrayAdapter.add(item);
            Toast.makeText(this, "Item Added successfull", Toast.LENGTH_SHORT).show();
            Writeitem();
            editText.getText().clear();
        }
    }
    private File getitem(){
        return new File(getFilesDir(),"text.txt");
    }
    private void Readitem(){
        try {
            arrayList = new ArrayList<>(FileUtils.readLines(getitem(), Charset.defaultCharset()));
        } catch (IOException e) {
            e.printStackTrace();
            arrayList = new ArrayList<>();
        }
    }
    private void Writeitem(){
        try {
            FileUtils.writeLines(getitem(),arrayList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
