package com.manofj.commons.minecraft.item.conversions

import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation

import com.manofj.commons.scala.util.conversions.Extension

import com.manofj.commons.minecraft.nbt.NBTTagCompoundUtils


/** $item のインスタンスに対して､ユーティリティメソッドを使用可能にする
  *
  * @define item  [[net.minecraft.item.ItemStack]]
  * @define self  `self`
  * @define true  `true`
  * @define false `false`
  */
trait ItemStackExtension
  extends Any
  with    Extension[ ItemStack ]
{

  /** アイテムとタグの内容が指定された $item と一致していれば $true を返す
    * そうでなければ $false を返す
    *
    * @param other 比較対象アイテム
    */
  def isItemAndTagsEqual( other: ItemStack ): Boolean =
    self.isItemEqual( other ) && ItemStack.areItemStackTagsEqual( self, other )

  /** アイテムとダメージ値､タグの内容が指定された $item と一致していれば $true を返す
    * そうでなければ $false を返す
    *
    * @param other 比較対象アイテム
    */
  def isItemAndTagsEqualIgnoreDurability( other: ItemStack ): Boolean =
    self.isItemEqualIgnoreDurability( other ) && ItemStack.areItemStackTagsEqual( self, other )

  /** $self に指定されたアイテムをマージする
    *
    * @param other マージされるアイテム
    * @param limit スタック数の制限値
    *
    * @return マージされたスタック数
    */
  def merge( other: ItemStack, limit: Int = self.getMaxStackSize ): Int = {
    if ( !mergeable( other ) ) 0
    else
    {
      val size = 0 max ( other.stackSize min remainingCapacity min limit )

      other.stackSize -= size
      self.stackSize += size

      size
    }
  }

  /** 指定されたアイテムが $self にマージ可能である場合は $true を返す
    * そうでなければ $false を返す
    *
    * @param other 評価対象のアイテム
    */
  def mergeable( other: ItemStack ): Boolean =
    self.ne( other ) && self.getMaxStackSize > self.stackSize && isItemAndTagsEqual( other )

  /** アイテムの登録名を返す
    * $self がアイテムを持たない場合は [[scala.None]] を返す
    */
  def registryName: Option[ ResourceLocation ] = Option( self.getItem ).flatMap( x => Option( x.getRegistryName ) )

  /** $self にスタック可能な残りアイテム数を返す
    */
  def remainingCapacity: Int = 0 max ( self.getMaxStackSize - self.stackSize )

  /** アイテム情報をバイト配列に変換する
    */
  def serializeByteArray: Array[ Byte ] = NBTTagCompoundUtils.toByteArray( self.serializeNBT )

  /** バイト配列からアイテム情報を復元する
    */
  def deserializeByteArray( bytes: Array[ Byte ] ): Unit = self.deserializeNBT( NBTTagCompoundUtils.fromByteArray( bytes ) )

}
