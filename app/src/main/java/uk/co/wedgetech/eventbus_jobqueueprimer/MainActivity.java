package uk.co.wedgetech.eventbus_jobqueueprimer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {

    Sink sink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button but = (Button) findViewById(R.id.button_start_pojo);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sink = new Sink(getApplicationContext());
            }
        });

        Button but2 = (Button) findViewById(R.id.button_queue_job);
        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Queue simple job
                final MyApplication application = (MyApplication) getApplicationContext();
                application.jobManager.addJobInBackground(new DelayJob());
            }
        });

        //Register for events
        EventBus.getDefault().register(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //Don't forget to unregister from EventBus
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MyEventMessage message) {
        new MyToast().show(this, "Activity:" + message.getMessage());
    }
}
