

## Algorithm



## Review

continue reading 《effective java》.

- Item 5 : Prefer Dependency injection to hardwiring resources.

  > 1. static utility classes and singletons are inappropriate for classes whose behavior is parameterized by an underlying resource.
  > 2. a simple pattern that satisfies that requirement is to pass the resource into the constructor when creating instance. This is one form of Dependency injection .
  > 3. Do not use a singleton or static utility class to implements a class that depends on one or more   underlying resource whose  behavior affects that of class. And do not have the class create these resource.Instead, pass the resources,or factories to create them, into the constructor.

- Item 6 : Avoid creating unnecessary object.

  > 1. you can often avoid creating unnecessary object by using `static factory method` in preference to constructor on immutable classes that provide both.like use `String a= "banana"` ,not `String a = new String("banana")`. Besides,`Boolean(String)` is deprecated in Java 9,cause it must create the instance each time it's invoked.The factory method `Boolean.valueOf(String)` is preferable to the constructor. 
  > 2. you can also reuse the **mutable **object if you know it won't be modified. Some object creations are so expensive , cache it for reuse will provides significant performance gains if they are invoked frequently. if the object is immutable , it is obvious it can be reused safely.
  > 3. Another way to create unnecessary object is **autoboxing** .we should prefer primitives to boxed primitives , and watch out unintentional autoboxing.
  > 4. Conversely,avoid object creation by maintaining you own object pool is a bad idea,unless the object is extremely heavyweight.

## Tips



## Share





