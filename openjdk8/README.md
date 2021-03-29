# build openjdk8 from source

周志明先生的《深入理解java虚拟机》第2版里面示例编译的是openjdk7，第3版里面编译的是openjdk12，个人偏好编译jdk8，详细内容可以参考[simple README](./openjdk/README)或者[Open JDK8 Build README](http://hg.openjdk.java.net/jdk8/jdk8/raw-file/tip/README-builds.html)以及书中相关章节。由于在编译时遇到了一些[问题](https://www.cnblogs.com/sunilsun/p/6078171.html)，我改动了里面的一点cpp源码，然后才有了现在的这个仓库（或者直接sed一把改源码是个更好的做法，避免了大量的代码上传）。编译最好是根据[官方的指导](http://openjdk.java.net/groups/build/doc/building.html)进行，并且最好是提前读完整个文档之后，对编译有一个大致的了解之后再进行，否则容易碰到各种各种的问题之后就失去信心。

## build on docker image

因为某些原因，我制作了一个开箱即可编译openjdk8的docker镜像，基于ubuntu 20.04 LTS，涵盖了所有需要的编译工具链，详细参见[jsycdut/openjdk8-build-env](https://hub.docker.com/r/jsycdut/openjdk8-build-env)，建议使用docker镜像启动容器进行编译，避免了自己配置环境的麻烦。

## build on your real machine

参考上方提到的参考资料是最好的方式，主要解决的就是依赖，尤其是gcc版本的问题，以下为个人编译过程的简要记录

```bash
# 以下为环境记录
➜  ~ uname -a
Linux dreamer 4.15.0-88-generic #88-Ubuntu SMP 
Tue Feb 11 20:11:34 UTC 2020 x86_64 x86_64 x86_64 GNU/Linux

➜  ~ gcc --version
gcc (Ubuntu 5.5.0-12ubuntu1) 5.5.0 20171010
Copyright (C) 2015 Free Software Foundation, Inc.
This is free software; see the source for copying conditions.  There is NO
warranty; not even for MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

➜  ~ g++ --version
g++ (Ubuntu 5.5.0-12ubuntu1) 5.5.0 20171010
Copyright (C) 2015 Free Software Foundation, Inc.
This is free software; see the source for copying conditions.  There is NO
warranty; not even for MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

➜  ~ make --version
GNU Make 4.1
Built for x86_64-pc-linux-gnu
Copyright (C) 1988-2014 Free Software Foundation, Inc.
License GPLv3+: GNU GPL version 3 or later <http://gnu.org/licenses/gpl.html>
This is free software: you are free to change and redistribute it.
There is NO WARRANTY, to the extent permitted by law.

➜  ~ java -version #这里是bootstrap jdk，需要jdk7
java version "1.7.0_80"
Java(TM) SE Runtime Environment (build 1.7.0_80-b15)
Java HotSpot(TM) 64-Bit Server VM (build 24.80-b11, mixed mode)

# 以下为编译简要记录
$ cd openjdk
$ bash ./configure # 检查编译环境
$ make all #进行编译，遇到的问题和花费的时间依据个人而异
----- Build times -------
Start 2020-03-04 17:04:53
End   2020-03-04 17:18:13
00:00:28 corba
00:00:28 demos
00:02:15 docs
00:04:35 hotspot
00:00:27 images
00:00:16 jaxp
00:00:23 jaxws
00:03:42 jdk
00:00:34 langtools
00:00:12 nashorn
00:13:20 TOTAL
-------------------------
Finished building OpenJDK for target 'all'
$ cd build/linux-x86_64-normal-server-release/images/j2sdk-image/bin 
$ ./java -version # 注意括号里面有我的用户名和编译的时间
openjdk version "1.8.0-internal"
OpenJDK Runtime Environment (build 1.8.0-internal-jsy_2020_03_04_15_33-b00)
OpenJDK 64-Bit Server VM (build 25.0-b70, mixed mode)
```
## debug

debug openjdk8 的hotspot可以参考下面的文章，注意openjdk 7和8的调试方法已经不同了，搜索相关资料需要注意版本。

- [Build OpenJDK9 on Mac OSX EI Captain](https://github.com/chaoyangnz/openjdk9/wiki/Build-OpenJDK9-on-Mac-OSX-EI-Captain)
- [调试HotSpot源代码](https://www.cnblogs.com/mazhimazhi/p/13217159.html)
