package mod.emt.thaumictweaker.util.libs;

import mod.emt.thaumictweaker.util.helpers.ModHelper;

import javax.annotation.Nullable;

public enum ModIds {
    crafttweaker(ConstIds.crafttweaker),
    thaumcraft_fix(ConstIds.thaumcraft_fix),
    thaumic_augmentation(ConstIds.thaumic_augmentation),
    thaumic_wonders(ConstIds.thaumic_wonders, ConstVersions.thaumic_wonders, true, false)
    ;

    public final String modId;
    public final String version;
    public final boolean isLoaded;

    ModIds(String modId, @Nullable String version, boolean isMinVersion, boolean isMaxVersion) {
        this.modId = modId;
        this.version = version;
        this.isLoaded = ModHelper.isModLoaded(modId, version, isMinVersion, isMaxVersion);
    }

    ModIds(String modId, @Nullable String version) {
        this.modId = modId;
        this.version = version;
        this.isLoaded = ModHelper.isModLoaded(modId, version);
    }

    ModIds(String modId) {
        this(modId, null);
    }

    @Override
    public String toString() {
        return this.modId;
    }

    public static class ConstIds {
        public static final String crafttweaker = "crafttweaker";
        public static final String thaumcraft = "thaumcraft";
        public static final String thaumcraft_fix = "thaumcraftfix";
        public static final String thaumic_augmentation = "thaumicaugmentation";
        public static final String thaumic_wonders = "thaumicwonders";
    }

    public static class ConstVersions {
        public static final String thaumic_wonders = "2.0.0";
    }
}
