package com.manofj.commons.minecraft.inventory.conversions

import net.minecraft.inventory.IInventory
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound

import com.manofj.commons.scala.util.conversions.Extension

import com.manofj.commons.minecraft.inventory.IInventoryUtils
import com.manofj.commons.minecraft.inventory.SubInventory
import com.manofj.commons.minecraft.nbt.NBTTagCompoundUtils


/** $inventory のインスタンスに対して､ユーティリティメソッドを使用可能にする
  *
  * @define inventory   [[net.minecraft.inventory.IInventory]]
  * @define tagCompound [[net.minecraft.nbt.NBTTagCompound]]
  */
trait IInventoryExtension
  extends Any
  with    Extension[ IInventory ]
{

  /** 指定された範囲内のアイテムを切り出し､新たなインベントリとして返す
    *
    * @param begin 指定範囲の開始インデックス
    * @param end   指定範囲の終了インデックス
    */
  def subInventory( begin: Int, end: Int ): IInventory = SubInventory( self, begin, end )

  /** 指定されたインデックスから最後のアイテムまでを切り出し､新たなインベントリとして返す
    *
    * @param begin 始点となるインデックス
    */
  def subInventory( begin: Int ): IInventory = SubInventory( self, begin )

  /** インベントリの内容を $tagCompound に変換する
    */
  def itemsToNBT: NBTTagCompound = IInventoryUtils.itemsToNBTTagCompound( self )

  /** 指定された $tagCompound からインベントリの内容を復元する
    */
  def itemsFromNBT( tagCompound: NBTTagCompound ): Unit = IInventoryUtils.itemsFromNBTTagCompound( self, tagCompound )

  /** インベントリの内容をバイト配列に変換する
    */
  def itemsToByteArray: Array[ Byte ] = NBTTagCompoundUtils.toByteArray( itemsToNBT )

  /** 指定されたバイト配列からインベントリの内容を復元する
    */
  def itemsFromByteArray( bytes: Array[ Byte ] ): Unit = itemsFromNBT( NBTTagCompoundUtils.fromByteArray( bytes ) )

  /** インベントリの内容を読み取るイテレータを返す
    */
  def toIterator: Iterator[ ItemStack ] = IInventoryUtils.toIterator( self )

}
