# Android Basics with JatPack Compose
JetPack Compose 是新的 UI 工具  

## Kotlin Basics
Kotlin Playground https://play.kotlinlang.org/  
```
fun main() {
    /*
     * 这里可以插入整段的多行注释
     * 一般在程序头部添加说明 
     */
    val count: Int = 2
    println("Do you have $count books?")
    println("Do you have ${count + count} books?")
    // 适当使用空格，在符号前后
    val price: Double = 9.98
    println("Say \"hello\"!")
}
```

根据变量值是否可以改变，使用不同的关键词：  
* val keyword - 变量值不会改变
* var keyword - 变量值回改变

留意自定义函数对参数和返回值类型的定义：  
```
fun main() {
    println(birthdayGreeting(age = 5))
    println(birthdayGreeting("Rex", 2))
}

fun birthdayGreeting(name: String = "Rover", age: Int): String {
    val nameGreeting = "Happy Birthday, $name!"
    val ageGreeting = "You are now $age years old!"
    return "$nameGreeting\n$ageGreeting"
}
```

## Types and Data Structures
val 关键词与 Java 中的 final 类似，定义的变量对象不可以改变。  
但是，变量对象自身的内容或状态可以改变，比如数组的成员是可变的：  
```
fun main() {
   val myMutableList = mutableListOf(1, 2, 3, 4, 5)
   myMutableList.add(6)
   println(myMutableList)
}
```
const 关键词定义的常量也是不可变，但存在如下限制条件：  
* 必须在程序运行前进行初始化，不可在程序运行中确定  
* 只能是基本数据类型，比如整形、浮点或字符串，不可以是数组  
* 只能在程序头部定义，不可以在任何方法内部定义  

除了 readln 也可以用 Scanner 读取输入，注意是否为整行读取：  
```
import java.util.Scanner
// or import java.util.*

fun main() { 
    val number = readln().toDouble() 
    val logNumber = Math.log10(number)
    println(logNumber) 

    val line1 = readLine()!! // 旧的整行读取方法 < Kotlin v1.6
    val line2 = readln() // 新的整行读取方法

    val scanner = Scanner(System.`in`)
    val line = scanner.nextLine() // 读入一整行，如 "Hello, Kotlin"
    val num = scanner.nextInt()   // 读入一个整数，如 123 注意不是整行
    val string = scanner.next()   // 读入一个字符串，如 "Hello" 注意不是整行
}
```

注意转换数字到字符再到数字的转换，使用两种不同的方法：  
```
fun main() {
    val n1: Int = 125
	val ch: Char = n1.toChar() // '}'
	val n2: Int = ch.code
    
    println(n1)
    println(ch)
    println(n2)
}
```

注意转换大数到小数时可先到 Int 再做转换，以免发生意外：  
```
val floatNumber = 10f
val doubleNumber = 1.0

// 实际上，一下两句在编译时会报错，提示要先转化成 Int
val shortNumber = floatNumber.toShort() // avoid this
val byteNumber = doubleNumber.toByte()  // avoid this

val shortNumber = floatNumber.toInt().toShort() // correct way
val byteNumber = doubleNumber.toInt().toByte()  // correct way
```

只有字符串可以转换为布尔值，可以区分或不区分大小写，兼容或不兼容
```
fun main() {
    println("true".toBoolean())
    println("True".toBoolean())
    println("fAlse".toBoolean())
    println("T".toBoolean())
    println()
    
    println("true".toBooleanStrict())     // true
	// println("True".toBooleanStrict())  // program crashes
	println("false".toBooleanStrict())    // false
	// println("faLse".toBooleanStrict()) // program crashes
	println()
    
	println("true".toBooleanStrictOrNull())  // true
	println("false".toBooleanStrictOrNull()) // false
	println("faLse".toBooleanStrictOrNull()) // null
}
```

逻辑操作符 ！&& 和 || 没有什么特别，只是 xor 异或比较有趣。  
除非操作符两边的布尔值不同才返回 true 否则异或操作符总是返回 false  
== 操作符只能比较同一类型的数据，其他操作符号没有这个限制。  

if 是带返回值的 expression 而不是 statement 如此使用时必须有 else  
```
val max = if (a > b) {
    println("Choose a")
    a
} else {
    println("Choose b")
    b
}
// 如上代码可以简化为如下
val max = if (a > b) a else b
```
如上 if 和 else 返回的值建议为同一数据类型，但不做绝对要求。  

