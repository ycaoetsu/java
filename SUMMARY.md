#Beijing Training (First Week -- Java Basic)
##String:
* Immutable
* StringBuilder/StringBuffer/String: avoid storage waste, not use string + to combine strings; use Stringbuilder when no thread safety problem. 
* “==“ and .equals();
* Identity v.s Equality v.s Hash
    * Indentity 完全一个object
    * equality 两个object但是满足一定的相等条件（值）
    * hash计算将objects 归类成several buckets，和上面两个概念还是有区别的，两个object equal， hash code一定相等； hash code相等不一定是equal的objects

##强制类型转换表
强制类型转换表
显式 Explicit(强制)  隐式 Implicit

From left to top (long double 64)

Byte|short|int|long|float|double
:---:|:---:|:---:|:---:|:---:|:---:|
Null|I	|I|I|I|I
E(0x7f)(0x80)*|	Null|		I|		I|		I|		I|
E|		E|		E|		Null|		I|		I|
E|		E|		E|		E|		Null|		I|
E|		E|		E|		E|		E|		Null|

(0x7f)(0x80)*:在short 转 byte时候(0x7f) 不会报错 大于才会报错(0x80)

##Final Keyword
* is a relationship, the reference cannot be changed, but if you refer to an object, like an array, you can modify the content of the array. 
* A final method cannot be overridden. A final class cannot be inherited.

##BooleanTest Notes
* &&, || are logical operators, &, |, and ~ are bitwise operators
* Priority: ~, &, |
* Primitive Type: Boolean: 1 byte | byte: 1 byte | short: 2 bytes | int: 4 bytes | long: 8 bytes | char: 2 bytes | float: 4 bytes | double: 8 bytes
* Default Value: int: 0; char: '\u0000’; double: 0d; String & other objects: null;
* If you declare an int with a hex number, and you assign a value as 0x1234, it should be 0x0000_1234 automatically. 

##Interface
Characteristics:

* Multiple implementations, a comma-separated list of all the interfaces that it extends.
* contain abstract (no bracket), default, and static methods, public as default.
* field (public, static, and final)
* package private as default, can be declared public
* an interface can extend other interfaces, just as a class can extends other classes.
* no instance
* if a class implements an interface, the class must override all the methods
* interface cannot be final

##“This" Keyword
* "this" keyword is used within an instance method or a constructor, “this” is a reference to the current object.  
* “This” can be used with a field (Instance Variable)
    * cannot create 2 instances/local variables with the same name. (we can have 1 instance variable/ 1 local variable with the same name) this is also called "method hiding"
* “This” can be used with a constructor: (when class contains a set of constructors)
    * “this” keyword can only be the 1st statement in constructor
    * A constructor can have either “this” or “super” but not both 
* Used inside methods to call another method from same class.
* as a method parameter

