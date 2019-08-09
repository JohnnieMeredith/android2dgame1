package edu.shepherd.rams.myfirst2dgame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.MediaPlayer;


public class CharacterSprite {
    private Bitmap image;
    public int x;
    public int y;
    public int xVelocity = 10;
    private int yVelocity = 12;
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;


    public CharacterSprite(Bitmap bmp) {
        image = bmp;
        x = 100;
        y = 100;

    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, x, y, null);
    }

    public void update() {
        x += xVelocity;
        y += yVelocity;
        if ((x > screenWidth - image.getWidth()) || (x < 0)) {
            xVelocity = xVelocity * -1;
        }
        if ((y > screenHeight - image.getHeight()) || (y < 0)) {
            yVelocity = yVelocity * -1;
        }


    }
    public int getxVelocity(){
        return this.xVelocity;
    }
    public void setImage(Bitmap bitmap){
        this.image=bitmap;
    }
}