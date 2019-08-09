package edu.shepherd.rams.myfirst2dgame;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.util.Log;
import android.view.SurfaceView;
import android.view.SurfaceHolder;



/**
 * created by Johnnie Meredith August 2, 2019
 */
public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread thread;
    private CharacterSprite characterSprite;
    final MediaPlayer mp;
    MediaStore.Audio.Media media;


    public GameView(Context context) {
        super(context);

        getHolder().addCallback(this);
        thread = new MainThread(getHolder(), this);
        setFocusable(true);
        this.mp = MediaPlayer.create(context, R.raw.shortcartoonsquelch);

    }
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread.setRunning(true);
        thread.start();
        characterSprite = new CharacterSprite(BitmapFactory.decodeResource(getResources(),R.drawable.weirdpac));


    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                thread.setRunning(false);
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }

    }
    public void update() {
        int comparex = characterSprite.getxVelocity();
        Log.d("xVelocity is: ", "" + characterSprite.getxVelocity());

        characterSprite.update();
        //if(comparex!=characterSprite.getxVelocity())
            //mp.start();
        if (characterSprite.getxVelocity() < 0) {

            characterSprite.setImage(BitmapFactory.decodeResource(getResources(), R.drawable.weirdpacrev));
        }
        else{

            characterSprite.setImage(BitmapFactory.decodeResource(getResources(), R.drawable.weirdpac));
        }
    }
    @Override
    public void draw(Canvas canvas) {

        super.draw(canvas);
        if (canvas != null) {
            canvas.drawColor(Color.WHITE);
            Paint paint = new Paint();
            paint.setColor(Color.rgb(250, 250, 0));
            canvas.drawRect(100, 100, 200, 200, paint);
            characterSprite.draw(canvas);
        }

    }
}
