/**
 * Copyright 2011-2017 GatlingCorp (http://gatling.io)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.gatling.http.action.ws2

import scala.concurrent.duration.FiniteDuration

case class WsCheckSequence(timeout: FiniteDuration, checks: List[WsCheck]) {
  require(checks.nonEmpty, "Can't pass empty check sequence")
  val head = checks.head
  val tail = checks.tail
}

case class WsCheck(name: String, matchConditions: List[WsTextCheck], checks: List[WsTextCheck]) {

  def matching(newMatchConditions: WsTextCheck*): WsCheck = copy(matchConditions = matchConditions ::: newMatchConditions.toList)

  def check(newChecks: WsTextCheck*): WsCheck = copy(checks = checks ::: newChecks.toList)
}
