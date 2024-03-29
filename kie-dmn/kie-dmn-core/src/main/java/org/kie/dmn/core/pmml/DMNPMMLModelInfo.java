/*
 * Copyright 2019 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.kie.dmn.core.pmml;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.kie.dmn.api.core.DMNType;
import org.kie.dmn.core.impl.DMNModelImpl;

public class DMNPMMLModelInfo extends PMMLModelInfo {

    private final Map<String, DMNType> inputFields;

    public DMNPMMLModelInfo(String name, Map<String, DMNType> inputFields, Collection<String> outputFields) {
        super(name, inputFields.keySet(), outputFields);
        this.inputFields = Collections.unmodifiableMap(new HashMap<>(inputFields));
    }

    public static DMNPMMLModelInfo from(PMMLModelInfo info, DMNModelImpl model) {
        Map<String, DMNType> inputFields = new HashMap<>();
        for (String name : info.inputFieldNames) {
            inputFields.put(name, model.getTypeRegistry().unknown());
        }
        return new DMNPMMLModelInfo(info.name, inputFields, info.outputFieldNames);
    }

    public Map<String, DMNType> getInputFields() {
        return inputFields;
    }

}