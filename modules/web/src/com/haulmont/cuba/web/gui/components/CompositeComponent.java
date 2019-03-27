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

package com.haulmont.cuba.web.gui.components;

import com.google.common.base.Preconditions;
import com.haulmont.bali.events.EventHub;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.SizeUnit;

import java.util.EventObject;

public class CompositeComponent<T extends Component> implements Component {

    protected T root;

    // private, lazily initialized
    private EventHub eventHub = null;

    protected EventHub getEventHub() {
        if (eventHub == null) {
            eventHub = new EventHub();
        }
        return eventHub;
    }

    public T getComposition() {
        return root;
    }

    protected T getCompositionNN() {
        Preconditions.checkState(root != null, "Composition root is not initialized");
        return root;
    }

    protected void setComposition(T composition) {
        Preconditions.checkState(root == null, "Composition root is already initialized");
        this.root = composition;
    }

    protected <E> void fireEvent(Class<E> eventType, E event) {
        getEventHub().publish(eventType, event);
    }

    @Override
    public String getId() {
        return getCompositionNN().getId();
    }

    @Override
    public void setId(String id) {
        getCompositionNN().setId(id);
    }

    @Override
    public Component getParent() {
        return getCompositionNN().getParent();
    }

    @Override
    public void setParent(Component parent) {
        getCompositionNN().setParent(parent);
    }

    @Override
    public boolean isEnabled() {
        return getCompositionNN().isEnabled();
    }

    @Override
    public void setEnabled(boolean enabled) {
        getCompositionNN().setEnabled(enabled);
    }

    @Override
    public boolean isResponsive() {
        return getCompositionNN().isResponsive();
    }

    @Override
    public void setResponsive(boolean responsive) {
        getCompositionNN().setResponsive(responsive);
    }

    @Override
    public boolean isVisible() {
        return getCompositionNN().isVisible();
    }

    @Override
    public void setVisible(boolean visible) {
        getCompositionNN().setVisible(visible);
    }

    @Override
    public boolean isVisibleRecursive() {
        return getCompositionNN().isVisibleRecursive();
    }

    @Override
    public boolean isEnabledRecursive() {
        return getCompositionNN().isEnabledRecursive();
    }

    @Override
    public float getHeight() {
        return getCompositionNN().getHeight();
    }

    @Override
    public SizeUnit getHeightSizeUnit() {
        return getCompositionNN().getHeightSizeUnit();
    }

    @Override
    public void setHeight(String height) {
        getCompositionNN().setHeight(height);
    }

    @Override
    public float getWidth() {
        return getCompositionNN().getWidth();
    }

    @Override
    public SizeUnit getWidthSizeUnit() {
        return getCompositionNN().getWidthSizeUnit();
    }

    @Override
    public void setWidth(String width) {
        getCompositionNN().setWidth(width);
    }

    @Override
    public Alignment getAlignment() {
        return getCompositionNN().getAlignment();
    }

    @Override
    public void setAlignment(Alignment alignment) {
        getCompositionNN().setAlignment(alignment);
    }

    @Override
    public String getStyleName() {
        return getCompositionNN().getStyleName();
    }

    @Override
    public void setStyleName(String styleName) {
        getCompositionNN().setStyleName(styleName);
    }

    @Override
    public void addStyleName(String styleName) {
        getCompositionNN().addStyleName(styleName);
    }

    @Override
    public void removeStyleName(String styleName) {
        getCompositionNN().removeStyleName(styleName);
    }

    @Override
    public <X> X unwrap(Class<X> internalComponentClass) {
        return getCompositionNN().unwrap(internalComponentClass);
    }

    @Override
    public <X> X unwrapComposition(Class<X> internalCompositionClass) {
        return getCompositionNN().unwrapComposition(internalCompositionClass);
    }

    public static class CreateEvent extends EventObject {

        public CreateEvent(CompositeComponent source) {
            super(source);
        }

        @Override
        public CompositeComponent getSource() {
            return (CompositeComponent) super.getSource();
        }
    }
}
