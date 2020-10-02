package eu.davidemartorana.performance.gatling.simulations

import java.util.concurrent.TimeUnit

import eu.davidemartorana.performance.gatling.CommonSimulationConfig
import eu.davidemartorana.performance.gatling.workflows.{FormatMessage, RetrieveCovid19Data}
import io.gatling.core.Predef._
import org.slf4j.{Logger, LoggerFactory}

object WorkFlowSimulations {

  val logger: Logger = LoggerFactory.getLogger(this.getClass);

  val commonWorkflowSimulation = exec(RetrieveCovid19Data.workflowRetrieveOneRandomly)
    .exec(RetrieveCovid19Data.workflowRetrieveFrom)
    .exec(RetrieveCovid19Data.workflowRetrieveTo)
    .exec(RetrieveCovid19Data.workflowRetrieveBetween)
    .pause("2", TimeUnit.SECONDS)
    .exec(session => {

      logger.debug("==========================================================")
      logger.debug("====   Starting Formatting Message Body Simulation    ====")
      logger.debug("==========================================================")

      session.set("enableAssertions", CommonSimulationConfig.enableAssertion)
    })
    .exec(FormatMessage.formatMessageFromRandomValues)
}
