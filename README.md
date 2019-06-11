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

   我们针对需要埋点的控件，生成一个唯一标识，如：**Activity+层层布局+id/index**。通过id与和埋点内容建立一套映射关系，当需要触发埋点时，根据标识获取到内容，进行埋点。

3. 动态修改：

   这就比较简单了，在2的方案下，可以直接下发映射关系到APP中，可以加入版本的概念，毕竟埋点本身并不会经常修改。

#### 具体实现

我们分析了需求，也初步找到了一些解决方案，下面就介绍一下具体的实现。

**Aspectj在Android中的使用**

1. 首先需要进行配置，原本想要使用AspectJ，需要[大量的配置](<https://fernandocejas.com/2014/08/03/aspect-oriented-programming-in-android/>)。当然也有简单的方法，这样可以直接使用沪江的[AspectJX](<https://github.com/HujiangTechnology/gradle_plugin_android_aspectjx>)，具体的配置方法见项目介绍。

2. AspectJ具体的使用，可以查看网络上的一些资料，[这篇文章](<https://blog.csdn.net/zlmrche/article/details/79643801>)写的挺好。

   Aspectj有很多高深的写法，主要还是靠自己多写多尝试。这里我写了一个点击事件的埋点
   
   ```java
   @Aspect
   public class ViewCore extends BaseCore {
   
       /**
        * 这是自定义注解的切点，如果在方法上加入了{@link com.warm.trackboy.annotation.Event},就认定是一个切点
        */
       @Pointcut("execution(@com.warm.someaop.annotation.Event * *(..))")
       public void method() {
   
       }
   
       /**
        * {@link android.view.View.OnClickListener#onClick(View)}的切点
     * 第二段为lambda的写法，
        * @param view
        */
       @Pointcut(value = "(execution(* android.view.View.OnClickListener.onClick(android.view.View))&&args(view))||(execution(void *..lambda*(android.view.View))&&args(view))")
       public void onClick(View view) {
   
       }
   
       /**
        * 具体的通知方法，当Pointcut中的方法被调用之后，触发该方法对一些信息进行拦截
        * @param joinPoint
        * @param view
        * @param obj
        * @throws Throwable
        */
       @After("onClick(view)&&!method()&&this(obj)")
       public void injectOnClick(JoinPoint joinPoint, View view,Object obj) throws Throwable {
           Trace trace = Data.getEvent(getName(joinPoint, view));
           if (trace != null) {
               track(trace.getId(), trace.getValue());
           }
       }
   
   
       private String getName(JoinPoint joinPoint, View view) {
   
           StringBuilder sb = new StringBuilder();
   
           sb.append(getViewName(view))
                   .append("$")
                   .append(getClassName(joinPoint.getTarget().getClass()))
                   .append("$")
                   .append(getClassName(view.getContext().getClass()));
   
           String md5 = Utils.toMD5(sb.toString());
   
           if (BuildConfig.DEBUG) {
               Log.d(TAG, "getName: " + sb.toString() + ",MD5: " + md5);
           }
   
           return md5;
       }
   
   }
   
   ```
   
   我们定义一个数据管理类，本项目中的**Data**，简单模拟了数据的存入和获取，在Application类中存入所有的埋点，实际项目中应该由网络直接下发。当我们拦截到点击事件后，从数据管理类中获取到埋点信息。

#### 存在的问题

1. 针对一些lambda表达式，并不能很好的拦截，比如：```this::onClick```这样的写法，就没办法统一拦截，只能单独写切点，需要我们在写代码时能统一一些格式。
2. 一些与业务有强耦合的埋点，比如有ABCDEFG个选项，又有输入框，点击按钮发生数据并带入数据埋点，这样的情况，原先点击的埋点就失效了，面对这种情况，我们可以封装组件，定义接口，将数据传入该接口中，这样就建立了一套关联，针对该接口进行埋点。还有一些其他强耦合的情况下，我们只能手动埋点了，不够应该也不会很多了。