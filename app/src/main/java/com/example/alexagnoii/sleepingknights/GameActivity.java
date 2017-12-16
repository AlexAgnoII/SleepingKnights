package com.example.alexagnoii.sleepingknights;

import android.app.FragmentManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.alexagnoii.sleepingknights.DialogFragments.HelpFragment;
import com.example.alexagnoii.sleepingknights.DialogFragments.SettingsFragment;
import com.example.alexagnoii.sleepingknights.Knight.Item;

public class GameActivity extends AppCompatActivity {
    public static final int NOTIFICATION_ID_PB = 0;
    public static final int PENDINT_SA = 0;
    FragmentManager fm;
    HelpFragment hf;
    SettingsFragment sf;
    RelativeLayout bg;
    Button btnSettings, btnHelp, btnMarket, btnInventory;
    DatabaseHelper dbh;
    SharedPreferences dsp;

    TextView lblhp, hpvalue, lblatk, atkvalue, lbldef, defvlaue;

    SurfaceView gameBoard;

    DrawingThread thread;

    int avatarX = 0;
    int avatarY = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        gameBoard = (SurfaceView) findViewById(R.id.container);

        lblhp = (TextView)findViewById(R.id.lblhp);
        hpvalue = (TextView)findViewById(R.id.hpvalue);
        lblatk = (TextView)findViewById(R.id.lblatk);
        atkvalue = (TextView)findViewById(R.id.atkvalue);
        lbldef = (TextView)findViewById(R.id.lbldef);
        defvlaue = (TextView)findViewById(R.id.defvalue);

        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/JourneyPS3.ttf");

        lblhp.setTypeface(tf);
        hpvalue.setTypeface(tf);
        lblatk.setTypeface(tf);
        atkvalue.setTypeface(tf);
        lbldef.setTypeface(tf);
        defvlaue.setTypeface(tf);

        gameBoard.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                thread = new DrawingThread(gameBoard.getHolder());
                thread.setRunning(true);
                thread.start();

                tryDrawing(surfaceHolder);
            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
                tryDrawing(surfaceHolder);
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

            }

            private void tryDrawing(SurfaceHolder holder) {
                Log.i("", "Trying to draw...");

                Canvas canvas = holder.lockCanvas();
                if (canvas == null) {
                    Log.e("", "Cannot draw onto the canvas as it's null");
                } else {
                    draw(canvas);
                    holder.unlockCanvasAndPost(canvas);
                }
            }


        });

        dbh = new DatabaseHelper(getBaseContext());
        //Get id from sharedpreference;
        dsp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        long userID = dsp.getLong("id", -1);
        updateMainMenuStats(userID);


        btnSettings = (Button)findViewById(R.id.btn_settings);
        btnHelp = (Button)findViewById(R.id.btn_help);
        btnMarket = (Button)findViewById(R.id.btn_market);
        btnInventory = (Button)findViewById(R.id.btn_inventory);

        fm = getFragmentManager();
      //  hf = new HelpFragment();
        sf = new SettingsFragment();

        btnInventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //SimpleDialog sd = new SimpleDialog(R.layout.inventory);
                //sd.show(getSupportFragmentManager(), "");
                InventoryDialog id = new InventoryDialog(GameActivity.this);
                id.requestWindowFeature(Window.FEATURE_NO_TITLE);
                id.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                id.show();

                //Equiping happens here
                id.setOnClickListener(new InventoryDialog.OnClickListener() {
                    @Override
                    public void onItemClick(long id) {
                        Log.i("LOGS|GAMEACTIVITY", "Equip: " + (id+""));
                    }
                });

            }
        });
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sf.show(fm, "settings");
            }
        });
        btnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HelpDialog hd = new HelpDialog(GameActivity.this);
                hd.requestWindowFeature(Window.FEATURE_NO_TITLE);
                hd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                hd.show();


            }
        });
        btnMarket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MarketDialog md = new MarketDialog(GameActivity.this);
                md.requestWindowFeature(Window.FEATURE_NO_TITLE);
              //  md.setContentView(R.layout.market);
                md.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
              //  ((TextView)dialog.findViewById(R.id.lblmarket)).setTypeface(Typeface.createFromAsset(getAssets(), "fonts/JourneyPS3.ttf"));
                md.show();

            }
        });
    }

    private void draw(Canvas canvas) {
        // draw all assets
        Log.i("", "Drawing...");

        if(canvas!=null) {
            // clear the board
            canvas.drawRGB(255, 255, 255);

            // ... or draw a bitmap
            Bitmap board = BitmapFactory.decodeResource(getResources(),R.drawable.boardsized);
            Bitmap monster = BitmapFactory.decodeResource(getResources(), R.drawable.monster);

            // the -(android.getWidth()/2) is just so that we can center the icon to the avatar's center
            //  canvas.drawBitmap(board, avatarX-(android.getWidth()/2), avatarY-, null);
            canvas.drawBitmap(board, 0, 0, null);
            canvas.drawBitmap(monster, (monster.getWidth()/2), (monster.getHeight()/2), null);

        }
    }

    class DrawingThread extends Thread {
        private SurfaceHolder _surfaceHolder;

        private boolean running = false;

        public DrawingThread(SurfaceHolder surfaceHolder) {
            _surfaceHolder = surfaceHolder;
        }

        public void setRunning(boolean run) {
            running = run;
        }

        @Override
        public void run() {
            Canvas c;
            while (running && !Thread.interrupted()) {
                c = null;
                try {
                    c = _surfaceHolder.lockCanvas(null);
                    synchronized (_surfaceHolder) {
                        //call a method that draws all the required objects onto the canvas.
                        draw(c);
                    }
                } finally {
                    // do this in a finally so that if an exception is thrown
                    // during the above, we don't leave the Surface in an
                    // inconsistent state
                    if (c != null) {
                        _surfaceHolder.unlockCanvasAndPost(c);
                    }
                }
                //sleep for a short period of time.
                if (!running) return;  //don't sleep, just exit if we are done.
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();

                }
            }
        }
    }


    private void updateMainMenuStats(long userID) {
        if(userID != -1) {
            Log.i("LOGS|GAMEACTIVITY", "User found = " + userID);
            Log.i("LOGS|GAMEACTIVITY", "User name: " + dsp.getString("name", null));
            Log.i("LOGS|GAMEACTIVITY", "Health: " + dsp.getInt("hp", -1));
            Log.i("LOGS|GAMEACTIVITY", "Attack: " + dsp.getInt("attack", -1));
            Log.i("LOGS|GAMEACTIVITY", "Defense: " + dsp.getInt("defense", -1));

            hpvalue.setText(dsp.getInt("hp", -1)+"");
            atkvalue.setText(dsp.getInt("attack", -1)+"");
            defvlaue.setText(dsp.getInt("defense", -1)+"");

        }

        else {
            Log.i("LOGS|GAMEACTIVITY", "No id for some reason wtf la?");
        }
    }

    private void updateAtk(Long id) {
        Item item = dbh.getItem(id);

        int prev = Integer.parseInt(atkvalue.getText().toString());
        atkvalue.setText((prev+item.getBoost())+"");
    }
}
