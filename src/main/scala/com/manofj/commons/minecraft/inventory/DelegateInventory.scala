package com.manofj.commons.minecraft.inventory

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.IInventory
import net.minecraft.item.ItemStack
import net.minecraft.util.text.ITextComponent


/** 内部に持つ [[net.minecraft.inventory.IInventory]] のインスタンスにすべての処理を委譲する
  */
abstract class DelegateInventory
  extends IInventory
{

  protected def delegate: IInventory


  override def getField( id: Int ): Int = delegate.getField( id )
  override def isItemValidForSlot( index: Int, stack: ItemStack ): Boolean = delegate.isItemValidForSlot( index, stack )
  override def isUseableByPlayer( player: EntityPlayer ): Boolean = delegate.isUseableByPlayer( player )
  override def setInventorySlotContents( index: Int, stack: ItemStack ): Unit = delegate.setInventorySlotContents( index, stack )
  override def getInventoryStackLimit: Int = delegate.getInventoryStackLimit
  override def setField( id: Int, value: Int ): Unit = delegate.setField( id, value )
  override def getFieldCount: Int = delegate.getFieldCount
  override def removeStackFromSlot( index: Int ): ItemStack = delegate.removeStackFromSlot( index )
  override def openInventory( player: EntityPlayer ): Unit = delegate.openInventory( player )
  override def decrStackSize( index: Int, count: Int ): ItemStack = delegate.decrStackSize( index, count )
  override def getStackInSlot( index: Int ): ItemStack = delegate.getStackInSlot( index )
  override def clear(): Unit = delegate.clear()
  override def getSizeInventory: Int = delegate.getSizeInventory
  override def closeInventory( player: EntityPlayer ): Unit = delegate.closeInventory( player )
  override def markDirty(): Unit = delegate.markDirty()

  override def getDisplayName: ITextComponent = delegate.getDisplayName
  override def hasCustomName: Boolean = delegate.hasCustomName
  override def getName: String = delegate.getName

}
