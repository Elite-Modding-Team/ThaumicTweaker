package mod.emt.thaumictweaker.compat.groovyscript;

import com.cleanroommc.groovyscript.documentation.linkgenerator.BasicLinkGenerator;
import mod.emt.thaumictweaker.ThaumicTweaker;

public class GSLinkGenerator extends BasicLinkGenerator {
    @Override
    public String id() {
        return ThaumicTweaker.MOD_ID;
    }

    @Override
    protected String domain() {
        return "https://github.com/Elite-Modding-Team/CongregaMystica/";
    }

    @Override
    protected String version() {
        return ThaumicTweaker.VERSION;
    }
}
