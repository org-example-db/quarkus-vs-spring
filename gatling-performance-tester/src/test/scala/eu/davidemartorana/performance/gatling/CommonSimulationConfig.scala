package eu.davidemartorana.performance.gatling

import com.typesafe.config.ConfigFactory
import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration.DurationInt

object CommonSimulationConfig  {

  val conf = ConfigFactory.load()

  val times: Int = conf.getInt("app.simulation.times");
  val timeFrameInSec: Int = conf.getInt("app.simulation.timeFrameInSec");
  val separationTimeInSec: Int = conf.getInt("app.simulation.separationTimeInSec");

  val rampUsersFrom: Int = conf.getInt("app.simulation.users.rampFrom");
  val rampUsersTo: Int = conf.getInt("app.simulation.users.rampTo");

  val enableAssertion: Boolean = conf.getBoolean("app.simulation.enableAssertions");

  var warmingUpUri: String = conf.getString("app.warming-up-uri");

  val apiSpringUrl: String = conf.getString("app.api.spring.url");
  val apiQuarkusUrl: String = conf.getString("app.api.quarkus.url");


  val headersInTheRequest = Map(
      "Content-Type" -> "application/json",
      "Cache-Control" -> "no-cache"
  )

  val setHTTPHeaders = http.headers(headersInTheRequest)

  //val jsonFeeder = core.Predef.jsonFile("testdata.json")

//  var userInjection1 = incrementUsersPerSec(rampUsersArrivingPerSec)
//                          .times(times)
//                          .eachLevelLasting(timeFrameInSec seconds)
//                          .separatedByRampsLasting(separationTimeInSec seconds)
//                          .startingFrom(startingUsers)

  var userInjection = rampUsersPerSec(rampUsersFrom) to rampUsersTo during (timeFrameInSec seconds)
}
