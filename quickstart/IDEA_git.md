#  IDEA git常用操作
### 1 拉取
![](https://i.imgur.com/994fnQv.png)
![](https://i.imgur.com/RlyJxZf.png)

### 2 更新
提交代码前先更新下远程仓库的代码到本地仓库，这样可以减少不必要的冲突，更新update可以直接通过快捷键**CTRL + T**也可以通过工具栏上按键来实现，![](https://i.imgur.com/VOCcFsI.png)分别是update，commit，compare with the same respository version。
![](https://i.imgur.com/kLnl05m.png)

请理解 merge rebase  branchdefault 的作用
### 3提交代码

通过快捷键**Ctrl + K** 或上图的工具栏打开提交弹框

![](https://i.imgur.com/w6CutbX.png)


**代码注释为必填项**

在弹框中，我们会看到文件被标识着不同颜色

红色：未被版本控制的文件，即未添加到版本控制的文件，例如我们添加到ignore中的文件。

绿色：新加入版本的文件，即我们新创建的文件，还未提交到远程仓库。

蓝色：修改过的文件，即远程仓库中已有该文件，我们这次对它进行了修改，但是还未提交。

按钮**commit**为提交到到本地仓库

如果commit完之后我们还需要push到远程仓库，这时候需要我们再右键项目-->选择Git-->Respository-->push。

按钮**Commit and Push**也就是Commit和push，我们可以直接在这里commit到本地仓库之后，再push到远程仓库。

项目中右键 -- git -- 也有各种git命令

![](https://i.imgur.com/VQcgA90.png)

## 4 分支管理

分支管理我们就需要用到IDEA底部的状态栏了。
在这里我们可以切换分支，新建分支，checkout分支代码，compare分支代码等等。
![](https://i.imgur.com/PzF1wUn.png)
![](https://i.imgur.com/7AfDRFD.png)