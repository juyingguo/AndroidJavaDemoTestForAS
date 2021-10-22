package com.sp.spmultipleapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.sp.spmultipleapp.R;
public class AnimationTestActivity extends AppCompatActivity {

    ImageView imageViewRotate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_test);

        imageViewRotate = findViewById(R.id.imageView);

        animationRotateTest();
    }
    private void animationRotateTest() {
        Animation operatingAnim = AnimationUtils.loadAnimation(this,R.anim.anim_rotation_image);
        LinearInterpolator linearInterpolator = new LinearInterpolator();
        operatingAnim.setInterpolator(linearInterpolator);
        imageViewRotate.startAnimation(operatingAnim);
    }

    @Override
    protected void onStop() {
        super.onStop();
        imageViewRotate.clearAnimation();
    }
}
