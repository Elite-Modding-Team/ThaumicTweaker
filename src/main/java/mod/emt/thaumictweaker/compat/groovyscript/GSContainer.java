package mod.emt.thaumictweaker.compat.groovyscript;

import com.cleanroommc.groovyscript.compat.mods.GroovyPropertyContainer;
import mod.emt.thaumictweaker.compat.groovyscript.handlers.PechTrades;
import mod.emt.thaumictweaker.compat.groovyscript.handlers.PorousStone;
import mod.emt.thaumictweaker.compat.groovyscript.handlers.SpecialMiningResult;
import mod.emt.thaumictweaker.compat.groovyscript.handlers.TweakerUtils;

public class GSContainer extends GroovyPropertyContainer {
    public static final PechTrades PechTrades = new PechTrades();
    public static final PorousStone PorousStone = new PorousStone();
    public static final SpecialMiningResult SpecialMiningResult = new SpecialMiningResult();
    public static final TweakerUtils Utils = new TweakerUtils();

    public GSContainer() {
        this.addProperty(PechTrades);
        this.addProperty(PorousStone);
        this.addProperty(SpecialMiningResult);
        this.addProperty(Utils);
    }
}
