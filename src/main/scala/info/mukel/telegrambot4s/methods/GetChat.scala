package info.mukel.telegrambot4s.methods

import info.mukel.telegrambot4s.models.Chat

/** Use this method to get up to date information about the chat (current name of the user for one-on-one conversations, current username of a user, group or channel, etc.).
  * Returns a Chat object on success.
  *
  * @param chatId	Integer or String	Unique identifier for the target chat or username of the target supergroup or channel (in the format @channelusername)
  */
case class GetChat(chatId: Long Either String) extends ApiRequestJson[Chat]
