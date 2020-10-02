package eu.davidemartorana.performance.gatling.simulations

import eu.davidemartorana.performance.gatling.CommonSimulationConfig
import eu.davidemartorana.performance.gatling.simulations.WorkFlowSimulations.logger
import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation

class SpringSimulation extends Simulation {


  val workFlowSimulation = scenario("Simulating Workflow on Spring Application")
    .exec(session => {
      session.set("enableAssertions", CommonSimulationConfig.enableAssertion)

      logger.debug("==========================================================")
      logger.debug("====   Starting Retrieve Covid-19 Stats Simulation    ====")
      logger.debug("==========================================================")

      session
    })
    .exec(WorkFlowSimulations.commonWorkflowSimulation)

  setUp(
    this.workFlowSimulation
      .inject(
        CommonSimulationConfig.userInjection
      )
      .protocols(CommonSimulationConfig.setHTTPHeaders.baseUrl(CommonSimulationConfig.apiSpringUrl))
  )
}
