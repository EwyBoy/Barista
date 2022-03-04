package com.ewyboy.barista.util;

import net.minecraft.block.BlockState;
import net.minecraft.state.properties.BlockStateProperties;

public class Statinator {

    public static void handleStates(BlockState state, StringBuilder builder, String separator) {
        if (state.hasProperty(BlockStateProperties.AGE_7))
            builder.append("Age:  ").append(state.getValue(BlockStateProperties.AGE_7)).append(" / 7").append(separator);

        if (state.hasProperty(BlockStateProperties.AGE_5))
            builder.append("Age:  ").append(state.getValue(BlockStateProperties.AGE_5)).append(" / 5").append(separator);

        if (state.hasProperty(BlockStateProperties.AGE_3))
            builder.append("Age:  ").append(state.getValue(BlockStateProperties.AGE_3)).append(" / 3").append(separator);

        if (state.hasProperty(BlockStateProperties.AGE_2))
            builder.append("Age:  ").append(state.getValue(BlockStateProperties.AGE_2)).append(" / 2").append(separator);

        if (state.hasProperty(BlockStateProperties.AGE_1))
            builder.append("Age:  ").append(state.getValue(BlockStateProperties.AGE_1)).append(" / 1").append(separator);

        if (state.hasProperty(BlockStateProperties.DELAY))
            builder.append("Delay:  ").append(state.getValue(BlockStateProperties.DELAY)).append(" / 4").append(separator);

        if (state.hasProperty(BlockStateProperties.MOISTURE))
            builder.append("Moisture:  ").append(state.getValue(BlockStateProperties.MOISTURE)).append(" / 7").append(separator);

        if (state.hasProperty(BlockStateProperties.LEVEL_CAULDRON))
            builder.append("Cauldron level:  ").append(state.getValue(BlockStateProperties.LEVEL_CAULDRON)).append(" / 3").append(separator);

        if (state.hasProperty(BlockStateProperties.LEVEL_COMPOSTER))
            builder.append("Compost level:  ").append(state.getValue(BlockStateProperties.LEVEL_COMPOSTER)).append(" / 8").append(separator);

        if (state.hasProperty(BlockStateProperties.LEVEL_HONEY))
            builder.append("Honey level:  ").append(state.getValue(BlockStateProperties.LEVEL_HONEY)).append(" / 5").append(separator);

        if (state.hasProperty(BlockStateProperties.POWER))
            builder.append("Power:  ").append(state.getValue(BlockStateProperties.POWER)).append(" / 15").append(separator);

        if (state.hasProperty(BlockStateProperties.LIT))
            builder.append("State: ").append(state.getValue(BlockStateProperties.LIT) ? "Lit" : "Unlit").append(separator);

        if (state.hasProperty(BlockStateProperties.OPEN))
            builder.append("State: ").append(state.getValue(BlockStateProperties.OPEN) ? "Open" : "Closed").append(separator);

        if (state.hasProperty(BlockStateProperties.POWERED))
            builder.append("State: ").append(state.getValue(BlockStateProperties.POWERED) ? "Active" : "Inactive").append(separator);

        if (state.hasProperty(BlockStateProperties.ENABLED))
            builder.append("State: ").append(state.getValue(BlockStateProperties.ENABLED) ? "Enabled" : "Disabled").append(separator);
    }

}
