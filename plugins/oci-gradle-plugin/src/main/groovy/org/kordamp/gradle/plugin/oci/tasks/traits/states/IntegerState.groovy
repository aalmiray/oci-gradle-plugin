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
package org.kordamp.gradle.plugin.oci.tasks.traits.states

import groovy.transform.CompileStatic
import org.gradle.api.Project
import org.gradle.api.provider.Property
import org.gradle.api.provider.Provider

import static org.kordamp.gradle.PropertyUtils.integerProvider

/**
 * @author Andres Almiray
 * @since 0.3.0
 */
@CompileStatic
class IntegerState {
    final Property<Integer> property
    final Provider<Integer> provider

    IntegerState(Project project, String envKey, String propertyKey) {
        property = project.objects.property(Integer)

        provider = integerProvider(
            envKey,
            propertyKey,
            property,
            project)
    }
}