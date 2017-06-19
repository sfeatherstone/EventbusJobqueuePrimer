package uk.co.wedgetech.eventbus_jobqueueprimer;

import android.app.Application;

import com.birbit.android.jobqueue.JobManager;
import com.birbit.android.jobqueue.config.Configuration;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MyApplication extends Application {
    public JobManager jobManager;
    public EventBus eventBus;

    @Override
    public void onCreate() {
        super.onCreate();

        //Setup JobManager
        Configuration.Builder builder = new Configuration.Builder(this);
        jobManager = new JobManager(builder.build());

        //Setup EventBus
        eventBus = EventBus.getDefault();

        //In this example, this class is subscribed to MyEventMessage, so we need to register
        EventBus.getDefault().register(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();

        // Clean up eventbus subscription
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onEvent(MyEventMessage message) {
        new MyToast().show(this, "App:" + message.getMessage());
    }
}
