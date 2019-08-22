package com.lazynessmind.farmingtools.init.blocks;

import com.lazynessmind.farmingtools.FarmingTools;
import com.lazynessmind.farmingtools.gui.FTGuis;
import com.lazynessmind.farmingtools.init.tileentities.TileEntityGrowthPedestal;
import com.lazynessmind.farmingtools.init.tileentities.TileEntityHarvester;
import com.lazynessmind.farmingtools.util.ParticleCreator;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class BlockGrowthPedestal extends BlockPedestal<TileEntityGrowthPedestal> {

    public BlockGrowthPedestal(Material material, String name) {
        super(material, name);
        this.setDefaultState(this.blockState.getBaseState().withProperty(this.getMetaProperty(), Integer.valueOf(0)));
        setTickRandomly(true);
        setLightLevel(7f);
    }

    @Override
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        if (worldIn.getWorldInfo().getWorldTime() < 12500) {
            ParticleCreator.spawnParticle(EnumParticleTypes.PORTAL, worldIn, pos, 10, rand);
        }
    }

    @Override
    public Class<TileEntityGrowthPedestal> getTileEntityClass() {
        return TileEntityGrowthPedestal.class;
    }

    @Override
    public TileEntityGrowthPedestal createTileEntity(World world, IBlockState state) {
        return new TileEntityGrowthPedestal();
    }
}
