import sbt._
import sbt.Keys._
import scoverage.ScoverageSbtPlugin.instrumentSettings
import CoverallsPlugin.coverallsSettings

object Finch extends Build {

  val baseSettings = Defaults.defaultSettings ++ Seq(
    libraryDependencies ++= Seq(
      "com.twitter" %% "finagle-http" % "6.22.0",
      "org.scalatest" % "scalatest_2.10" % "2.2.1" % "test"
    ),
    scalacOptions ++= Seq( "-unchecked", "-deprecation", "-feature")
  )

  lazy val buildSettings = Seq(
    organization := "io",
    version := "0.1.8",
    scalaVersion := "2.10.4"
  )

  lazy val publishSettings = Seq(
    publishMavenStyle := true,
    publishArtifact := true,
    publishTo := Some(Resolver.file("localDirectory", file(Path.userHome.absolutePath + "/repo"))),
    licenses := Seq("Apache 2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0")),
    homepage := Some(url("https://github.com/finagle/finch")),
    pomExtra := (
      <scm>
        <url>git://github.com/finagle/finch.git</url>
        <connection>scm:git://github.com/finagle/finch.git</connection>
      </scm>
      <developers>
        <developer>
          <id>vkostyukov</id>
          <name>Vladimir Kostyukov</name>
          <url>http://vkostyukov.ru</url>
        </developer>
      </developers>
    )
  )

  lazy val root = Project(id = "finch",
    base = file("."),
    settings = baseSettings ++ buildSettings ++ publishSettings ++ instrumentSettings ++ coverallsSettings)
}
