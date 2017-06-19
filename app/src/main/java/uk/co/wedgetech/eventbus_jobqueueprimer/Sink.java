package uk.co.wedgetech.eventbus_jobqueueprimer;

import android.content.Context;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class Sink {

    Context appContext;
    public Sink(Context context) {
        appContext = context;

        //Register for EventBus events
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MyEventMessage message) {
        new MyToast().show(appContext, "Class:" + message.getMessage());
    }

}
