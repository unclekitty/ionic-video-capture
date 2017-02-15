# ionic-video-capture
Apache Cordova Plugin video-capture 

## Installation
    cordova plugin add https://github.com/unclekitty/ionic-video-capture.git

## Supported Platforms

- Android

## Build 

### in build.gradle add 
1
```
dependencies {
    compile fileTree(dir: 'libs', include: '*.jar')
    // SUB-PROJECT DEPENDENCIES START
    debugCompile project(path: "CordovaLib", configuration: "debug")
    releaseCompile project(path: "CordovaLib", configuration: "release")
    // SUB-PROJECT DEPENDENCIES END
    compile 'com.android.support:support-v4:20.0.0'
    compile 'com.android.support:support-annotations:22.2.0'
    compile 'com.android.support:appcompat-v7:23.1.1'
}
```
2
```
import com.ionicframework.video202440.R;
```
