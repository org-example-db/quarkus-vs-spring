package eu.davidemartorana.performance.gatling.requests

import io.gatling.core.Predef._
import io.gatling.http.Predef._

object Covid19Endpoints {

  val retrieveAllCovidCases = exec(
    http("Get All Covid-19 Cases")
      .get("/covid19/italy")
  );

}
