package mod.emt.thaumictweaker.config.generics;

import mod.emt.thaumictweaker.util.helpers.RegistryHelper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Config;

public class GolemMaterialCategory {
    @Config.RequiresMcRestart
    @Config.Name("Golem Material Override")
    @Config.Comment
            ({
                    "The golem material item override. Golem materials are normally selected from the first value registered",
                    "in the ore dictionary. This overrides that value with a specific item.",
                    "Examples:",
                    "  minecraft:iron_ingot",
                    "  minecraft:iron_ingot:0"
            })
    public String materialItem = "";

    @Config.RequiresMcRestart
    @Config.RangeInt(min = 0, max = 100)
    @Config.Name("Material Armor")
    @Config.Comment("The base golem material armor value.")
    public int statArmor;

    @Config.RequiresMcRestart
    @Config.RangeInt(min = 0, max = 100)
    @Config.Name("Material Damage")
    @Config.Comment("The base golem material damage value.")
    public int statDamage;

    @Config.RequiresMcRestart
    @Config.RangeInt(min = 0, max = 100)
    @Config.Name("Material Health")
    @Config.Comment("The base golem material health value. Actual golem health will be this value plus 10.")
    public int statHealth;

    @Config.Ignore
    public String fallbackItem;

    @Config.Ignore
    private ItemStack stackCache = ItemStack.EMPTY;

    public GolemMaterialCategory(int health, int armor, int damage, String fallbackItem) {
        this.statArmor = armor;
        this.statDamage = damage;
        this.statHealth = health;
        this.fallbackItem = fallbackItem;
    }

    public ItemStack getMaterialStack() {
        if(this.stackCache.isEmpty()) {
            this.stackCache = RegistryHelper.getConfigStack(this.materialItem);
        }
        if(this.stackCache.isEmpty()) {
            this.stackCache = RegistryHelper.getConfigStack(this.fallbackItem);
        }
        return this.stackCache;
    }
}
