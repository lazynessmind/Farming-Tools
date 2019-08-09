package com.lazynessmind.farmingtools.network.packet;

import com.lazynessmind.farmingtools.init.blocks.BlockPlanter;
import com.lazynessmind.farmingtools.init.blocks.FTBlock;
import com.lazynessmind.farmingtools.init.tileentities.FTBlockTileEntity;
import com.lazynessmind.farmingtools.init.tileentities.FTTileEntity;
import com.lazynessmind.farmingtools.init.tileentities.TileEntityPlanter;
import com.lazynessmind.farmingtools.interfaces.IRange;
import com.lazynessmind.farmingtools.interfaces.IRedPower;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageRedstonePower implements IMessage {

    public boolean state;
    private String tileEntityId;
    private int x;
    private int y;
    private int z;

    public MessageRedstonePower() {
    }

    public MessageRedstonePower(String tileEntity, boolean state, int x, int y, int z) {
        this.state = state;
        this.tileEntityId = tileEntity;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.state = buf.readBoolean();
        this.tileEntityId = ByteBufUtils.readUTF8String(buf);
        this.x = buf.readInt();
        this.y = buf.readInt();
        this.z = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(this.state);
        ByteBufUtils.writeUTF8String(buf, tileEntityId);
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
    }

    //I dont know how this is working ... looooooool (09-08-2019)
    public static class Handler implements IMessageHandler<MessageRedstonePower, IMessage> {

        @Override
        public IMessage onMessage(MessageRedstonePower message, MessageContext ctx) {
            EntityPlayerMP player = ctx.getServerHandler().player;
            player.getServer().addScheduledTask(new Runnable() {
                @Override
                public void run() {
                    BlockPos pos = new BlockPos(message.x, message.y, message.z);
                    World world = player.world;
                    if(world.getBlockState(pos).getBlock() == FTBlock.getBlockFromName(message.tileEntityId)) {
                        Block temp = FTBlock.getBlockFromName(message.tileEntityId);
                        if (temp instanceof FTBlockTileEntity) {
                            TileEntity blockTileEntity = ((FTBlockTileEntity) temp).getTileEntity(world, pos);
                            if(blockTileEntity instanceof IRedPower){
                                ((IRedPower) blockTileEntity).setNeedRedstonePower(message.state);
                                ((FTBlockTileEntity) temp).scheduleUpdate(world, pos);
                            }

                        }
                    }
                }
            });
            return null;
        }
    }
}
