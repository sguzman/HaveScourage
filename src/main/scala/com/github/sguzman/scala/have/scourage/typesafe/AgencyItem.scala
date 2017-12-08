package com.github.sguzman.scala.have.scourage.typesafe

case class AgencyItem(
                     id: String,
                     account_type: String,
                     agency_id: Int,
                     agency_name: String,
                     agency_type: String,
                     center: Center,
                     city: String,
                     domain: String,
                     incident_datasent: String,
                     state: String,
                     tipsoft_id: String,
                     zip: String,
                     namespace: String,
                     primary_key: String,
                     crimes: Array[String],
                     plus_enabled: Boolean,
                     clusters: Array[Cluster]
                     )
