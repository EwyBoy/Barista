# Barista Changelog

### v1.3.0

* Added support for 1.21.1
* Switched the Forge module to NeoForge
* Fixed the session timer counting minutes past 60, so an hour and five minutes now reads `1hrs 5min` instead of `1hrs 65min`
* Window title updates are now rate limited on a clock instead of a render counter, which cuts the work done per frame considerably
* The mod no longer claims compatibility with Minecraft versions newer than 1.21.1

### v1.2.0

* Added support for 1.20
