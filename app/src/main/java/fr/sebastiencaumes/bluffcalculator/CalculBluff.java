package fr.sebastiencaumes.bluffcalculator;

/**
 * Created by sebastien on 15/09/15.
 */
public class CalculBluff {


    /**********************************************/
    /****************BLUFF************************/
    /*********************************************/

    //fe calculation fe=bluffamt/(bluffamt+potsize)

    public static Float feBluff (Float potSize, Float bluffAmount){
        float fe;

        fe = bluffAmount/(bluffAmount + potSize);
        fe = CustomCalc.goPercent(fe);
        fe = CustomCalc.cutDecimal(fe);

        return fe;
    }

    //bluffAmount = potSize*fe/(1-fe)

    public static Float bluffAmountBluff (Float potSize, Float fe){
        float bluffAmount;
        fe /= 100;
        bluffAmount = potSize*fe/(1-fe);
        return bluffAmount;
    }

    //potSize = bluffAmount/(fe/(1-fe))

    public static Float potSizeBluff (Float bluffAmount, Float fe){
        float potSizeBluff;
        fe /= 100;
        potSizeBluff = bluffAmount/(fe/(1-fe));
        return potSizeBluff;
    }

    /**********************************************/
    /****************SEMI BLUFF************************/
    /*********************************************/

    // pot size before bluff calculation
    public static Float potSizeBeforeSemi (Float bluffAmount, Float fe, Float eqIfCall, Float ev){
        float potSizeBeforeSemi;
        fe /= 100;
        eqIfCall /= 100;
        potSizeBeforeSemi = (ev-eqIfCall*(1-fe)*bluffAmount+(1-fe)*(1-eqIfCall)*bluffAmount)/(fe+eqIfCall*(1-fe));
        return potSizeBeforeSemi;
    }

    //Bluff amount calculation
    public static Float bluffAmountSemi (Float potSizeBefore, Float fe, Float eqIfCall, Float ev){
        float bluffAmountSemi;
        fe /= 100;
        eqIfCall /= 100;
        bluffAmountSemi = (fe*potSizeBefore+(1-fe)*eqIfCall*potSizeBefore-ev)/(-eqIfCall*(1-fe)+(1-fe)*(1-eqIfCall));
        return bluffAmountSemi;
    }

    //fe calculation
    public static Float feSemi (Float potSizeBefore, Float bluffAmount, Float eqIfCall, Float ev){
        float feSemi;
        eqIfCall /= 100;
        feSemi = (ev-eqIfCall*(potSizeBefore+bluffAmount)+(1-eqIfCall)*bluffAmount)/(potSizeBefore-eqIfCall*(potSizeBefore+bluffAmount)+(1-eqIfCall)*bluffAmount);
        feSemi = CustomCalc.goPercent(feSemi);
        feSemi = CustomCalc.cutDecimal(feSemi);
        return feSemi;
    }

    //equity if vilain call calculation
    public static Float eqIfCallSemi (Float potSizeBefore, Float bluffAmount, Float fe, Float ev){
        float eqIfCallSemi;
        fe /= 100;

        eqIfCallSemi = (((ev-(fe*potSizeBefore))/(1-fe))+bluffAmount)/(potSizeBefore+2*bluffAmount);
        eqIfCallSemi = CustomCalc.goPercent(eqIfCallSemi);
        eqIfCallSemi = CustomCalc.cutDecimal(eqIfCallSemi);
        return eqIfCallSemi;
    }

    //ev calculation
    public static Float evSemi (Float potSizeBefore, Float bluffAmount, Float fe, Float eqIfCall){
        float evSemi;
        fe /= 100;
        eqIfCall /= 100;
        evSemi = fe*potSizeBefore+(1-fe)*(eqIfCall*(potSizeBefore+bluffAmount)-(1-eqIfCall)*bluffAmount);
        evSemi = CustomCalc.goPercent(evSemi);
        evSemi = CustomCalc.cutDecimal(evSemi);
        return evSemi;
    }


    /**********************************************/
    /****************CALL************************/
    /*********************************************/

    //Amount to call calcultation
    public static Float amountToCall (Float totPotAfter, Float eq, Float potOdds, Float ev){
        float amountToCall;
        eq /= 100;
        potOdds /=100;
        amountToCall = eq*totPotAfter-ev;
        return amountToCall;
    }

    //Total pot after call calculation
    public static Float totPotAfterCall (Float amountToCall, Float eq, Float potOdds, Float ev){
        float totPotAfterCall;
        eq /= 100;
        potOdds /=100;
        totPotAfterCall= (amountToCall+ev)/eq;
        return totPotAfterCall;
    }

    //Equity calculation
    public static Float equityCall (Float amountToCall, Float totPotAfterCall, Float potOdds, Float ev){
        float equityCall;
        potOdds /=100;
        equityCall = (amountToCall+ev)/totPotAfterCall;
        return equityCall;

    }

    //Ev calculation
    public  static  Float evCall (Float amountToCall, Float totPotAfterCall, Float eq, Float potOdds){
        float evCall;
        potOdds /=100;
        eq /= 100;
        evCall = eq*totPotAfterCall-amountToCall;
        return evCall;
    }

    //pot Odds Calculation
    public static Float potOdds (Float amountToCall, Float totPotAfterCall){
        float potOdds;
        potOdds = amountToCall/totPotAfterCall;
        potOdds = CustomCalc.goPercent(potOdds);
        potOdds = CustomCalc.cutDecimal(potOdds);
        return potOdds;
    }

}
