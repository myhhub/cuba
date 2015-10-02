/*
 * Copyright (c) 2008-2013 Haulmont. All rights reserved.
 * Use is subject to license terms, see http://www.cuba-platform.com/license for details.
 */

package com.haulmont.cuba.gui.xml.layout.loaders;

import com.haulmont.cuba.gui.components.RichTextArea;

/**
 * @author artamonov
 * @version $Id$
 */
public class RichTextAreaLoader extends AbstractTextFieldLoader<RichTextArea> {
    @Override
    public void createComponent() {
        resultComponent = (RichTextArea) factory.createComponent(RichTextArea.NAME);
        loadId(resultComponent, element);
    }
}