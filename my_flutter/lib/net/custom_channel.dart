import 'dart:convert';

import 'package:flutter/services.dart';

/*
    处理Flutter App跳转
    By hukuan

    ===> 原生与Flutter通信须知--特卖首页.md

 */

class CustomChannel {
  final MethodChannel commomChanel =
      const MethodChannel('com.further.run');

  static CustomChannel _channel;

  static CustomChannel getInstance() {
    if (_channel == null) {
      _channel = new CustomChannel();
    }
    return _channel;
  }

  jump(String methodName) {
    _channel.jumpToApp(methodName);
  }

  Future<void> jumpToApp(String methodName) async {
    // Errors occurring on the platform side cause invokeMethod to throw
    // PlatformExceptions.
//    NativeVo model = NativeVo.fromJson(vo);
    // print("model.toString() ${jsonEncode(vo)}");
//    a.addAll(Map("",""));
    try {
      await commomChanel.invokeMethod(methodName);
    } on PlatformException catch (e) {
      throw 'Unable to play  ${e.message}';
    }
  }

  void setMethodCallHandler(Future<dynamic> handler(MethodCall call)) {
    commomChanel.setMethodCallHandler(handler);
  }
}
