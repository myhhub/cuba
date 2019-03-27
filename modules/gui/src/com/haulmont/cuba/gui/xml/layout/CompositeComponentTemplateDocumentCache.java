/*
 * Copyright (c) 2008-2019 Haulmont.
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

package com.haulmont.cuba.gui.xml.layout;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.dom4j.Document;

@org.springframework.stereotype.Component(CompositeComponentTemplateDocumentCache.NAME)
public class CompositeComponentTemplateDocumentCache {

    public static final String NAME = "cuba_CompositeComponentTemplateDocumentCache";

    protected Cache<String, Document> cache;

    public CompositeComponentTemplateDocumentCache() {
        this(100);
    }

    public CompositeComponentTemplateDocumentCache(int cacheDescriptorsCount) {
        cache = CacheBuilder.newBuilder().maximumSize(cacheDescriptorsCount).build();
    }

    public void put(String xml, Document document) {
        cache.put(xml, document);
    }

    public Document get(String xml) {
        return cache.getIfPresent(xml);
    }

    public void invalidateAll() {
        cache.invalidateAll();
    }
}
