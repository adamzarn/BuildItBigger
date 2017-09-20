package com.example;

/**
 * Created by adamzarn on 9/19/17.
 */

public class Joke {

    private String setup;
    private String punchline;

    public String getSetup() {
        return setup;
    }

    public String getPunchline() {
        return punchline;
    }

    public String getSetupAndPunchline() {
        return setup + "\n\n" + punchline;
    }

    public void setText(String setup, String punchline) {
        this.setup = setup;
        this.punchline = punchline;
    }

}