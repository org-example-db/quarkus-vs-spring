package eu.davidemartorana.performance.gatling

import io.gatling.recorder.GatlingRecorder
import io.gatling.recorder.config.RecorderPropertiesBuilder

object Recorder extends App {

  val props = new RecorderPropertiesBuilder
  //props.simulationOutputFolder(IDEPathHelper.recorderOutputDirectory.toString)
  props.simulationPackage("eu.davidemartorana.performance.gatling.workflows")
  props.simulationsFolder(IDEPathHelper.recorderDirectory.toString)
  //props.bodiesFolder(IDEPathHelper.bodiesDirectory.toString)

  GatlingRecorder.fromMap(props.build, Some(IDEPathHelper.recorderConfigFile))
}
