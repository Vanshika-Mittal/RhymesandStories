package com.example.android.rhymesandstories;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by vanshika on 12/5/17.
 *
 * Creates a new {@link Rhyme object}
 */
public class Rhyme {

    //Rhyme name.
    private String mRhymeName;

    //Rhyme resource id.
    private int mAudioResourceId;

    /**
     * Create a new Rhyme object
     *
     * @param RhymeName is the name of the rhyme
     * @param AudioResourceId is the word's pronunciation.
     */
    public Rhyme(String RhymeName, int AudioResourceId) {
        mRhymeName = RhymeName;
        mAudioResourceId = AudioResourceId;
    }

    /**
     * Return the Rhyme name of the word.
     */
    public String getRhymeName() {
        return mRhymeName;
    }

    /**
     * Return the Audio resource id of the word.
     */
    public int getAudioResourceId() {
        return mAudioResourceId;
    }

}
