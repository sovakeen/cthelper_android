## Android app for CTHelper project

This app was developed as mobile interface for interacting with core CTH API (check out [this repo](https://github.com/Dmitruk-Bohdan/cth_api)), implementing students' interation with custom or community CT tests. App also includes mixed test generation, stats overview and profile management. Project utilises the following stack:
- core Kotlin 2.2.10, Gradle 9.3.1, AGP 9.1.1, KSP 2.3.7
- Jetpack Compose (BOM 2025.12.00, Material 3), Navigation Compose 2.9.8
- MVVM (ViewModel 2.10.0)
- Hilt 2.59.2 + hilt-navigation-compose for DI
- Retrofit 2.11.0 + OkHttp 4.12.0 as a network core
- kotlinx.serialization-json 1.7.3

### Known issues

- local deployment and hence local API required
- using plain SharedPreferences for auth token storage
- no tests coverage
- no license and CI

### Android app demo screenshots

<table>
    <tr>
        <td>Taking test</td>
        <td>Stats overview</td>
    </tr>
    <tr>
        <td><img src="/git-docs/screenshots/mob-stud-test-take.png" alt="mob-stud-test-take" width="300"/></td>
        <td><img src="/git-docs/screenshots/mob-stud-stats-overview.png" alt="mob-stud-stats-overview" width="300"/></td>
    </tr>
</table>

## Extras
### Web app

During CTH project developement, web app (primarily for teacher role) was implemented. Here are some demo screenshots (follow [this repo](https://github.com/Dmitruk-Bohdan/cth_django) for future updates):

![web-teach-test-manage](/git-docs/screenshots/web-teach-test-manage.png)
<sub>Teacher's screen for test management</sub>


![web-teach-test-manage](/git-docs/screenshots/web-stud-test-take.png)
<sub>Student's screen for test taking</sub>
