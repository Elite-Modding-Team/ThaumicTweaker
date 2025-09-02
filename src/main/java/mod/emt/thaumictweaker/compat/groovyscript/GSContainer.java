package mod.emt.thaumictweaker.compat.groovyscript;

import com.cleanroommc.groovyscript.compat.mods.GroovyPropertyContainer;
import mod.emt.thaumictweaker.compat.groovyscript.handlers.PechTrades;
import mod.emt.thaumictweaker.compat.groovyscript.handlers.SpecialMiningResult;

public class GSContainer extends GroovyPropertyContainer {
    public static final PechTrades PechTrades = new PechTrades();
    public static final SpecialMiningResult SpecialMiningResult = new SpecialMiningResult();

    public GSContainer() {
        this.addProperty(PechTrades);
        this.addProperty(SpecialMiningResult);
    }
}
