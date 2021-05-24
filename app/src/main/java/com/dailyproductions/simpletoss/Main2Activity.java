package com.dailyproductions.simpletoss;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class Main2Activity extends AppCompatActivity {
    TextView toastText;
    Button clickButton;
    Animation scaleUp, scaleDown, scaleImageUp, scaleImageDown;
    ImageView image;
    TextView tossView, clickView, noteView, dailyView, resultView;
    Typeface jFs;
    String array[] = {"Tails", "Heads"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //Changing fonts
        clickButton = findViewById(R.id.clickButton);
        image = findViewById(R.id.coinImage);
        tossView = findViewById(R.id.tossView);
        clickView = findViewById(R.id.clickView);
        noteView = findViewById(R.id.noteView);
        dailyView = findViewById(R.id.dailyView);
        resultView = findViewById(R.id.resultView);
        jFs = Typeface.createFromAsset(getAssets(), "josefinsans_regular.ttf");

        clickButton.setTypeface(jFs);
        tossView.setTypeface(jFs);
        clickView.setTypeface(jFs);
        noteView.setTypeface(jFs);
        dailyView.setTypeface(jFs);
        resultView.setTypeface(jFs);

        //Changing image everytime App launches
        Random randomNumber = new Random();
        int randomN = randomNumber.nextInt(array.length);
        System.out.println(randomN);
        if(randomN == 0){
            image.setImageResource(R.drawable.tails);
        }else if(randomN == 1){
            image.setImageResource(R.drawable.heads);
        }

        //Button Animation on press
        scaleUp = AnimationUtils.loadAnimation(this,R.anim.scale_up);
        scaleDown = AnimationUtils.loadAnimation(this, R.anim.scale_down);
        clickButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()== MotionEvent.ACTION_DOWN){
                    clickButton.startAnimation(scaleUp);
                    clickButton.setBackgroundResource(R.drawable.button_click);
                }else if(event.getAction()== MotionEvent.ACTION_UP){
                    clickButton.startAnimation(scaleDown);
                    clickButton.setBackgroundResource(R.drawable.button_border);
                }
                return false;
            }
        });
    }

    public void tossIt(View view) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        LayoutInflater inflater = this.getLayoutInflater();
//        builder.setView(inflater.inflate((R.layout.custom_loading),null));
//        final AlertDialog dialog = builder.create();
//        dialog.show();
//        dialog.setCancelable(false);
        LayoutInflater layoutInflater = getLayoutInflater();
        View layout = layoutInflater.inflate(R.layout.custom_toast, (ViewGroup)findViewById(R.id.toast_root));
        toastText = layout.findViewById(R.id.toast_text);
        jFs = Typeface.createFromAsset(getAssets(), "josefinsans_regular.ttf");
        toastText.setTypeface(jFs);

        scaleImageUp = AnimationUtils.loadAnimation(this, R.anim.scale_image_up);
        scaleImageDown = AnimationUtils.loadAnimation(this, R.anim.scale_image_down);
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.coin_toss);
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mpx) {
                mpx.reset();
                mpx.release();
            }
        });
        mp.start();
        final Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        resultView = findViewById(R.id.resultView);
                image = findViewById(R.id.coinImage);
        Random random = new Random();
        int number = random.nextInt(array.length);
                if(number == 0){

                    toastText.setText(R.string.tails);

                    image.animate().withLayer().rotationX(360).setDuration(300).withStartAction(new Runnable() {
                        @Override
                        public void run() {
                            image.startAnimation(scaleImageUp);
                        }
                    }).withEndAction(new Runnable() {
                        @Override
                        public void run() {
                            image.setImageResource(R.drawable.tails);
                            image.setRotationX(-360);
                            image.animate().withLayer().rotationX(0).setDuration(300).withStartAction(new Runnable() {
                                @Override
                                public void run() {
                                    image.startAnimation(scaleImageDown);
                                }
                            }).start();
                        }
                    }).start();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //toast.setText(R.string.tails);
                            resultView.setText("Result : Tails");
                            toast.show();
                            new CountDownTimer(500,500){

                                @Override
                                public void onTick(long millisUntilFinished) {
                                    //dialog.dismiss();
                                }

                                @Override
                                public void onFinish() {
                                    toast.cancel();
                                }
                            }.start();

                        }
                    },300);
                }
                else if(number == 1){

                    toastText.setText(R.string.heads);

                    image.animate().withLayer().rotationX(360).setDuration(300).withStartAction(new Runnable() {
                        @Override
                        public void run() {
                            image.startAnimation(scaleImageUp);
                        }
                    }).withEndAction(new Runnable() {
                        @Override
                        public void run() {
                            image.setImageResource(R.drawable.heads);
                            image.setRotationX(-360);
                            image.animate().withLayer().rotationX(0).setDuration(300).withStartAction(new Runnable() {
                                @Override
                                public void run() {
                                    image.startAnimation(scaleImageDown);
                                }
                            }).start();
                        }
                    }).start();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //toast.setText(R.string.heads);
                            resultView.setText("Result : Heads");
                            toast.show();
                            new CountDownTimer(500,500){

                                @Override
                                public void onTick(long millisUntilFinished) {
                                    //dialog.dismiss();
                                }

                                @Override
                                public void onFinish() {
                                    toast.cancel();
                                }
                            }.start();
                        }
                    },300);
                }
            }

    private long backPressedTime;
    Toast backToast;
    @Override
    public void onBackPressed() {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast , (ViewGroup) findViewById(R.id.toast_root));

        TextView toastText = layout.findViewById(R.id.toast_text);
        jFs = Typeface.createFromAsset(getAssets(), "josefinsans_regular.ttf");
        toastText.setTypeface(jFs);
        toastText.setText("Tap on back again to exit!");

        if(backPressedTime + 2000 > System.currentTimeMillis()){
            backToast.cancel();
            super.onBackPressed();
            return;
        }
        else{
            backToast = new Toast(getApplicationContext());
            backToast.setDuration(Toast.LENGTH_SHORT);
            backToast.setView(layout);
            backToast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }
}
