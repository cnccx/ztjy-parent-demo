## 通过maven的archetype快速创建项目
### 基本设置
如下设置，解决maven加载太慢的问题
![](index_files/01bc9db5-fc83-4cf8-b015-0d2344e30d30.png)

### 模板坐标

```
<groupId>com.ztjy</groupId>
<artifactId>ztjy-archetype</artifactId>
<version>1.0-SNAPSHOT</version>
```
注：version可能会迭代更新
`注： 坐标以此为准，演示图中坐标仅用于演示`

### 创建工程
File--new--project，添加模板坐标。


![](index_files/bde9c907-a48e-49b2-bc97-e791f7a7e94b.png)

选择模板，创建工程

![](index_files/create_by_ar.gif)


### 运行前配置和编译
#### openservice-facade配置和编译
openservice-facade子工程坐标，需手动修改

![](index_files/2af62bc8-f65c-4137-bb42-73f337d21682.png)

```
openservice-facade修改成为openservice-facade-videoserver（本项目以video-server为例）
```
执行mvn install把jar包安装到本地仓库，也可通过IDEA执行，如下图

![](index_files/2fc96a3e-0a9e-45e4-9594-eeee304deef1.png)

#### application配置和启动
配置application的pom文件，修改对openservice-facade的依赖

![](index_files/e1771a6a-d7ef-4fcb-a5f9-afa9c958d812.png)

```
openservice-facade修改为openservice-facade-videoserver
```

刷新maven，启动application的main

![](index_files/b495d52d-2093-4531-8133-a8e7cdca185b.png)

在浏览器中输入
```
http://localhost:8083/hello
```
发现成功运行

![](index_files/5c4dabb2-3890-4a6b-9ed3-362b553f8c14.png)

## 其他
* <a href='generate_using_archetype.md'>archetype的生成以及版本迭代更新</a><br>






