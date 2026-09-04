package uwu.llkc.cnc.common.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TallFlowerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.phys.BlockHitResult;
import uwu.llkc.cnc.common.init.EntityTypeRegistry;
import uwu.llkc.cnc.common.init.ItemRegistry;
import uwu.llkc.cnc.common.config.CNCConfig;

public class SunflowerBlock extends TallFlowerBlock {
    public static final BooleanProperty HAS_SEEDS = BooleanProperty.create("has_seeds");

    public SunflowerBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(HALF, DoubleBlockHalf.LOWER).setValue(HAS_SEEDS, false));
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        DoubleBlockHalf doubleblockhalf = state.getValue(HALF);
        if (facing.getAxis() != Direction.Axis.Y
                || doubleblockhalf == DoubleBlockHalf.LOWER != (facing == Direction.UP)
                || facingState.is(this) && facingState.getValue(HALF) != doubleblockhalf) {
            if (doubleblockhalf == DoubleBlockHalf.LOWER && facing == Direction.DOWN && !state.canSurvive(level, currentPos)) {
                return Blocks.AIR.defaultBlockState();
            }

            if (updateSeeds(state, facing, facingState, doubleblockhalf))
                return super.updateShape(state, facing, facingState, level, currentPos, facingPos)
                        .setValue(HAS_SEEDS, facingState.getValue(HAS_SEEDS));

            return super.updateShape(state, facing, facingState, level, currentPos, facingPos);
        }

        if (updateSeeds(state, facing, facingState, doubleblockhalf))
            return super.updateShape(state, facing, facingState, level, currentPos, facingPos)
                    .setValue(HAS_SEEDS, facingState.getValue(HAS_SEEDS));

        return Blocks.AIR.defaultBlockState();
    }

    private boolean updateSeeds(BlockState state, Direction facing, BlockState facingState, DoubleBlockHalf doubleblockhalf) {
        if (facingState.is(this) && doubleblockhalf != state.getValue(HALF) && facing.getAxis() != Direction.Axis.Y) {
            return facingState.getValue(HAS_SEEDS) != state.getValue(HAS_SEEDS);
        }
        return false;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(HALF).add(HAS_SEEDS);
    }

    @Override
    protected boolean isRandomlyTicking(BlockState state) {
        return !state.getValue(HAS_SEEDS);
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        super.randomTick(state, level, pos, random);
        if (level.isDay() && !state.getValue(HAS_SEEDS) && random.nextFloat() < CNCConfig.SUNFLOWER_SEED_GROWTH_CHANCE.get()) {
            level.setBlockAndUpdate(pos, state.setValue(HAS_SEEDS, true));
            if (random.nextFloat() < CNCConfig.SUNFLOWER_PLANT_SPAWN_CHANCE.get()) {
                EntityTypeRegistry.SUNFLOWER.get().spawn(level, pos, MobSpawnType.SPAWNER);
            }
        }
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (state.getValue(HAS_SEEDS)) {
            if (level.isClientSide()) {
                player.playSound(SoundEvents.ITEM_FRAME_REMOVE_ITEM);
                return InteractionResult.SUCCESS;
            }
            level.setBlockAndUpdate(pos, state.setValue(HAS_SEEDS, false));
            var item = new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ItemRegistry.SUNFLOWER_SEEDS.get(), level.getRandom().nextInt(5, 10)));
            level.addFreshEntity(item);
            return InteractionResult.SUCCESS;
        }

        return super.useWithoutItem(state, level, pos, player, hitResult);
    }
}
