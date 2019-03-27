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

package com.haulmont.cuba.gui.xml.layout.loaders;

import com.haulmont.cuba.gui.components.Frame;
import com.haulmont.cuba.gui.data.DsContext;
import com.haulmont.cuba.gui.model.ScreenData;
import com.haulmont.cuba.gui.screen.ScreenOptions;
import com.haulmont.cuba.gui.xml.layout.ComponentLoader;

import java.util.Map;

public class CompositeComponentLoaderContext implements ComponentLoader.Context {

    @Override
    public ScreenOptions getOptions() {
        return null;
    }

    @Override
    public ScreenData getScreenData() {
        return null;
    }

    @Override
    public Map<String, Object> getParams() {
        return null;
    }

    @Override
    public DsContext getDsContext() {
        return null;
    }

    @Override
    public void addPostInitTask(ComponentLoader.PostInitTask task) {

    }

    @Override
    public void executePostInitTasks() {

    }

    @Override
    public void addInjectTask(ComponentLoader.InjectTask task) {

    }

    @Override
    public void executeInjectTasks() {

    }

    @Override
    public void addInitTask(ComponentLoader.InitTask task) {

    }

    @Override
    public void executeInitTasks() {

    }

    @Override
    public Map<String, String> getAliasesMap() {
        return null;
    }

    @Override
    public Frame getFrame() {
        return null;
    }

    @Override
    public void setFrame(Frame frame) {

    }

    @Override
    public String getFullFrameId() {
        return null;
    }

    @Override
    public void setFullFrameId(String frameId) {

    }

    @Override
    public String getCurrentFrameId() {
        return null;
    }

    @Override
    public void setCurrentFrameId(String currentFrameId) {

    }

    @Override
    public ComponentLoader.Context getParent() {
        return null;
    }

    @Override
    public void setParent(ComponentLoader.Context parent) {

    }
}
