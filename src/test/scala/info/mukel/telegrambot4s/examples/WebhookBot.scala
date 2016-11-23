package info.mukel.telegrambot4s.examples

import java.net.URLEncoder

import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{HttpRequest, Uri}
import akka.http.scaladsl.unmarshalling.Unmarshal
import info.mukel.telegrambot4s.Implicits._
import info.mukel.telegrambot4s.api._
import info.mukel.telegrambot4s.methods._
import info.mukel.telegrambot4s.models._

/**
  * Webhook-backed JS calculator.
  */
class WebhookBot(token: String) extends TestBot(token) with Webhook {

  val port = 8443
  val webhookUrl = "https://a11385d9.ngrok.io"

  val baseUrl = "http://api.mathjs.org/v1/?expr="

  override def onMessage(msg: Message): Unit = {
    for (text <- msg.text) {
      val url = baseUrl + URLEncoder.encode(text, "UTF-8")
      for {
        res <- Http().singleRequest(HttpRequest(uri = Uri(url)))
        if res.status.isSuccess()
        result <- Unmarshal(res).to[String]
      } /* do */ {
        request(SendMessage(msg.sender, result))
      }
    }
  }
}