```
Variable Hiding:
class JBT {

        int variable = 5;

        public static void main(String args[]) {
                JBT obj = new JBT();

                obj.method(20);
                obj.method();
        }

        void method(int variable) {
                variable = 10;
                System.out.println("Value of Instance variable :" + this.variable);
                System.out.println("Value of Local variable :" + variable);
        }

        void method() {
                int variable = 40;
                System.out.println("Value of Instance variable :" + this.variable);
                System.out.println("Value of Local variable :" + variable);
        }
}
Result:
Value of Instance variable :5
Value of Local variable :10
Value of Instance variable :5
Value of Local variable :40 
```
```         
public class Rectangle {
            private int x, y;
            private int width, height;
        
            public Rectangle() {
                this(0, 0, 1, 1);
            }
            public Rectangle(int width, int height) {
                this(0, 0, width, height);
            }
            public Rectangle(int x, int y, int width, int height) {
                this.x = x;
                this.y = y;
                this.width = width;
                this.height = height;
            }
            ...
        }
      
```
```
class JBT {

        public static void main(String[] args) {
                JBT obj = new JBT();
                obj.methodTwo();
        }
        void methodOne(){
                System.out.println("Inside Method ONE");
                }

        void methodTwo(){
                System.out.println("Inside Method TWO");
                this.methodOne();// same as calling methodOne()
        }
}
```
```
A method Parameter:
public class JBTThisAsParameter {

        public static void main(String[] args) {
                JBT1 obj = new JBT1();
                obj.i = 10;
                obj.method();
        }

}

class JBT1 extends JBTThisAsParameter {
        int i;

        void method() {
                method1(this);
        }

        void method1(JBT1 t) {
                System.out.println(t.i);
        }
}
```
###"Instance Methods" v.s "Static Methods" 
    
   * Instance method are methods which require an object of its class to be created before it can be called. To invoke a instance method, we have to create an object of the class in within which it defined. 
        * Instance methods belong to the Object of the class not to the class. 
        * (Can be called after the object creation, dynamic binding at run time)
    * Static Method are the methods can be called without creating an object of class. (ClassName.methodName(args)). 
        * Share among all objects created from the same class
        * Static method cannot be overridden, but can be overloaded since static binding by compiler at compile time

    * Instance method can access the instance methods and instance variables directly.
    * Instance method can access static variables and static methods directly.
    * Static methods can access the static variables and static methods directly.
    * Static methods can’t access instance methods and instance variables directly. They must use reference to object. And static method can’t use this keyword as there is no instance for ‘this’ to refer to.

##Access Modifier
* public, protected, package private, private

Modifier|Class|Package|Subclass|World
:---:|:---:|:---:|:---:|:---:|
public|Y|Y|Y|Y
protected|Y|Y|Y|N
no modifier|Y|Y|N|N
private|Y|N|N|N

##Initializing Fields
####Static Initialization Blocks
```
static {
    // whatever code is needed for initialization goes here
}
```

* Static initialization blocks: any number, any where in the class body, called in the order that they appear in the source code.
* Alternative way:


```
class Whatever {
    public static varType myVar = initializeClassVariable();
        
    private static varType initializeClassVariable() {

        // initialization code goes here
    }
}

```
* Order of execution of initialization blocks and constructor in Java
    * Static initialization block will run whenever the class is loaded first time in JVM before constructors is invoked
    * Initialization blocks run in the same order where they appear in the program
    * Instance initialization blocks are executed whenever the class is initialized and before constructor is invoked.
    * Static and instance initialization blocks execution order is according to where they appear
* ClassName.method(); //the way only call static method, no instance method would be executed.

```
E.g
class GFG {
  
    GFG(int x)
    {
        System.out.println("ONE argument constructor");
    }
  
    GFG()
    {
        System.out.println("No  argument constructor");
    }
  
    static
    {
        System.out.println("1st static init");
    }
      // Instance Initialization Block
    {
        System.out.println("1st instance init");
    }
  
    {
        System.out.println("2nd instance init");
    }
  
    static
    {
        System.out.println("2nd static init");
    }
  
    public static void main(String[] args)
    {
        new GFG();
        new GFG(8);
    }
}
Result: 
1st static init
2nd static init
1st instance init
2nd instance init
No  argument constructor
1st instance init
2nd instance init
ONE argument constructor
```
##Constructor call 顺序：
    
* this() and super() should be the first statement in the constructor.
    1. 先调super(), 如果没有显示调用的话
    2. 然后调用自身的constructor

或
有this()的时候，默认先调super(), 再调this(), 再call自己的constructor方法

##instanceof:  
只要是能够cast到另外的那个type，那么instanceof就是true。

        e.g: Number —> Integer, Long
               Integer instanceof Integer -> T
                Integer instanceof Long -> F 
         
##.equals()
Equality 和 identity

*  	Obj != null;
*  	a.equals(a); 自身等于自身
*  	值相等 自反性
*  	传递性 
*  	对称性 a.equals(b) b.equals(a);
*  	Hashcode

没法比较父子类之间的相等（违反对称性和传递性）

##Method Signature
only for method name & parameters

