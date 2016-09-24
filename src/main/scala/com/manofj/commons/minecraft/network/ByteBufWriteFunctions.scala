package com.manofj.commons.minecraft.network

import io.netty.buffer.ByteBufOutputStream

import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound

import com.manofj.commons.minecraftforge.network.io.ByteBufWriteFunction
import com.manofj.commons.minecraftforge.network.io.conversions.ByteBufOutputStream$

import com.manofj.commons.minecraft.item.ItemStackUtils
import com.manofj.commons.minecraft.nbt.NBTTagCompoundUtils


/** Minecraft のオブジェクトを [[io.netty.buffer.ByteBufOutputStream]] に書き込むための
  * [[com.manofj.commons.minecraftforge.network.io.ByteBufWriteFunction]] 実装クラスを提供する
  */
object ByteBufWriteFunctions {

  trait NBTTagCompoundWriteFunction extends ByteBufWriteFunction[ NBTTagCompound ] {
    override def apply( v: ( ByteBufOutputStream, NBTTagCompound ) ): Unit = v._1.writeBinary( NBTTagCompoundUtils.toByteArray( v._2 ) )
  }
  implicit object NBTTagCompound extends NBTTagCompoundWriteFunction

  trait ItemStackWriteFunction extends ByteBufWriteFunction[ ItemStack ] {
    override def apply( v: ( ByteBufOutputStream, ItemStack ) ): Unit = v._1.writeBinary( ItemStackUtils.toByteArray( v._2 ) )
  }
  implicit object ItemStack extends ItemStackWriteFunction

}
