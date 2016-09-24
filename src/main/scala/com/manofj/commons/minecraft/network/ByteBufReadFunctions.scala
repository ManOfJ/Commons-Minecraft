package com.manofj.commons.minecraft.network

import io.netty.buffer.ByteBufInputStream

import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound

import com.manofj.commons.minecraftforge.network.io.ByteBufReadFunction
import com.manofj.commons.minecraftforge.network.io.conversions.ByteBufInputStream$

import com.manofj.commons.minecraft.item.ItemStackUtils
import com.manofj.commons.minecraft.nbt.NBTTagCompoundUtils


/** Minecraft のオブジェクトを [[io.netty.buffer.ByteBufInputStream]] から読み込むための
  * [[com.manofj.commons.minecraftforge.network.io.ByteBufReadFunction]] 実装クラスを提供する
  */
object ByteBufReadFunctions {

  trait NBTTagCompoundReadFunction extends ByteBufReadFunction[ NBTTagCompound ] {
    override def apply( v: ByteBufInputStream ): NBTTagCompound = NBTTagCompoundUtils.fromByteArray( v.readBinary )
  }
  implicit object NBTTagCompound extends NBTTagCompoundReadFunction

  trait ItemStackWriteFunction extends ByteBufReadFunction[ ItemStack ] {
    override def apply( v: ByteBufInputStream ): ItemStack = ItemStackUtils.fromByteArray( v.readBinary )
  }
  implicit object ItemStack extends ItemStackWriteFunction

}
