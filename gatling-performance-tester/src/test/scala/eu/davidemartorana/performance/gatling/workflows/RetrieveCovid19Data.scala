package eu.davidemartorana.performance.gatling.workflows

import com.typesafe.scalalogging.StrictLogging
import io.gatling.core.Predef._
import eu.davidemartorana.performance.gatling.requests._
import eu.davidemartorana.performance.gatling.FunctionsHelper;

object RetrieveCovid19Data extends StrictLogging {

  val workflowRetrieveOneRandomly =
    tryMax(3) (
      exec(Covid19Endpoints.retrieveAllCovidCasesAndSelectOneRandomly)
      .exec( session => {
            logger.debug("Selected stats ID: '{}'", session("randomStatsId").as[String])
            session
          })
      .exec( session => {
          session
            .set("statsId", session("randomStatsId").as[String])
        })
      .exec(Covid19Endpoints.retrieveById)
      .exec(session => {
          logger.debug("Retrieved Stats Body: {}", session("covid19StatsBodyResponse").as[String])
          session
      })
    );

  val workflowRetrieveFrom =
    tryMax(3) (
      exec(RandomsEndpoints.retrieveRandomDate)
      .exec(session => {
        session.set("startDate", session("randomDate").as[String].substring(0,10))
      })
      .exec(Covid19Endpoints.retrieveWithStartDate)
    )


  val workflowRetrieveTo =
    tryMax(3) (
      exec(RandomsEndpoints.retrieveRandomDate)
      .exec(session => {
        session.set("endDate", session("randomDate").as[String].substring(0,10))
      })
      .exec(Covid19Endpoints.retrieveWithEndDate)
    );

  val workflowRetrieveBetween =
    tryMax(3) (
      exec(RandomsEndpoints.retrieveRandomDate)
      .exec(session => {
        session.set("firstDate", session("randomDate").as[String].substring(0,10))
      })
      .exec(RandomsEndpoints.retrieveRandomDate)
      .exec(session => {
        session.set("secondDate", session("randomDate").as[String].substring(0,10))
      })
      .exec(session => {

        val first: String  = session("firstDate").as[String]
        val second: String = session("secondDate").as[String]

        val startDate: String = FunctionsHelper.getMaximum(first,second);
        val endDate: String = FunctionsHelper.getMinimum(first, second);

        session
          .set("startDate", startDate)
          .set("endDate", endDate)
      })
      .exec(Covid19Endpoints.retrieveWithinRangeDate)
      .exec(session => {
        session
          .removeAll("endDate", "randomDate", "firstDate", "secondDate", "startDate", "endDate")
      })
    );



}
