package mod.emt.thaumictweaker.util.helpers;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.LoaderException;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.versioning.VersionParser;
import net.minecraftforge.fml.common.versioning.VersionRange;

import javax.annotation.Nullable;

public class ModHelper {
    public static boolean isModLoaded(String modId) {
        return isModLoaded(modId, null);
    }

    public static boolean isModLoaded(String modId, @Nullable String version) {
        return isModLoaded(modId, version, true, false);
    }

    public static boolean isModLoaded(String modId, @Nullable String version, boolean isMinVersion, boolean isMaxVersion) {
        return Loader.isModLoaded(modId) && isSpecifiedVersion(modId, version, isMinVersion, isMaxVersion);
    }

    private static boolean isSpecifiedVersion(String modId, @Nullable String version, boolean isMinVersion, boolean isMaxVersion) {
        if (version == null)
            return true;

        boolean match = true;
        ModContainer container = Loader.instance().getIndexedModList().get(modId);
        if (container != null) {
            try {
                VersionRange versionRange = VersionParser.parseRange(getVersionString(version, isMinVersion, isMaxVersion));
                match = versionRange.containsVersion(container.getProcessedVersion());
            } catch (LoaderException ignored) {
            }
        }
        return match;
    }

    private static String getVersionString(String version) {
        return getVersionString(version, true, false);
    }

    private static String getVersionString(String version, boolean isMinVersion, boolean isMaxVersion) {
        String versionStr = "";
        versionStr += isMinVersion ? "[" : "(";
        versionStr += isMaxVersion ? "," : "";
        versionStr += version;
        versionStr += isMinVersion ? "," : "";
        versionStr += isMaxVersion ? "]" : ")";
        return versionStr;
    }
}
