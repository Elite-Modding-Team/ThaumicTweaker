package mod.emt.thaumictweaker.commands.subcommands;

import com.google.common.collect.Lists;
import mod.emt.thaumictweaker.commands.CommandBaseTT.CommandOperation;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import thaumcraft.api.capabilities.IPlayerWarp.EnumWarpType;
import thaumcraft.api.capabilities.ThaumcraftCapabilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SubCommandWarp implements ISubCommand {
    @Override
    public @NotNull String getSubCommand() {
        return "warp";
    }

    @Override
    public int getMinCommandLength() {
        return 4;
    }

    @Override
    public int getMaxCommandLength() {
        return 5;
    }

    @Override
    public @NotNull List<String> getTabCompletions(@NotNull MinecraftServer server, @NotNull ICommandSender sender, String @NotNull [] args, @Nullable BlockPos targetPos) {
        if(args.length == 1) {
            return Collections.singletonList("warp");
        } else if(args.length == 2) {
            return CommandBase.getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames());
        } else if(args.length == 3) {
            return Lists.newArrayList("add", "remove", "set");
        } else if(args.length == 4) {
            return Collections.singletonList("0");
        } else if(args.length == 5) {
            return Lists.newArrayList("PERM", "TEMP", "NORMAL", "ALL");
        }
        return Collections.emptyList();
    }

    @Override
    public void execute(@NotNull MinecraftServer server, @NotNull ICommandSender sender, String @NotNull [] args) throws CommandException {
        if(args.length < 4 || args.length > 5) {
            throw new CommandException(new TextComponentTranslation("command.thaumictweaker:warp.invalid").getFormattedText());
        }

        EntityPlayer player = CommandBase.getPlayer(server, sender, args[1]);
        CommandOperation op = CommandOperation.getOperation(this.getSubCommand(), args[2]);
        int amount = CommandBase.parseInt(args[3]);
        List<EnumWarpType> warpTypes = new ArrayList<>();
        if(args.length == 5) {
            String arg = args[4];
            if(arg.equalsIgnoreCase("PERM")) {
                warpTypes.add(EnumWarpType.PERMANENT);
            } else if(arg.equalsIgnoreCase("TEMP")) {
                warpTypes.add(EnumWarpType.TEMPORARY);
            } else if(arg.equalsIgnoreCase("NORMAL")) {
                warpTypes.add(EnumWarpType.NORMAL);
            } else if(arg.equalsIgnoreCase("ALL")) {
                warpTypes.addAll(Arrays.asList(EnumWarpType.values()));
            } else {
                throw new CommandException(new TextComponentTranslation("command.thaumictweaker:warp.invalid").getFormattedText());
            }
        } else {
            warpTypes.add(EnumWarpType.NORMAL);
        }

        for(EnumWarpType type : warpTypes) {
            if (op == CommandOperation.ADD) {
                ThaumcraftCapabilities.getWarp(player).add(type, amount);
                player.sendMessage(new TextComponentTranslation("§5" + sender.getName() + " set your warp to " + ThaumcraftCapabilities.getWarp(player).get(type)));
            } else if (op == CommandOperation.REMOVE) {
                int curr = ThaumcraftCapabilities.getWarp(player).get(type);
                ThaumcraftCapabilities.getWarp(player).set(type, Math.max(0, curr - amount));
                player.sendMessage(new TextComponentTranslation("§5" + sender.getName() + " set your warp to " + ThaumcraftCapabilities.getWarp(player).get(type)));
            } else if (op == CommandOperation.SET) {
                ThaumcraftCapabilities.getWarp(player).set(type, Math.max(0, amount));
                player.sendMessage(new TextComponentTranslation("§5" + sender.getName() + " set your warp to " + ThaumcraftCapabilities.getWarp(player).get(type)));
            }
        }
    }

    @Override
    public boolean isUsernameIndex(String @NotNull [] args, int index) {
        return index == 1;
    }
}
