package eu.davidemartorana.performance.gatling.workflows

import com.typesafe.scalalogging.StrictLogging
import eu.davidemartorana.performance.gatling.requests.RandomsEndpoints
import io.gatling.core.Predef._

object FormatMessage extends StrictLogging {

  val formatMessageFromRandomValues =
    tryMax(3)(
        exec(RandomsEndpoints.retrieveRandomDate)
        .exec(
          session => {
            session
              .set("random-time", session("randomDate").as[String])
          }
        )
        .exec(RandomsEndpoints.retrieveRandomDescription)
        .exec(
          session => {
            session
              .set("random-description", session("randomDescription").as[String])

          }
        )
        .exec(session => {
          logger.debug("Random time:[{}] ", session("random-time").as[String])
          logger.debug("Random description:[{}] ", session("random-description").as[String])

          session
        })
        .exec(RandomsEndpoints.reformatRandomly)
        .exec(session => {
          logger.debug("Formatted body First time - result:\n{} ", session("formattedBody").as[String])
          session
        })
        .exec(RandomsEndpoints.reformatRandomly)
        .exec(session => {
          logger.debug("Formatted body Second time - result:\n{} ", session("formattedBody").as[String])
          session
        })
    )
    .exec(session => {
      session
        .removeAll("random-time", "randomDate", "randomDescription", "random-description", "formattedBody")
    })

}
