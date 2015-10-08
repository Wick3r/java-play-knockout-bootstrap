name := "java-play-2"

version := "1.0"

lazy val `java-play-2` = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq( javaJdbc , javaEbean , cache , javaWs )

libraryDependencies ++= Seq(
  // WebJars pull in client-side web libraries
  "org.webjars" %% "webjars-play" % "2.3.0",
  "org.webjars" % "bootstrap" % "3.0.0",
  "org.webjars" % "knockout" % "3.0.0",
  "org.webjars" % "requirejs" % "2.1.8"
  // Add your own project dependencies in the form:
  // "group" % "artifact" % "version"
)

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )  