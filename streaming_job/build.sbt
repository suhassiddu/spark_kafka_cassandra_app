name := "streaming_job"

val sparkVersion = "2.1.1"
val sparkDependencies = Seq(
	"org.apache.spark" %% "spark-core" % sparkVersion,
	"org.apache.spark" %% "spark-hive" % sparkVersion, 
	"org.apache.spark" %% "spark-streaming" % sparkVersion,
	"org.apache.spark" %% "spark-streaming-kafka-0-8" % sparkVersion,
	"org.apache.spark" %% "spark-mllib" % sparkVersion
	// "org.apache.spark" %% "spark-streaming-twitter" % sparkVersion
)

libraryDependencies ++= Seq(
	"org.apache.spark" %% "spark-core" % sparkVersion,
	"org.apache.spark" %% "spark-sql" % sparkVersion,
	"org.apache.spark" %% "spark-streaming" % sparkVersion,
	"org.apache.spark" %% "spark-streaming-kafka-0-8" % sparkVersion,
	"org.apache.spark" %% "spark-mllib" % sparkVersion,
	// "org.apache.spark" %% "spark-streaming-twitter" % sparkVersion,

	"com.typesafe" % "config" % "1.3.1",
	"joda-time" % "joda-time" % "2.9.9",
	"org.joda" % "joda-convert" % "1.9.2",

	"com.datastax.spark" % "spark-cassandra-connector_2.11" % "1.6.1",
	"com.datastax.cassandra" % "cassandra-driver-core" % "3.0.1",
	"com.github.nscala-time" % "nscala-time_2.11" % "2.6.0",
	"org.slf4j" % "slf4j-api" % "1.7.7",
	"com.twitter" % "chill_2.11" % "0.7.2",
	"com.twitter" % "algebird-core_2.11" % "0.11.0",

	"org.apache.kafka" % "kafka-clients" % "0.8.2.1",
	"org.apache.kafka" % "kafka_2.11" % "0.8.2.1"
)

libraryDependencies ++= sparkDependencies.map(_ % "provided")

lazy val commonSettings = Seq(
  scalaVersion := "2.11.8",
  resolvers += Resolver.mavenLocal,
  resolvers += Resolver.typesafeRepo("releases")
)

commonSettings

outputStrategy := Some(StdoutOutput)

run in Compile <<= Defaults.runTask(fullClasspath in Compile, mainClass in (Compile, run), runner in (Compile, run))

//update your Intellij configuration to use `mainRunner` when running from Intellij (not the default)
lazy val mainRunner = project.in(file("mainRunner")).dependsOn(RootProject(file("."))).settings(
  commonSettings,
  libraryDependencies ++= sparkDependencies.map(_ % "compile"),
  assembly := new File(""),
  publish := {},
  publishLocal := {}
)