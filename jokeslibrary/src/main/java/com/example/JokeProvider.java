package com.example;

public class JokeProvider {

    public static Joke getJoke() {
        Joke joke = new Joke();
        joke.setText("Why is 6 afraid of 7?", "Because 7 8 9.");
        return joke;
    }

}
