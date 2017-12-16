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
import android.widget.Toast;

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
    int originalValue;

    TextView lblhp, hpvalue, lblatk, atkvalue, lbldef, defvlaue, lblarmor, armorvalue,
            lblweapon, weaponvalue, lblshield, shieldvalue;

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
        lblarmor = (TextView)findViewById(R.id.lblarmor);
        armorvalue = (TextView)findViewById(R.id.armorvalue);
        lblweapon = (TextView)findViewById(R.id.lblweapon);
        weaponvalue = (TextView)findViewById(R.id.weaponvalue);
        lblshield = (TextView)findViewById(R.id.lblshield);
        shieldvalue = (TextView)findViewById(R.id.shieldvalue);

        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/JourneyPS3.ttf");

        lblhp.setTypeface(tf);
        hpvalue.setTypeface(tf);
        lblatk.setTypeface(tf);
        atkvalue.setTypeface(tf);
        lbldef.setTypeface(tf);
        defvlaue.setTypeface(tf);

        lblarmor.setTypeface(tf);
        armorvalue.setTypeface(tf);
        lblshield.setTypeface(tf);
        shieldvalue.setTypeface(tf);
        lblweapon.setTypeface(tf);
        weaponvalue.setTypeface(tf);

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
                    public void onItemClick(long id, int kind) {
                        Log.i("LOGS|GAMEACTIVITY", "Equip: " + (id+""));
                        if(kind == 1)
                            equiping(id);
                        else {
                            unequiping(id);
                        }
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

    private void unequiping(long id) {
        SharedPreferences.Editor dspEditor = dsp.edit();
        long currentArmor, currentWeapon, currentShield;

        currentArmor = dsp.getLong("armor", -1);
        currentWeapon = dsp.getLong("weapon", -1);
        currentShield = dsp.getLong("shield", -1);

        if(currentArmor == id && currentArmor != -1) {
            Item item = dbh.getItem(id);
            int defense = dsp.getInt("defense", -1);
            int original;

            if(defense != -1) {
                original = defense - item.getBoost();
                dspEditor.putInt("defense", original);
                dspEditor.putLong("armor", 0);
                dspEditor.apply();
                armorvalue.setText("N/A");
                defvlaue.setText(original+"");
            }
        }
        else if(currentWeapon == id && currentWeapon != -1) {
            Item item = dbh.getItem(id);
            int attack = dsp.getInt("attack", -1);
            int original;

            if(attack != -1) {
                original = attack - item.getBoost();
                dspEditor.putInt("attack", original);
                dspEditor.putLong("weapon", 0);
                dspEditor.apply();
                weaponvalue.setText("N/A");
                atkvalue.setText(original+"");
            }
        }
        else if (currentShield == id && currentShield != -1) {
            Item item = dbh.getItem(id);
            int defense = dsp.getInt("defense", -1);
            int original;

            if(defense != -1) {
                original = defense - item.getBoost();
                dspEditor.putInt("defense", original);
                dspEditor.putLong("shield", 0);
                dspEditor.apply();
                shieldvalue.setText("N/A");
                defvlaue.setText(original+"");
            }
        }
        else {
            Toast.makeText(getBaseContext(), "Currently not equipped!", Toast.LENGTH_SHORT).show();
        }
    }

    private void equiping(long id) {
        Item item = dbh.getItem(id);
        int type = item.getType();

        SharedPreferences.Editor dspEditor = dsp.edit();
        Log.i("LOGS|GAMEACTIVITY", "To be worn: " +id+"");
        //Check if there's currently an equipped item
        if(type == 1) { //weapon
            long weaponCheck;
            weaponCheck = dsp.getLong("weapon", -1);
            Log.i("LOGS|GAMEACTIVITY", weaponCheck+"");
            if(weaponCheck == 0) {
                Log.i("LOGS|GAMEACTIVITY", "Has no equiped");
                performEquip(item, "weapon");
                dspEditor.putLong("weapon", id);
                dspEditor.apply();
                Log.i("LOGS|GAMEACTIVITY", dsp.getLong("weapon", -1)+"");

            }
            else {
                Log.i("LOGS|GAMEACTIVITY", "Has equiped");

                //if equal, dont equip.
                if(weaponCheck == id) {
                    Toast.makeText(getBaseContext(), "Already equipped!", Toast.LENGTH_SHORT).show();
                }
                //If not, equip.
                else {
                    performNewEquip(item, "weapon");
                    dspEditor.putLong("weapon", id);
                    dspEditor.apply();

                }
            }

        }
        else if (type == 2) { //armor
            long armorCheck;
            armorCheck = dsp.getLong("armor", -1);
            Log.i("LOGS|GAMEACTIVITY", armorCheck+"");
            if(armorCheck == 0) {
                Log.i("LOGS|GAMEACTIVITY", "Has no equiped");
                performEquip(item, "armor");
                dspEditor.putLong("armor", id);
                dspEditor.apply();
            }
            else {
                Log.i("LOGS|GAMEACTIVITY", "Has equiped");
                if(armorCheck == id) {
                    Toast.makeText(getBaseContext(), "Already equipped!", Toast.LENGTH_SHORT).show();
                }
                else {
                    performNewEquip(item, "armor");
                    dspEditor.putLong("weapon", id);
                    dspEditor.apply();
                }
            }
            Log.i("LOGS|GAMEACTIVITY", dsp.getLong("armor", -1)+"");

        }

        else if (type == 3) { //shield
            long shieldCheck;
            shieldCheck = dsp.getLong("shield", -1);
            Log.i("LOGS|GAMEACTIVITY", shieldCheck+"");
            if(shieldCheck == 0) {
                Log.i("LOGS|GAMEACTIVITY", "Has no equiped");
                performEquip(item, "shield");
                dspEditor.putLong("shield", id);
                dspEditor.apply();
                Log.i("LOGS|GAMEACTIVITY", dsp.getLong("shield", -1)+"");
            }
            else {
                Log.i("LOGS|GAMEACTIVITY", "Has equiped");
                if(shieldCheck == id) {
                    Toast.makeText(getBaseContext(), "Already equipped!", Toast.LENGTH_SHORT).show();
                }
                else {
                    performNewEquip(item, "shield");
                    dspEditor.putLong("shield", id);
                    dspEditor.apply();
                }
            }

        }
        else {
            Log.i("LOGS|GAMEACTIVITY", "didnt find wep type.");
        }

    }

    private void performNewEquip(Item item, String name) {
        SharedPreferences.Editor dspEditor = dsp.edit();
        originalValue = 0;
        long id = dsp.getLong(name, -1);
        Item currentItem = dbh.getItem(id);

            if(name == "weapon") {
                int currentAtk = dsp.getInt("attack", -1);
                originalValue = currentAtk - currentItem.getBoost() + item.getBoost();
                dspEditor.putInt("attack", originalValue);
                dspEditor.apply();
                atkvalue.setText(originalValue+"");
                weaponvalue.setText(item.getName());
            }
            else if (name == "shield" || name == "armor") {
                int currentDef = dsp.getInt("attack", -1);
                originalValue = currentDef - currentItem.getBoost() + item.getBoost();
                dspEditor.putInt("defense", originalValue);
                dspEditor.apply();
                defvlaue.setText(originalValue+"");

                if(name == "armor") {
                    armorvalue.setText(item.getName());
                }
                else {
                    shieldvalue.setText(item.getName());
                }
            }

    }

    private void performEquip(Item item, String name) {
        SharedPreferences.Editor dspEditor = dsp.edit();

        originalValue = 0;
        //wear

            if(name == "weapon") {
                originalValue = Integer.parseInt(atkvalue.getText().toString());
                dspEditor.putInt("attack", originalValue+item.getBoost());
                dspEditor.apply();
                atkvalue.setText(originalValue+item.getBoost()+"");

                weaponvalue.setText(item.getName());
            }
            else if(name == "armor" || name == "shield") {
                originalValue = Integer.parseInt(defvlaue.getText().toString());
                dspEditor.putInt("defense", originalValue+item.getBoost());
                dspEditor.apply();
                defvlaue.setText(originalValue+item.getBoost()+"");

                if(name == "armor") {
                    armorvalue.setText(item.getName());
                }
                else {
                    shieldvalue.setText(item.getName());
                }
            }

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

            long weapon , armor , shield;

            weapon = dsp.getLong("weapon", -1);
            armor = dsp.getLong("armor", -1);
            shield = dsp.getLong("weapon", -1);

            //Only update the label
            if(weapon != 0 && weapon != -1) {
                Item item = dbh.getItem(weapon);
                weaponvalue.setText(item.getName());
            }
            if(armor != 0 && armor != -1) {
                Item item = dbh.getItem(armor);
                armorvalue.setText(item.getName());
            }
            if (shield != 0 && shield != -1) {
                Item item = dbh.getItem(shield);
                shieldvalue.setText(item.getName());
            }

        }

        else {
            Log.i("LOGS|GAMEACTIVITY", "No id for some reason wtf la?");
        }
    }

}
