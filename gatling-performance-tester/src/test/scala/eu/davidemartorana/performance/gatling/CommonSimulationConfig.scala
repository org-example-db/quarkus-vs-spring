package eu.davidemartorana.performance.gatling

import com.typesafe.config.ConfigFactory
import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration.DurationInt

object CommonSimulationConfig  {

  val conf = ConfigFactory.load()

  val customer = CustomerProp;

  val times: Int = conf.getInt("app.simulation.times");
  val timeFrameInSec: Int = conf.getInt("app.simulation.timeFrameInSec");
  val separationTimeInSec: Int = conf.getInt("app.simulation.separationTimeInSec");

  val startingUsers: Int = conf.getInt("app.simulation.users.starting");
  val rampUsersArrivingPerSec: Int = conf.getInt("app.simulation.users.ramp");

  val enableAssertion: Boolean = conf.getBoolean("app.simulation.enableAssertions");

  var warmingUpUri: String = conf.getString("app.warming-up-uri");

  **val experienceLayerUrl: String = conf.getString("app.layers.experience.url");

  **val businessLayerUrl: String = conf.getString("app.layers.business.url");

  **val authenticationHeader: String = conf.getString("app.headers.authorization");


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

  var userInjection = rampUsersPerSec(2) to 5 during (5 minutes) randomized
}
