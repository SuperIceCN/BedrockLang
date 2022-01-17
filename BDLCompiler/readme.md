# 2020-1-16的聊天记录

现在的bdlCompiler只是在做语义树

因为语法还没完全确定

com.blocklynukkit.bedrockLang.compiler.ast

这个下面是语义分析和代码生成

com.blocklynukkit.bedrockLang.compiler.parser下面是暂定的一个lexer和parser

然后你会在com.blocklynukkit.bedrockLang.compiler.ast.compile下面看到一堆接口和抽象类

所以compiler.ast是负责把ast转化成字节码的对吧

我把这两个混合了

实际上有五个概念

源代码 词法流 语法树 语义树 字节码

compiler.ast负责把语法树转为语义树进行分析，分析的过程中生成字节码

如果分析出错误就停下来

是的，没有做错误恢复

javac都没做，我为什么做呢

你还有不明白的吗？

肯定有

所以说语义树是啥。。。

就是用一个树形数据结构做语义分析

本质上就是整理一下语法树

把里面的函数调用和变量提取一下

然后验证一下导入

就是分析树对吧

一个比较杂的分析树

还有一点语法树的东西混了进去

然后从这个语义树生成字节码

而且有些概念我不知道中文（）

一边聊一边翻译

的确

bdl每个文件都是一个unit

就是编译单元

所以ast前都是antlr生成的对吧

是的

antlr包揽了ast前所有工作

antlr人类福音啊

（）

antlr通常比手写的更快

而且自带语法分析中的错误恢复

然后说说语义树的事？

我这个人设计架构的时候喜欢无脑堆接口......

看出来了。。。

全是接口

语义树每个节点都是一个Piece

（来自swift的奇怪命名

Piece有三个字类，Stat语句，Expr表达式，Block块

Stat没有返回值

Expr有返回值

Block没有返回值但是能够在语义树上储存局部变量

Unit是特殊的Block，还能储存全局变量

只有Unit才能做根节点，Unit是Block字类

我想你大概明白结构了

有个叫VariableStore的接口

实现了这个接口的Piece能够储存变量

LocalVariableStore继承了VariableStore

实现了这个接口的Piece能够储存局部变量

搜嘎

这么一讲

然后生成的时候从Unit往下遍历

注释还真是精简捏（）

CodeGenerator

这个玩意就是代码生成器

每个Piece都有一个CodeGenerator

生成字节码的时候只要从Unit开始挨个遍历调用CodeGenerator.generate就行了

CodeGenerator有4个子类

好吧其实是5个

CompilerCodeGenerator

仅供Unit使用

返回的是字节码

StatCodeGenerator没有返回

ExprCodeGenerator返回这个表达式的返回值类型

ControlFlowCodeGenerator返回一个UnfinishedGen

因为jvm字节码使用goto控制

很多时候你需要下一个语句的字节码位置才能完成goto

所以所有控制流相关语句都实现ControlFlowCodeGenerator

然后返回一个UnfinishedGen

调用者接收这个UnfinishedGen

把下一条语句生成之后的位置交给UnfinishedGen

让UnfinishedGen继续完成ControlFlowCodeGenerator剩下的跳转

这就是生成字节码的大概流程

