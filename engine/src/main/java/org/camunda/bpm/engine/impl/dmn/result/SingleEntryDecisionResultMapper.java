/*
 * Copyright © 2012 - 2018 camunda services GmbH and various authors (info@camunda.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.camunda.bpm.engine.impl.dmn.result;

import org.camunda.bpm.dmn.engine.DmnDecisionResult;
import org.camunda.bpm.dmn.engine.DmnEngineException;
import org.camunda.bpm.engine.impl.ProcessEngineLogger;
import org.camunda.bpm.engine.impl.dmn.DecisionLogger;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.TypedValue;

/**
 * Maps the decision result to a single typed entry.
 *
 * @author Philipp Ossler
 */
public class SingleEntryDecisionResultMapper implements DecisionResultMapper {

  protected static final DecisionLogger LOG = ProcessEngineLogger.DECISION_LOGGER;

  @Override
  public Object mapDecisionResult(DmnDecisionResult decisionResult) {
    try {
      TypedValue typedValue = decisionResult.getSingleEntryTyped();
      if (typedValue != null) {
        return typedValue;
      }
      else {
        return Variables.untypedNullValue();
      }
    } catch (DmnEngineException e) {
      throw LOG.decisionResultMappingException(decisionResult, this, e);
    }
  }

  @Override
  public String toString() {
    return "SingleEntryDecisionResultMapper{}";
  }

}
