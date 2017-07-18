
/***
 *对于闭包(closure)，当外部函数返回之后，内部函数依然可以访问外部函数的变量。
 *代码中，外部函数f1只执行了一次，变量N设为0，并将内部函数f2赋值给了变量result。
 *由于外部函数f1已经执行完毕，其内部变量N应该在内存中被清除，
 *然而事实并不是这样：我们每次调用result的时候，
 *发现变量N一直在内存中，并且在累加。为什么呢？这就是闭包的神奇之处了！
 **/
function f1(){    
	var N = 0; // N是f1函数的局部变量

    function f2() // f2是f1函数的内部函数，是闭包    
    {
        N += 1; // 内部函数f2中使用了外部函数f1中的变量N
        console.log(N);
    }    
    return f2;
}
var result = f1();
result(); // 输出1result(); // 输出2result(); // 输出3
/**
 *模块化
 *JavaScript并非模块化编程语言，
 *至少ES6落地之前都不是。
 *然而对于一个复杂的Web应用，模块化编程是一个最基本的要求。
 *这时，可以使用立即执行函数来实现模块化，
 *正如很多JS库比如jQuery以及我们Fundebug都是这样实现的。
 **/
var module = (function() {  
  var N = 5;    
  function print(x) { 
         console.log("The result is: " + x);
    }
  function add(a) {  
        var x = a + N;
        print(x);
    }    
  return {
        description: "This is description",
        add: add
    };
})();
console.log(module.description); // 输出"this is description" 
module.add(5); // 输出“The result is: 10”
/**
 *函数重载
 *所谓函数重载(method overloading)，就是函数名称一样，但是输入输出不一样。
 *或者说，允许某个函数有各种不同输入，根据不同的输入，返回不同的结果。
 *凭直觉，函数重载可以通过if...else或者switch实现，这就不去管它了。
 *jQuery之父John Resig提出了一个非常巧(bian)妙(tai)的方法，利用了闭包。
 *从效果上来说，people对象的find方法允许3种不同的输入:
 *0个参数时，返回所有人名；1个参数时，根据firstName查找人名并返回；2个参数时，根据完整的名称查找人名并返回。
 *难点在于，people.find只能绑定一个函数，那它为何可以处理3种不同的输入呢？
 *它不可能同时绑定3个函数find0,find1与find2啊！这里的关键在于old属性。
 *由addMethod函数的调用顺序可知，people.find最终绑定的是find2函数。
 *然而，在绑定find2时，old为find1；同理，绑定find1时，old为find0。
 *3个函数find0,find1与find2就这样通过闭包链接起来了。
 *根据addMethod的逻辑，当f.length与arguments.length不匹配时，就会去调用old，直到匹配为止。
 **/

 function addMethod(object, name, f){
 	var old = object[name];　　
    object[name] = function()    {   
         // f.length为函数定义时的参数个数
        // arguments.length为函数调用时的参数个数　　　　
        if (f.length === arguments.length)
        {　　            
        	return f.apply(this, arguments);　　　　
        } else if (typeof old === "function")
        {            
        	return old.apply(this, arguments);　　　　
        }　　
    };
}
// 不传参数时，返回所有name
function find0(){　
	return this.names;
}
// 传一个参数时，返回firstName匹配的name
function find1(firstName){　　    
	var result = [];　　    
	for (var i = 0; i < this.names.length; i++)
    {　　　　        
    	if (this.names[i].indexOf(firstName) === 0)
        {　　　　　　
            result.push(this.names[i]);　　　　
        }　　
    }　　    
    return result;
}// 传两个参数时，返回firstName和lastName都匹配的name
function find2(firstName, lastName){　    
	var result = [];　　    
	for (var i = 0; i < this.names.length; i++)
    {　　　　        
    	if (this.names[i] === (firstName + " " + lastName))
        {　　　　　　
            result.push(this.names[i]);　　　　
        }　　
    }　　    
    return result;
}
var people = {　　
    names: ["Dean Edwards", "Alex Russell", "Dean Tom"]
};


addMethod(people, "find", find0);
addMethod(people, "find", find1);
addMethod(people, "find", find2);
console.log(people.find()); // 输出["Dean Edwards", "Alex Russell", "Dean Tom"]
console.log(people.find("Dean")); // 输出["Dean Edwards", "Dean Tom"]
console.log(people.find("Dean", "Edwards")); // 输出["Dean Edwards"]
