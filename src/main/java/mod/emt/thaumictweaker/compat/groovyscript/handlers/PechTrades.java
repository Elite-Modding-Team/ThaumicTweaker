package mod.emt.thaumictweaker.compat.groovyscript.handlers;

import com.cleanroommc.groovyscript.api.GroovyLog;
import com.cleanroommc.groovyscript.api.IIngredient;
import com.cleanroommc.groovyscript.api.documentation.annotations.*;
import com.cleanroommc.groovyscript.helper.recipe.AbstractRecipeBuilder;
import com.cleanroommc.groovyscript.registry.VirtualizedRegistry;
import mod.emt.thaumictweaker.ThaumicTweaker;
import mod.emt.thaumictweaker.util.helpers.PechHelper;
import mod.emt.thaumictweaker.util.misc.EnumPechType;
import mod.emt.thaumictweaker.util.misc.PechTrade;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("unused")
@RegistryDescription(
        linkGenerator = ThaumicTweaker.MOD_ID,
        reloadability = RegistryDescription.Reloadability.FLAWED
)
public class PechTrades extends VirtualizedRegistry<PechTrade> {

    @Override
    public void onReload() {

    }

    @Override
    public void afterScriptLoad() {
        PechHelper.verifyTrades();
    }

    @MethodDescription(
            type = MethodDescription.Type.ADDITION,
            example = {
                    @Example("'MINER', 1, item('minecraft:diamond')"),
                    @Example("'MAGE', 1, item('minecraft:diamond')"),
                    @Example("'ARCHER', 1, item('minecraft:diamond')"),
                    @Example("'COMMON', 1, item('minecraft:diamond')")
            }
    )
    public void addTrade(String pechType, int tradeLevel, ItemStack tradeItem) {
        recipeBuilder().setPechType(pechType).setTradeLevel(tradeLevel).setTradeItem(tradeItem).register();
    }

    @MethodDescription(
            type = MethodDescription.Type.REMOVAL,
            example = {
                    @Example("'MINER', item('minecraft:blaze_rod')"),
                    @Example("'MAGE', item('minecraft:experience_bottle')"),
                    @Example("'ARCHER', item('minecraft:compass')")
            }
    )
    public void removeTrade(String pechType, IIngredient ingredient) {
        PechHelper.removePechTrade(PechHelper.getPechType(pechType), ingredient.toMcIngredient());
    }

    @MethodDescription(
            type = MethodDescription.Type.REMOVAL,
            example = @Example("'MINER'"),
            priority = 1001,
            description = "groovyscript.wiki.congregamystica.pech_trades.removeAllTrades1"
    )
    public void removeAllTrades(String pechType) {
        PechHelper.removeAllPechTrades(PechHelper.getPechType(pechType));
    }

    @MethodDescription(
            type = MethodDescription.Type.REMOVAL,
            description = "groovyscript.wiki.congregamystica.pech_trades.removeAllTrades2"
    )
    public void removeAllTrades() {
        PechHelper.removeAllPechTrades();
    }

    @RecipeBuilderDescription(
            example = {
                    @Example(".setPechType('MINER').setTradeLevel(1).setTradeItem(item('minecraft:diamond'))")
            }
    )
    public RecipeBuilder recipeBuilder() {
        return new RecipeBuilder();
    }

    public static class RecipeBuilder extends AbstractRecipeBuilder<PechTrade> {
        @Property(comp = @Comp(unique = "'MINER', 'MAGE', 'ARCHER', or 'COMMON'"), defaultValue = "'MINER'")
        private String pechType;
        @Property(comp = @Comp(gte = 1, lte = 5), defaultValue = "1")
        private int tradeLevel;
        @Property
        private ItemStack tradeItem;

        public RecipeBuilder() {
            this.pechType = "MINER";
            this.tradeLevel = 1;
            this.tradeItem = ItemStack.EMPTY;
        }

        @RecipeBuilderMethodDescription(field = "pechType")
        public RecipeBuilder setPechType(String pechType) {
            this.pechType = pechType;
            return this;
        }

        @RecipeBuilderMethodDescription(field = "tradeLevel", priority = 1001)
        public RecipeBuilder setTradeLevel(int tradeLevel) {
            this.tradeLevel = tradeLevel;
            return this;
        }

        @RecipeBuilderMethodDescription(field = "tradeItem", priority = 1002)
        public RecipeBuilder setTradeItem(ItemStack tradeItem) {
            this.tradeItem = tradeItem;
            return this;
        }

        @Override
        public String getErrorMsg() {
            return "Error adding pech trade";
        }

        @Override
        public void validate(GroovyLog.Msg msg) {
            try {
                EnumPechType.valueOf(this.pechType.toUpperCase());
            } catch (IllegalArgumentException e) {
                msg.add(true, "");
            }
            msg.add(this.tradeLevel < 1 || this.tradeLevel > 5, "");
            msg.add(this.tradeItem.isEmpty(), "Trade item cannot be empty");
        }

        @RecipeBuilderRegistrationMethod
        @Override
        public @Nullable PechTrade register() {
            if(this.validate()) {
                PechTrade trade = new PechTrade(
                        this.pechType,
                        this.tradeLevel,
                        this.tradeItem
                );
                PechHelper.addPechTrade(trade);
                return trade;
            }
            return null;
        }
    }
}