除了整数，字符或字符串也可以使用 .. 来表示范围：  
```
println(5 in 2..7) // true
println(8 in 2..7 || 9..11) // false

println('b' in 'a'..'c') // true
println('k' in 'a'..'e') // false

println("hello" in "he".."hi") // true
println("abc" in "aab".."aac") // false
```
注意如果是反向的范围表示，要用 downTo 而不是 ..  
```
for (i in 4 downTo 1) {
    println(i)
}
```
如果正向范围不包括最大值，可以用 until  
```
for (i in 1 until 4) {
    println(i)
}
```
如不不是连续值，可指明 step  
```
for (i in 1 until 10 step 2) {
    println(i)
}
```

用 when 构造类似 switch 的选择逻辑结构：  
```
fun main() {
    // 一个标准的 when 例子
    val (var1, op, var2) = readln().split(" ")
    val a = var1.toInt()
    val b = var2.toInt()
    when (op) {
        "+", "plus" -> println(a + b)
        "-", "minus" -> println(a - b)
        "*", "times" -> println(a * b)
        else -> println("Unknown operator") // 这里 else 可选
    }
    // 一个 expression 表达式的例子
    println(when (op) {
        "+", "plus" -> a + b
        "-", "minus" -> a - b
        "*", "times" -> a * b
        else -> "Unknown operator" // 这里 else 必须
    })
    // 另一种方式
    when {
        op == "+", op == "plus" -> println(a + b)
        op == "-", op == "minus" -> println(a - b)
        op == "*", op == "times" -> println(a * b)
        else -> println("Unknown operator") // 这里 else 可选
    }
}
```
一切服从代码可读性和简化的原则下，以上方式都可以。  
```
fun main() {
    val (num1, op, num2) = readln().split(" ")
    val x = num1.toLong()
    val y = num2.toLong()
    println(
        when (op) {
            "+" -> x + y
            "-" -> x - y
            "*" -> x * y
            "/" -> 
                if (y == 0.toLong()) {
                    "Division by 0!"
                } else {
                    x / y
                }
            else -> "Unknown operator"
        }
    )
}
```

fun main() {
    val (when, what) = readln().split("节")
    val x = num1.toLong()
    val y = num2.toLong()
    println(
        when (op) {
            "+" -> x + y
            "-" -> x - y
            "*" -> x * y
            "/" -> 
                if (y == 0.toLong()) {
                    "Division by 0!"
                } else {
                    x / y
                }
            else -> "Unknown operator"
        }
    )
}

变量名大小写敏感，可包含字母、数字和 _ 但不能以数字开始，也不能是关键字。  
有个特例是变量名可以包含空格，用反向单引号即可，如：`good name` = 5  
推荐使用首字母小写的驼峰命名法，例如：goodNamedVariable  

意义不明确的数会被认为是 magic numbers 是需要尽量避免使用的。  
一个好的习惯是定义常量：const val SEASONS_NUM = 4  
注意常量需要定义在所有 fun 之外且使用全大写加下划线的命名规则。  

从 Kotlin 1.4 开始，在定义函数时可以在最后一个形式参数后加上逗号：  
```
fun max(
    a: Int,
    b: Int,
): Int { // 可以交换形式参数而不必在意逗号
    // 
}
```
IDE 也会根据逗号来自动格式化代码，这在其他编程语言中也是推荐做法。  

不返回任何值的函数可省略返回值定义，或指定返回 Unit 类型。  
另外，只有一条语句的函数也可以用如上的简化方式定义，省略大括号。 
对于此类函数，编译器自己判断返回值的类型，如下会函数会返回整形值：  
```
fun sum(a: Int, b: Int) = a + b // Int
```

字符类型可以用默认方法转换成数字，或者直接转换 Unicode  
```
fun main() {
    println('\u0041'.toChar())  // unicode in hex
    println('A'.code) // unicode in decimal
    
    val num = 'A'.code
    println(num.toChar()) // number to char

    // read one char from standard input
    val ch: Char = readln().first()

    val ch1 = 'b'
    val ch2 = ch1 + 1 // 'c'
    val ch3 = ch2 - 2 // 'a'

    var ch = 'A'
    ch += 10
    println(ch)   // 'K'
    println(++ch) // 'L'
    println(++ch) // 'M'
    println(--ch) // 'L'
}
```

