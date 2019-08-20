package com.lazynessmind.farmingtools.container;

import com.lazynessmind.farmingtools.container.slots.SlotPlanter;
import com.lazynessmind.farmingtools.init.tileentities.TileEntityPlanter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemSeedFood;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class ContainerPlanter extends Container {

    private final TileEntityPlanter tileEntityPlanter;

    public ContainerPlanter(InventoryPlayer inventoryPlayer, TileEntityPlanter planter) {
        this.tileEntityPlanter = planter;
        IItemHandler handler = planter.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);

        this.addSlotToContainer(new SlotPlanter(handler, 0, 80, 33));

        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 9; x++) {
                this.addSlotToContainer(new Slot(inventoryPlayer, x + y * 9 + 9, 8 + x * 18, 84 + y * 18));
            }
        }
        for (int x = 0; x < 9; x++) {
            this.addSlotToContainer(new Slot(inventoryPlayer, x, 8 + x * 18, 142));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return tileEntityPlanter.isUsableByPlayer(playerIn);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = (Slot)this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index != 0)
            {
                if (itemstack1.getItem() instanceof ItemSeedFood || itemstack1.getItem() instanceof ItemSeeds)
                {
                    if (!this.mergeItemStack(itemstack1, 0, 0+1, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
                else if (index >= 1 && index < 28)
                {
                    if (!this.mergeItemStack(itemstack1, 28, 37, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
                else if (index >= 28 && index < 37 && !this.mergeItemStack(itemstack1, 1, 28, false))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.mergeItemStack(itemstack1,1, 37, false))
            {
                return ItemStack.EMPTY;
            }

            if (itemstack1.getCount() == 0)
            {
                slot.putStack((ItemStack)ItemStack.EMPTY);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount())
            {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, itemstack1);
        }
        return itemstack;
    }


}
