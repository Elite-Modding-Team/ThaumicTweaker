package mod.emt.thaumictweaker.compat.groovyscript;

import com.cleanroommc.groovyscript.compat.mods.GroovyPropertyContainer;
import mod.emt.thaumictweaker.compat.groovyscript.handlers.PechTrades;

public class GSContainer extends GroovyPropertyContainer {
    public static final PechTrades PechTrades = new PechTrades();

    public GSContainer() {
        this.addProperty(PechTrades);
    }
}
