package eu.davidemartorana.performance.gatling

import java.nio.file.{Path, Paths}

import io.gatling.commons.util.PathHelper._

object IDEPathHelper {
  val gatlingConfUrl: Path = Paths.get(getClass.getClassLoader.getResource("gatling.conf").toURI)
  val projectRootDir = gatlingConfUrl.ancestor(3)

  val mavenSourcesDirectory = projectRootDir / "src" / "src/test/scala/test" / "scala"
  val mavenResourcesDirectory = projectRootDir / "src" / "src/test/scala/test" / "resources"
  val mavenTargetDirectory = projectRootDir / "target"
  val mavenBinariesDirectory = mavenTargetDirectory / "test-classes"

  val dataDirectory = mavenResourcesDirectory / "data"
  val bodiesDirectory = mavenResourcesDirectory / "bodies"

  val recorderDirectory = mavenSourcesDirectory
  val resultsDirectory = mavenTargetDirectory / "gatling"

  val recorderConfigFile = mavenResourcesDirectory / "recorder.conf"

}
