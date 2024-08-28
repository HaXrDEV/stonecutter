# Developing your mod
Remember the terminology part from the introduction? Me neither! So let's go over it again.

## Terminology again
In Stonecutter, you always have a single **active version**.
That is the version you write code in your `src` folder and build.
If you try to run or build an inactive version, it will crash shortly because your mod doesn't exist there at the moment.

## Managing versions
Provided you use IntelliJ IDEA, you should have a Gradle menu on the right.
There in the `stonecutter` group you'll see several tasks:
- "Refresh active version" - reruns the comment processor on the current version.
- "Reset active version" - switches the active version to `vcsVersion` from `settings.gradle[.kts]`. Remember to run this before commits.
- "Set active version to <>" - switches the active version to the selected one.

Switching the active version will apply the correct dependencies to the root source directory 
and modify the code using versioned commits, which will be covered later.

## Running Minecraft
Provided you have IDE-generated configs, you can use them.
Remember to launch the correct one.  
If you’re forgetful, you can include something like this in your `build.gradle[.kts]`:
::: code-group
```kotlin [build.gradle[.kts]]
if (stonecutter.current.isActive) {
    rootProject.tasks.register("buildActive") {
        group = "project"
        dependsOn(tasks.named("build"))
    }
}
```
:::

## Building the mod
Running `build` on the root project will result in the same error as mentioned above.
However, remember the chiseled task from the last chapter? Just in case:
::: code-group
```kotlin [stonecutter.gradle.kts]
plugins {
    id("dev.kikugie.stonecutter")
}
stonecutter active "1.20.6" /* [SC] DO NOT EDIT */

stonecutter registerChiseled tasks.register("chiseledBuild", stonecutter.chiseled) { // [!code focus:4]
    group = "project"
    ofTask("build")
}
```
```groovy [stonecutter.gradle]
plugins {
    id "dev.kikugie.stonecutter"
}
stonecutter.active "1.20.6" /* [SC] DO NOT EDIT */

stonecutter.registerChiseled tasks.register("chiseledBuild", stonecutter.chiseled) { // [!code focus:4]
    setGroup "project"
    ofTask "build"
}
```
:::
Similarly, you can create chiseled tasks for anything that requires all versions, such as publishing.