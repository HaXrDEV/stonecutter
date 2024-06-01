# Stonecutter configuration
This page covers Stonecutter methods available in `build.gradle[.kts]` or `stonecutter.configureEach { }` in `stonecutter.gradle[.kts]`.  
For more information, visit the Dokka page.

## Processor config
### Swaps
Swaps are used to define preset replacements for certain values, 
which is less boilerplate than copy-pasting the same condition block.
::: code-group
```kotlin [build.gradle.kts]
stonecutter.swap("swap_token") {
    if (stonecutter.compare(stonecutter.current.version, "1.20.1")) "func1()" else "func2()"
}
```

```groovy [build.gradle]
stonecutter.swap("swap_token") {
    stonecutter.compare(stonecutter.current.version, "1.20.1") ? "func1()" : "func2()"
}
```
:::
The first argument is the identifier string, used in the comment. 
The second is the replacement value.

### Constants
Constants add variables, that can be checked in the code.
::: code-group
```kotlin [build.gradle[.kts]]
stonecutter.const("fabric", isFabric)
stonecutter.const("forge", isForge)
```
```kotlin [code.kt]
//? if fabric
println("good")
```
:::

### Dependencies
Stonecutter dependencies allow specifying additional targets for the version checker.
::: code-group
```kotlin [build.gradle[.kts]]
stonecutter.dependency("sodium", "0.5.3")
```
```kotlin [code.kt]
//? if sodium: >0.6
/*withSodium6()*/
```
:::
Implicit version checks default to `minecraft`, which means you can write:
```kotlin [code.kt]
//? if minecraft: >1.20.1
func()
```
You can also overwrite the checked minecraft dependency this way:
```kotlin [build.gradle[.kts]]
val mcVersion = stonecutter.current.version // = 1.20.1
stonecutter.dependency("minecraft", "1.20.6")
```
This may be useful in cases where you have both multi-version and multi-loader setup, but be careful with this knowledge.

### Filters
Files can be filtered with the following functions:
```kotlin
stonecutter.whitelist { it: Path ->
}

stonecutter.blacklist { it: Path ->
}
```

### Comparisons
Sometimes it can be handy to compare semantic versions in the buildscript (in a `swap` or `const` definition).
For this purpose `stonecutter.compare` exists:
````kotlin
val mcVersion = stonecutter.current.version // = 1.19.4
val isOneTwentyPlus = stonecutter.compare("1.20", mcVersion) >= 0 // false
````

## Plugin properties
### Project info
Object returned by the following methods contains:
- `project` - directory name of this subversion.
- `version` - specified Minecraft version.
- `isActive` - whenever this subversion is selected as active.

### Current version
Accessed with `stonecutter.current` and returns the project info for this instance of the buildscript.

### Active version
Accessed with `stonecutter.active` and returns the project info for the current active version, 
which is the same for all buildscript instances.

### All versions
Accessed with `stonecutter.versions` and returns the list of all registered subversions.

## Global config
Global options are configured in `stonecutter.gradle[.kts]`.  
Currently the only available option is `stonecutter.includeResources = true`, 
which removes the default `.java` and `.kt` file filter.