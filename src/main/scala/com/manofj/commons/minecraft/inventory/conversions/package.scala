package com.manofj.commons.minecraft.inventory

import net.minecraft.inventory.IInventory


/** インベントリに関するクラスを暗黙的に変換､拡張するクラスを提供する
  */
package object conversions {

  implicit class IInventory$( protected val self: IInventory ) extends AnyVal with IInventoryExtension

}
