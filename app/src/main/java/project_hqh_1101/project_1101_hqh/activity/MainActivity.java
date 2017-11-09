package project_hqh_1101.project_1101_hqh.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.Switch;

import project_hqh_1101.project_1101_hqh.R;


public class MainActivity extends AppCompatActivity {
    private Switch Switch1;
    private Switch Switch2;
    private ImageView img1;
    private ImageView img2;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            flash();
            handler.postDelayed(this,2000);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }

    private void initListener() {
        Switch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Switch1.isChecked())
                {
                  img1.setImageResource(R.drawable.roadlamp_on);
                  handler.postDelayed(runnable,2000);
                }
                else
                {
                    img1.setImageResource(R.drawable.roadlamp_off);
                    handler.removeCallbacks(runnable);
                    img1.clearAnimation();
                }
            }
        });

        Switch2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Switch2.isChecked())
                {
                    img2.setImageResource(R.drawable.blower_on);
                    Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.xuanzhuan);
                    LinearInterpolator linearInterpolator = new LinearInterpolator();
                    animation.setInterpolator(linearInterpolator);
                    img2.startAnimation(animation);
                }
                else
                {
                    img2.setImageResource(R.drawable.blower_off);
                    img2.clearAnimation();
                }
            }
        });
    }

    private void initView() {
        Switch1 = (Switch) this.findViewById(R.id.swith1);
        Switch2  = (Switch) this.findViewById(R.id.swith2);
        img1 = (ImageView) this.findViewById(R.id.light);
        img2 = (ImageView) this.findViewById(R.id.fenshan);
    }
    public void flash()
    {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(img1, "alpha", 1, 0.2f,1);
        objectAnimator.setDuration(2000);
        AnimatorSet set=new AnimatorSet();
        set.playSequentially(objectAnimator);
        set.start();
    }
}
