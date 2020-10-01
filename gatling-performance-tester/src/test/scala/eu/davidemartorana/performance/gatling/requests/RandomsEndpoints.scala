package eu.davidemartorana.performance.gatling.requests

import io.gatling.core.Predef._
import io.gatling.http.Predef._

object RandomsEndpoints {

  val retrieveRandomDate = exec(
    http("Get Random Time")
      .get("/random/date")
      .check(status.is(200))
      .check(jsonPath("$.dateTime").saveAs("randomDate"))
  );


  val retrieveRandomDescription = exec(
    http("Get Random Description")
      .get("/random/description")
      .check(status.is(200))
      .check(jsonPath("$.description").saveAs("randomDescription"))
  );

  val reformatRandomly = exec(
      http("Format Json Body Randomly")
        .post("/random/format")
        .body(ElFileBody("load-test/random-format-request.tmpl.json")).asJson
        .check(bodyString.saveAs("formattedBody"))
        .check(status.is(200))
        .check(checkIf("${enableAssertions}")(jsonPath("$.dateTime").exists))
        .check(checkIf("${enableAssertions}")(jsonPath("$.description").exists))  //Not sure
  );
}
