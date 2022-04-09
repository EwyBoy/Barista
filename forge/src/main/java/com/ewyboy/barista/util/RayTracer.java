package com.ewyboy.barista.util;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class RayTracer {

    public static BlockState getStateFromRaytrace(Minecraft mc) {
        if (mc.hitResult != null && mc.hitResult.getType() != HitResult.Type.BLOCK) {
            return null;
        }

        Vec3 blockVector;

        if (mc.hitResult != null) {
            blockVector = mc.hitResult.getLocation();

            double blockX = blockVector.x();
            double blockY = blockVector.y();
            double blockZ = blockVector.z();

            double playerX = 0;
            double playerY = 0;
            double playerZ = 0;

            if (mc.player != null) playerX = mc.player.getX();
            if (mc.player != null) playerY = mc.player.getY();
            if (mc.player != null) playerZ = mc.player.getZ();

            if(blockX == Math.floor(blockX) && blockX <= playerX)     {blockX--;}
            if(blockY == Math.floor(blockY) && blockY <= playerY + 1) {blockY--;}
            if(blockZ == Math.floor(blockZ) && blockZ <= playerZ)     {blockZ--;}

            if (mc.level != null) {
                return mc.level.getBlockState(new BlockPos(blockX, blockY, blockZ));
            }
        }

        return null;
    }
}
