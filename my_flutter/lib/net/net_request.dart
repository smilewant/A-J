import 'dart:io';
import 'package:http/http.dart' as http;
import 'package:my_flutter/dio/dio.dart';
import 'package:my_flutter/dio/form_data.dart';
import 'package:my_flutter/dio/response.dart';

class NetRequest {
  /*
    有没有await表现一样，意义在哪
    Future的底层实现，作用？好处？
   */
  static fetchPost() async {
    Response response = await Dio().get("https://www.baidu.com/");
    print('state ${response.data}');
//    return await http.get(
//      'https://jsonplaceholder.typicode.com/posts/1',
//      headers: {HttpHeaders.authorizationHeader: "1243553213"},
    post();
//    );
  }
/*
  post请求里data是怎么添加的？
 */
  static post() async {
    Dio dio = new Dio();
    FormData formData = new FormData.from({
      "secondChannel": "LVMM",
    });
    Response response = await dio.post(
        'https://api3g2.lvmama.com/route/router/rest.do?method=api.com.route.common.product.getRouteDetails&productId=1550627&productDestId=0&version=2.0.0&clientTimestamp=1551690754570&BSFIT_DEVICEID=QW8WgSd2Gp8IxuCGJMcRO-pZIyfe9z5P3sQhdHyoZ9-REln_yxbHeND_rO9FKZqeRv_TccRNtzxfkjjCPqT_xhU4l4qiCPXS5WQni-UubHB1VQap_qp1SrtJJjCuA509PZgAddPw0QEhHxfsxCvHyf8x6vK481QK&udid=862750030495722&lvsessionid=86dc5cc0-5abb-4410-9950-8795504bc68e&iuf=1551690754570210760&globalLatitude=31.23498&globalLongitude=121.323037&lvtukey=4d3ce40224b6c323d5e921ec2ac6acd6&osVersion=6.0&lvversionCode=117&lvversion=8.2.30&firstChannel=ANDROID&deviceName=M5&formate=json', data:formData);
    print('post ${response.data}');
  }
}
