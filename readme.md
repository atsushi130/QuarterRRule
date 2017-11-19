# Quarter RRule
[![apache licensed](https://img.shields.io/badge/License-Apache_2.0-d94c32.svg)](./license-apache.md)
[![quarter rrule](https://img.shields.io/badge/Kotlin-QuarterRRue-3B5998.svg)](https://github.com/atsushi130/QuarterRRule.git)
![kotlin](https://img.shields.io/badge/Language-Kotlin-f88909.svg)

## Usage
```kotlin
import quarter.rrule.QuarterRRule
import org.joda.time.LocalDate

class Sample(@Autowired private val quarterRRule: QuarterRRule) {
    fun sample() {
        val timestamp = this.quarterRRule.findTimeStampBy(LocalDate(2017, 1, 2))
        println(timestamp)   // 2017/01/01
    }
}
```