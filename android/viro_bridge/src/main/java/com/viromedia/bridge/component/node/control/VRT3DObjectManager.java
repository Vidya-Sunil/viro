/**
 * Copyright © 2016 Viro Media. All rights reserved.
 */
package com.viromedia.bridge.component.node.control;

import android.support.annotation.Nullable;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.viromedia.bridge.component.node.VRTNodeManager;
import com.viromedia.bridge.utility.ViroEvents;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Object3dManager for building a {@link VRT3DObject}
 * corresponding to Viro3dObject.js control.
 */
public class VRT3DObjectManager extends VRTNodeManager<VRT3DObject> {
    private static final String URI_KEY = "uri";

    public VRT3DObjectManager(ReactApplicationContext context) {
        super(context);
    }

    @Override
    public String getName() {
        return "VRT3DObject";
    }

    @Override
    protected VRT3DObject createViewInstance(ThemedReactContext reactContext) {
        return new VRT3DObject(reactContext);
    }

    @ReactProp(name = "source")
    public void setSource(VRT3DObject object3d, @Nullable ReadableMap map) {
        if (!map.hasKey(URI_KEY)) {
            throw new IllegalArgumentException("Unable to find \"uri\" key in given source map.");
        }

        object3d.setSource(map.getString(URI_KEY));
    }

    @ReactProp(name = "resources")
    public void setResources(VRT3DObject object3d, @Nullable ReadableArray resources) {
        List<String> resourceList = null;
        if (resources != null) {
            resourceList = new ArrayList<>();
            for (int i = 0; i < resources.size(); i++) {
                ReadableMap resourceMap = resources.getMap(i);
                if (!resourceMap.hasKey(URI_KEY)) {
                    throw new IllegalArgumentException("Received object resource map without URI key.");
                }
                resourceList.add(resourceMap.getString(URI_KEY));
            }
        }
        object3d.setResources(resourceList);
    }

    @ReactProp(name = "type")
    public void setType(VRT3DObject object3d, @Nullable String type) {
        object3d.setType(type);
    }

    @ReactProp(name = "lightReceivingBitMask", defaultInt = 1)
    public void setLightReceivingBitMask(VRT3DObject object3d, int bitMask) {object3d.setLightReceivingBitMask(bitMask); }

    @ReactProp(name = "shadowCastingBitMask", defaultInt = 1)
    public void setShadowCastingBitMask(VRT3DObject object3d, int bitMask) {object3d.setShadowCastingBitMask(bitMask); }

    @Override
    public Map getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.of(
                ViroEvents.ON_LOAD_START, MapBuilder.of("registrationName", ViroEvents.ON_LOAD_START),
                ViroEvents.ON_LOAD_END, MapBuilder.of("registrationName", ViroEvents.ON_LOAD_END),
                ViroEvents.ON_ERROR, MapBuilder.of("registrationName", ViroEvents.ON_ERROR),
                ViroEvents.ON_ANIMATION_START, MapBuilder.of("registrationName", ViroEvents.ON_ANIMATION_START),
                ViroEvents.ON_ANIMATION_FINISH, MapBuilder.of("registrationName", ViroEvents.ON_ANIMATION_FINISH));
    }
}
