package mod.emt.thaumictweaker.commands.subcommands;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchCategory;
import thaumcraft.common.lib.research.ResearchManager;

import java.util.Collections;
import java.util.List;

public class SubCommandReload implements ISubCommand {
    @Override
    public String getSubCommand() {
        return "reload";
    }

    @Override
    public int getMinCommandLength() {
        return 1;
    }

    @Override
    public int getMaxCommandLength() {
        return 1;
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos targetPos) {
        return Collections.emptyList();
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        for(ResearchCategory category : ResearchCategories.researchCategories.values()) {
            category.research.clear();
        }
        ResearchManager.parseAllResearch();
        sender.sendMessage(new TextComponentTranslation("§5Success!"));
    }
}
