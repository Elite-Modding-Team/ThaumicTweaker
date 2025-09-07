package mod.emt.thaumictweaker.mixins.tiles;

import mod.emt.thaumictweaker.config.ConfigTweaksTT;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import thaumcraft.common.tiles.crafting.TileResearchTable;

@Mixin(value = TileResearchTable.class, remap = false)
public class TileResearchTableMixin {
    @ModifyConstant(method = "checkSurroundingAids", constant = @Constant(intValue = -1))
    private int lowerVerticalBoundsMixin(int constant) {
        return -ConfigTweaksTT.research_table.aidSearchVertical;
    }

    @ModifyConstant(method = "checkSurroundingAids", constant = @Constant(intValue = 1))
    private int upperVerticalBoundsMixin(int constant) {
        return ConfigTweaksTT.research_table.aidSearchVertical;
    }

    @ModifyConstant(method = "checkSurroundingAids", constant = @Constant(intValue = -4))
    private int lowerHorizontalBoundsMixin(int constant) {
        return -ConfigTweaksTT.research_table.aidSearchHorizontal;
    }

    @ModifyConstant(method = "checkSurroundingAids", constant = @Constant(intValue = 4))
    private int upperHorizontalBoundsMixin(int constant) {
        return ConfigTweaksTT.research_table.aidSearchHorizontal;
    }

}
