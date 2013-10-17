package vivadaylight3.myrmecology.common.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import vivadaylight3.myrmecology.api.MyrmopaediaProperties;
import vivadaylight3.myrmecology.common.Myrmecology;
import vivadaylight3.myrmecology.common.Reference;
import vivadaylight3.myrmecology.common.Register;
import vivadaylight3.myrmecology.common.lib.Resources;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemMyrmopaedia extends Item {

    public ItemMyrmopaedia(int par1) {
	super(par1);
	setCreativeTab(Register.tabMyrmecology);
	setMaxStackSize(1);
	setUnlocalizedName(Reference.ITEM_MYRMOPAEDIA_NAME);
	// func_111206_d(Resources.TEXTURE_PREFIX +
	// Reference.ITEM_ANTOPEDIA_NAME);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister) {
	this.itemIcon = par1IconRegister.registerIcon(Resources.TEXTURE_PREFIX
		+ Reference.ITEM_MYRMOPAEDIA_NAME);
    }

    @Override
    public void addInformation(ItemStack par1ItemStack,
	    EntityPlayer par2EntityPlayer, List par3List, boolean par4) {

	par3List.add("Analyses the properties of ants");

    }

    @Override
    public void onCreated(ItemStack par1ItemStack, World par2World,
	    EntityPlayer par3EntityPlayer) {

	MyrmopaediaProperties.initiateMyrmopaedia(par1ItemStack);

    }

    @Override
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer player,
	    World world, int x, int y, int z, int par7, float par8, float par9,
	    float par10) {

	player.openGui(Myrmecology.instance, Register.GUI_ID_MYRMOPAEDIA,
		world, x, y, z);

	return false;

    }

}