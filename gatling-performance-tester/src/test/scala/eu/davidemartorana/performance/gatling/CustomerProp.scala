package eu.davidemartorana.performance.gatling

import com.typesafe.config.{Config, ConfigFactory}

object CustomerProp {

  val conf: Config = ConfigFactory.load()

  val email: String = conf.getString("app.customer.email");
  val password: String = conf.getString("app.customer.password")
  val name: String = conf.getString("app.customer.name");
  val lastName: String = conf.getString("app.customer.lastname");

}
