package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.android.displayjokes.DisplayJokeActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements JokeReceivedListener {

    ProgressBar progressBar;
    InterstitialAd interstitialAd;
    private String setupAndPunchline;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        progressBar = (ProgressBar) root.findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.INVISIBLE);

        AdView mAdView = (AdView) root.findViewById(R.id.adView);

        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);

        root.findViewById(R.id.tell_joke_button)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        progressBar.setVisibility(View.VISIBLE);
                        new EndpointsAsyncTask().execute(MainActivityFragment.this);
                    }
                }
        );

        return root;
    }

    private void displayJoke(String setupAndPunchline) {
        Intent intent = new Intent(getContext(), DisplayJokeActivity.class);
        intent.putExtra("joke", setupAndPunchline);
        startActivity(intent);
    }

    @Override
    public void jokeReceived(String setupAndPunchline) {
        progressBar.setVisibility(View.INVISIBLE);
        this.setupAndPunchline = setupAndPunchline;

        if (interstitialAd.isLoaded()) {
            interstitialAd.show();
        } else {
            displayJoke(setupAndPunchline);
        }

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        interstitialAd = new InterstitialAd(getActivity());
        interstitialAd.setAdUnitId(getString(R.string.banner_ad_unit_id));
        getAd();

        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                getAd();
                displayJoke(setupAndPunchline);
            }
        });

    }

    private void getAd() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        interstitialAd.loadAd(adRequest);
    }

}
