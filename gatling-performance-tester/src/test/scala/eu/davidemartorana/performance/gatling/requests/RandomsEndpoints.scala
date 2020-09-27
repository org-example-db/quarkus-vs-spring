package eu.davidemartorana.performance.gatling.requests

import io.gatling.core.Predef._
import io.gatling.http.Predef._

object RandomsEndpoints {

  val retrieveRandomDateTime = exec(
    http("Get Random Time")
      .get("random/time")
      .check(status.is(200))
      .check(jsonPath("$.dateTime").exists.saveAs("randomTime"))
  );


  val retrieveRandomDescription = exec(
    http("Get Random Description")
      .get("random/description")
      .check(status.is(200))
      .check(jsonPath("$.description").exists.saveAs("randomDescription"))
  );

  val reformatRandomly = exec(
      session => {
        session
          .set("random-time", session("randomTime").as[String])
          .set("random-description", session("randomDescription").as[String])
      }
    )
    .exec(
      http("Format Json Body Randomly")
        .post("/random/format")
        .body(ElFileBody("load-test/random-format-request.tmpl.json"))
        .check(status.is(200))
        .check(jsonPath("$.dateTime").exists)
        .check(jsonPath("$.description").exists)
  );
}
