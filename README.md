# Mutator

## Why Mutator?

As the [immutable object](https://en.wikipedia.org/wiki/Immutable_object) becomes the default pattern of functional programming language, such as [Value Object](https://en.wikipedia.org/wiki/Value_object) in Java, and [Data Class](https://kotlinlang.org/docs/data-classes.html) in Kotlin, even the immutable pattern has a lot of advantages, modifying properties of deeply nested immutable objects is still not very convenient, for example:

```kotlin
data class Person(
    val name: String,
    val address: Address
)

data class Address(
    val nation: Nation,
    val state: String,
    val street: String
)

data class Nation(
    val name: String,
    val code: String
)
```

If we want to modify the `Person.address.nation.code`, we have to write code like this:

```kotlin
val person = Person(...)
// ...
val clone = person.copy(
    address = person.address.copy(
        nation = person.address.nation.copy(
            code = "New Code"
        )       
    )
)
```

## Getting Started

1. Add dependency into `build.gradle` or `build.gradle.kts`

    ```kotlin
    dependencies {
        implementation("io.johnsonlee:mutator:1.1.0")
    }
    ```

1. Call the extension function `mutate(...)`

    ```kotlin
    val clone = person.mutate(Person::address, Address::nation, Nation::code, "New Code")
    ```
   
    The code above is equivalent to the following

    ```kotlin
    val clone = person.copy(
       address = person.address.copy(
           nation = person.address.nation.copy(
               code = "New Code"
           )       
       )
    )
    ```


