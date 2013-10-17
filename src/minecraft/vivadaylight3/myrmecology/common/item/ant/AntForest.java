package vivadaylight3.myrmecology.common.item.ant;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import vivadaylight3.myrmecology.api.IEntityAnt;
import vivadaylight3.myrmecology.api.ItemAnt;
import vivadaylight3.myrmecology.common.Reference;
import vivadaylight3.myrmecology.common.entity.EntityAnt;
import vivadaylight3.myrmecology.common.entity.ant.EntityAntForest;
import vivadaylight3.myrmecology.common.lib.Time;

public class AntForest extends ItemAnt {

    public AntForest(int par1) {
	super(par1);
    }

    @Override
    public String getSpeciesName() {
	// TODO Auto-generated method stub
	return "Black Ant";
    }

    @Override
    public String getSpeciesSubName() {
	return Reference.ANT_FOREST_NAME;
    }

    @Override
    public boolean isHillAnt() {
	// TODO Auto-generated method stub
	return true;
    }

    @Override
    public int getFertility() {
	// TODO Auto-generated method stub
	return 2;
    }

    @Override
    public int getLifetime() {
	// TODO Auto-generated method stub
	return Time.getTicksFromMinutes(10);
    }

    @Override
    public boolean eatsSweet() {
	// TODO Auto-generated method stub
	return true;
    }

    @Override
    public boolean eatsSavoury() {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public boolean eatsMeat() {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public boolean eatsLarvae() {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public void performBehaviour(World world, int x, int y, int z) {
	// TODO Auto-generated method stub

    }

    @Override
    public String getSpeciesBinomialName() {
	// TODO Auto-generated method stub
	return "Lasius Niger";
    }

    @Override
    public BiomeGenBase[] getAntBiomes() {

	BiomeGenBase[] biomes = new BiomeGenBase[] { BiomeGenBase.forest,
		BiomeGenBase.forestHills };

	return biomes;
    }

    @Override
    public boolean getWinged() {

	return false;

    }

    @Override
    public boolean getNocturnal() {

	return false;

    }
    
    @Override
    public IEntityAnt getNewEntity(World parWorld){
	
	IEntityAnt entity = new EntityAntForest(parWorld);
	
	return entity;
	
    }

}
