package mod.emt.thaumictweaker.compat.groovyscript.handlers;

import com.cleanroommc.groovyscript.api.documentation.annotations.Example;
import com.cleanroommc.groovyscript.api.documentation.annotations.MethodDescription;
import com.cleanroommc.groovyscript.api.documentation.annotations.RegistryDescription;
import com.cleanroommc.groovyscript.compat.mods.thaumcraft.aspect.AspectStack;
import com.cleanroommc.groovyscript.helper.Alias;
import com.cleanroommc.groovyscript.registry.NamedRegistry;
import mod.emt.thaumictweaker.ThaumicTweaker;
import mod.emt.thaumictweaker.util.helpers.AspectContainerHelper;
import net.minecraft.item.ItemStack;

@SuppressWarnings("unused")
@RegistryDescription(linkGenerator = ThaumicTweaker.MOD_ID)
public class TweakerUtils extends NamedRegistry {
    public TweakerUtils() {
        super(new Alias().andGenerate("Utils"));
    }

    @MethodDescription(
            type = MethodDescription.Type.VALUE,
            example = {
                    @Example("aspect('ignis')"),
                    @Example("aspect('ignis') * 100")
            }
    )
    public ItemStack getVisCrystal(AspectStack aspectStack) {
        return AspectContainerHelper.createAspectCrystal(aspectStack.getAspect(), aspectStack.getAmount());
    }

    @MethodDescription(
            type = MethodDescription.Type.VALUE,
            example = @Example("aspect('ignis')"),
            priority = 1001
    )
    public ItemStack getAspectPhial(AspectStack aspectStack) {
        return AspectContainerHelper.createAspectPhial(aspectStack.getAspect());
    }
}
