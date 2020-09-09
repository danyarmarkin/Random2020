package com.onlinetest.android.random2020;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class MainActivity extends AppCompatActivity {
    public static byte people, board,k = 0; //количество человек в классе
    public static boolean[] HasBeenCalled = new boolean[256];
    public static TextView main;
    public static Button enter, reset;
    public static String[] names = new String[256];
    public static CheckBox recall, defaultClass, firstGroup, secondGroup, custom1, custom2, custom3;
    public static boolean RECALL = false, SD = false;
    public static InputStream path;
    public static int progress = 0;
    public static ProgressBar bar;
    public static String pathSD, LOG = "random_data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);

        main = findViewById(R.id.main);
        enter = findViewById(R.id.enter);
        reset = findViewById(R.id.reset);
        recall = findViewById(R.id.checkBox1);

        defaultClass = findViewById(R.id.defautClass);
        firstGroup = findViewById(R.id.firstGroup);
        secondGroup = findViewById(R.id.secondGroup);
        custom1 = findViewById(R.id.custom1);
        custom2 = findViewById(R.id.custom2);
        custom3 = findViewById(R.id.custom3);

        bar = findViewById(R.id.progressBar2);
        pathSD = "class1.txt";
//        ReadSD();

        defaultClass.setChecked(true);
        path = getResources().openRawResource(R.raw.class1);
        try {
            Read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        custom1.setVisibility(View.INVISIBLE);
        custom2.setVisibility(View.INVISIBLE);
        custom3.setVisibility(View.INVISIBLE);

        //весь класс
        defaultClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(defaultClass.isChecked()) {
                    firstGroup.setChecked(false);
                    secondGroup.setChecked(false);
                    custom1.setChecked(false);
                    custom2.setChecked(false);
                    custom3.setChecked(false);
                    if(SD == true){
                        pathSD = "class1.txt";
//                        ReadSD();
                    }else{
                        path = getResources().openRawResource(R.raw.class1);
                        try {
                            Read();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        });


        //первая группа
        firstGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(firstGroup.isChecked()) {
                    defaultClass.setChecked(false);
                    secondGroup.setChecked(false);
                    custom1.setChecked(false);
                    custom2.setChecked(false);
                    custom3.setChecked(false);
                    if(SD == true){
                        pathSD = "class2.txt";
//                        ReadSD();
                    }else{
                        path = getResources().openRawResource(R.raw.class2);
                        try {
                            Read();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        //вторая группа
        secondGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(secondGroup.isChecked()) {
                    defaultClass.setChecked(false);
                    firstGroup.setChecked(false);
                    custom1.setChecked(false);
                    custom2.setChecked(false);
                    custom3.setChecked(false);
                    if(SD == true){
                        pathSD = "class3.txt";
//                        ReadSD();
                    }else{
                        path = getResources().openRawResource(R.raw.class3);
                        try {
                            Read();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        //кастом 1
        custom1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(custom1.isChecked()) {
                    defaultClass.setChecked(false);
                    secondGroup.setChecked(false);
                    firstGroup.setChecked(false);
                    custom2.setChecked(false);
                    custom3.setChecked(false);
                    if(SD == true){
                        pathSD = "class4.txt";
//                        ReadSD();
                    }
                }
            }
        });

        //кастом 2
        custom2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(custom2.isChecked()) {
                    defaultClass.setChecked(false);
                    secondGroup.setChecked(false);
                    custom1.setChecked(false);
                    firstGroup.setChecked(false);
                    custom3.setChecked(false);
                    if(SD == true){
                        pathSD = "class5.txt";
//                        ReadSD();
                    }
                }
            }
        });

        //кастом 3
        custom3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(custom3.isChecked()) {
                    defaultClass.setChecked(false);
                    secondGroup.setChecked(false);
                    custom1.setChecked(false);
                    custom2.setChecked(false);
                    firstGroup.setChecked(false);
                    if(SD == true){
                        pathSD = "class6.txt";
//                        ReadSD();
                    }
                }
            }
        });

        recall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (recall.isChecked()) {
                    RECALL = true;
                }else{
                    RECALL = false;
                }
            }
        });


        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               initEnter();
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(byte i =0; i<people; i++){
                    HasBeenCalled[i] = false;
                    main.setText("успешно очищено");
                }
                bar.setProgress(0);
                progress = 0;
            }
        });


