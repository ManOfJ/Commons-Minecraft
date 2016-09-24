package com.manofj.commons.minecraft.util

import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiIngame
import net.minecraft.client.gui.GuiScreen
import net.minecraft.client.settings.GameSettings
import net.minecraft.server.MinecraftServer
import net.minecraft.server.management.PlayerList

import net.minecraftforge.fml.client.FMLClientHandler
import net.minecraftforge.fml.common.FMLCommonHandler

import com.manofj.commons.scala.alias.java.IO.JavaFile


/** Minecraft ゲームインスタンスの取得メソッド､
  * ゲームインスタンスのフィールド取得メソッドなどを提供する
  */
object Minecrafts {

  def client: Minecraft = FMLClientHandler.instance.getClient

  def server: MinecraftServer = FMLCommonHandler.instance.getMinecraftServerInstance


  def currentScreen: GuiScreen = client.currentScreen

  def settings: GameSettings = client.gameSettings

  def ingameGui: GuiIngame = client.ingameGUI

  def gameDir: JavaFile = client.mcDataDir


  def playerList: PlayerList = server.getPlayerList

}
