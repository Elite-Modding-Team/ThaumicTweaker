package mod.emt.thaumictweaker.compat.groovyscript.handlers;

import com.cleanroommc.groovyscript.api.GroovyLog;
import com.cleanroommc.groovyscript.api.IIngredient;
import com.cleanroommc.groovyscript.api.documentation.annotations.*;
import com.cleanroommc.groovyscript.helper.recipe.AbstractRecipeBuilder;
import com.cleanroommc.groovyscript.registry.VirtualizedRegistry;
import mod.emt.thaumictweaker.ThaumicTweaker;
import mod.emt.thaumictweaker.util.helpers.SpecialMiningHelper;
import mod.emt.thaumictweaker.util.misc.MiningResult;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;

@RegistryDescription(
        linkGenerator = ThaumicTweaker.MOD_ID,
        reloadability = RegistryDescription.Reloadability.FLAWED
)
public class SpecialMiningResult extends VirtualizedRegistry<MiningResult> {
    @Override
    public void onReload() {}

    @MethodDescription(
            type = MethodDescription.Type.ADDITION,
            example = {
                    @Example("ore('oreIron'), item('thaumcraft:cluster:0'), 1.0"),
                    @Example("ore('gemQuartz'), item('thaumcraft:cluster:7'), 1.0")
            }
    )
    public void add(IIngredient harvestDrop, ItemStack result, float chance) {
        this.recipeBuilder()
                .setHarvestDrop(harvestDrop)
                .setResult(result)
                .setChance(chance)
                .register();
    }

    @MethodDescription(
            type = MethodDescription.Type.REMOVAL,
            example = @Example("ore('oreIron')")
    )
    public void removeByHarvestDrop(IIngredient harvestDrop) {
        SpecialMiningHelper.removeSpecialMiningResultByInput(harvestDrop.toMcIngredient());
    }

    @MethodDescription(
            type = MethodDescription.Type.REMOVAL,
            example = @Example("item('thuamcraft:cluster:0')"),
            priority = 1001
    )
    public void removeByOutput(IIngredient output) {
        SpecialMiningHelper.removeSpecialMiningResultByOutput(output.toMcIngredient());
    }

    @MethodDescription(
            type = MethodDescription.Type.REMOVAL
    )
    public void removeAll() {
        SpecialMiningHelper.removeAllSpecialMiningResults();
    }

    @RecipeBuilderDescription(
            example = {
                    @Example(".setHarvested(item('minecraft:iron_ore')).setResult(item('thaumcraft:cluster:0')).setChance(1.0)")
            }
    )
    public RecipeBuilder recipeBuilder() {
        return new RecipeBuilder();
    }

    public static class RecipeBuilder extends AbstractRecipeBuilder<MiningResult> {
        @Property
        private IIngredient harvestDrop;
        @Property
        private ItemStack result;
        @Property(comp = @Comp(gte = 0, lte = 1))
        private float chance;

        public RecipeBuilder() {
            this.harvestDrop = IIngredient.EMPTY;
            this.result = ItemStack.EMPTY;
            this.chance = 1.0f;
        }

        @RecipeBuilderMethodDescription(field = "harvestDrop")
        public RecipeBuilder setHarvestDrop(IIngredient stack) {
            this.harvestDrop = stack;
            return this;
        }

        @RecipeBuilderMethodDescription(field = "result", priority = 1001)
        public RecipeBuilder setResult(ItemStack result) {
            this.result = result;
            return this;
        }

        @RecipeBuilderMethodDescription(field = "chance", priority = 1002)
        public RecipeBuilder setChance(float chance) {
            this.chance = chance;
            return this;
        }

        @Override
        public String getErrorMsg() {
            return "Failed to add special mining result";
        }

        @Override
        public void validate(GroovyLog.Msg msg) {
            msg.add(this.harvestDrop.isEmpty(), "Harvested stack cannot be empty");
            msg.add(this.result.isEmpty(), "Result stack cannot be empty");
            msg.add(this.chance < 0 || this.chance > 1.0f, "Chance must be between 0 and 1");
        }

        @RecipeBuilderRegistrationMethod
        @Override
        public @Nullable MiningResult register() {
            if(this.validate()) {
                MiningResult miningResult = new MiningResult(this.harvestDrop.toMcIngredient(), this.result, this.chance);
                SpecialMiningHelper.addSpecialMiningResult(miningResult);
                return miningResult;
            }
            return null;
        }
    }
}
