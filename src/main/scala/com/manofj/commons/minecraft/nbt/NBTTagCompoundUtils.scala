package com.manofj.commons.minecraft.nbt

import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.DataInputStream
import java.io.DataOutputStream

import net.minecraft.nbt.CompressedStreamTools
import net.minecraft.nbt.NBTTagCompound


/** [[net.minecraft.nbt.NBTTagCompound]] に対するユーティリティメソッドを提供する
  */
object NBTTagCompoundUtils {

  /** 指定されたバイト配列からタグを復元する
    *
    * @param bytes      復元するデータ
    * @param compressed データが圧縮されたものであるか否か
    */
  def fromByteArray( bytes: Array[ Byte ], compressed: Boolean = true ): NBTTagCompound = {
    val bis = new ByteArrayInputStream( bytes )

    if ( compressed ) CompressedStreamTools.readCompressed( bis )
    else
    {
      val dis = new DataInputStream( bis )
      CompressedStreamTools.read( dis )
    }
  }

  /** 指定されたタグをバイト配列に変換する
    *
    * @param tagCompound 対象のタグ
    * @param compressed  データを圧縮するか否か
    */
  def toByteArray( tagCompound: NBTTagCompound, compressed: Boolean = true ): Array[ Byte ] = {
    val bos = new ByteArrayOutputStream()

    if ( compressed ) CompressedStreamTools.writeCompressed( tagCompound, bos )
    else
    {
      val dos = new DataOutputStream( bos )
      CompressedStreamTools.write( tagCompound, dos )
    }

    bos.toByteArray
  }

}
