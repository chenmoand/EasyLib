# EsayLib 一个Bukkit注解扩展

> 这是一个方便Bukkit开发者的一个小工具, 他不会对服务器造成负担, 如果可以的话, 还可呀扩展更多注解, 有兴趣的点个Star

### 1.  使用方法

``` java
	@Override
    public void onEnable() {
        new EasyHook().register(this).init(); // 注册到EasyLib 推荐放到第一行
    }
```

### 2. 注解和用法

##### @Command  

```java
	@Command("easylib")
    public boolean easyLib(CommandSender sender, String[] args){
        sender.sendMessage("这是一条测试的命令");
        return true;
    }
```

##### @BukkitListener

```java
	@BukkitListener
	public class PluginInit implements Listener {code...}
```

更多正在补充