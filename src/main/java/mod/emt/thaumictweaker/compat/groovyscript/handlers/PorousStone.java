package mod.emt.thaumictweaker.compat.groovyscript.handlers;

import com.cleanroommc.groovyscript.api.GroovyLog;
import com.cleanroommc.groovyscript.api.IIngredient;
import com.cleanroommc.groovyscript.api.IScriptReloadable;
import com.cleanroommc.groovyscript.api.documentation.annotations.Example;
import com.cleanroommc.groovyscript.api.documentation.annotations.MethodDescription;
import com.cleanroommc.groovyscript.api.documentation.annotations.RegistryDescription;
import com.cleanroommc.groovyscript.helper.SimpleObjectStream;
import com.cleanroommc.groovyscript.registry.NamedRegistry;
import mod.emt.thaumictweaker.ThaumicTweaker;
import mod.emt.thaumictweaker.util.helpers.PorousStoneHelper;
import net.minecraft.item.ItemStack;
import thaumcraft.api.internal.WeightedRandomLoot;

@SuppressWarnings("unused")
@RegistryDescription(linkGenerator = ThaumicTweaker.MOD_ID)
public class PorousStone extends NamedRegistry implements IScriptReloadable {
    @Override
    public void onReload() {
        PorousStoneHelper.initDrops();
    }

    @Override
    public void afterScriptLoad() {}

    @MethodDescription(
            type = MethodDescription.Type.ADDITION,
            example = @Example("item('minecraft:clay'), 5")
    )
    public void addDrop(ItemStack stack, int weight) {
        GroovyLog.Msg msg = GroovyLog.msg("Failed to add Porous Stone drop.");
        msg.add(weight <= 0, "Drop weight must be greater than 0");
        if(msg.postIfNotEmpty()) {
            return;
        }
        PorousStoneHelper.addDrop(stack, weight);
    }

    @MethodDescription(
            type = MethodDescription.Type.REMOVAL,
            example = {
                    @Example("item('thaumcraft:crystal_essence')"),
                    @Example("item('thaumcraft:cluster:*')")
            }
    )
    public void removeDrop(IIngredient ingredient) {
        PorousStoneHelper.removeDrop(ingredient.toMcIngredient());
    }

    @MethodDescription(type = MethodDescription.Type.REMOVAL)
    public void removeAll() {
        PorousStoneHelper.removeAllDrops();
    }

    @MethodDescription(type = MethodDescription.Type.QUERY)
    public SimpleObjectStream<WeightedRandomLoot> streamDrops() {
        return new SimpleObjectStream<>(PorousStoneHelper.porousStoneDrops);
    }
}
