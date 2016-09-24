package com.manofj.commons.minecraft.inventory

import net.minecraft.inventory.IInventory
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.nbt.NBTTagList

import net.minecraftforge.common.util.Constants.NBT


/** $inventory に対するユーティリティメソッドを提供する
  *
  * @define inventory   [[net.minecraft.inventory.IInventory]]
  * @define tagCompound [[net.minecraft.nbt.NBTTagCompound]]
  * @define tagList     [[net.minecraft.nbt.NBTTagList]]
  */
object IInventoryUtils {

  /** 指定されたインベントリの内容を読み取るイテレータを返す
    *
    * @param inventory 対象のインベントリ
    */
  def toIterator( inventory: IInventory ): Iterator[ ItemStack ] =
    Iterator.range( 0, inventory.getSizeInventory ).map( inventory.getStackInSlot )

  /** 指定された $tagCompound からインベントリの内容を復元する
    *
    * @param inventory   対象のインベントリ
    * @param tagCompound 復元するデータ
    */
  def itemsFromNBTTagCompound( inventory: IInventory, tagCompound: NBTTagCompound ): Unit = {
    val tagName = "Inventory:" + inventory.getName

    itemsFromNBTTagList( inventory, tagCompound.getTagList( tagName, NBT.TAG_COMPOUND ) )
  }

  /** 指定された $tagList からインベントリの内容を復元する
    *
    * @param inventory 対象のインベントリ
    * @param tagList   復元するデータ
    */
  def itemsFromNBTTagList( inventory: IInventory, tagList: NBTTagList ): Unit = {
    ( 0 until inventory.getSizeInventory ).foreach { i =>
      val tagCompound = tagList.getCompoundTagAt( i )
      val item = if ( !tagCompound.hasNoTags ) ItemStack.loadItemStackFromNBT( tagCompound ) else null

      inventory.setInventorySlotContents( i, item )
    }
  }

  /** 指定されたインベントリの内容を $tagCompound に変換する
    *
    * @param inventory 対象のインベントリ
    */
  def itemsToNBTTagCompound( inventory: IInventory ): NBTTagCompound = {
    val tagCompound = new NBTTagCompound()
    val tagName = "Inventory:" + inventory.getName

    tagCompound.setTag( tagName, itemsToNBTTagList( inventory ) )

    tagCompound
  }

  /** 指定されたインベントリの内容を $tagList に変換する
    *
    * @param inventory 対象のインベントリ
    */
  def itemsToNBTTagList( inventory: IInventory ): NBTTagList = {
    val tagList = new NBTTagList()

    toIterator( inventory ).foreach {
      case null => tagList.appendTag( new NBTTagCompound() )
      case item => tagList.appendTag( item.serializeNBT )
    }

    tagList
  }

}
