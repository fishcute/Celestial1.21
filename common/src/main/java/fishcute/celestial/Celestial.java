package fishcute.celestial;

import fishcute.celestialmain.util.Util;

public class Celestial {
    public static final String MOD_ID = "celestial";

    public static void init() {
        Util.log("Initializing Celestial");
        VInstances.setInstances();
    }
}