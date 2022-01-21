package f06.reverseroutes

import f06.endpoint.{CounterEndpoint, NumberEndpoint}

class ReverseRoutes extends ReverseRoutesPre {
  override def numberEndpoint: NumberEndpoint   = new NumberEndpoint
  override def counterEndpoint: CounterEndpoint = new CounterEndpoint
}
