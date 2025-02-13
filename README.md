
The app consists of three screens that display images loaded from different sources: the internet, disk storage, and Android resources. It utilizes the following libraries:


**Coil: For efficient image loading and displaying.

**Decompose: For seamless navigation between screens.

**MVIKotlin: For implementing the MVI (Model-View-Intent) architecture.



-------------

Screens Overview
Screen 1: Displays a list of images fetched from the internet using Retrofit and an API. The images are loaded and displayed using Coil.

Screen 2: Displays a list of images in JPEG and SVG formats retrieved from disk storage. The images are first downloaded to android app, saved to the device's storage, and then displayed using Coil.

Screen 3: Displays a list of images in JPEG and SVG formats loaded directly from Android resources .

---------------
