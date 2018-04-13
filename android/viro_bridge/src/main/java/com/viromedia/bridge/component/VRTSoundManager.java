/**
 * Copyright © 2017 Viro Media. All rights reserved.
 */
package com.viromedia.bridge.component;

import android.support.annotation.Nullable;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.viromedia.bridge.utility.ViroCommands;
import com.viromedia.bridge.utility.ViroEvents;

import java.util.Map;

public class VRTSoundManager extends VRTViroViewGroupManager<VRTSound> {

    public VRTSoundManager(ReactApplicationContext context) {
        super(context);
    }

    @Override
    public String getName() {
        return "VRTSound";
    }

    @Override
    public VRTSound createViewInstance(ThemedReactContext reactContext) {
        return new VRTSound(reactContext);
    }

    @ReactProp(name = "source")
    public void setSource(VRTSound sound, ReadableMap source) {
        sound.setSource(source);
    }

    @ReactProp(name = "paused", defaultBoolean = false)
    public void setPaused(VRTSound sound, boolean paused) {
        sound.setPaused(paused);
    }

    @ReactProp(name = "volume", defaultFloat = 1.0f)
    public void setVolume(VRTSound sound, float volume) {
        sound.setVolume(volume);
    }

    @ReactProp(name = "muted", defaultBoolean = false)
    public void setMuted(VRTSound sound, boolean muted) {
        sound.setMuted(muted);
    }

    @ReactProp(name = "loop", defaultBoolean = false)
    public void setLoop(VRTSound sound, boolean loop) {
        sound.setLoop(loop);
    }

    @Override
    public @Nullable Map getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.of(
                ViroEvents.ON_FINISH, MapBuilder.of("registrationName", ViroEvents.ON_FINISH),
                ViroEvents.ON_ERROR, MapBuilder.of("registrationName", ViroEvents.ON_ERROR)
        );
    }

    @Override
    public void receiveCommand(VRTSound sound, int commandType, @Nullable ReadableArray args) {
        switch (commandType) {
            case ViroCommands.SEEK_TO_TIME_INDEX:
                sound.seekToTime((int) args.getDouble(0));
                break;
            default:
                throw new IllegalArgumentException("Unsupported command " + commandType
                        + " received by" + getClass().getSimpleName());
        }
    }

    @Override
    public Map<String,Integer> getCommandsMap() {
        return MapBuilder.of(ViroCommands.SEEK_TO_TIME_NAME, ViroCommands.SEEK_TO_TIME_INDEX);
    }
}
