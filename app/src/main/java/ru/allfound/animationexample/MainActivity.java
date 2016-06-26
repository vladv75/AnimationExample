package ru.allfound.animationexample;

import android.animation.ObjectAnimator;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button buttonStart, buttonShow;
    EditText editText;
    TextView textView1, textView2, textView3;

    private boolean start = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonStart = (Button) findViewById(R.id.buttonStart);
        buttonShow = (Button) findViewById(R.id.buttonShow);
        editText = (EditText) findViewById(R.id.editText);
        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);

        final OvershootInterpolator over = new OvershootInterpolator(2);
        final ObjectAnimator rotate_180_textView3 = ObjectAnimator.ofFloat(textView3, "rotation", 0f, 180f);

        final ImageView spaceshipImage = (ImageView) findViewById(R.id.imageView);
        final Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(this, R.anim.hyperspace_jump);

        final ImageView mImageViewFilling = (ImageView) findViewById(R.id.imageview_animation_list_filling);

        buttonShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView1.setText(editText.getText().toString());
                textView1.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_text1));

                textView2.setText(editText.getText().toString());
                textView2.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_text2));

                //textView3.setText(editText.getText().toString());
                rotate_180_textView3.setDuration(1000).start();

                assert spaceshipImage != null;
                spaceshipImage.startAnimation(hyperspaceJumpAnimation);
            }
        });

        buttonStart.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(final View v, final MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (start) {
                            buttonStart.setBackground(getResources().getDrawable(R.drawable.frame_button_start));
                            assert mImageViewFilling != null;
                            ((AnimationDrawable) mImageViewFilling.getBackground()).stop();
                            buttonStart.setText("Start");
                            start = false;
                        } else {
                            buttonStart.setBackground(getResources().getDrawable(R.drawable.frame_button_stop));
                            assert mImageViewFilling != null;
                            ((AnimationDrawable) mImageViewFilling.getBackground()).start();
                            buttonStart.setText("Stop");
                            start = true;
                        }
                        buttonStart.animate().scaleX(0.75f).scaleY(0.75f).setDuration(750);
                        break;
                    case MotionEvent.ACTION_MOVE: break;
                    case MotionEvent.ACTION_UP:
                        buttonStart.animate().scaleX(1f).scaleY(1f).setDuration(500).setInterpolator(over);
                        break;
                }
                return false;
            }
        });

    }

}
