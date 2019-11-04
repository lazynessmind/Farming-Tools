package com.lazynessmind.farmingtools.client.specialrenderer;

import com.lazynessmind.farmingtools.block.tileentities.TileEntityPlanter;

public class PlanterSpecialRenderer extends PedestalSpecialRenderer<TileEntityPlanter> {

    @Override
    public void render(TileEntityPlanter te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        renderItemOnTop(te, x, y, z);
        renderRangeArea(te, x, y, z);
    }
}
