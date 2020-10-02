package eu.davidemartorana.performance.gatling.simulations

import eu.davidemartorana.performance.gatling.CommonSimulationConfig
import eu.davidemartorana.performance.gatling.simulations.WorkFlowSimulations.logger
import io.gatling.core.Predef._

class QuarkusSimulation extends Simulation{

  val workFlowSimulations = scenario("Simulating Workflow on Quarkus Application")
    .exec(session => {
      session.set("enableAssertions", CommonSimulationConfig.enableAssertion)

      logger.debug("==========================================================")
      logger.debug("====   Starting Retrieve Covid-19 Stats Simulation    ====")
      logger.debug("==========================================================")

      session
    })
    .exec(WorkFlowSimulations.commonWorkflowSimulation)


  setUp(
    this.workFlowSimulations
      .inject(
        CommonSimulationConfig.userInjection
      )
      .protocols(CommonSimulationConfig.setHTTPHeaders.baseUrl(CommonSimulationConfig.apiQuarkusUrl))
  )

}
