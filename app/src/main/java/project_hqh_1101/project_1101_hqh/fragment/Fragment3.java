package project_hqh_1101.project_1101_hqh.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import project_hqh_1101.project_1101_hqh.R;
import project_hqh_1101.project_1101_hqh.activity.MainActivity;


public class Fragment3 extends Fragment {
    private TextView time;
    private TextView skip;
    private int num = 0;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what)
            {
                case 1:
                    if(num>3)
                    {
                        handler.removeCallbacks(runnable);
                        startActivity(new Intent(getActivity(), MainActivity.class));
                        getActivity().overridePendingTransition(R.anim.star,R.anim.end);
                        getActivity().finish();
                        break;
                    }
                    time.setText(num+" ");

                    break;
            }
        }
    };
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            addTime();
            handler.postDelayed(this,1000);
        }
    };
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment3, container, false);
        initView(view);
        initListener();
        handler.postDelayed(runnable,1000);
        return view;
    }

    private void initListener() {
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.removeCallbacks(runnable);
                startActivity(new Intent(getActivity(),MainActivity.class));
                getActivity().overridePendingTransition(R.anim.star,R.anim.end);
                getActivity().finish();
            }
        });
    }

    private void initView(View view) {
        time = (TextView) view.findViewById(R.id.time);
        skip = (TextView) view.findViewById(R.id.skip);
    }
    public void addTime()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message message = new Message();
                message.what=1;
                handler.sendMessage(message);
                num++;
            }
        }).start();
    }
}
