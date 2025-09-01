package mod.emt.thaumictweaker.compat.crafttweaker;

import crafttweaker.mc1120.events.ScriptRunEvent;
import mod.emt.thaumictweaker.util.helpers.PechHelper;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CrafttweakerTT {
    @SubscribeEvent
    public void onScriptReloading(ScriptRunEvent.Pre event) {

    }

    @SubscribeEvent
    public void onScriptReloadingPost(ScriptRunEvent.Post event) {
        PechHelper.verifyTrades();
    }
}
