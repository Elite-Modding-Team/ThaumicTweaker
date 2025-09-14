package mod.emt.thaumictweaker.util.libs;

import mod.emt.thaumictweaker.ThaumicTweaker;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;

public class LootTablesTT {
    //Entities that don't get loot tables
    //  Brainy Zombie - it's just a zombie with a brain drop
    //  Crimson Portal (Lesser) - better to add drops with the event
    //  Crimson Portal (Greater) - same as Crimson Portal
    //  Cultists - already have a designated loot table
    //  Giant Taintacle - requires custom drop logic for the prim pearl
    //  Mind Spider - 1 hp illusion mob
    //  Pech - already has a loot table
    //  Spellbat - not a real entity
    //  Thaumic Slime - just a slime loot table with a flux crystal
    //  Wisp - would need a custom loot function to handle crystal types

    public static final ResourceLocation EMPTY = LootTableList.EMPTY;

    //Bosses
    public static final ResourceLocation BOSS_SHARED = LootTableList.register(new ResourceLocation(ThaumicTweaker.MOD_ID, "entity/boss/boss_shared"));
    public static final ResourceLocation CRIMSON_PRAETOR = LootTableList.register(new ResourceLocation(ThaumicTweaker.MOD_ID, "entity/boss/crimson_praetor"));
    public static final ResourceLocation ELDRITCH_GOLEM = LootTableList.register(new ResourceLocation(ThaumicTweaker.MOD_ID, "entity/boss/eldritch_golem"));
    public static final ResourceLocation ELDRITCH_WARDEN = LootTableList.register(new ResourceLocation(ThaumicTweaker.MOD_ID, "entity/boss/eldritch_warden"));

    //Mobs
    public static final ResourceLocation ELDRITCH_CRAB = LootTableList.register(new ResourceLocation(ThaumicTweaker.MOD_ID, "entity/eldritch_crab"));
    public static final ResourceLocation ELDRITCH_GUARDIAN = LootTableList.register(new ResourceLocation(ThaumicTweaker.MOD_ID, "entity/eldritch_guardian"));
    public static final ResourceLocation FIREBAT = LootTableList.register(new ResourceLocation(ThaumicTweaker.MOD_ID, "entity/firebat"));
    public static final ResourceLocation FURIOUS_ZOMBIE = LootTableList.register(new ResourceLocation(ThaumicTweaker.MOD_ID, "entity/furious_zombie"));
    public static final ResourceLocation MIND_SPIDER = LootTableList.register(new ResourceLocation(ThaumicTweaker.MOD_ID, "entity/mind_spider"));
    public static final ResourceLocation SHAMBLING_HUSK = LootTableList.register(new ResourceLocation(ThaumicTweaker.MOD_ID, "entity/shambling_husk"));
    public static final ResourceLocation TAINT_CRAWLER = LootTableList.register(new ResourceLocation(ThaumicTweaker.MOD_ID, "entity/taint_crawler"));
    public static final ResourceLocation TAINT_SEED = LootTableList.register(new ResourceLocation(ThaumicTweaker.MOD_ID, "entity/taint_seed"));
    public static final ResourceLocation TAINT_SEED_GIANT = LootTableList.register(new ResourceLocation(ThaumicTweaker.MOD_ID, "entity/taint_seed_giant"));
    public static final ResourceLocation TAINT_SWARM = LootTableList.register(new ResourceLocation(ThaumicTweaker.MOD_ID, "entity/taint_swarm"));
    public static final ResourceLocation TAINTACLE = LootTableList.register(new ResourceLocation(ThaumicTweaker.MOD_ID, "entity/taintacle"));
    public static final ResourceLocation TAINTACLE_SMALL = LootTableList.register(new ResourceLocation(ThaumicTweaker.MOD_ID, "entity/taintacle_small"));

}
