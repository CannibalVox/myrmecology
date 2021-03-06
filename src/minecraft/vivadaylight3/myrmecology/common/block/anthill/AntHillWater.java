package vivadaylight3.myrmecology.common.block.anthill;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import vivadaylight3.myrmecology.api.block.BlockAntHill;
import vivadaylight3.myrmecology.api.item.ItemAnt;
import vivadaylight3.myrmecology.common.Reference;
import vivadaylight3.myrmecology.common.Register;
import vivadaylight3.myrmecology.common.lib.Environment;
import vivadaylight3.myrmecology.common.lib.Maths;

public class AntHillWater extends BlockAntHill {

    public AntHillWater(int par1, Material material) {
	super(par1, material);
	// TODO Auto-generated constructor stub
    }

    @Override
    public ItemAnt getAnt() {
	return Register.antWater;
    }

    @Override
    public String getHillName() {
	// TODO Auto-generated method stub
	return "Water Ant Hill";
    }

    @Override
    public String getHillSubName() {
	return Reference.HILL_WATER_NAME;
    }

    @Override
    public int getDropQuantity() {
	// TODO Auto-generated method stub
	return 2;
    }

    @Override
    public BiomeGenBase[] getHillBiomes() {

	BiomeGenBase[] biomes = new BiomeGenBase[] { BiomeGenBase.ocean,
		BiomeGenBase.river };

	return biomes;

    }

    @Override
    public boolean usesNativeGeneration() {
	// TODO Auto-generated method stub
	return true;
    }

    @Override
    public int getGenerationHeightOffset(World world, int x, int currentHeight,
	    int z) {

	int radius = 1;

	int[] blocks = new int[radius];

	blocks = Environment.getBlocksFrom("y", radius, world, x,
		currentHeight, z);

	for (int k = 0; k < blocks.length; k++) {

	    if (k != blocks.length - 1) {

		int blockUnder = world.getBlockId(x, currentHeight - k - 1, z);

		if (blockUnder == Block.dirt.blockID
			|| blockUnder == Block.sand.blockID
			|| blockUnder == Block.blockClay.blockID) {

		    return k;

		}

	    }

	}

	return 0;

    }

}
