package com.github.sguzman.scala.have.scourage

import com.github.sguzman.scala.have.scourage.typesafe.Agency
import fr.hmil.roshttp.HttpRequest

import io.circe.parser.decode
import io.circe.generic.auto._

import scala.concurrent.Await
import monix.execution.Scheduler.Implicits.global

import scala.concurrent.duration.Duration

object Main {
  def main(args: Array[String]): Unit = {
    val request = get
    val response = Await.result(request.send, Duration.Inf)
    val body = response.body
    val agency = decode[Agency](body).right.get
    val clusters = agency.agencies.head.clusters
    println(clusters.length)
  }

  def get: HttpRequest = HttpRequest("https://www.crimereports.com/api/crimes/cluster.json?agency_id=33")
    .withQueryParameter("end_date", "2017-12-07")
    .withQueryParameter("include_sex_offenders", "false")
    .withQueryParameter("lat1", "37.490455")
    .withQueryParameter("lng1", "-122.200928")
    .withQueryParameter("lat2", "37.217548")
    .withQueryParameter("lng2", "-121.701050")
    .withQueryParameter("start_date", "2016-01-01")
    .withQueryParameter("zoom", "14")
}
