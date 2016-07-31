<h1 align="center">kotlin-cycle</h1>

<p align="center">🚴🚲🚵🚴🚲🚵🚴🚲🚵</p>

[![Download][bintray-badge]][version]

> :arrows_counterclockwise: [Cycle][cycle.js] for Android (written in Kotlin)


## Settings

```gradle
repositories {
    jcenter()
}

dependencies {
    compile 'com.importre:kotlin-cycle:<VERSION>'
}
```


## Example

```kotlin
class HelloCycleActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hello)

        cycle {
            val change = dom.select(helloEdit).textChanges()
            val model = change.map(::greeting)
            model.map { message -> { helloText.text = message } }
        }
    }
}
```

| Hello, World!  | BMI          |
|:--------------:|:------------:|
| ![cycle-hello] | ![cycle-bmi] |


## References

### cycle.js

- http://cycle.js.org/
- https://github.com/cyclejs-community/cycle-android

### videos

- [Unidirectional data flow architectures](https://vimeo.com/168652278)
- [What if the user was a function?](https://youtu.be/1zj7M1LnJV4)


## License

Apache 2.0 © Jaewe Heo










[cycle.js]: http://cycle.js.org/
[cycle-hello]:  https://cloud.githubusercontent.com/assets/1744446/17257722/61ad44de-55fe-11e6-9c0f-4fbc2f139eba.gif
[cycle-bmi]: https://cloud.githubusercontent.com/assets/1744446/17257842/f101bfc0-55fe-11e6-88a5-bdb41cb4e523.gif
[bintray-badge]: https://api.bintray.com/packages/importre/maven/kotlin-cycle/images/download.svg
[version]: https://bintray.com/importre/maven/kotlin-cycle/_latestVersion
