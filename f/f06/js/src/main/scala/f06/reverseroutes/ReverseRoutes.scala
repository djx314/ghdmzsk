package f06.reverseroutes

import f06.endpoint.NumberEndpoint

class ReverseRoutes extends ReverseRoutesPre {
  override def numberEndpoint: NumberEndpoint = new NumberEndpoint
}
