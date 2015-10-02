/*
 * Copyright (c) 2008-2013 Haulmont. All rights reserved.
 * Use is subject to license terms, see http://www.cuba-platform.com/license for details.
 */
package com.haulmont.cuba.gui.xml.layout.loaders;

import com.haulmont.cuba.gui.components.Table;

/**
 * @author abramov
 * @version $Id$
 */
public class TableLoader extends AbstractTableLoader<Table> {
    @Override
    public void createComponent() {
        resultComponent = (Table) factory.createComponent(Table.NAME);
        loadId(resultComponent, element);
        createButtonsPanel(resultComponent, element);
    }
}