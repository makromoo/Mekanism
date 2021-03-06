package mekanism.common.miner;

import java.util.ArrayList;

import mekanism.common.util.MekanismUtils;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import com.google.common.io.ByteArrayDataInput;

public class MOreDictFilter extends MinerFilter
{
	public String oreDictName;
	
	@Override
	public boolean canFilter(ItemStack itemStack)
	{
		if(itemStack == null || !(itemStack.getItem() instanceof ItemBlock))
		{
			return false;
		}
		
		if(oreDictName.equals("*") && itemStack.itemID != Block.bedrock.blockID)
		{
			return true;
		}
		
		String oreKey = MekanismUtils.getOreDictName(itemStack);
		
		if(oreKey == null)
		{
			return false;
		}
		
		if(oreDictName.equals(oreKey))
		{
			return true;
		}
		else if(oreDictName.endsWith("*") && !oreDictName.startsWith("*"))
		{
			if(oreKey.startsWith(oreDictName.substring(0, oreDictName.length()-1)))
			{
				return true;
			}
		}
		else if(oreDictName.startsWith("*") && !oreDictName.endsWith("*"))
		{
			if(oreKey.endsWith(oreDictName.substring(1)))
			{
				return true;
			}
		}
		else if(oreDictName.startsWith("*") && oreDictName.endsWith("*"))
		{
			if(oreKey.contains(oreDictName.substring(1, oreDictName.length()-1)))
			{
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public NBTTagCompound write(NBTTagCompound nbtTags)
	{
		nbtTags.setInteger("type", 1);
		nbtTags.setString("oreDictName", oreDictName);
		
		return nbtTags;
	}
	
	@Override
	protected void read(NBTTagCompound nbtTags)
	{
		oreDictName = nbtTags.getString("oreDictName");
	}
	
	@Override
	public void write(ArrayList data)
	{
		data.add(1);
		data.add(oreDictName);
	}
	
	@Override
	protected void read(ByteArrayDataInput dataStream)
	{
		oreDictName = dataStream.readUTF();
	}
	
	@Override
	public int hashCode() 
	{
		int code = 1;
		code = 31 * code + oreDictName.hashCode();
		return code;
	}
	
	@Override
	public boolean equals(Object filter)
	{
		return super.equals(filter) && filter instanceof MOreDictFilter && ((MOreDictFilter)filter).oreDictName.equals(oreDictName);
	}
	
	@Override
	public MOreDictFilter clone()
	{
		MOreDictFilter filter = new MOreDictFilter();
		filter.oreDictName = oreDictName;
		
		return filter;
	}
}
