package uk.co.wedgetech.eventbus_jobqueueprimer;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.birbit.android.jobqueue.Job;
import com.birbit.android.jobqueue.Params;
import com.birbit.android.jobqueue.RetryConstraint;

import uk.co.wedgetech.eventbus_jobqueueprimer.MyApplication;
import uk.co.wedgetech.eventbus_jobqueueprimer.MyEventMessage;

public class DelayJob extends Job {
    DelayJob() { super(new Params(1));
    }

    @Override
    public void onAdded() {
    }

    @Override
    public void onRun() throws Throwable {
        //Simple job that take time
        Thread.sleep(5000);

        MyApplication application = (MyApplication) getApplicationContext();
        //Send event back to subscribers of MyEventMessage
        application.eventBus.post(new MyEventMessage("Hello world!"));
    }

    @Override
    protected void onCancel(int i, @Nullable Throwable throwable) {
    }

    @Override
    protected RetryConstraint shouldReRunOnThrowable(@NonNull Throwable throwable, int i, int i1) {
        return null;
    }
}
