package com.manofj.commons.minecraft.util

import java.util.UUID

import scala.collection.convert.WrapAsScala.asScalaBuffer

import net.minecraft.client.entity.EntityPlayerSP
import net.minecraft.entity.player.EntityPlayerMP


/** プレイヤーエンティティの取得メソッドなどを提供する
  */
object Players {

  def list: Seq[ EntityPlayerMP ] = Minecrafts.playerList.getPlayerList

  def entitySP: EntityPlayerSP = Minecrafts.client.thePlayer

  def entityMP: EntityPlayerMP = Minecrafts.playerList.getPlayerByUUID( entitySP.getUniqueID )

  /** 指定された条件に一致するプレイヤーをすべて返す
    * 条件に一致するプレイヤーが存在しない場合は空のコレクションを返す
    *
    * @param predicate プレイヤーの検索条件
    */
  def findAllBy( predicate: EntityPlayerMP => Boolean ): Seq[ EntityPlayerMP ] = list.filter( predicate )

  /** 指定された条件に一致する最初のプレイヤーを返す
    * 条件に一致するプレイヤーが存在しない場合は [[scala.None]] を返す
    *
    * @param predicate プレイヤーの検索条件
    */
  def findBy( predicate: EntityPlayerMP => Boolean ): Option[ EntityPlayerMP ] = list.find( predicate )

  def findByUUID( uuid: UUID ): Option[ EntityPlayerMP ] = Option( Minecrafts.playerList.getPlayerByUUID( uuid ) )

  def findByName( name: String ): Option[ EntityPlayerMP ] = Option( Minecrafts.playerList.getPlayerByUsername( name ) )

}
