# TrackBoy
使用AspectJ实现AOP无痕埋点操作

#### 需求和解决方案分析

在做一个项目时，正常情况下，都会有埋点手机用户行为的需求。

大多公司都会使用友盟或类似的方案，进行代码埋点，在需要埋点的地方，调用相关的方法记录一下，在合适的时候进行上报，具体如何记录，如何上报由SDK决定，我们只需要关心如何在合适的地方加入类型如下的代码：

```java
public static void onEvent(Context context, String eventID);
public static void onEvent(Context context, String eventID, String label);
```

比较简单的方法就是在代码的各处手动加入埋点的操作，手动埋点虽然比较灵活简单，但就会存在如下几个问题：

1. 埋点代码和业务代码耦合太严重，可能所有点击的地方都需要加入埋点，需要大量重复操作，不够优雅。
2. 埋点容易出错且难以维护，每个版本都可能存在增删改埋点，一般都会面对一个Excel表格，一改改半天，过两天要发布了，产品又发来一个表格，埋点需要轻微的修改。
3. 埋点一旦上线后就无法增减或修改。

针对上面几个问题，我们逐一寻找解决方案：

1. 代码耦合问题：

   * 写一个代理类，再通过反射替换事件。

   * 通过AOP的方式，针对需要埋点的切面，插入代码。

     通过反射，需要额外损耗，且有Android版本的限制。

2. 维护问题：

   我们针对需要埋点的控件，生成一个唯一标识，如：**Activity+层层布局+id/index**。通过id与和埋点内容建立一套映射关系，当需要触发埋点时，根据id获取到内容，进行埋点。

3. 动态修改：

   这就比较简单了，在2的方案下，可以直接下发映射关系到APP中，可以加入版本的概念，毕竟埋点本身并不会经常修改。

#### 具体实现

我们分析了需求，也初步找到了一些解决方案，下面就介绍一下具体的实现。

**Aspectj在Android中的使用**

1. 首先需要进行配置，原本想要使用AspectJ，需要[大量的配置](<https://fernandocejas.com/2014/08/03/aspect-oriented-programming-in-android/>)。当然也有简单的方法，这样可以直接使用沪江的[AspectJX](<https://github.com/HujiangTechnology/gradle_plugin_android_aspectjx>)，具体的配置方法见项目介绍。

2. AspectJ具体的使用，可以直接查看文档，这里我们简单介绍一下常规的操作，实现对点击事件的拦截。

   ```java
   @Pointcut(value = "(execution(* android.view.View.OnClickListener.onClick(android.view.View))&&args(view))||(execution(void *..lambda*(android.view.View))&&args(view))")
   public void onClick(View view) {
   
   }
   
   
   @After("onClick(view)&&!method()&&this(obj)")
   public void injectOnClick(JoinPoint joinPoint, View view,Object obj) throws Throwable {
       Log.d(TAG, "injectOnClick: "+obj.getClass().getSimpleName());
       Trace trace = Data.getEvent(getName(joinPoint, view));
       if (trace != null) {
           track(trace.getId(), trace.getValue());
       }
   }
   ```

   