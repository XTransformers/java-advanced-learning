
**行为参数化**，就是一个方法接收多个不同的行为作为参数，并在内部使用它们，完成不同行为的能力。

**流**，是从支持数据处理操作的源，生成的，元素序列。

- 集合是数据结构，目的是以特定的时间/空间复杂度存储和访问元素。
- 流的目的，在于表达计算。

与迭代器类似，流只能遍历一次。遍历完之后，我们就说这个流已经被消费掉了。

Stream 

短路
循环合并 filter + map

**函数式接口**，就是只定义一个抽象方法的接口。

- 即使接口有很多默认方法，只要接口只定义了一个抽象方法，仍然是一个函数式接口。

Lambda 表达式，允许直接以内联的形式为函数式接口的抽象方法提供实现，并把整个表达式作为函数式接口的实例。

- 用匿名内部类也可以完成同样的事情，只不过比较笨拙：需要提供一个实现，然后再直接内联将它实例化。

函数式接口的抽象方法的签名，基本上就是 Lambda 表达式的签名。我们将这种抽象方法叫做函数描述符。

@FunctionalInterface

- java.util.function.Predicate\<T>
  - test(T t) : boolean
- java.util.function.Consumer\<T>
  - accept(T t) : void
- java.util.function.Function\<T, R>
  - apply(T t) : R
- java.util.function.Supplier\<T>
  - get() : T

泛型，只能绑定到引用类型，由泛型内部实现方式造成的。

避免自动装箱、拆箱的性能损耗及内存空间的额外要求：

- IntPredicate
- test(int i) : boolean
- DoublePredicate 
- IntConsumer
- LongBinaryOperator
- IntFunction
- ToIntFunction\<T>
- IntToDoubleFunction

JDK 提供的函数式接口不允许抛出受检异常（checked exception），解决方案：

- 定义一个自己的函数式接口，并声明受检异常
- 把 Lambda 表达式包在一个 try/catch 块中



Lambda 的类型是从使用 Lambda 的上下文推断出来的。

Lambda 表达式需要的类型，称为**目标类型**。

如果 Lambda 表达式的主体是一个语句表达式，它就和一个返回 void 的函数描述符兼容（当然需要参数列表也兼容）。

```java
List<String> list = new ArrayList<>();
Predicate<String> predicate = s -> list.add(s);
Consumer<String> consumer = s -> list.add(s);
```

Lambda 可以没有限制地捕获实例变量和静态变量。

- 但局部变量必须声明为 final，或事实上是 final。
- Lambda 表达式只能捕获指派给它们的局部变量一次。
- 捕获实例变量可以被看做捕获最终局部变量 this。

对局部变量的限制，是因为：

- 实例变量都保存在堆中，而局部变量保存在栈上。
- 不鼓励使用改变外部变量的典型命令式编程模式，会阻碍并行处理。

闭包，是一个函数的实例，且可以无限制地访问那个函数的非本地变量。

- 闭包可以作为参数传递给另一个函数。
- 也可以访问和修改其作用域之外的变量。

Java 8 的 Lambda 和匿名类可以做类似于闭包的事情：它们可以作为参数传递给方法，并且可以访问其作用域之外的变量。

- 但有一个限制：它们不能修改定义 Lambda 的方法的局部变量的内容，这些变量必须是**隐式最终的**。
- 可以认为 Lambda 是对值封闭，而不是对变量封闭。
- 这种限制存在的原因在于局部变量保存在栈上，并且隐式表示他们仅限于其所在线程。
- 如果允许捕获可改变的局部变量，就会引发造成线程不安全的新的可能性，而这是我们不想看到的（实例变量可以，因为它们保存在堆中，而堆是在线程之间共享的）。

可以把**方法引用**看作针对仅仅涉及单一方法的 Lambda 的语法糖。

方法引用分类：

- 指向静态方法，如 Integer::parseInt
- 指向任意类型实例方法，如 String::length
- 指向现有对象的实例方法
- 构造函数引用，如 ClassName::new

