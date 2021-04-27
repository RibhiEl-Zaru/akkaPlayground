package rezonation.akka
package org.rezonation.akka.patterns

import akka.actor.ActorSystem
import akka.pattern.Patterns.after
import com.typesafe.scalalogging.LazyLogging

import java.util.concurrent.Callable
import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scala.util.Random

object DelayCallableDemo extends App with LazyLogging{


  val as = ActorSystem()
  implicit val executionContext = scala.concurrent.ExecutionContext.global



  logger.info("Often times in programming, we want to delay the execution of a callable function.\n" +
    "However, built in protocols like Thread.sleep(x) are bad because they stop the whole world.\n" +
    "Instead, we can leverage Akka's built in schedule to have a thread safe delay, demonstrated below.\n" +
    "Note that the hello log continues to happen while we wait for the integer to get logged.\n\n")

  val callable: Callable[Future[Int]] = () => Future.successful(Random.nextInt())



  var stop = false

  val integerDuration = 2.second
  val yaberDuration = 500.millis

  logger.info(s"In $integerDuration from now you will see the randomly generated integer")

  after(integerDuration, as.scheduler, executionContext, callable).map(result => {
    logger.info(s"$result")
    stop = true
  })

  while(!stop){
    Await.result(after(yaberDuration, as.scheduler, executionContext, () => {
      Future.successful({
        if(!stop) logger.info("hello")
        else {
          logger.info("goodbye")
          as.terminate().map(_ => System.exit(0))
        }
      })
    }), Duration.Inf)
  }
}
