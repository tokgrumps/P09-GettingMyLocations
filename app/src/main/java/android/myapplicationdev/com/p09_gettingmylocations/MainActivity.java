package android.myapplicationdev.com.p09_gettingmylocations;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class MainActivity extends AppCompatActivity {

    Button btnStart, btnStop, btnCheck;
    String folderLocation;
    TextView result, result2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = (Button) this.findViewById(R.id.btnStart);
        btnStop = (Button) this.findViewById(R.id.btnStop);
        btnCheck = (Button) this.findViewById(R.id.btnCheck);
        result = (TextView) this.findViewById(R.id.tvResult);
        result2 = (TextView) this.findViewById(R.id.tvResult2);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Service started",
                        Toast.LENGTH_LONG).show();
                Intent i = new Intent(MainActivity.this, MyService.class);
                startService(i);

                folderLocation = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Test";
                File folder = new File(folderLocation);
                if (!folder.exists()){
                    boolean result = folder.mkdir();
                    if (result){
                        Log.d("File Read/Write", "Folder created");
                    }
                }
                File targetFile = new File(folderLocation, "last.txt");

                if (targetFile.exists()){
                    String data ="";
                    if (data.equals("")){
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
                        } catch (Exception e) {
                            Toast.makeText(MainActivity.this, "Failed to read!",
                                    Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }
                        result.setText("Last known location when the activity started\n" + data);
                    }

                }
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Service stopped",
                        Toast.LENGTH_LONG).show();
                Intent i = new Intent(MainActivity.this, MyService.class);
                stopService(i);

                folderLocation = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Test";
                File folder = new File(folderLocation);
                if (!folder.exists()){
                    boolean result = folder.mkdir();
                    if (result){
                        Log.d("File Read/Write", "Folder created");
                    }
                }

                File targetFile = new File(folderLocation, "record.txt");

                try {
                    FileWriter writer = new FileWriter(targetFile, false);
                    writer.write("");
                    writer.flush();
                    writer.close();
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Failed to write!",
                            Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                folderLocation = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Test";
                File folder = new File(folderLocation);
                if (!folder.exists()){
                    boolean result = folder.mkdir();
                    if (result){
                        Log.d("File Read/Write", "Folder created");
                    }
                }
                File targetFile = new File(folderLocation, "record.txt");

                if (targetFile.exists()){
                    String data ="";
                    if (data.equals("")){
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
                        } catch (Exception e) {
                            Toast.makeText(MainActivity.this, "Failed to read!",
                                    Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }
                        Toast.makeText(MainActivity.this, data,
                                Toast.LENGTH_LONG).show();
                    }

                }
            }
        });



    }


}
