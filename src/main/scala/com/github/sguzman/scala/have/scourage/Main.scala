package com.github.sguzman.scala.have.scourage

import com.github.sguzman.scala.have.scourage.typesafe.Agency
import fr.hmil.roshttp.HttpRequest
import fr.hmil.roshttp.response.SimpleHttpResponse
import org.scalajs.dom.window
import monix.execution.Scheduler.Implicits.global

import io.circe.parser.decode
import io.circe.generic.auto._

import scala.scalajs.js
import scala.util.{Failure, Success}

object Main {
  var gmap: Option[google.maps.Map] = None
  def main(args: Array[String]): Unit = {
    google.maps.event.addListener(window, "load", initMap)

    val request = HttpRequest(url)
    val response = request.send
    response.onComplete({
      case s: Success[SimpleHttpResponse] =>
        val body = s.value.body
        val agency = decode[Agency](body).right.get
        val latLngs = agency
            .agencies
            .head
            .clusters
            .map(_.snap)
            .map(_.coordinates)
            .map(t => new google.maps.LatLng(t(1), t(0)))

        val zipped = agency.agencies.head.clusters.zip(latLngs)
        val markers = zipped.map(t => new google.maps.Marker(google.maps.MarkerOptions(
          position = t._2,
          map = this.gmap.get
        )))
      case err: Failure[SimpleHttpResponse] => Console.err.println(err)
    })
  }

  def url: String =
    "https://www.crimereports.com/api/crimes/cluster.json?agency_id=33&end_date=2017-12-08&include_sex_offenders=false&lat1=37.490455&lng1=-122.200928&lat2=37.217548&lng2=-121.701050&start_date=2016-01-01&zoom=14"

  def initMap = js.Function {
    val opts = google.maps.MapOptions(
      backgroundColor = "red",
      center = new google.maps.LatLng(37.333274, -121.961643),
      zoom = 14,
      panControl = false,
      streetViewControl = false,
      mapTypeControl = true
    )
    this.gmap = Some(new google.maps.Map(org.scalajs.dom.document.getElementById("map"), opts))

    ""
  }
}
