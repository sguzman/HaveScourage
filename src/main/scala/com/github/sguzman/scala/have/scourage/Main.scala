package com.github.sguzman.scala.have.scourage

object Main {
  def main(args: Array[String]): Unit = {
    println(url)
  }

  def url: String =
    "https://www.crimereports.com/api/crimes/cluster.json?agency_id=33&end_date=2017-12-08&include_sex_offenders=false &lat1=37.490455&lng1=-122.200928&lat2=37.217548 &lng2=-121.701050&start_date=2016-01-01&zoom=14"
}
