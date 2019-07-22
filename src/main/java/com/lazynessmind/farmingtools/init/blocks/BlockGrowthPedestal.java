package com.lazynessmind.farmingtools.init.blocks;

import com.lazynessmind.farmingtools.init.tileentities.FTBlockTileEntity;
import com.lazynessmind.farmingtools.init.tileentities.TileEntityGrowthPedestal;
import com.lazynessmind.farmingtools.util.ParticleCreator;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class BlockGrowthPedestal extends FTBlockTileEntity<TileEntityGrowthPedestal> {

    protected static final AxisAlignedBB BASE_AABB = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.5625D, 0.9375D);


    public BlockGrowthPedestal(Material materialIn, String name) {
        super(materialIn, name);
        setLightLevel(7F);
        setTickRandomly(true);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(TextFormatting.RED + "Only work during day time.");
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
        TileEntityGrowthPedestal tileEntityGrowthPedestal = getTileEntity(worldIn, pos);
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return BASE_AABB;
    }

    @Override
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        ParticleCreator.spawnParticle(EnumParticleTypes.PORTAL, worldIn, pos, 10, rand);
    }

    @Override
    public TileEntityGrowthPedestal createTileEntity(World world, IBlockState state) {
        return new TileEntityGrowthPedestal();
    }

    @Override
    public Class<TileEntityGrowthPedestal> getTileEntityClass() {
        return TileEntityGrowthPedestal.class;
    }


}
