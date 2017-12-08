package com.github.sguzman.scala.have.scourage

import org.scalajs.dom.window

import scala.scalajs.js

object Main {
  var gmap: Option[google.maps.Map] = None
  def main(args: Array[String]): Unit = {
    google.maps.event.addListener(window, "load", initMap)
  }

  def url: String =
    "https://www.crimereports.com/api/crimes/cluster.json?agency_id=33&end_date=2017-12-08&include_sex_offenders=false &lat1=37.490455&lng1=-122.200928&lat2=37.217548 &lng2=-121.701050&start_date=2016-01-01&zoom=14"

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
