package com.example.a15056112.p09_gettingmylocations;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class RecordActivity extends AppCompatActivity {

    ListView lv;
    ArrayList<String> al;
    ArrayAdapter adapter;
    Button btnRefresh;
    TextView tvRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        lv = (ListView)findViewById(R.id.lv);
        btnRefresh = (Button)findViewById(R.id.btnRefresh);
        tvRecord = (TextView)findViewById(R.id.tvRecord);


        al = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, al);

        File targetFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/P09","data.txt");
        if (targetFile.exists() == true){
            String data ="";
            try {
                FileReader reader = new FileReader(targetFile);
                BufferedReader br = new BufferedReader(reader);
                String line = br.readLine();
                while (line != null){
                    data += line + "\n";
                    line = br.readLine();
                }
                br.close();
                reader.close();
                String[] dataArray = data.split("\n");
                for (int i = 0; i < dataArray.length; i++) {
                    al.add(dataArray[i]);
                }

                lv.setAdapter(adapter);
                adapter.notifyDataSetChanged();

                tvRecord.setText("Number of records: " + dataArray.length);


            } catch (Exception e) {
                Toast.makeText(RecordActivity.this, "Failed to read!",
                        Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }

        }

        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                al.clear();

                File targetFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/P09","data.txt");
                if (targetFile.exists() == true){
                    String data ="";
                    try {
                        FileReader reader = new FileReader(targetFile);
                        BufferedReader br = new BufferedReader(reader);
                        String line = br.readLine();
                        while (line != null){
                            data += line + "\n";
                            line = br.readLine();
                        }
                        br.close();
                        reader.close();
                        String[] dataArray = data.split("\n");
                        for (int i = 0; i < dataArray.length; i++) {
                            al.add(dataArray[i]);
                        }

                        lv.setAdapter(adapter);
                        adapter.notifyDataSetChanged();

                        tvRecord.setText("Number of records: " + dataArray.length);


                    } catch (Exception e) {
                        Toast.makeText(RecordActivity.this, "Failed to read!",
                                Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }

                }
            }
        });

    }
}
