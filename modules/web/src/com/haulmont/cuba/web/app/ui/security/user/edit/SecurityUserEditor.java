/*
 * Copyright (c) 2008 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.

 * Author: Dmitry Abramov
 * Created: 05.02.2009 13:35:20
 * $Id$
 */
package com.haulmont.cuba.web.app.ui.security.user.edit;

import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.security.entity.User;

import java.util.Collection;

public class SecurityUserEditor extends AbstractEditor {
    public SecurityUserEditor(Window frame) {
        super(frame);
    }

    protected void init() {
        Button button = getComponent("browse");
        button.setAction(new AbstractAction("Browse") {
            public String getCaption() {
                return "Browse...";
            }

            public boolean isEnabled() {
                return true;
            }

            public void actionPerform(Component component) {
                openLookup("/com/haulmont/cuba/web/app/ui/security/user/browse/security-user-browse.xml", new Lookup.Handler() {
                    public void handleLookup(Collection items) {
                        if (items.size() == 1) {
                            final User item = (User) items.iterator().next();
                            final Field field = getComponent("name");
                            field.setValue(item.getName());
                        }
                    }
                }, WindowManager.OpenType.THIS_TAB);
            }
        });
    }
}
