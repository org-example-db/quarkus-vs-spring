package eu.davidemartorana.performance.gatling.requests

import io.gatling.core.Predef._
import io.gatling.http.Predef._

object Covid19Endpoints {

  val retrieveAllCovidCasesAndSelectOneRandomly = exec(
    http("Get All Covid-19 Stats")
      .get("/covid19/italy")
      .check(status.is(200))
      .check(jsonPath("$[*].id").findRandom.saveAs("randomStatsId"))
  );

  val retrieveById = exec(
    http("Get Covid-19 Stats By Id")
      .get("/covid19/italy/${statsId}")
      .check(status.is(200))
      .check(bodyString.saveAs("covid19StatsBodyResponse"))
  );


  val retrieveWithStartDate = exec(
    http("Get covid-19 Stats with StartDate")
      .get("/covid19/italy?start=${startDate}")
      .check(status.is(200))
  );

  val retrieveWithEndDate = exec(
    http("Get covid-19 Stats with StartDate")
      .get("/covid19/italy?end=${endDate}")
      .check(status.is(200))
  );

  val retrieveWithinRangeDate = exec(
    http("Get covid-19 Stats within Date Range")
      .get("/covid19/italy?start=${startDate}&end=${endDate}")
      .check(status.is(200))
  );
}
