package com.github.sguzman.scala.have.scourage

import fr.hmil.roshttp.HttpRequest

import scala.concurrent.Await
import monix.execution.Scheduler.Implicits.global
import scala.concurrent.duration.Duration

object Main {
  def main(args: Array[String]): Unit = {
    val request = get
    val response = Await.result(request.send, Duration.Inf)
    val body = response.body
    println(body)
  }

  def get: HttpRequest = HttpRequest("https://www.crimereports.com/api/crimes/cluster.json?agency_id=33")
    .withQueryParameter("days", "sunday,monday,tuesday,wednesday,thursday,friday,saturday" )
    .withQueryParameter("end_date", "2017-12-07")
    .withQueryParameter("end_time", "23")
    .withQueryParameter("incident_types", "Assault,Assault" +
      "+with" +
      "+Deadly" +
      "+Weapon,Breaking" +
      "+%26" +
      "+Entering,Disorder,Drugs,Homicide,Kidnapping,Liquor,Other" +
      "+Sexual" +
      "+Offense,Property" +
      "+Crime,Property" +
      "+Crime" +
      "+Commercial,Property" +
      "+Crime" +
      "+Residential,Quality" +
      "+of" +
      "+Life,Robbery,Sexual" +
      "+Assault,Sexual" +
      "+Offense,Theft,Theft" +
      "+from" +
      "+Vehicle,Theft" +
      "+of" +
      "+Vehicle")
    .withQueryParameter("include_sex_offenders", "false")
    .withQueryParameter("lat1", "37.612055711412815")
    .withQueryParameter("lat2", "36.787291466820015")
    .withQueryParameter("lng1", "-119.84710693359375")
    .withQueryParameter("lng2", "-123.80218505859375")
    .withQueryParameter("sandbox", "false")
    .withQueryParameter("start_date", "2016-01-01")
    .withQueryParameter("start_time", "0")
    .withQueryParameter("zoom", "9")
}
