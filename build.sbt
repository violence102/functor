name := "functor"

version := "0.1"

scalaVersion := "2.13.1"

libraryDependencies += "org.typelevel" %% "simulacrum" % "1.0.0"
libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.8"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.8" % "test"
libraryDependencies += "org.typelevel" %% "cats-core" % "2.0.0"

scalacOptions += "-Ymacro-annotations"
