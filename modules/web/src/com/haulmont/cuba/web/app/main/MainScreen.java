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

package com.haulmont.cuba.web.app.main;

import com.haulmont.cuba.core.global.Events;
import com.haulmont.cuba.core.global.FtsConfigHelper;
import com.haulmont.cuba.core.global.Messages;
import com.haulmont.cuba.gui.Route;
import com.haulmont.cuba.gui.ScreenTools;
import com.haulmont.cuba.gui.Screens;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.Image;
import com.haulmont.cuba.gui.components.ThemeResource;
import com.haulmont.cuba.gui.components.Window;
import com.haulmont.cuba.gui.components.dev.LayoutAnalyzerContextMenuProvider;
import com.haulmont.cuba.gui.components.mainwindow.AppMenu;
import com.haulmont.cuba.gui.components.mainwindow.AppWorkArea;
import com.haulmont.cuba.gui.components.mainwindow.FtsField;
import com.haulmont.cuba.gui.components.mainwindow.UserIndicator;
import com.haulmont.cuba.gui.events.UserRemovedEvent;
import com.haulmont.cuba.gui.events.UserSubstitutionsChangedEvent;
import com.haulmont.cuba.gui.screen.Screen;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;
import com.haulmont.cuba.web.WebConfig;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;

import javax.annotation.Nullable;
import javax.inject.Inject;

import static com.haulmont.cuba.gui.ComponentsHelper.walkComponents;

/**
 * Base class for a controller of application Main screen.
 */
@Route(path = "main", root = true)
@UiDescriptor("main-screen.xml")
@UiController("main")
public class MainScreen extends Screen implements Window.HasWorkArea, Window.HasUserIndicator {

    @Inject
    protected WebConfig webConfig;
    @Inject
    protected Messages messages;
    @Inject
    protected Screens screens;
    @Inject
    protected ScreenTools screenTools;

    protected AppMenu mainMenu;
    protected FtsField ftsField;
    protected Image logoImage;
    protected AppWorkArea workArea;
    protected UserIndicator userIndicator;

    public MainScreen() {
        addInitListener(this::initComponents);
    }

    protected void initComponents(@SuppressWarnings("unused") InitEvent e) {
        findComponents();

        initLogoImage();
        initFtsField();
        initLayoutAnalyzerContextMenu(logoImage);

        if (webConfig.getUseInverseHeader()) {
            Component titleBar = getWindow().getComponent("titleBar");
            if (titleBar != null) {
                titleBar.setStyleName("c-app-menubar c-inverse-header");
            }
        }

        if (mainMenu != null) {
            mainMenu.focus();
        }
    }

    protected void findComponents() {
        walkComponents(getWindow(), component -> {
            if (component instanceof AppMenu) {
                mainMenu = (AppMenu) component;
            } else if (component instanceof FtsField) {
                ftsField = ((FtsField) component);
            } else if (component instanceof Image && "logoImage".equals(component.getId())) {
                logoImage = ((Image) component);
            } else if (component instanceof AppWorkArea) {
                workArea = (AppWorkArea) component;
            } else if (component instanceof UserIndicator) {
                userIndicator = (UserIndicator) component;
            }
            return false;
        });
    }

    protected void initLogoImage() {
        String logoImagePath = messages.getMainMessage("application.logoImage");
        if (logoImage != null
                && StringUtils.isNotBlank(logoImagePath)
                && !"application.logoImage".equals(logoImagePath)) {
            logoImage.setSource(ThemeResource.class).setPath(logoImagePath);
        }
    }

    protected void initFtsField() {
        if (ftsField != null
                && !FtsConfigHelper.getEnabled()) {
            ftsField.setVisible(false);
        }
    }

    protected void initLayoutAnalyzerContextMenu(Component contextMenuTarget) {
        if (contextMenuTarget != null) {
            LayoutAnalyzerContextMenuProvider laContextMenuProvider =
                    getBeanLocator().get(LayoutAnalyzerContextMenuProvider.NAME);
            laContextMenuProvider.initContextMenu(getWindow(), contextMenuTarget);
        }
    }

    @Order(Events.LOWEST_PLATFORM_PRECEDENCE - 100)
    @EventListener
    protected void onUserSubstitutionsChange(UserSubstitutionsChangedEvent event) {
        if (userIndicator != null) {
            userIndicator.refreshUserSubstitutions();
        }
    }

    @Order(Events.LOWEST_PLATFORM_PRECEDENCE - 100)
    @EventListener
    protected void onUserRemove(UserRemovedEvent event) {
        if (userIndicator != null) {
            userIndicator.refreshUserSubstitutions();
        }
    }

    @Subscribe
    protected void onAfterShow(AfterShowEvent event) {
        screenTools.openDefaultScreen(screens);
    }

    @Nullable
    @Override
    public AppWorkArea getWorkArea() {
        return workArea;
    }

    @Nullable
    @Override
    public UserIndicator getUserIndicator() {
        return userIndicator;
    }
}
