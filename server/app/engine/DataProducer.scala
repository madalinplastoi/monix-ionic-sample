package engine

import monifu.reactive.{Subscriber, Observable}
import monifu.concurrent.FutureUtils.delayedResult
import monifu.util.Random
import shared.models.Signal
import shared.models.Friend
import scala.concurrent.duration._

final class DataProducer(interval: FiniteDuration, seed: Long)
  extends Observable[Signal] {

  def onSubscribe(subscriber: Subscriber[Signal]): Unit =
    points.onSubscribe(subscriber)

  private val points = Observable.create[Signal] { subscriber =>
    import subscriber.{scheduler => s}

    val random = Observable
      .fromStateAction(Random.intInRange(-20, 20))(s.currentTimeMillis() + seed)
      .flatMap { x => delayedResult(interval)(x) }

    val generator = random.scan(Signal(Friend(0, "name-0", "", ""),s.currentTimeMillis())) {
      case (Signal(Friend(value,_,_,_), _), rnd) =>
        val next = value + rnd
        Signal(Friend(next, s"name-${next}","", ""), s.currentTimeMillis())
    }

    generator.drop(1)
      .onSubscribe(subscriber)
  }

  private case class State(x: Int, y: Int, ts: Long)
}
