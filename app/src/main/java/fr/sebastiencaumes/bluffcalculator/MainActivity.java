package fr.sebastiencaumes.bluffcalculator;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends FragmentActivity {
    ViewPager viewPager;
    //Array for tabs titles
    String[] bigTitles = {"Bluff", "Semi Bluff", "Call"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PagerAdapter pAdapter = new PagerAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(pAdapter);

        //first initialisation, if not, tab indicator not move if there is no first click on;
        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            public void onPageSelected(int position) {
                getActionBar().setSelectedNavigationItem(position);

            }
        });

        //tabs settings
        ActionBar actionBar = getActionBar();
        actionBar.setLogo(R.mipmap.ic_logo);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        ActionBar.TabListener tabListener = new ActionBar.TabListener() {

            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
                //viewPager = (ViewPager) findViewById(R.id.pager);
                viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
                    public void onPageSelected(int position){
                        getActionBar().setSelectedNavigationItem(position);

                    }
                });
            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
                viewPager.setCurrentItem(tab.getPosition());
            }
        };

        for (int i = 0; i < 3; i++) {
            actionBar.addTab(actionBar.newTab().setText(bigTitles[i]).setTabListener(tabListener));

        }


    }


    //Calculation for Bluff Fragment
    public void calculateBluff(View v){

        EditText potSizeBefBluff = (EditText) findViewById(R.id.editText_bluff_beforebluff);
        EditText bluffAmount = (EditText) findViewById(R.id.editText_bluff_bluffamont);
        EditText fe = (EditText) findViewById(R.id.editText_bluff_fe);

        //fe calculation
        if(!TextUtils.isEmpty(bluffAmount.getText().toString()) && !TextUtils.isEmpty(potSizeBefBluff.getText().toString())
                && TextUtils.isEmpty(fe.getText().toString())){

            Float numPotSizeBeBluff = Float.parseFloat(potSizeBefBluff.getText().toString());
            Float numBluffAmount = Float.parseFloat(bluffAmount.getText().toString());

            Float result = CalculBluff.feBluff(numPotSizeBeBluff, numBluffAmount);
            fe.setText(result.toString());


        }

        //pot size prebluf calculation
        if(!TextUtils.isEmpty(bluffAmount.getText().toString()) && TextUtils.isEmpty(potSizeBefBluff.getText().toString())
                && !TextUtils.isEmpty(fe.getText().toString())){

            Float numFe = Float.parseFloat(fe.getText().toString());
            Float numBluffAmount = Float.parseFloat(bluffAmount.getText().toString());

            Float result = CalculBluff.potSizeBluff(numBluffAmount, numFe);
            potSizeBefBluff.setText(result.toString());
        }

        //bluff amount calculation
        if(TextUtils.isEmpty(bluffAmount.getText().toString()) && !TextUtils.isEmpty(potSizeBefBluff.getText().toString())
                && !TextUtils.isEmpty(fe.getText().toString())){

            Float numFe = Float.parseFloat(fe.getText().toString());
            Float numPotSizeBeBluff = Float.parseFloat(potSizeBefBluff.getText().toString());

            Float result = CalculBluff.bluffAmountBluff(numPotSizeBeBluff, numFe);
            bluffAmount.setText(result.toString());
        }

    }


    //Calculation for semi Bluff
    public void calculateSemi(View v) {
        EditText potSizeBeforeSemi = (EditText) findViewById(R.id.editText_semi_size_before);
        EditText bluffAmountSemi = (EditText) findViewById(R.id.editText_semi_bluff_amount);
        EditText feSemi = (EditText) findViewById(R.id.editText_semi_fe);
        EditText eqIfCallSemi = (EditText) findViewById(R.id.editText_semi_equity_if_call);
        EditText evSemi = (EditText) findViewById(R.id.editText_semi_ev);

        //Pot size before bluff calculation
        if (TextUtils.isEmpty(potSizeBeforeSemi.getText().toString()) && !TextUtils.isEmpty(bluffAmountSemi.getText().toString())
        && !TextUtils.isEmpty(feSemi.getText().toString()) && !TextUtils.isEmpty(eqIfCallSemi.getText().toString())
        && !TextUtils.isEmpty(evSemi.getText().toString())){

            Float numBluffAmount = Float.parseFloat(bluffAmountSemi.getText().toString());
            Float numFeSemi = Float.parseFloat(feSemi.getText().toString());
            Float numEqIfCall = Float.parseFloat(eqIfCallSemi.getText().toString());
            Float numEv = Float.parseFloat(evSemi.getText().toString());

            Float result = CalculBluff.potSizeBeforeSemi(numBluffAmount, numFeSemi, numEqIfCall, numEv);
            potSizeBeforeSemi.setText(result.toString());
        }

        //Bluff Amount calculation
        if (!TextUtils.isEmpty(potSizeBeforeSemi.getText().toString()) && TextUtils.isEmpty(bluffAmountSemi.getText().toString())
        && !TextUtils.isEmpty(feSemi.getText().toString()) && !TextUtils.isEmpty(eqIfCallSemi.getText().toString())
        && !TextUtils.isEmpty(evSemi.getText().toString())){

            Float numPotSizeBeforeSemi = Float.parseFloat(potSizeBeforeSemi.getText().toString());
            Float numFeSemi = Float.parseFloat(feSemi.getText().toString());
            Float numEqIfCall = Float.parseFloat(eqIfCallSemi.getText().toString());
            Float numEv = Float.parseFloat(evSemi.getText().toString());

            Float result = CalculBluff.bluffAmountSemi(numPotSizeBeforeSemi, numFeSemi, numEqIfCall, numEv);
            bluffAmountSemi.setText(result.toString());
        }

        //Fold equity calculation
        if (!TextUtils.isEmpty(potSizeBeforeSemi.getText().toString()) && !TextUtils.isEmpty(bluffAmountSemi.getText().toString())
        && TextUtils.isEmpty(feSemi.getText().toString()) && !TextUtils.isEmpty(eqIfCallSemi.getText().toString())
        && !TextUtils.isEmpty(evSemi.getText().toString())){

            Float numPotSizeBeforeSemi = Float.parseFloat(potSizeBeforeSemi.getText().toString());
            Float numBluffAmount = Float.parseFloat(bluffAmountSemi.getText().toString());
            Float numEqIfCall = Float.parseFloat(eqIfCallSemi.getText().toString());
            Float numEv = Float.parseFloat(evSemi.getText().toString());

            Float result = CalculBluff.feSemi(numPotSizeBeforeSemi, numBluffAmount, numEqIfCall, numEv);
            feSemi.setText(result.toString());

        }

        //Equity if villain call calculation
        if (!TextUtils.isEmpty(potSizeBeforeSemi.getText().toString()) && !TextUtils.isEmpty(bluffAmountSemi.getText().toString())
        && !TextUtils.isEmpty(feSemi.getText().toString()) && TextUtils.isEmpty(eqIfCallSemi.getText().toString())
        && !TextUtils.isEmpty(evSemi.getText().toString())){

            Float numPotSizeBeforeSemi = Float.parseFloat(potSizeBeforeSemi.getText().toString());
            Float numBluffAmount = Float.parseFloat(bluffAmountSemi.getText().toString());
            Float numFe = Float.parseFloat(feSemi.getText().toString());
            Float numEv = Float.parseFloat(evSemi.getText().toString());

            Float result = CalculBluff.eqIfCallSemi(numPotSizeBeforeSemi, numBluffAmount, numFe, numEv);
            eqIfCallSemi.setText(result.toString());
        }

        //Ev calculation
        if (!TextUtils.isEmpty(potSizeBeforeSemi.getText().toString()) && !TextUtils.isEmpty(bluffAmountSemi.getText().toString())
        && !TextUtils.isEmpty(feSemi.getText().toString()) && !TextUtils.isEmpty(eqIfCallSemi.getText().toString())
        && TextUtils.isEmpty(evSemi.getText().toString())){

            Float numPotSizeBeforeSemi = Float.parseFloat(potSizeBeforeSemi.getText().toString());
            Float numBluffAmount = Float.parseFloat(bluffAmountSemi.getText().toString());
            Float numFe = Float.parseFloat(feSemi.getText().toString());
            Float numEqIfCall = Float.parseFloat(eqIfCallSemi.getText().toString());

            Float result = CalculBluff.evSemi(numPotSizeBeforeSemi, numBluffAmount, numFe, numEqIfCall);
            evSemi.setText(result.toString());
            CustomCalc.setBack(evSemi);
        }
    }

    public void calculateCall(View v){
        EditText amountToCallCall = (EditText) findViewById(R.id.editText_call_amount);
        EditText totPotAfterCallCall = (EditText) findViewById(R.id.editText_call_total_pot_after);
        EditText eqCall = (EditText) findViewById(R.id.editText_call_equity);
        EditText potOddsCall = (EditText) findViewById(R.id.editText_call_pot_odds);
        EditText evCall = (EditText) findViewById(R.id.editText_call_ev);

        //Amount to call Calculation
        if (TextUtils.isEmpty(amountToCallCall.getText().toString()) && !TextUtils.isEmpty(totPotAfterCallCall.getText().toString())
                && !TextUtils.isEmpty(eqCall.getText().toString()) && !TextUtils.isEmpty(potOddsCall.getText().toString())
                && !TextUtils.isEmpty(evCall.getText().toString())){

            Float numTotPotAfterCallCall = Float.parseFloat(totPotAfterCallCall.getText().toString());
            Float numEqCall = Float.parseFloat(eqCall.getText().toString());
            Float numEvCall = Float.parseFloat(evCall.getText().toString());
            Float numPotOddsCall = Float.parseFloat(potOddsCall.getText().toString());

            Float result = CalculBluff.amountToCall(numTotPotAfterCallCall, numEqCall, numPotOddsCall, numEvCall);
            amountToCallCall.setText(result.toString());
        }



        //Total pot after call calculation
        if (!TextUtils.isEmpty(amountToCallCall.getText().toString()) && TextUtils.isEmpty(totPotAfterCallCall.getText().toString())
                && !TextUtils.isEmpty(eqCall.getText().toString()) && !TextUtils.isEmpty(potOddsCall.getText().toString())
                && !TextUtils.isEmpty(evCall.getText().toString())){

            Float numAmountToCallCall = Float.parseFloat(amountToCallCall.getText().toString());
            Float numEqCall = Float.parseFloat(eqCall.getText().toString());
            Float numEvCall = Float.parseFloat(evCall.getText().toString());
            Float numPotOddsCall = Float.parseFloat(potOddsCall.getText().toString());

            Float result = CalculBluff.totPotAfterCall(numAmountToCallCall, numEqCall, numPotOddsCall, numEvCall);
            totPotAfterCallCall.setText(result.toString());
        }


        //Equity calculation
        if (!TextUtils.isEmpty(amountToCallCall.getText().toString()) && !TextUtils.isEmpty(totPotAfterCallCall.getText().toString())
                && TextUtils.isEmpty(eqCall.getText().toString()) && !TextUtils.isEmpty(potOddsCall.getText().toString())
                && !TextUtils.isEmpty(evCall.getText().toString())){

            Float numAmountToCallCall = Float.parseFloat(amountToCallCall.getText().toString());
            Float numTotPotAfterCallCall = Float.parseFloat(totPotAfterCallCall.getText().toString());
            Float numEvCall = Float.parseFloat(evCall.getText().toString());
            Float numPotOddsCall = Float.parseFloat(potOddsCall.getText().toString());

            Float result = CalculBluff.equityCall(numAmountToCallCall, numTotPotAfterCallCall, numPotOddsCall, numEvCall);
            eqCall.setText(result.toString());
        }




        //Pot odds calculation
        if (!TextUtils.isEmpty(amountToCallCall.getText().toString()) && !TextUtils.isEmpty(totPotAfterCallCall.getText().toString())){

            Float numAmountToCallCall = Float.parseFloat(amountToCallCall.getText().toString());
            Float numTotPotAfterCallCall = Float.parseFloat(totPotAfterCallCall.getText().toString());

            Float result = CalculBluff.potOdds(numAmountToCallCall, numTotPotAfterCallCall);
            potOddsCall.setText(result.toString());
        }

        //EV calculation
        if (!TextUtils.isEmpty(amountToCallCall.getText().toString()) && !TextUtils.isEmpty(totPotAfterCallCall.getText().toString())
                && !TextUtils.isEmpty(eqCall.getText().toString()) && !TextUtils.isEmpty(potOddsCall.getText().toString())
                && TextUtils.isEmpty(evCall.getText().toString())){
            Float numAmountToCallCall = Float.parseFloat(amountToCallCall.getText().toString());
            Float numTotPotAfterCallCall = Float.parseFloat(totPotAfterCallCall.getText().toString());
            Float numEqCall = Float.parseFloat(eqCall.getText().toString());
            Float numPotOddsCall = Float.parseFloat(potOddsCall.getText().toString());

            Float result = CalculBluff.evCall(numAmountToCallCall, numTotPotAfterCallCall, numEqCall, numPotOddsCall);
            evCall.setText(result.toString());
        }
    }

    public void deleteBluff (View v){
        EditText potSizeBefBluff = (EditText) findViewById(R.id.editText_bluff_beforebluff);
        EditText bluffAmount = (EditText) findViewById(R.id.editText_bluff_bluffamont);
        EditText fe = (EditText) findViewById(R.id.editText_bluff_fe);

        potSizeBefBluff.setText("");
        bluffAmount.setText("");
        fe.setText("");
    }

    public void deleteSemi (View v){
        EditText potSizeBeforeSemi = (EditText) findViewById(R.id.editText_semi_size_before);
        EditText bluffAmountSemi = (EditText) findViewById(R.id.editText_semi_bluff_amount);
        EditText feSemi = (EditText) findViewById(R.id.editText_semi_fe);
        EditText eqIfCallSemi = (EditText) findViewById(R.id.editText_semi_equity_if_call);
        EditText evSemi = (EditText) findViewById(R.id.editText_semi_ev);

        potSizeBeforeSemi.setText("");
        bluffAmountSemi.setText("");
        feSemi.setText("");
        eqIfCallSemi.setText("");
        evSemi.setText("");
        evSemi.setBackgroundColor(Color.WHITE);
    }

    public void deleteCall (View v){
        EditText amountToCallCall = (EditText) findViewById(R.id.editText_call_amount);
        EditText totPotAfterCallCall = (EditText) findViewById(R.id.editText_call_total_pot_after);
        EditText eqCall = (EditText) findViewById(R.id.editText_call_equity);
        EditText potOddsCall = (EditText) findViewById(R.id.editText_call_pot_odds);
        EditText evCall = (EditText) findViewById(R.id.editText_call_ev);

        amountToCallCall.setText("");
        totPotAfterCallCall.setText("");
        eqCall.setText("");
        potOddsCall.setText("");
        evCall.setText("");
    }
}