//        in.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                defaultClass.setText("8-4(весь)");
//                firstGroup.setText("8-4(1 группа)");
//                secondGroup.setText("8-4(2 группа)");
//                custom1.setVisibility(View.INVISIBLE);
//                custom2.setVisibility(View.INVISIBLE);
//                custom3.setVisibility(View.INVISIBLE);
//                if(in.isChecked()){
//                    sd.setChecked(false);
//                    SD = false;
//                }
//                defaultClass.setChecked(true);
//                firstGroup.setChecked(false);
//                secondGroup.setChecked(false);
//                custom1.setChecked(false);
//                custom2.setChecked(false);
//                custom3.setChecked(false);
//            }
//        });

//        sd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                custom1.setVisibility(View.VISIBLE);
//                custom2.setVisibility(View.VISIBLE);
//                custom3.setVisibility(View.VISIBLE);
//                if(sd.isChecked()){
//                    in.setChecked(false);
//                    SD = true;
//                }
//            }
//        });
    }




    public void Read() throws IOException {
        progress = 0;
        bar.setProgress(0);
        people = 0;
        int g = 0;
            try{
               // File f = new File(uri);
                BufferedReader reader = new BufferedReader(new InputStreamReader(path));
                String line = reader.readLine();
                while (line != null) {
                    names[g] = line;
                    line = reader.readLine();
                    people++;
                    g++;
                }
            }catch(IOException ioe){
                ioe.printStackTrace();
            }
            System.out.println(people);
            for (byte i = 0; i < people; i++) {
                HasBeenCalled[i] = false;
            }
            main.setText("список успешно загружен");
    }


//    public void ReadSD(){
//        progress = 0;
//        bar.setProgress(0);
//        people = 0;
//        int g = 0;
//        try{
//            File sdc = Environment.getExternalStorageDirectory();
//            File f = new File(sdc,"/storage/emulated/0/random_data/"+ pathSD);
//            BufferedReader reader = new BufferedReader(new FileReader(f));
//            String line = reader.readLine();
//            while(line != null){
//                names[g] = line;
//                line = reader.readLine();
//                people++;
//                g++;
//            }
//            reader.close();
//        } catch(OutOfMemoryError om){
//            om.printStackTrace();
//        } catch(Exception ex){
//            ex.printStackTrace();
//        }
//        for (byte i = 0; i < people; i++) {
//            HasBeenCalled[i] = false;
//        }
//        main.setText("список успешно загружен");
//    }

    public static void initEnter(){
        k=0;

        if(RECALL == true){
            board = (byte) (0+(int)(Math.random()*(people)));
            main.setText(names[board]);
        }else if(RECALL == false){
            for(int i = 0; i < people; i++){
                if(HasBeenCalled[i] == true){
                    k++;
                }
            }
            if(k==people-1) {
                progress = 100;
                bar.setProgress(100);
            }else{
                if(people == k){
                    progress+=Math.round((100-progress)/(people-k+1));
                }else{
                    progress+=Math.round((100-progress)/(people-k));
                }
                bar.setProgress(progress);
                System.out.println(progress);
            }

            if(k==people){
                main.setText("Все люди были вызваны.\n Нажмите RESET");
                bar.setProgress(100);
            }else{
                while(true){
                    board = (byte) (0+(int)(Math.random()*(people)));
                    if(HasBeenCalled[board] == false){
                        main.setText(names[board]);
                        HasBeenCalled[board] = true;
                        break;
                    }
                }
            }
        }
    }
//    public static void SetSD(){
//        defaultClass.setVisibility(View.INVISIBLE);
//        firstGroup.setVisibility(View.INVISIBLE);
//        secondGroup.setVisibility(View.INVISIBLE);
//        custom1.setVisibility(View.INVISIBLE);
//        custom2.setVisibility(View.INVISIBLE);
//        custom3.setVisibility(View.INVISIBLE);
//
//        readFileFromSD.initRead = true;
//
//        for(int i=1; i<=5; i++){
//            readFileFromSD.ioe = false;
//            readFileFromSD.file = "class" + Integer.toString(i);
//            readFileFromSD.readFileSD();
//            if(readFileFromSD.ioe == false){
//                switch (i){
//                    case 1:
//                        defaultClass.setVisibility(View.VISIBLE);
//                        break;
//                    case 2:
//                        firstGroup.setVisibility(View.VISIBLE);
//                        break;
//                    case 3:
//                        secondGroup.setVisibility(View.VISIBLE);
//                        break;
//                    case 4:
//                        custom1.setVisibility(View.VISIBLE);
//                        break;
//                    case 5:
//                        custom2.setVisibility(View.VISIBLE);
//                        break;
//                    case 6:
//                        custom3.setVisibility(View.VISIBLE);
//                        break;
//                }
//            }
//        }
//    }

}