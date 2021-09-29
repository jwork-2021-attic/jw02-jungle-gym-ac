建议使用**typora**或其他具有markdown渲染效果的阅读方式阅读本文档，以获得较好的阅读体验。



# 任务一

## 1.example的类图

![](http://www.plantuml.com/plantuml/png/PLAxJYin4Etz5TPMzq8s7v02XJHfA28UD8Z2IOQmmh5NjbSGGWh4GrI8ceAQ2a6Txua2Vy747Y-6JEDvp9npPkIsTSAuQYpvG0fhmJ8qx_8km2MOAUDemUVk5CX3yuKzRHo6rZy-CmAt7zl-_VSf2oxqKImB1GSb1QKm5dfIA8hX0WQL0uncZ1KAfqx4008HgP5Q32Cqfib5OeAPqGQTgj-Na4uYtj4n5q6wCuKQyH9d0jIvAF-oxXHA8CGqhj7Jjd25LWdbMkPGo2h89na8GWYn6JMKvDfmX2hPWZodWy8wt3P6J5BWhgxCaDURSTCODVg_Y6gQYEr7EfDTeSu8rv1B5uYJAK_C1IQw2PVXZQUXT9YBFJpFTIgdzNdzl7XwO34H6cYHMAG_XHNwT-jZsLYeIPRd6vndMa7cRun4hUNyTjN8M81iDg-sefRMukrb-VZEqb-nglFWrlKVbiqyzy1Okh_xkdbTVimPMXlKi1hBRm00)

## 2.Scene中main方法执行过程中的对象时序图

![](http://www.plantuml.com/plantuml/png/NL9DIyD04BtlhrZ8fJtq3mHIYXSzU12g1uyXZidYkXko4u9UAoWgYX_s87XLKVJYfHRihsdI_WopcNOBFUrxRsOUyuRj6Gmpp8ybOsaDH2JIK27lHw20XuQR1dWRCWSMHgWplWbm2fcjnOIujwLnHwWZ1We5dl1jKO_O1cc1LoG2OLdhwmnfsX3oza5ANRXP7JUJEcOi2CgBb_BjghmU1W6BCWWH6az6MtMw3VCL58pLo2dSJtDijPdIzK0cuWJzwd4o-_kUZWRJqSVyxdD--Lg-3zZIb98uMw9-94DZ5dbdpsSnu6u2HAlXR_NpD1rzSGM5iw58peOehKUMTi65QW3f5YqIsZPlTNDVZc-3mBKlypJFNkgEeEDLIqsuJ_MMFNsxDffF7ihpyUfgHIRSPfqkCRlOcj8yQzgXp4sofaLI6s2i1-gW_Zh_)

## 3.从面向对象编程角度理解`example`的设计理念

| 现实中的某实体       | 类（对现实的抽象） |
| -------------------- | ------------------ |
| 排序算法             | Sorter             |
| 冒泡排序             | BubbleSort         |
| 排序的对象(某种实体) | Linable            |
| 葫芦(娃)             | Gourd              |
| 队列                 | Line               |
| 位置                 | Position           |
| 老爷爷               | Geezer             |

接口`Sorter`定义了**排序算法类**所需要实现的三个方法：

- 通过`load()`方法将需要排序的数组作为参数传入
- 通过`sort()`方法将数组元素进行排序，同时生成排序的步骤`plan`
- 通过`getPlan()`方法获得字符串形式的排序步骤`plan`

`BubbleSort`类实现了该接口，通过**改进版的冒泡排序**（每一轮冒泡时检测是否已经完成排序）的形式进行数组排序，定义了用于交换元素的方法`swap`，并在该方法中将交换元素的步骤记入`plan`中



接口`Linable`定义了**可排序对象类**需要实现的三个方法：

- `setPosition` 设定位置
- `getPosition` 获得当前位置
- `getValue`  获得排序中的键值（用于排序时比较大小）

**枚举**类型`Gourd`实现了`Linable`接口，实现了可排序的葫芦类。按**编号**定义了**七个枚举类对象（七个葫芦）**，名字即为ONE,TWO,THREE...

- 该类中三个成员变量r,g,b表示了该葫芦的颜色。
- getValue方法返回的是葫芦编号(1号到7号)，以便于将葫芦按编号排序。
- swapPosition方法将两个葫芦交换位置。



 队列类Line中定义了**内部类Position**

- Linable成员变量表示当前该Position上的Linable对象，**Position和Linable互相包含了对方的reference作为成员变量**。
- setLinable方法中调用Linable类的getPosition方法，修改了当前Position上的Linable。
  - 该方法被`Gourd`类中的swapPosition()方法调用，以交换两个葫芦的位置。



队列类`Line`中：

- Position数组成员，存储所有位置
- get()方法，获得指定位置上的Linable对象
- put()方法，将指定Linable对象置于指定位置上



老头类`Geezer`中：

- Geezer成员：指挥排队的老爷爷
- Sorter成员，使用的排序算法
- 静态方法`getTheGeezer`，创建Geezer对象
- parsePlan()方法，将`getPlan()`方法得到的排序步骤的字符串**解析**为字符串数组。
- execute()方法，执行字符串形式的step步骤，交换两个葫芦的位置。
- **核心方法！！！Lineup方法**
  - 将传入的Line类型参数通过toArray方法转化为该Line中Linable的数组
  - 获得Linable数组中每个Linable 的编号的数组
  - 将编号的数组**使用Sorter进行排序**
  - 使用parsePlan解析排序的步骤，分解为每一个step
  - 依次调用`execute()`执行每个step，将每一步后的队列**以字符串的形式**输出到终端中，**以每一帧的编码形式**输出到日志**log**中



Scene类中提供了main方法入口，在main方法中：

- 在Line上按打乱过的顺序放置七个葫芦
- 调用老爷爷Geezer的LineUp方法，通过冒泡排序指挥葫芦娃排序。获取排序的输出日志**log**。
- 将log中的结果写入**result.txt**中



## 4.具体阐述这样写的好处与可改进之处

好处：

- 将“老爷爷指挥七个葫芦娃按排行顺序站成一队”的场景中的所有实体以及实体之间的关系进行了抽象，使代码易于理解，实现了对现实的模拟
- 采用了面向对象的封装机制，使得数据和操作绑定在一起，降低了程序出错的可能性。
- 利用了继承带来的多态，**提高了代码的可维护性和可拓展性**，代码稍作修改即可对其他Linable类的子类对象进行排序
- 将Sorter和Linable定义为接口，降低耦合度，提高代码的可维护性和可拓展性

可改进之处：

- **项目设计**方面：
  - 本项目的排序过程中，先将葫芦的编号复制为int数组，对int数组进行排序，再根据int数组排序的步骤对葫芦进行排序，这样的过程我认为**有些冗余，且不能完全达到对现实的模拟**。我认为可以做如下设计：Sorter直接对Linable元素构成的数组进行操作，通过Linable的getValue方法进行比较，**直接交换两个Linable元素的位置**。(但尝试之后发现这样重构遇到了一些实现上的**困难**，看来我的功力还是不够)

- 代码书写方面
  - `Bubblesort.java`中，将类的成员变量写在一起，同时将`Bubblesort`中实现的`Sort`中的三个方法写在一起，能提高代码的可读性。
  - `Line.java`中，按顺序写内部类`Position`的定义，再写成员变量和方法的定义。
  - `Gourd.java`中，将重载的方法写在一起。
  - `Geezer.java`中，先定义成员变量，再定义构造函数等方法，`execute`和`parsePlan`方法代码量较小且在`Lineup`方法中被调用，因此应该写在`Lineup`方法之前。

# 任务二

## 类图



![](http://www.plantuml.com/plantuml/png/bLAxJYin4Etz5QEjxYER3yYLhW8rIBmRHE5iJB3YU1VRAuWW1U8XAaHDGKs5eCxtH86_OBAU2Si32RRnFEQSEJCxRH-a2-N0G6QazyeB_2eJrgtigoC10D64iAUY0H2ZkGlAWQ-UAj9JOOEAVlsk8bw5M4Q-qLPj5kGKqdcrOgGbNnsgh0mpw4W8RH6rApD5VQ8IayikKsEOJEnBD2CMhupAGXs9VDix8AFouBJjGO6OI7KWYnfujTHP_sVOojndtFi9bhIL7KFBz2WszphetBBypo9RqfIqYgWAIOY1Yr5JGQQF2orh6o4hv57nt5rmJWxhnCi8HwKmdnLSezVeD9oq_JVzk1iD7cJG6QDH2zu7EVuVYw30ud0DoR0fR-mRyt99b4Kh11DVtcGC4uvh84tpkkpMqydDvFfIS83oF3UdwPunIGV_0PSCf1qcMF47ajogfFedoDIQZizcXK8mPxDv_9_bj2RtjzEh1r6_oLaUeZdFqzbzbNwLyJTDgywOkZX_FhsRFeu5Vcrbk-N0l000)



## 可视化结果展示链接

[![asciicast](https://asciinema.org/a/fWb9RqowaTd9a7Deg5DmfvQ8J.svg)](https://asciinema.org/a/fWb9RqowaTd9a7Deg5DmfvQ8J)

# 任务三

## 类图

![](http://www.plantuml.com/plantuml/png/bLCxZjH04Ctx5AM9-Cpi0GO9BSIBj7mWGGIztcB6cdRRTBV5ZfOD41yH8M82OY84yPvdDN0BobrLdaOM4ZXnVUgzUltiFep9XjGt3YfdOyHewCaXFFHsW-S603Y4j4OE0BZQXeG1OdxboWhJ8ncwTZrN8YOUazpL7XztadGsH3nsrakEPrZrQO1U65DxGZst5SeULk9QUwhKLAPGzWhDXOf7XrKgaSJtz1bOLfv2xLVG4OQfNjgk0D_lwshpdzWXVA6up2Lazom1pfZdY4vFH4qkANFsv0ZzAgtPbRuubaIflHE2tOuPYnZM53uHkprnUAx67xUnJdNhzspJoXFhUYpb4eamw309xYICKu5JIQNUqWU2ar8O7OKmNyrFvi7wpH_xT9lOcsogAqMJbhH6IU8j5I65kzi7H4PBzRlymtbrX6M9XU3uhKvoMN0w0uj5MyfU_dpz-UhJ1wC5xgloiIstXffyhQQuMMFzTaOJDs3MUfpbRq9Coztbss5mIAT3_9-DGqRt7XoykgsIbrVVlkm-VZVbxHZwmE7Ovmlmr_PUwit58gkZrljtlzvytVsuDFGSeZ_j6_SR)



## 可视化结果展示链接

[![asciicast](https://asciinema.org/a/1UmP2z410LnsSxbTBih66Hb43.svg)](https://asciinema.org/a/1UmP2z410LnsSxbTBih66Hb43)

