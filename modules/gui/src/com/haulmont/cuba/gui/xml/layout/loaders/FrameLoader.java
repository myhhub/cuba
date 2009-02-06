/*
 * Copyright (c) 2008 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.

 * Author: Dmitry Abramov
 * Created: 19.12.2008 15:27:37
 * $Id$
 */
package com.haulmont.cuba.gui.xml.layout.loaders;

import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.IFrame;
import com.haulmont.cuba.gui.xml.layout.ComponentLoader;
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory;
import com.haulmont.cuba.gui.xml.layout.LayoutLoaderConfig;
import org.dom4j.Element;

import java.util.Locale;
import java.util.ResourceBundle;

public class FrameLoader extends ContainerLoader implements ComponentLoader {

    public FrameLoader(Context context, LayoutLoaderConfig config, ComponentsFactory factory) {
        super(context, config, factory);
    }

    public Component loadComponent(ComponentsFactory factory, Element element) throws InstantiationException, IllegalAccessException {
        final IFrame frame = factory.createComponent("iframe");

        assignXmlDescriptor(frame, element);
        loadId(frame, element);

        loadResourceBundle(frame, element);
        loadSubcomponentsAndExpand(frame, element.element("layout"));

        return frame;
    }

    protected void loadResourceBundle(IFrame frame, Element element) {
        final String resourceBundleName = element.attributeValue("resourceBundle");
        final ResourceBundle bundle;
        
        if (resourceBundleName != null) {
            Locale locale = getLocale();
            bundle = ResourceBundle.getBundle(resourceBundleName, locale);
        } else {
            bundle = null;
        }

        if (bundle != null) {
            frame.setResourceBundle(bundle);
            setResourceBundle(bundle);
        } else {
            frame.setResourceBundle(this.resourceBundle);
            setResourceBundle(this.resourceBundle);
        }
    }
}