字符串可以当作一个字符的数组来对待：  
```
val greeting = "Hello"
// 用索引值访问字符串中的字符
val first = greeting[0]  // 'H'
val second = greeting[1] // 'e'
val five = greeting[4]   // 'o'
// 用字符串的长度属性访问末位字符
val last = greeting[greeting.length - 1] // 'o'
val prelast = greeting[greeting.length - 2] // 'l'
// 用字符串默认方法
println(greeting.first())   // 'H'
println(greeting.last())    // 'o'
println(greeting.lastIndex) // 4
// 确认一个字符串是否为空
val emptyString = ""
println(emptyString.length == 0) //true
println(emptyString.isEmpty()) //true
```

可以使用数组初始化方法或通过控制台标准输入初始化数组：  
```
// 静态初始化
val array = intArrayOf(1, 2, 3, 4,)
parintln(array.joinToString())
// 从控制台标准输入初始化，逐行输入
val numbers = IntArray(5) { readln().toInt() }
println(numbers.joinToString())
// 从控制台初始化，一行输入，用空格分隔元素
val numbers = readln().split(" ").map { it.toInt() }.toTypedArray()
println(numbers.joinToString())
// 使用正则表达式判断分隔符
val regex = "\\s+".toRegex()
val str = "1 2\t\t3  4\t5  6"
val nums = str.split(regex).map { it.toInt() }.toTypedArray()
println(nums.joinToString()) // 1, 2, 3, 4, 5, 6
// 注意要使用特殊的方法比较两个数组的内容
val numbers1 = intArrayOf(1, 2, 3, 4)
val numbers2 = intArrayOf(1, 2, 3, 4)
val numbers3 = intArrayOf(1, 2, 3)
println(numbers1.contentEquals(numbers2)) // true
println(numbers1.contentEquals(numbers3)) // false
```

对于字符串类型的数组，可以用如下方式初始化：  
```
val stringArray1 = arrayOf("array", "of", "strings")
// 或者也可以指明类型
val stringArray2 = arrayOf<String>("array", "of", "strings")
// 或者初始化一个空的数组
val newEmptyArray = emptyArray<String>()
```
再次提醒，一个用 val 定义的数组可以修改元素值但不能添加或删除元素。  

列表只能包含同类型数据，有几种初始化可变列表的方式：  
```
// 初始化一个空的可变列表
val mutList: MutableList<Int> = mutableListOf()
// 使用 MutableList 方法
val list1 = MutableList(5) { 0 }
println(list) // [0, 0, 0, 0, 0]
// 使用 MutableList 方法和 readln 逐行输入初始化值
val list2 = MutableList(5) { readln().toInt() }
println(list)
// 使用 readln 并在一行中输入所有列表初始化值
val list3 = readln().split(" ").map { it.toInt() }.toMutableList()
println(list3)
// 指明列表元素，让系统自己决定数据类型
val mutableListA = mutableListOf(1, 2, 3, 4, 5)
println(mutableListA) // [1, 2, 3, 4, 5]
// 指明列表元素和数据类型
val mutableListB = mutableListOf<Int>(1, 2, 3, 4, 3)
println(mutableListB)
```
如果读入的数据存在多余制表符和空格，可用正则表达式分隔：  
```
val regex = "\\s+".toRegex()
val str = "1 2\t\t3  4\t5  6"
val nums = str.split(regex).map { it.toInt() }.toMutableList()
println(nums.joinToString()) // 1, 2, 3, 4, 5, 6
```

一些可以方便操作列表的函数：  
```
fun main() {
	val southernCross = mutableListOf("Acrux", "Gacrux", "Imai", "Mimosa")
    println(southernCross.joinToString())  // Acrux, Gacrux, Imai, Mimosa
    println(southernCross.joinToString(" -> "))  // Acrux -> Gacrux -> Imai -> Mimosa
    
    println(southernCross.removeAt(0)) // Acrus
    println(southernCross) // ["Gacrux", "Imai", "Mimosa"]
    println(southernCross.remove("Gacrux")) // true
    println(southernCross) // ["Imai", "Mimosa"]
    
    southernCross.add("Gacrux")
    println(southernCross.joinToString()) // Imai, Mimosa, Gacrux
    southernCross.add(2, "Acrux") // 在指定索引位置插入指定字符串
    println(southernCross.joinToString()) // Imai, Mimosa, Acrux, Gacrux
    println("sorted: ${southernCross.sorted()}")
    println("sortedDesc: ${southernCross.sortedDescending()}")
    
    southernCross.addAll(southernCross) // 合并两个数组
    println(southernCross.joinToString())
    val list = southernCross.subList(2, 6)
    println(list.joinToString()) // 从索引位2开始取四位
    val index = list.indexOf("Imai")
    println("index of Imai: $index")
    println("min: ${list.minOrNull()}")
    println("max: ${list.maxOrNull()}")
    
    southernCross.clear() // 清除数组元素
    println(southernCross)
    println("empty? ${southernCross.isEmpty()}")
    southernCross += "Acrux" // 添加元素到数组
    println(southernCross)
    println("not empty? ${southernCross.isNotEmpty()}")
    southernCross += southernCross // 添加数组到数组
    println(southernCross)
    val include = "Acrux" in southernCross
    println("Acrux in list? $include")
    println("sum of 1 to 5: ${mutableListOf(1,2,3,4,5).sum()}")
}
```

