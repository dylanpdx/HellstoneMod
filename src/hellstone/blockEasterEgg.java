package hellstone;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDragonEgg;
import net.minecraft.block.BlockSand;
import net.minecraft.block.material.Material;
// import net.minecraft.entity.item.EntityFallingSand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class blockEasterEgg extends BlockDragonEgg
{
    public blockEasterEgg()
    {
        super();
        this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 1.0F, 0.9375F);
    }
    

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World par1World, int par2, int par3, int par4)
    {
        par1World.scheduleBlockUpdate(par2, par3, par4, this, this.tickRate(par1World));
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
    {
        par1World.scheduleBlockUpdate(par2, par3, par4, this, this.tickRate(par1World));
    }

    /**
     * Ticks the block if it's been scheduled
     */

    /**
     * Checks if the dragon egg can fall down, and if so, makes it fall.
     */

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
        this.teleportNearby(par1World, par2, par3, par4);
        //par1World.spawnParticle("crit", par2, par3+1, par4, 0.0D, 0.0D, 0.0D);
        System.out.println("ppffft");
        return true;
    }

    /**
     * Called when the block is clicked by a player. Args: x, y, z, entityPlayer
     */
    public void onBlockClicked(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer)
    {
        this.teleportNearby(par1World, par2, par3, par4);
    }

    /**
     * Teleports the dragon egg somewhere else in a 31x19x31 area centered on the egg.
     */
    private void teleportNearby(World par1World, int par2, int par3, int par4)
    {
        if (getIdFromBlock(par1World.getBlock(par2, par3, par4)) == getIdFromBlock(this))
        {
            for (int l = 0; l < 1000; ++l)
            {
                int i1 = par2 + par1World.rand.nextInt(16) - par1World.rand.nextInt(16);
                int j1 = par3 + par1World.rand.nextInt(8) - par1World.rand.nextInt(8);
                int k1 = par4 + par1World.rand.nextInt(16) - par1World.rand.nextInt(16);

                if (getIdFromBlock(par1World.getBlock(i1, j1, k1)) == 0)
                {
                    if (!par1World.isRemote)
                    {
                        par1World.setBlock(i1, j1, k1, this, par1World.getBlockMetadata(par2, par3, par4), 2);
                        par1World.setBlockToAir(par2, par3, par4);
                    }
                    else
                    {
                        short short1 = 128;

                        for (int l1 = 0; l1 < short1; ++l1)
                        {
                            double d0 = par1World.rand.nextDouble();
                            float f = (par1World.rand.nextFloat() - 0.5F) * 0.2F;
                            float f1 = (par1World.rand.nextFloat() - 0.5F) * 0.2F;
                            float f2 = (par1World.rand.nextFloat() - 0.5F) * 0.2F;
                            double d1 = (double)i1 + (double)(par2 - i1) * d0 + (par1World.rand.nextDouble() - 0.5D) * 1.0D + 0.5D;
                            double d2 = (double)j1 + (double)(par3 - j1) * d0 + par1World.rand.nextDouble() * 1.0D - 0.5D;
                            double d3 = (double)k1 + (double)(par4 - k1) * d0 + (par1World.rand.nextDouble() - 0.5D) * 1.0D + 0.5D;
                            //par1World.spawnParticle("portal", d1, d2, d3, (double)f, (double)f1, (double)f2);
                    		//for(int countparticles = 0; countparticles <= 10; ++countparticles)
                    		//{
                    			
                    			//par1World.spawnParticle("smoke", d, par3, par4, 0.0D, 0.0D, 0.0D);
                    			//par1World.spawnParticle("smoke", par2, par3, par4, 0.0D, 0.0D, 0.0D);                  
                    			par1World.spawnParticle("witchMagic", par2+.5, par3+.5, par4+.5, par1World.rand.nextInt(10)-5, par1World.rand.nextInt(10)-5, par1World.rand.nextInt(10)-5);
                    			//System.out.println("x "+par2+" y "+par3+" z "+par4);
                    		//}
                    		
                        }
                    }

                    return;
                }
            }
        }
    }

    /**
     * How many world ticks before ticking
     */
    public int tickRate(World par1World)
    {
        return 5;
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return false;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @SideOnly(Side.CLIENT)

    /**
     * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
     * coordinates.  Args: blockAccess, x, y, z, side
     */
    public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        return true;
    }

    /**
     * The type of render function that is called for this block
     */
    public int getRenderType()
    {
        return 27;
    }

    @SideOnly(Side.CLIENT)

    /**
     * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
     */
    public int idPicked(World par1World, int par2, int par3, int par4)
    {
        return 0;
    }
}
