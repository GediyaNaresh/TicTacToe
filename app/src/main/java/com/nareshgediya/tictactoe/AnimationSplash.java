package com.nareshgediya.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class AnimationSplash extends AppCompatActivity {

    LottieAnimationView lottieAnimationView;
    TextView textView1;
    Button btn1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_splash);

        lottieAnimationView = findViewById(R.id.lottieAnimationView);
        textView1 = findViewById(R.id.text1);
        btn1 = findViewById(R.id.btn1);
        btn1.setAlpha(0);


//        textView1.setTranslationY(-800);
  //      lottieAnimationView.setTranslationY(500);

        btn1.animate().alpha(1).setDuration(2000).setStartDelay(3000);
        textView1.animate().translationY(1000).setDuration(2000).start();
        textView1.animate().translationY(-500).setDuration(1000).start();
        btn1.animate().translationY(-500).setDuration(1500).setStartDelay(4000).start();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AnimationSplash.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}