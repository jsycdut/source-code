# openjdk8
看了周志明的《深入理解Java虚拟机》，想着编译一把jdk，书上写的jdk7，编译的时候要配置的问题比较多，但是jdk8的编译较为简单，但是依然在过程中遇到了很多问题。

从源代码编译jdk8, 未修改过的源代码文件为`openjdk-8-src-b132-03_mar_2014.zip`，个人在编译过程中遇到了诸多无奈的问题，比如`-Werror`导致的warning as error问题，gcc版本(7.4)的问题，cpp函数声明位置，系统内核版本问题等，这其中很大一部分都需要修改源代码里面的配置文件，所以我将这些需要修改的东西都修改好了，并且也能正确编译出可运行的jdk。

## 个人环境

这里比较需要注意的是gcc的版本，较新的版本比如7.4可能会报错

```bash
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

```

## 提前预备资料
- [README](./openjdk/README)
- [README-builds.html](./openjdk/README-builds.html)
- [参考资料](https://www.cnblogs.com/sunilsun/p/6078171.html)

## 编译流程
```bash
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
