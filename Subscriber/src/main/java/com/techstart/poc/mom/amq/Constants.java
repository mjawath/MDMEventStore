package com.techstart.poc.mom.amq;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Constants {
    public static String subid;
    public static String DurableSubscriptionName;

    public static void setArgs(String [] args){
        try {
            subid = args.length>0? args[0]:"DummyClient";
            DurableSubscriptionName=args.length>1? args[1]:"DummySub";

        }catch (Exception e){
            e.printStackTrace();
            Logger.getGlobal().log(Level.SEVERE,
                    "parameter index mismatch",e);
        }
    }
}
