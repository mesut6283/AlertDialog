package com.masoud.example.alertdialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public MediaPlayer player;
    public int playok = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b = (Button) findViewById(R.id.button1);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (playok == 0) {
                    player = MediaPlayer.create(MainActivity.this, R.raw.pat_mat);
                    player.setLooping(true);
                    player.setVolume(100, 100);
                    playok = 1;
                    player.start();

                }

                ShowAlertDialog();
            }
        });

        Button stopButton = (Button) findViewById(R.id.button2);

            stopButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (playok == 1) {
                        player.stop();
                        playok = 0;
                    }
                }
            });

    }

    public void ShowAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setTitle(R.string.title);
        builder.setMessage(R.string.message);
        builder.setIcon(R.mipmap.ic_launcher);

        builder.setPositiveButton(R.string.Yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                Toast.makeText(getApplicationContext(), "بله شما خنگول هستین خودش هم از نوع بز", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton(R.string.No, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                Toast.makeText(getApplicationContext(), "فکر کردی خیال کردی معلومه که خنگولی", Toast.LENGTH_LONG).show();

            }
        });


        builder.show();

    }



    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this).setIcon(R.mipmap.ic_launcher).setTitle("Exit")
                .setMessage("خنگول مطمئنی ؟")
                .setPositiveButton("بله", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }
                }).setNegativeButton("خیر", null).show();
        if(playok==1) {
            player.stop();
            playok = 0;
        }
    }

}
