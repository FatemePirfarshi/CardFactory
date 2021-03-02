package com.example.cardfactory.viewmodel;

import android.app.Application;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Vibrator;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.cardfactory.R;
import com.example.cardfactory.data.model.Card;
import com.example.cardfactory.data.repository.CardRepository;

import java.io.IOException;
import java.util.List;
import java.util.Random;

public class CardViewModel extends AndroidViewModel {

    private CardRepository mRepository;
    private MediaPlayer mMediaPlayer;

    private LiveData<List<Card>> mCardsLiveData;
    private MutableLiveData<Card> mCurrentCardLiveData = new MutableLiveData<>();
    private MutableLiveData<Integer> mRandomIndexLiveData = new MutableLiveData<>();

    public CardViewModel(@NonNull Application application) {
        super(application);
        mRepository = CardRepository.getInstance();
        mCardsLiveData = mRepository.getCardsLiveData();
    }

    public LiveData<List<Card>> getCardsLiveData() {
        return mCardsLiveData;
    }

    public MutableLiveData<Card> getCurrentCardLiveData() {
        return mCurrentCardLiveData;
    }

    public MutableLiveData<Integer> getRandomIndexLiveData() {
        return mRandomIndexLiveData;
    }

    public void fetchCardItems() {
        mRepository.fetchCardItems();
    }

    public void clickTry() {
        int random = randomIndex();
        mCurrentCardLiveData.setValue(mCardsLiveData.getValue().get(random));
        mRandomIndexLiveData.setValue(random);
        checkCode();
    }

    private int randomIndex() {
        return new Random().nextInt(mCardsLiveData.getValue().size());
    }

    private void checkCode() {
        switch (mCurrentCardLiveData.getValue().getCode()) {
            case 1:
                vibrate();
                break;
            case 2:
                playMusic();
                break;
        }
    }

    public void vibrate() {
        Vibrator vibe = (Vibrator) getApplication().getSystemService(Context.VIBRATOR_SERVICE);
        vibe.vibrate(100);
    }

    //this methods for play music
    public void playMusic() {
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer.release();
            startPlayMusic();
        } else {
            startPlayMusic();
        }
    }

    private void startPlayMusic() {
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mMediaPlayer.setDataSource(mCurrentCardLiveData.getValue().getSoundLink());
            mMediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mMediaPlayer.start();
    }


    //this methods for set theme
    public int setBackgroundTheme() {
        return setThemeCards(
                R.drawable.art_background,
                R.drawable.fun_background,
                R.drawable.sport_background
        );
    }

    public int setIconCards() {
        return setThemeCards(
                R.drawable.ic_art_cards,
                R.drawable.ic_fun_cards,
                R.drawable.ic_sport_cards);
    }

    private int setThemeCards(int artTheme, int funTheme, int sportTheme) {
        switch (mCurrentCardLiveData.getValue().getTag()) {
            case "art":
                return artTheme;
            case "fun":
                return funTheme;
            default:
                return sportTheme;
        }
    }
}
