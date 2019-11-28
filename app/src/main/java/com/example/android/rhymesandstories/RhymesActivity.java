package com.example.android.rhymesandstories;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

public class RhymesActivity extends AppCompatActivity {

    //Handles media playback of all audio files.
    private MediaPlayer mMediaPlayer;

    /**
     * This listener is triggered when the {@link MediaPlayer} has completed playing the audio file.
     */
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            LinearLayout popUpBar = (LinearLayout) findViewById(R.id.pop_up_bar);
            popUpBar.setVisibility(View.GONE);

            //Finds the pause button by view id.
            Button pauseButton = (Button) findViewById(R.id.pause_button);
            //Turns the pause button's visibility state to visible so that it shows
            pauseButton.setVisibility(View.VISIBLE);

            //Finds the play button by view id.
            Button playButton = (Button) findViewById(R.id.play_button);
            //Turns the play button's visibility state to gone so that it takes up no space
            playButton.setVisibility(View.GONE);

            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ArrayList<Rhyme> rhymes = new ArrayList<>();
        rhymes.add(new Rhyme("Alouette", R.raw.alluetto));
        rhymes.add(new Rhyme("Rudolf the Red Nosed Reindeer", R.raw.rudolph_red_nose_reindeer));
        rhymes.add(new Rhyme("Ishiya Went To Farmhouse (new)", R.raw.ishiya_farmhouse_2));
        rhymes.add(new Rhyme("Shepherd Boy", R.raw.shephard_boy));
        rhymes.add(new Rhyme("Lion and Mouse", R.raw.lion_mouse));
        rhymes.add(new Rhyme("Fox, Ducky and Monkey", R.raw.fox_ducky_monkey));
        rhymes.add(new Rhyme("Ishiya Went to Farmhouse (old)", R.raw.ishiya_went_to_farmhouse));
        rhymes.add(new Rhyme("Head, Shoulder, Knees and Toes", R.raw.head_shoulders));
        rhymes.add(new Rhyme("Ontro Pontro", R.raw.ontro_pontro));
        rhymes.add(new Rhyme("Jingle Bells", R.raw.jingle_bells));
        rhymes.add(new Rhyme("Peter Peter Pumpkin Eater", R.raw.peter_pumpkin_eater));
        rhymes.add(new Rhyme("Ringa Ringa Roses", R.raw.ringa_ringa_roses));
        rhymes.add(new Rhyme("Yankee Doodle Went To Town", R.raw.yankee_doodle));

        //Initializing the ArrayAdapter.
        RhymeAdapter adapter = new RhymeAdapter(this, rhymes);
        //Finding the ListView.
        ListView listView = (ListView) findViewById(R.id.list);
        //Adding the Array Adapter to the list view.
        listView.setAdapter(adapter);

        /**
         *Set a click listener to play an audio file when the list item is clicked on.
         */
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                releaseMediaPlayer();

                //Finds the pause button by view id.
                Button pauseButton = (Button) findViewById(R.id.pause_button);
                //Turns the pause button's visibility state to visible so that it shows
                pauseButton.setVisibility(View.VISIBLE);

                //Finds the play button by view id.
                Button playButton = (Button) findViewById(R.id.play_button);
                //Turns the play button's visibility state to gone so that it takes up no space
                playButton.setVisibility(View.GONE);

                //Finds the pop up bar by view id
                LinearLayout popUpBar = (LinearLayout) findViewById(R.id.pop_up_bar);
                //Makes the pop up bar visible so that the user can control the audio
                popUpBar.setVisibility(View.VISIBLE);

                //Get the {@link Word} object at the given position the user has clicked on.
                Rhyme rhyme = rhymes.get(position);

                //Create and start the media player
                mMediaPlayer = MediaPlayer.create(RhymesActivity.this, rhyme.getAudioResourceId());
                mMediaPlayer.start();

                //Set a completion listener on the media player 
                mMediaPlayer.setOnCompletionListener(mCompletionListener);
            }
        });

    }

    public void pauseMediaPlayer(View view) {
        //Pauses the media player
        mMediaPlayer.pause();

        //Finds the pause button by view id.
        Button pauseButton = (Button) findViewById(R.id.pause_button);
        //Turns the pause button's visibility state to gone so that it takes up no space
        pauseButton.setVisibility(View.GONE);

        //Finds the play button by view id.
        Button playButton = (Button) findViewById(R.id.play_button);
        //Turns the play button's visibility state to visible so that it shows
        playButton.setVisibility(View.VISIBLE);
    }

    public void playMediaPlayer(View view) {
        //Resumes the media player
        mMediaPlayer.start();

        //Finds the pause button by view id.
        Button pauseButton = (Button) findViewById(R.id.pause_button);
        //Turns the pause button's visibility state to visible so that it shows
        pauseButton.setVisibility(View.VISIBLE);

        //Finds the play button by view id.
        Button playButton = (Button) findViewById(R.id.play_button);
        //Turns the play button's visibility state to gone so that it takes up no space
        playButton.setVisibility(View.GONE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        //When the app is stopped, release media player resources.
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {
        //If media player is not null, it might be playing a sound.
        if (mMediaPlayer != null) {
            //Release MediaPlayer resources regardless of its state because we no longer need it.
            mMediaPlayer.release();
        }

        //Set media player to null.
        //In our code we have decided that it is an easy way to tell that the media player is not
        // configuered at the moment.
        mMediaPlayer = null;
    }
}

