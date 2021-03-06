# EsayLib 一个Bukkit注解扩展

> 这是一个方便Bukkit开发者的一个小工具, 他不会对服务器造成负担,
>
>  如果可以的话, 还可呀扩展更多注解, 有兴趣的点个Star

### 1.  使用方法

请在plugin.yml文件里加上

``` yaml
depend: EasyLib #或者 
softdepend: EasyLib 
```

然后这样:

``` java
@Override
public void onEnable() {
    new EasyHook().register(this).init(); // 注册到EasyLib 推荐放到第一行
}
```

### 2. 注解和用法

##### @lombok...

```
lombok不是注解但他是注解的一个集合插件携带了这个工具包

```
详情[看这个使用教程](https://www.jianshu.com/p/453c379c94bd) 

##### @Cmmand  

```java
@Command("easylib") // 注解是在你的插件注册指令哦!
public boolean easyLib(CommandSender sender, String[] args){
    sender.sendMessage("这是一条测试的命令");
    return true;
}
```

##### @BukkitListener

```java
@BukkitListener // 注解是在你的插件注册监听器哦!
public class PluginInit implements Listener {code...}
```

##### @Bean

```java
@Bean // 将类交给EasyLib管理
public class PluginInit{
    @Bean // 将属性交给EasyLib管理
    public String str = "这是个测试";

}
```

##### @AutoJoin

```java
@Bean("hi")
public String str = "这是个测试";

@AutoJoin("hi")
private static String java; 
// 获取str 的值 因为bukkit限制太大, 最多能导入静态
// 如果想更多使用请
private static final HashMap<String, Object> beans = BeanVaule.getPluginBean(this);
 // this 可以是一个 JavaPlugin 或者 Plugin
private String hi = (String) beans.get("hi"); //输出"这是个测试"
```



更多正在补充

### 3. 如何扩展注解

> 类必须继承 **AnnotationMethod**
>
> ```java
> public 构造器(Class<? extends Annotation> baseAnnotation) {
>         super(baseAnnotation);
> }
> public void init(AnnotationTools annotationTools) {} // 写入
> ```
> annotationTools 是一个小工具包

annotationTools 会分发一个已经配置好的扫描器 → [Reflections](https://github.com/ronmamo/reflections)  ← 和一个你的JavaPlugin类

例子: [实现监听器的类](./src/main/java/com/brageast/easylib/realization/ListenerMethod.java)

然后修改onEnable

```java
@Override
public void onEnable() {
    new EasyHook().
        registerAnnotation(new ListenerMethod(BukkitListener.class)).
        register(this).init(); // 注册到EasyLib 推荐放到第一行
}
```

