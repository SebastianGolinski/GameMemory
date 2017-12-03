package com.example.sebastian.gamememory;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteCursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;

public class Game extends AppCompatActivity {

    Baza db;
    ImageView image11;
    ImageView image12;
    ImageView image21;
    ImageView image22;
    ImageView image31;
    ImageView image32;
    TextView textScore;
    Integer Cards[] = {11, 21, 12, 22, 13, 23};
    String PathImage[] = {"d","s","a"};
    int index;
    Bitmap i11,i12,i21,i22,i31,i32;
    int firstCard,secondCard,clickedFirst,clickedSecond,cardNumber=1,turn=1,score=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        db = new Baza(this);
        index =0;
        SQLiteCursor cursor = db.TakeData();
        while (cursor.moveToNext()){
            PathImage[index]=cursor.getString(2);
            index++;
        }

        image11 = (ImageView) findViewById(R.id.imageView11);
        image12 = (ImageView) findViewById(R.id.imageView12);
        image21 = (ImageView) findViewById(R.id.imageView21);
        image22 = (ImageView) findViewById(R.id.imageView22);
        image31 = (ImageView) findViewById(R.id.imageView31);
        image32 = (ImageView) findViewById(R.id.imageView32);
        textScore = (TextView) findViewById(R.id.textScore);
        textScore.setText(Integer.toString(score));
        image11.setTag("0");
        image12.setTag("1");
        image21.setTag("2");
        image22.setTag("3");
        image31.setTag("4");
        image32.setTag("5");

        frontCard();
        Collections.shuffle(Arrays.asList(Cards));

        image11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String) view.getTag());
                doStuff(image11,theCard);
            }
        });
        image12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String) view.getTag());
                doStuff(image12,theCard);
            }
        });
        image21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String) view.getTag());
                doStuff(image21,theCard);
            }
        });
        image22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String) view.getTag());
                doStuff(image22,theCard);
            }
        });
        image31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String) view.getTag());
                doStuff(image31,theCard);
            }
        });
        image32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String) view.getTag());
                doStuff(image32,theCard);
            }
        });

    }

    private void doStuff(ImageView iv, int card) {
        if(Cards[card] == 11){
            iv.setImageBitmap(i11);
        }else if(Cards[card] == 21){
            iv.setImageBitmap(i12);
        }
        else if(Cards[card] == 12){
            iv.setImageBitmap(i21);
        }
        else if(Cards[card] == 22){
            iv.setImageBitmap(i22);;
        }
        else if(Cards[card] == 13){
            iv.setImageBitmap(i31);
        }
        else if(Cards[card] == 23){
            iv.setImageBitmap(i32);
        }

        if(cardNumber == 1){
            firstCard=Cards[card];
            if(firstCard>20)
            {
                firstCard -= 10;
            }
            cardNumber =2;
            clickedFirst = card;
            iv.setEnabled(false);
        }else if(cardNumber == 2)
        {
            secondCard = Cards[card];
            if( secondCard>20)
            {
                secondCard -= 10;
            }
            cardNumber =1;
            clickedSecond = card;
            image11.setEnabled(false);
            image12.setEnabled(false);
            image21.setEnabled(false);
            image22.setEnabled(false);
            image31.setEnabled(false);
            image32.setEnabled(false);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable(){
                public void run(){
                    calculate();
                }
            },1000);

        }
    }

    private void calculate() {
        if(firstCard == secondCard) {
            if(clickedFirst == 0){
            image11.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 1){
            image12.setVisibility(View.INVISIBLE);
            }
            if(clickedFirst == 2){
                image21.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 3){
                image22.setVisibility(View.INVISIBLE);
            }
            if(clickedFirst == 4){
                image31.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 5){
                image32.setVisibility(View.INVISIBLE);
            }

            if(clickedSecond == 0){
                image11.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 1){
                image12.setVisibility(View.INVISIBLE);
            }
            if(clickedSecond == 2){
                image21.setVisibility(View.INVISIBLE);
            }
            else if(clickedSecond == 3){
                image22.setVisibility(View.INVISIBLE);
            }
            if(clickedSecond == 4){
                image31.setVisibility(View.INVISIBLE);
            }
            else if(clickedSecond == 5){
                image32.setVisibility(View.INVISIBLE);
            }
            if(turn==1){
                score++;
                textScore.setText(Integer.toString(score));
            }
        }else{
            image11.setImageResource(R.drawable.znak_zapytania);
            image12.setImageResource(R.drawable.znak_zapytania);
            image21.setImageResource(R.drawable.znak_zapytania);
            image22.setImageResource(R.drawable.znak_zapytania);
            image31.setImageResource(R.drawable.znak_zapytania);
            image32.setImageResource(R.drawable.znak_zapytania);
            if(turn ==1)
            {
                turn = 2;
            }else if (turn ==2){
                turn = 1;
            }
        }

        image11.setEnabled(true);
        image12.setEnabled(true);
        image21.setEnabled(true);
        image22.setEnabled(true);
        image31.setEnabled(true);
        image32.setEnabled(true);
        checkEnd();
    }

    private void checkEnd() {
        if(image11.getVisibility() == View.INVISIBLE &&
                image12.getVisibility() == View.INVISIBLE &&
                image21.getVisibility() == View.INVISIBLE &&
                image22.getVisibility() == View.INVISIBLE &&
                image31.getVisibility() == View.INVISIBLE &&
                image32.getVisibility() == View.INVISIBLE ){
            AlertDialog.Builder dialog = new AlertDialog.Builder(Game.this);
            dialog
                    .setMessage("Koniec gry")
                    .setCancelable(false)
                    .setNegativeButton("wyjscie", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    });
            AlertDialog alertDialog = dialog.create();
            alertDialog.show();
        }
    }

    private void frontCard() {
        i11 = GiveBitmap(PathImage[0]);
        i12 = GiveBitmap(PathImage[0]);
        i21 = GiveBitmap(PathImage[1]);
        i22 = GiveBitmap(PathImage[1]);
        i31 = GiveBitmap(PathImage[2]);
        i32 = GiveBitmap(PathImage[2]);


    }

    private Bitmap GiveBitmap(String path){
        return BitmapFactory.decodeFile(path);
    }
}
