package mod.emt.thaumictweaker.commands.subcommands;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

import java.util.List;

public interface ISubCommand {
    String getSubCommand();

    int getMinCommandLength();

    int getMaxCommandLength();

    List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos targetPos);

    void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException;

    default boolean isUsernameIndex(String [] args, int index) {
        return false;
    }
}
