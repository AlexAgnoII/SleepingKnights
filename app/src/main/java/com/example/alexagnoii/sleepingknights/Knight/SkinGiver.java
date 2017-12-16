package com.example.alexagnoii.sleepingknights.Knight;

import com.example.alexagnoii.sleepingknights.R;

/**
 * Created by Alex Agno II on 12/7/2017.
 */

public class SkinGiver {

    public static int[] skin = {R.drawable.armour, R.drawable.sword1, R.drawable.shield,
                         R.drawable.sword2, R.drawable.armour2, R.drawable.shield2};


    public static int give(long id) {
        return skin[(int) id-1];
    }

}
