import 'dart:convert';
import 'package:convert/convert.dart';
import 'package:crypto/crypto.dart' as crypto;

class Log {
  static void log(var tag, var str) {
    print("flutter $tag \n\t : $str");
  }

  static String generateMd5String(String data) {
    var content = new Utf8Encoder().convert(data);
    var md5 = crypto.md5;
    var digest = md5.convert(content);
    log("MD5", hex.encode(digest.bytes));
    return hex.encode(digest.bytes);
  }
}
