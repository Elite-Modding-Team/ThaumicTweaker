package mod.emt.thaumictweaker.commands.subcommands;

import com.google.common.collect.Lists;
import mod.emt.thaumictweaker.commands.CommandBaseTT.CommandOperation;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import thaumcraft.api.aura.AuraHelper;

import java.util.Collections;
import java.util.List;

public class SubCommandChunkVis implements ISubCommand {
    @Override
    public @NotNull String getSubCommand() {
        return "chunkvis";
    }

    @Override
    public int getMinCommandLength() {
        return 7;
    }

    @Override
    public int getMaxCommandLength() {
        return 7;
    }

    @Override
    public @NotNull List<String> getTabCompletions(@NotNull MinecraftServer server, @NotNull ICommandSender sender, String @NotNull [] args, @Nullable BlockPos targetPos) {
        if(args.length == 2) {
            return Lists.newArrayList("add", "remove", "set");
        } else if(args.length == 3) {
            return Collections.singletonList(String.valueOf(10));
        } else if(args.length == 4) {
            return Lists.newArrayList("VIS", "FLUX");
        } else if(targetPos != null) {
            if(args.length == 5) {
                return Collections.singletonList(String.valueOf(targetPos.getX()));
            } else if(args.length == 6) {
                return Collections.singletonList(String.valueOf(targetPos.getY()));
            } else if(args.length == 7) {
                return Collections.singletonList(String.valueOf(targetPos.getZ()));
            }
        }
        return Collections.emptyList();
    }

    @Override
    public void execute(@NotNull MinecraftServer server, @NotNull ICommandSender sender, String[] args) throws CommandException {
        CommandOperation op = CommandOperation.getOperation(this.getSubCommand(), args[1]);
        double toAdd = CommandBase.parseDouble(args[2]);
        String arg = args[3];
        boolean isVis;
        if(arg.equalsIgnoreCase("VIS")) {
            isVis = true;
        } else if(arg.equalsIgnoreCase("FLUX")) {
            isVis = false;
        } else {
            throw new CommandException(new TextComponentTranslation("command.thaumictweaker:chunkvis.invalid").getFormattedText());
        }
        int xPos = CommandBase.parseInt(args[4]);
        int yPos = CommandBase.parseInt(args[5]);
        int zPos = CommandBase.parseInt(args[6]);

        World world = server.getEntityWorld();
        BlockPos pos = new BlockPos(xPos, yPos, zPos);
        if(isVis) {
            if(op == CommandOperation.ADD) {
                AuraHelper.addVis(world, pos, (float) toAdd);
            } else if(op == CommandOperation.REMOVE) {
                AuraHelper.drainVis(world, pos, (float) toAdd, false);
            } else {
                float vis = AuraHelper.getVis(world, pos);
                if(vis > toAdd) {
                    double difference = vis - toAdd;
                    AuraHelper.drainVis(world, pos, (float) difference, false);
                } else if(vis < toAdd) {
                    double difference = toAdd - vis;
                    AuraHelper.addVis(world, pos, (float) difference);
                }
            }
        } else {
            if(op == CommandOperation.ADD) {
                AuraHelper.polluteAura(world, pos, (float) toAdd, false);
            } else if(op == CommandOperation.REMOVE) {
                AuraHelper.drainFlux(world, pos, (float) toAdd, false);
            } else {
                float flux = AuraHelper.getFlux(world, pos);
                if(flux > toAdd) {
                    double difference = flux - toAdd;
                    AuraHelper.drainFlux(world, pos, (float) difference, false);
                } else if(flux < toAdd) {
                    double difference = toAdd - flux;
                    AuraHelper.polluteAura(world, pos, (float) difference, false);
                }
            }
        }
    }
}
