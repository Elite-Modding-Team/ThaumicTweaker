package mod.emt.thaumictweaker.core;

import com.google.common.collect.ImmutableMap;
import mod.emt.thaumictweaker.config.ConfigTweaksTT;
import zone.rong.mixinbooter.ILateMixinLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BooleanSupplier;

public class ModMixinsTT implements ILateMixinLoader {
    private static final Map<String, BooleanSupplier> mixinConfigs = ImmutableMap.copyOf(new HashMap<String, BooleanSupplier>() {
        {
            put("mixins.thaumictweaker.json", () -> true);
            put("mixins.thaumictweaker.loottables.json", () -> ConfigTweaksTT.misc_tweaks.lootTableOverhaul);
        }
    });

    @Override
    public List<String> getMixinConfigs() {
        return new ArrayList<>(mixinConfigs.keySet());
    }

    @Override
    public boolean shouldMixinConfigQueue(String mixinConfig) {
        BooleanSupplier supplier = mixinConfigs.get(mixinConfig);
        return supplier == null || supplier.getAsBoolean();
    }
}
