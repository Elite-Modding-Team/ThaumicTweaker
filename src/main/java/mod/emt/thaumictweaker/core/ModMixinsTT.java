package mod.emt.thaumictweaker.core;

import com.google.common.collect.ImmutableMap;
import zone.rong.mixinbooter.ILateMixinLoader;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BooleanSupplier;

public class ModMixinsTT implements ILateMixinLoader {
    private static final Map<String, BooleanSupplier> mixinConfigs = ImmutableMap.copyOf(new HashMap<String, BooleanSupplier>() {
        {
            put("mixins.thaumictweaker.json", () -> true);
            put("mixins.thaumictweaker.loottables.json", () -> false);//TODO: Loot table config
        }
    });

    @Override
    public List<String> getMixinConfigs() {
        return Collections.singletonList("mixins.thaumictweaker.json");
    }

    @Override
    public boolean shouldMixinConfigQueue(String mixinConfig) {
        BooleanSupplier supplier = mixinConfigs.get(mixinConfig);
        return supplier == null || supplier.getAsBoolean();
    }
}
