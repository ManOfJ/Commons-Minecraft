package com.manofj.commons.minecraft.item

import net.minecraft.item.ItemStack

import com.manofj.commons.minecraft.nbt.NBTTagCompoundUtils


/** [[net.minecraft.item.ItemStack]] に対するユーティリティメソッドを提供する
  */
object ItemStackUtils {

  /** 指定されたバイト配列からアイテムを復元する
    *
    * @param bytes      復元するデータ
    * @param compressed データが圧縮されたものであるか否か
    */
  def fromByteArray( bytes: Array[ Byte ], compressed: Boolean = true ): ItemStack =
    ItemStack.loadItemStackFromNBT( NBTTagCompoundUtils.fromByteArray( bytes, compressed ) )

  /** 指定されたアイテムをバイト配列に変換する
    *
    * @param item       対象のアイテム
    * @param compressed データを圧縮するか否か
    */
  def toByteArray( item: ItemStack, compressed: Boolean = true ): Array[ Byte ] =
    NBTTagCompoundUtils.toByteArray( item.serializeNBT, compressed )

}
