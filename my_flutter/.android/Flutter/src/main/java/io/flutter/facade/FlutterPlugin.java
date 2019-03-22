package io.flutter.facade;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry;

/**
 * Created by Zion
 * 2019/3/22.
 */
public class FlutterPlugin implements MethodChannel.MethodCallHandler {
    private static final String FLUTTER_PLUGIN = "com.further.run";
    static MethodChannel channel;
    static Activity activity;
    @Override
    public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
        if (FlutterConstant.METHOD_JUMP_TO_ACTIVITY.equals(methodCall.method)) {
            Intent intent = new Intent();
            intent.setClassName("com.further.run", "com.further.run.labzone.eventdispatch.EventDispatchActivity");
            activity.startActivity(intent);
        }
    }

    public static void registerWith(PluginRegistry.Registrar registrar, Activity activity1) {
        activity = activity1;
        channel = new MethodChannel(registrar.messenger(), FLUTTER_PLUGIN);
        channel.setMethodCallHandler(new FlutterPlugin());
    }
}