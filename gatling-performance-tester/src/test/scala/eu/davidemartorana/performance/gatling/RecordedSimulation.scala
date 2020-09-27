package eu.davidemartorana.performance.gatling

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class RecordedSimulation extends Simulation {

	val httpProtocol = http
		.baseUrl("http://localhost:8080")
		.proxy(Proxy("localhost", 8080))
		.inferHtmlResources()
		.acceptHeader("*/*")
		.acceptEncodingHeader("gzip, deflate")
		.userAgentHeader("PostmanRuntime/7.26.5")


	val scn = scenario("RecordedSimulation")
		.exec(http("request_0")
			.get("/api/covid19/italy")
		.exec(http("request_1")
			.get("/api/covid19/italy/190")
		.exec(http("request_2")
			.get("/api/covid19/italy/200")
			.check(status.is(404)))
		.exec(http("request_3")
			.get("/api/covid19/italy/125")
		.exec(http("request_4")
			.get("/api/covid19/italy?start=2020-02-01")
		.pause(4)
		.exec(http("request_5")
			.get("/api/covid19/italy?start=2020-06-01")
		.pause(15)
		.exec(http("request_6")
			.get("/api/covid19/italy?start=2020-06-01&end=2020-06-30")
		.pause(22)
		.exec(http("request_7")
			.get("/api/covid19/italy?start=2020-06-01&end=2020-07-01")
		.pause(59)
		.exec(http("request_8")
			.get("/api/covid19/italy?start=2020-07-01&end=2020-07-31")
		.pause(5)
		.exec(http("request_9")
			.get("/api/covid19/italy?start=2020-07-01&end=2020-07-31")
		.pause(10)
		.exec(http("request_10")
			.get("/api/covid19/italy?start=2020-08-01&end=2020-08-31")
		.pause(23)
		.exec(http("request_11")
			.get("/api/covid19/italy?start=2020-09-01&end=2020-09-30")
		.pause(21)
		.exec(http("request_12")
			.get("/api/random/time")
		.pause(3)
		.exec(http("request_13")
			.get("/api/random/time")
		.pause(1)
		.exec(http("request_14")
			.get("/api/random/time")
			.resources(http("request_15")
			.get("/api/random/time")
		.pause(53)
		.exec(http("request_16")
			.get("/api/random/description")
		.pause(1)
		.exec(http("request_17")
			.get("/api/random/description")
		.pause(1)
		.exec(http("request_18")
			.get("/api/random/description")
		.pause(4)
		.exec(http("request_19")
			.get("/api/random/time")
		.pause(60)
		.exec(http("request_20")
			.post("/api/random/format")
			.body(RawFileBody("eu/davidemartorana/performance/gatling/recordedsimulation/random-format-request.tmpl.json")))
		.pause(1)
		.exec(http("request_21")
			.post("/api/random/format")
			.body(RawFileBody("eu/davidemartorana/performance/gatling/recordedsimulation/0021_request.json")))
		.pause(1)
		.exec(http("request_22")
			.post("/api/random/format")
			.body(RawFileBody("eu/davidemartorana/performance/gatling/recordedsimulation/0022_request.json"))
			.resources(http("request_23")
			.post("/api/random/format")
			.body(RawFileBody("eu/davidemartorana/performance/gatling/recordedsimulation/0023_request.json")),
            http("request_24")
			.post("/api/random/format")
			.body(RawFileBody("eu/davidemartorana/performance/gatling/recordedsimulation/0024_request.json")),
            http("request_25")
			.post("/api/random/format")
			.body(RawFileBody("eu/davidemartorana/performance/gatling/recordedsimulation/0025_request.json")),
            http("request_26")
			.post("/api/random/format")
			.body(RawFileBody("eu/davidemartorana/performance/gatling/recordedsimulation/0026_request.json")),
            http("request_27")
			.post("/api/random/format")
			.body(RawFileBody("eu/davidemartorana/performance/gatling/recordedsimulation/0027_request.json")),
            http("request_28")
			.post("/api/random/format")
			.body(RawFileBody("eu/davidemartorana/performance/gatling/recordedsimulation/0028_request.json")),
            http("request_29")
			.post("/api/random/format")
			.body(RawFileBody("eu/davidemartorana/performance/gatling/recordedsimulation/0029_request.json")),
            http("request_30")
			.post("/api/random/format")
			.body(RawFileBody("eu/davidemartorana/performance/gatling/recordedsimulation/0030_request.json")),
            http("request_31")
			.post("/api/random/format")
			.body(RawFileBody("eu/davidemartorana/performance/gatling/recordedsimulation/0031_request.json")),
            http("request_32")
			.post("/api/random/format")
			.body(RawFileBody("eu/davidemartorana/performance/gatling/recordedsimulation/0032_request.json")),
            http("request_33")
			.post("/api/random/format")
			.body(RawFileBody("eu/davidemartorana/performance/gatling/recordedsimulation/0033_request.json"))))

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}
