package vivadaylight3.myrmecology.common.block.hills;

import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import vivadaylight3.myrmecology.common.block.BlockAntHill;
import vivadaylight3.myrmecology.common.item.ItemAnt;
import vivadaylight3.myrmecology.common.lib.Register;

public class AntHillForest extends BlockAntHill{

    public AntHillForest(int par1) {
	super(par1, Material.ground);
    }
    
    @Override
    public ItemAnt getAnt() {
	return Register.antForest;
    }
    
    @Override
    public String getHillName() {
	// TODO Auto-generated method stub
	return "Forest Ant Hill";
    }
    
    @Override
    public String getHillSubName() {
	return "antHillForest";
    }
    
    @Override
    public int getDropQuantity(World world, int x, int y, int z) {
	// TODO Auto-generated method stub
	return 2;
    }
    
    @Override
    public BiomeGenBase[] getHillBiomes() {
	
	BiomeGenBase[] biomes = new BiomeGenBase[1];
	
	biomes[0] = BiomeGenBase.forest;
	
	return biomes;
	
    }
    
    @Override
    public boolean usesNativeGeneration() {
	// TODO Auto-generated method stub
	return true;
    }
    
    @Override
    public int[] getRequiredTouchingBlocks() {
	return null;
    }
    
}
