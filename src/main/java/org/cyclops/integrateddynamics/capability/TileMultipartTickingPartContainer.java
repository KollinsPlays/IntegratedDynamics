package org.cyclops.integrateddynamics.capability;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.cyclops.integrateddynamics.api.network.IPartNetwork;
import org.cyclops.integrateddynamics.api.part.IPartContainer;
import org.cyclops.integrateddynamics.block.BlockCable;
import org.cyclops.integrateddynamics.core.block.ICollidable;
import org.cyclops.integrateddynamics.core.tileentity.TileMultipartTicking;

import javax.annotation.Nullable;

/**
 * Implementation of an {@link IPartContainer} for a tile entity.
 * @author rubensworks
 */
public class TileMultipartTickingPartContainer extends DefaultPartContainer {

    private final TileMultipartTicking tile;

    public TileMultipartTickingPartContainer(TileMultipartTicking tile) {
        this.tile = tile;
    }

    protected TileMultipartTicking getTile() {
        return tile;
    }

    @Override
    protected void markDirty() {
        getTile().markDirty();
    }

    @Override
    protected void sendUpdate() {
        getTile().sendUpdate();
    }

    @Override
    protected World getWorld() {
        return getTile().getWorld();
    }

    @Override
    protected BlockPos getPos() {
        return getTile().getPos();
    }

    @Override
    protected IPartNetwork getNetwork() {
        return getTile().getNetwork();
    }

    @Nullable
    @Override
    public EnumFacing getWatchingSide(World world, BlockPos pos, EntityPlayer player) {
        ICollidable.RayTraceResult<EnumFacing> rayTraceResult = ((BlockCable) world.getBlockState(pos).getBlock()).doRayTrace(world, pos, player);
        if(rayTraceResult != null) {
            return rayTraceResult.getPositionHit();
        }
        return null;
    }
}
