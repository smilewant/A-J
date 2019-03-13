class Post {
  int userId;
  int id;
  String title;
  String body;

  Post({this.userId, this.id, this.title, this.body});

  /*
  当实例化了一个构造函数后，不想每次都创建该类的一个新的实例的时候使用factory关键字，
  定义工厂构造函数，从缓存中返回一个实例，或返回一个子类型的实例
   */
  factory Post.fromJson(Map<String, dynamic> json) {
    return Post(
      userId: json['userId'],
      id: json['id'],
      title: json['title'],
      body: json['body'],
    );
  }
}
