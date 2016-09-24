package com.manofj.commons.minecraft.item

import net.minecraft.item.ItemStack


/** アイテムに関するクラスを暗黙的に変換､拡張するクラスを提供する
  */
package object conversions {

  implicit class ItemStack$( protected val self: ItemStack ) extends AnyVal with ItemStackExtension

}