使用 for 可以遍历整个可变数组：  
```
fun main() {
    val daysOfWeek = mutableListOf("Sun", "Mon", "Tues", "Wed", "Thur", "Fri", "Sat")

    for (day in daysOfWeek){
        println(day) // 逐行打印数组所有元素
    }
    println()

    for (index in daysOfWeek.indices){
        println("${index + 1}: ${daysOfWeek[index]}") // 使用数组索引打印元素和索引
    }
    println()
    
	for (index in 0 until daysOfWeek.lastIndex + 1 step 2) {
        println("${index + 1}: ${daysOfWeek[index]}")
    }
    println()
    
    for (index in daysOfWeek.lastIndex downTo 0 step 3) {
        println("${index +1}: ${daysOfWeek[index]}")
    }
}
```

### Collections
Kotlin 支持如下几种集合，他们一般保存同类型数据，可以是不可变或可变类型：  
* List - 按顺序保存元素，可通过索引值访问元素，元素值可重复  
* Set - 元素值唯一，不可重复，且顺序不重要  
* Map - 保存键值对形式的元素，键唯一，但值可以有重复，数据类型可不同  
集合拥有如下通用属性或方法：  
* size - 返回集合中的元素数量  
* contains(element) - 确认是否包含指定元素  
* containsAll(elements) - 确认是否包含所有指定元素  
* isEmpty() - 确认集合是否为空  
* joinToString() - 输出全部元素，使用默认逗号分隔符  
* indexOf(element) - 返回指定元素的索引值  
可变类型的集合还拥有如下方法：  
* clear() - 清除所有元素  
* remove(element) - 清除指定元素  
* removeAll(elements) - 清除指定的多个元素  
用 element in collection 可实现与 collection.contains(element) 同样效果。  
实际上，根据可读性原则，使用 in 是推荐的判断方式：  
https://kotlinlang.org/docs/idioms.html#check-the-presence-of-an-element-in-a-collection 


### Composable function 
Composable function 名必须为名词且命名符合 PascalCased 规则。  
即 DoneButton() 而不是 Done() 或 doneButton() 或 Done()  

### The Project
```
import java.util.Scanner

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    var total = 0
    var times = 1
    var type: String
    
    when (args[0]) {
        "-dataType" -> type = args[1]
        else -> type = "word"
    }
    when (type) {
        "long" -> {
            var greatest = 0
            while (scanner.hasNext()) {
                total += 1
                val next = scanner.nextInt()
                when {
                    next > greatest -> {
                        greatest = next
                        times = 1}
                    next == greatest -> times += 1
                }
            }
            val percent = times.toDouble() / total.toDouble() * 100
            println("Total numbers: $total.")
            println("The greatest number: $greatest ($times time(s), $percent%).")
        }
        "line" -> {
            var greatest = ""
            while (scanner.hasNext()) {
                total += 1
                val next = scanner.nextLine()
                when {
                    next.length > greatest.length -> {
                        greatest = next
                        times = 1}
                    next == greatest -> times += 1
                }
            }
            val percent = times.toDouble() / total.toDouble() * 100
            println("Total lines: $total.")
            println("The longest line:\n$greatest\n($times time(s), $percent%).")
        }
        "word" -> {
            var greatest = ""
            while (scanner.hasNext()) {
                total += 1
                val next = scanner.next()
                when {
                    next.length > greatest.length -> {
                        greatest = next
                        times = 1}
                    next == greatest -> times += 1
                }
            }
            val percent = times.toDouble() / total.toDouble() * 100
            println("Total words: $total.")
            println("The greatest word: $greatest ($times time(s), ${percent.toInt()}%).")
        }
    }
}
```