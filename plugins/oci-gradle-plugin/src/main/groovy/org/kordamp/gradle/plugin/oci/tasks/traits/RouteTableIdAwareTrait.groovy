/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2019-2020 Andres Almiray.
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
package org.kordamp.gradle.plugin.oci.tasks.traits

import com.oracle.bmc.OCID
import groovy.transform.CompileStatic
import org.gradle.api.provider.Property
import org.gradle.api.provider.Provider
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.options.Option
import org.kordamp.gradle.plugin.oci.tasks.interfaces.PathAware
import org.kordamp.gradle.plugin.oci.tasks.interfaces.ProjectAware
import org.kordamp.gradle.plugin.oci.tasks.traits.states.StringState

import static org.kordamp.gradle.StringUtils.isBlank

/**
 * @author Andres Almiray
 * @since 0.1.0
 */
@CompileStatic
trait RouteTableIdAwareTrait implements PathAware, ProjectAware {
    private final StringState state = new StringState(project, 'OCI_ROUTE_TABLE_ID', 'oci.route.table.id')

    @Internal
    Property<String> getRouteTableId() {
        state.property
    }

    @Input
    Provider<String> getResolvedRouteTableId() {
        state.provider
    }

    @Option(option = 'route-table-id', description = 'The id of the RouteTable (REQUIRED).')
    void setRouteTableId(String routeTableId) {
        getRouteTableId().set(routeTableId)
    }

    void validateRouteTableId() {
        if (isBlank(getResolvedRouteTableId().orNull)) {
            throw new IllegalStateException("Missing value for 'routeTableId' in $path")
        }
        if (!OCID.isValid(getResolvedRouteTableId().get())) {
            throw new IllegalStateException("RouteTable id '${getResolvedRouteTableId().get()}' is invalid")
        }
    }
}
