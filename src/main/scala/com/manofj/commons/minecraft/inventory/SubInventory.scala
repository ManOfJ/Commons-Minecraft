package com.manofj.commons.minecraft.inventory

import net.minecraft.inventory.IInventory
import net.minecraft.item.ItemStack


object SubInventory {

  /** 指定されたインベントリの一部分のみ参照可能なインベントリを返す
    *
    * @param parent 対象のインベントリ
    * @param begin  参照可能な範囲の開始インデックス
    * @param end    参照可能な範囲の終了インデックス
    */
  def apply( parent: IInventory, begin: Int, end: Int ): SubInventory = {
    Option( parent ) match {
      case None => throw new NullPointerException( "Inventory is null" )
      case Some( x ) =>
        if ( begin > end )
          throw new IllegalArgumentException( s"'begin' must be less than or equal to 'end': [ $begin, $end ]" )

        else if ( end >= parent.getSizeInventory )
          throw new IndexOutOfBoundsException( s"'end' is over the size limit for parent inventory: [ end: $end, inventorySize: ${ parent.getSizeInventory } ]" )

        else
          new SubInventory( parent, begin, end )
    }
  }

  /** 指定されたインベントリの `begin` から最後まで参照可能なインベントリを返す
    *
    * @param parent 対象のインベントリ
    * @param begin  参照可能な範囲の開始インデックス
    */
  def apply( parent: IInventory, begin: Int ): IInventory = apply( parent, begin, parent.getSizeInventory - 1 )

}

/** 指定された [[net.minecraft.inventory.IInventory]] の一部分をインベントリとして扱う
  *
  * @param delegate 対象のインベントリ
  * @param begin    参照可能な範囲の開始インデックス
  * @param end      参照可能な範囲の終了インデックス
  */
class SubInventory private( protected val delegate: IInventory,
                            private   val begin:    Int,
                            private   val end:      Int )
  extends DelegateInventory
{

  override def isItemValidForSlot( index: Int, stack: ItemStack ): Boolean = super.isItemValidForSlot( index + begin, stack )
  override def setInventorySlotContents( index: Int, stack: ItemStack ): Unit = super.setInventorySlotContents( index + begin, stack )
  override def removeStackFromSlot( index: Int ): ItemStack = super.removeStackFromSlot( index + begin )
  override def decrStackSize( index: Int, count: Int ): ItemStack = super.decrStackSize( index + begin, count )
  override def getStackInSlot( index: Int ): ItemStack = super.getStackInSlot( index + begin )
  override def getSizeInventory: Int = ( end - begin ) + 1

}