##Lambda Expression 
* Anonymous Class: an inner class without a name and only a single object is created. 
    * Extends a class
    * Implements a interface
    * Defines inside method / constructor argument
    * A normal class can implement any number of interfaces but anonymous inner class can implement only one interface at a time
    * A normal class can extends and implement any number of interface at the same time, but anonymous can extend a class or implement a interface but not both at a time
    * Normal class can write any number of constructors but anonymous class cannot write any constructor 
    * An anonymous class cannot access local variables in its enclosing scope that are not declared as final or effectively final (1*)
    * Cannot have static blocks or variables

```
1*
Variable is accessed within inner class, needs to be declared final. 
-> Java doesn’t want devs to change local variables from within an inner class or an anonymous inner class. 
-> Why? 

    Inner class “capture” the variable. 
    { It copies the variable from it's enclosing scope to a new variable, and brings just that copy inside the inner class. Anything it does to that copy is independent from the variable in the enclosing scope. So if the variable changes in the inner class, and then it is used later in the enclosing scope, the changes made in the inner class did not persist in the enclosing scope.}
    
    
    Closure: function + context the inner class is a closure.
    
public class RadiusStuff { //This is the outer class
    public void start(Stage stage) { //This method is the enclosing context
    Button submit = new Button("Submit");
    final string radius = "10";
    submit.setOnAction(new EventHandler<ActionEvent>() {
        //This is the inner class, specifically an anonymous class.
        @Override
        public void handle(ActionEvent e) {
            submit.setText(radius);
        }
    });
  }
}
```
###Functional Interface

  * An interface contains only one abstract method. Have only 1 functionality to exhibit. Lambda expression can be used to represent the instance of a functional interface. 
  * Can have multiple default methods.
  * @FunctionalInterface Annotation for compiler, not mandatory
  
  
  
#####方法引用符号： “：："
    * ClassName :: staticMethod
    * ClassName :: instanceMethod
    * Constructor E.g 2* (ArrayList :: new)
```
E.g 2*
public interface GenericFunc<T> {
    T getValue();
}
@Test
void should_bind_to_constructor() {
    // TODO: please bind lambda to constructor of ArrayList<Integer>
    // <--start
    GenericFunc<ArrayList<Integer>> lambda = ArrayList :: new;
    // --end-->

    ArrayList<Integer> value = lambda.getValue();
    value.add(1); //better way?

    assertEquals(1, value.size());
}
```

###NaN: 
* not a number: v != v (一个NaN不和本身相等）
* Math.round(number) 只和小数点后一位（或者说要求精度的后一位）有关 2.49 ~~ 2
* 计算机中能精确表示1/3吗？表示无限位：精度是有限的 （下溢：计算机太小表示不出）
* 栈溢出：recursive 、 调用栈太深。 （循环不会栈溢出，函数定义完后，空间使用多少已经定了）
* 在add两个numbers的时候，用Math.addExact(num1, num2); 避免overflow

##Others
* Java pass by value / reference address
* Method overload: should choose method while compiling time. (Object)”sss” —>go to find methodName(Object s) {}
* >>>, >> difference >>> unsign right moving// >> sign right moving, if negative, the highest pos keep as 1.
* [Ljava.lang.Object;@4678c730  [数组  类名 @hash code
* method(Object[] args) {} 
method(Object… args) {} 可变长度参数 var length parameter
* 实际参数 Argument
形式参数 Parameter
实参传入后，形参改变，实参不会变 形参新指向 值传递 不穿引用本身 
* Integer.MAX_VALUE + 1 = Integer.MIN_VALUE 反之min-1=max
* 子类数组和父类数组不是父子关系，没有任何关系
* CodePoint 是字符串中物理意义上的一个字符，codepoint在目前Java（UTF-16 编码）中由一个或两个char组成


##shortcuts

* 编译选择的文件、包或模块 command+shift+f9
* 跳转到声明处 command+b
* 显示方法层次结构 command+shift+h command+f12
* 前往父类所在方法 command+u
* 打开项目结构对话框 command+;
* 大小写切换 command+shift+u
* 从工具窗口进入代码文件窗口 esc
* 自动缩进行 ctrl+option+i
* 基本代码补全 ctl+space
* 展开所有代码块 command+shift++