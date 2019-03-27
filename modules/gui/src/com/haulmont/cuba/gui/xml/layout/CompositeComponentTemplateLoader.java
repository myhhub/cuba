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

import com.google.common.base.Strings;
import com.haulmont.bali.util.Dom4j;
import com.haulmont.cuba.core.global.DevelopmentException;
import com.haulmont.cuba.core.global.Resources;
import org.apache.commons.io.IOUtils;
import org.dom4j.Document;
import org.dom4j.Element;

import javax.annotation.Nullable;
import javax.inject.Inject;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@org.springframework.stereotype.Component(CompositeComponentTemplateLoader.NAME)
public class CompositeComponentTemplateLoader {

    public static final String NAME = "cuba_CompositeComponentTemplateLoader";

    @Inject
    protected CompositeComponentTemplateDocumentCache documentCache;

    @Inject
    protected Resources resources;


    public Element load(String templateString) {
        String loadedTemplate = loadTemplate(templateString);
        String template = loadedTemplate != null ? loadedTemplate : templateString;
        if (Strings.isNullOrEmpty(template)) {
            throw new DevelopmentException("Template is not found or empty");
        }
        Document document = getDocument(template);
        return document.getRootElement();
    }

    @Nullable
    protected String loadTemplate(String resourcePath) {
        try (InputStream stream = resources.getResourceAsStream(resourcePath)) {
            return stream != null ? IOUtils.toString(stream, StandardCharsets.UTF_8) : null;
        } catch (IOException e) {
            throw new RuntimeException("Unable to read component template");
        }
    }

    protected Document getDocument(String template) {
        Document document = documentCache.get(template);
        if (document == null) {
            document = createDocument(template);
            documentCache.put(template, document);
        }
        return document;
    }

    protected Document createDocument(String template) {
        return Dom4j.readDocument(template);
    }
}
