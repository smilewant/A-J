import 'dart:ui';

import 'package:flutter/material.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: MyHomePage(title: 'Flutter Demo Home Page'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  MyHomePage({Key key, this.title}) : super(key: key);

  final String title;

  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            ScreenSizeWidget(),
            Container(
              color: Colors.greenAccent,
              width: window.physicalSize.width / (4 * window.devicePixelRatio),
              height:
              window.physicalSize.height / (4 * window.devicePixelRatio),
            )
          ],
        ),
      ),
    );
  }
}

class ScreenSizeWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    final mq = MediaQuery.of(context);
    print('window.physicalSize.width: ${window.physicalSize.width}\n'
        'window.physicalSize.height: ${window.physicalSize.height}\n'
        'window.physicalSize.padding: ${mq.padding.top}\n'
        'window.devicePixelRatio: ${window.devicePixelRatio}');
    return Text(
      'window.physicalSize.width: ${window.physicalSize.width}\n'
          'window.physicalSize.height: ${window.physicalSize.height}\n'
          'window.devicePixelRatio: ${window.devicePixelRatio}\n'
          'window.physicalSize.padding: ${mq.padding.top}\n',
    );
  }
}