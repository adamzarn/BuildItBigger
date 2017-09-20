package com.udacity.gradle.builditbigger;

import android.os.ConditionVariable;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertTrue;

/**
 * Created by adamzarn on 9/20/17.
 */

@RunWith(AndroidJUnit4.class)
public class GetJokeTest implements JokeReceivedListener {

    private EndpointsAsyncTask endpointsAsyncTask;
    private ConditionVariable execution;
    private String setupAndPunchline;

    @Test
    public void jokeSuccessfullyRetrieved() {

        endpointsAsyncTask = new EndpointsAsyncTask();
        execution = new ConditionVariable();

        endpointsAsyncTask.execute(this);
        execution.block(); //Block the thread until the joke is received.

        String expectedJoke = "Why is 6 afraid of 7?\n\nBecause 7 8 9.";
        assertTrue(this.setupAndPunchline.equals(expectedJoke));

    }

    @Override
    public void jokeReceived(String setupAndPunchline) {
        execution.open(); //Continue execution now that the joke is received.
        this.setupAndPunchline = setupAndPunchline;
    }


}
