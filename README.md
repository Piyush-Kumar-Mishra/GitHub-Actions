## ▣ CI workflow defining build, unit test, instrumentation test & packaging stages.
### Pipeline order
- build
- unit-test
- instrumentation-test
- Build APK


Detekt is used for static analysis of Kotlin code

| `./gradlew.bat testDebugUnitTest` | `./gradlew.bat detekt` | `./gradlew.bat lintDebug` | `./gradlew.bat jacocoTestReport` |
|--------------------|------------------|------------------|----------------------|


## ⬤  Jacoco Test Report
<img width="1400" height="300" alt="image" src="https://github.com/user-attachments/assets/787f66f4-7110-4fb9-8ef5-7fd41dd26422" />




  
## ⬤  Workflow
<img width="981" height="778" alt="image" src="https://github.com/user-attachments/assets/8747b2c5-cf36-4d60-9048-76ce68eba690" />
