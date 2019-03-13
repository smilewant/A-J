import 'dart:ui';

import 'package:flutter/material.dart';
import 'package:my_flutter/bean/post.dart';
import 'package:my_flutter/net/net_request.dart';
import 'dart:convert';

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

  Post _post;

  @override
  void initState(){
    super.initState();
    print("initState");
    NetRequest.fetchPost();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(_post == null ? "2" :  _post.title),
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
        'window.devicePixelRatio: ${window.devicePixelRatio}\n'
    );


    return Text(
      'window.physicalSize.width: ${window.physicalSize.width}\n'
          'window.physicalSize.height: ${window.physicalSize.height}\n'
          'window.devicePixelRatio: ${window.devicePixelRatio}\n'
          'window.physicalSize.padding: ${mq.padding.top}\n',
    );
  }
}