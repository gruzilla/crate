/*
 * Licensed to Crate under one or more contributor license agreements.
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.  Crate licenses this file
 * to you under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.  You may
 * obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.  See the License for the specific language governing
 * permissions and limitations under the License.
 *
 * However, if you have executed another commercial license agreement
 * with Crate these terms will supersede the license and you may use the
 * software solely pursuant to the terms of the relevant commercial
 * agreement.
 */

package io.crate.planner.operators;

import io.crate.analyze.relations.AbstractTableRelation;
import io.crate.expression.symbol.SelectSymbol;
import io.crate.expression.symbol.Symbol;

import java.util.List;
import java.util.Map;

/**
 * The abstract base for all {@link LogicalPlan}s.
 *
 * For now, this just reduces the amount of boiler plate code each instance
 * has to implement. Generally, this is an extension point where functionality
 * resides which is used by all plans.
 */
abstract class LogicalPlanBase implements LogicalPlan {

    protected final List<Symbol> outputs;
    protected final Map<Symbol, Symbol> expressionMapping;
    protected final List<AbstractTableRelation> baseTables;
    protected final Map<LogicalPlan, SelectSymbol> dependencies;

    LogicalPlanBase(List<Symbol> outputs,
                    Map<Symbol, Symbol> expressionMapping,
                    List<AbstractTableRelation> baseTables,
                    Map<LogicalPlan, SelectSymbol> dependencies) {
        this.outputs = outputs;
        this.expressionMapping = expressionMapping;
        this.baseTables = baseTables;
        this.dependencies = dependencies;
    }

    @Override
    public List<Symbol> outputs() {
        return outputs;
    }

    @Override
    public Map<Symbol, Symbol> expressionMapping() {
        return expressionMapping;
    }

    @Override
    public List<AbstractTableRelation> baseTables() {
        return baseTables;
    }

    @Override
    public Map<LogicalPlan, SelectSymbol> dependencies() {
        return dependencies;
    }
}
