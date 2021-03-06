package vivadaylight3.myrmecology.common.block;

import static net.minecraftforge.common.ForgeDirection.DOWN;
import static net.minecraftforge.common.ForgeDirection.UP;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import vivadaylight3.myrmecology.common.Myrmecology;
import vivadaylight3.myrmecology.common.Reference;
import vivadaylight3.myrmecology.common.Register;
import vivadaylight3.myrmecology.common.lib.Resources;
import vivadaylight3.myrmecology.common.tileentity.TileEntityAntChest;

import com.google.common.collect.Lists;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockAntChest extends BlockContainer {

    private Random random;

    public BlockAntChest(int id) {
	super(id, Material.wood);
	setUnlocalizedName(Reference.BLOCK_ANTCHEST_NAME);
	setHardness(3.0F);
	setBlockBounds(0.0625F, 0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
	random = new Random();
	setCreativeTab(Register.tabMyrmecology);
    }

    @Override
    public TileEntity createNewTileEntity(World w) {
	return null;
    }

    @Override
    public boolean isOpaqueCube() {
	return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
	return false;
    }

    @Override
    public int getRenderType() {
	return -1;
    }

    @Override
    public TileEntity createTileEntity(World world, int metadata) {
	return new TileEntityAntChest();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public Icon getIcon(int i, int j) {
	return blockIcon;
    }

    @Override
    public ArrayList<ItemStack> getBlockDropped(World world, int x, int y,
	    int z, int metadata, int fortune) {
	ArrayList<ItemStack> items = Lists.newArrayList();
	ItemStack stack = new ItemStack(this, 1, metadata);
	items.add(stack);
	return items;
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z,
	    EntityPlayer parPlayer, int par6, float par7, float par8, float par9) {

	if (!parPlayer.isSneaking() && !world.isRemote) {

	    parPlayer.openGui(Myrmecology.instance, Register.GUI_ID_ANTCHEST,
		    world, x, y, z);

	    return true;

	}

	return false;

    }

    @Override
    public void onBlockAdded(World world, int i, int j, int k) {
	super.onBlockAdded(world, i, j, k);
	world.markBlockForUpdate(i, j, k);
    }

    @Override
    public void onBlockPlacedBy(World world, int i, int j, int k,
	    EntityLivingBase entityliving, ItemStack itemStack) {
	byte chestFacing = 0;
	int facing = MathHelper
		.floor_double((double) ((entityliving.rotationYaw * 4F) / 360F) + 0.5D) & 3;
	if (facing == 0) {
	    chestFacing = 2;
	}
	if (facing == 1) {
	    chestFacing = 5;
	}
	if (facing == 2) {
	    chestFacing = 3;
	}
	if (facing == 3) {
	    chestFacing = 4;
	}
	TileEntity te = world.getBlockTileEntity(i, j, k);
	if (te != null && te instanceof TileEntityAntChest) {
	    TileEntityAntChest teic = (TileEntityAntChest) te;
	    teic.setFacing(chestFacing);
	    world.markBlockForUpdate(i, j, k);
	}
    }

    @Override
    public int damageDropped(int i) {
	return i;
    }

    @Override
    public void breakBlock(World world, int i, int j, int k, int i1, int i2) {
	TileEntityAntChest tileentitychest = (TileEntityAntChest) world
		.getBlockTileEntity(i, j, k);
	if (tileentitychest != null) {
	    dropContent(0, tileentitychest, world, tileentitychest.xCoord,
		    tileentitychest.yCoord, tileentitychest.zCoord);
	}
	super.breakBlock(world, i, j, k, i1, i2);
    }

    public void dropContent(int newSize, IInventory chest, World world,
	    int xCoord, int yCoord, int zCoord) {
	for (int l = newSize; l < chest.getSizeInventory(); l++) {
	    ItemStack itemstack = chest.getStackInSlot(l);
	    if (itemstack == null) {
		continue;
	    }
	    float f = random.nextFloat() * 0.8F + 0.1F;
	    float f1 = random.nextFloat() * 0.8F + 0.1F;
	    float f2 = random.nextFloat() * 0.8F + 0.1F;
	    while (itemstack.stackSize > 0) {
		int i1 = random.nextInt(21) + 10;
		if (i1 > itemstack.stackSize) {
		    i1 = itemstack.stackSize;
		}
		itemstack.stackSize -= i1;
		EntityItem entityitem = new EntityItem(world, (float) xCoord
			+ f, (float) yCoord + (newSize > 0 ? 1 : 0) + f1,
			(float) zCoord + f2, new ItemStack(itemstack.itemID,
				i1, itemstack.getItemDamage()));
		float f3 = 0.05F;
		entityitem.motionX = (float) random.nextGaussian() * f3;
		entityitem.motionY = (float) random.nextGaussian() * f3 + 0.2F;
		entityitem.motionZ = (float) random.nextGaussian() * f3;
		if (itemstack.hasTagCompound()) {
		    entityitem.getEntityItem().setTagCompound(
			    (NBTTagCompound) itemstack.getTagCompound().copy());
		}
		world.spawnEntityInWorld(entityitem);
	    }
	}
    }

    @Override
    public int getComparatorInputOverride(World par1World, int par2, int par3,
	    int par4, int par5) {
	return Container
		.calcRedstoneFromInventory((TileEntityAntChest) par1World
			.getBlockTileEntity(par2, par3, par4));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister) {
	this.blockIcon = par1IconRegister.registerIcon(Resources.TEXTURE_PREFIX
		+ Reference.BLOCK_ANTCHEST_NAME);
    }

    private static final ForgeDirection[] validRotationAxes = new ForgeDirection[] {
	    UP, DOWN };

    @Override
    public ForgeDirection[] getValidRotations(World worldObj, int x, int y,
	    int z) {
	return validRotationAxes;
    }

    @Override
    public boolean rotateBlock(World worldObj, int x, int y, int z,
	    ForgeDirection axis) {
	if (worldObj.isRemote) {
	    return false;
	}
	if (axis == UP || axis == DOWN) {
	    TileEntity tileEntity = worldObj.getBlockTileEntity(x, y, z);
	    if (tileEntity instanceof TileEntityAntChest) {
		TileEntityAntChest icte = (TileEntityAntChest) tileEntity;
		icte.rotateAround(axis);
	    }
	    return true;
	}
	return false;
    }
}