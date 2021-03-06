# appraisalOrg
// buildscript必须在顶部，注意位置
buildscript {
    ext {
        springBootVersion = '2.1.1.RELEASE'
    }

    repositories {
        // 优先使用国内源
        maven { url 'https://maven.aliyun.com/repository/public' }
        mavenCentral()
    }
    dependencies {
        // 让spring-boot支持gradle
        classpath("org.springframework.boot:spring-boot-gradle-plugin:${springBootVersion}")
    }
}

plugins {
    id 'java'
    id 'org.springframework.boot' version '2.1.1.RELEASE'
}

apply plugin: 'java'
apply plugin: 'scala'
// 使用spring boot
apply plugin: "org.springframework.boot"
// 使用spring boot的自动依赖管理
apply plugin: 'io.spring.dependency-management'

// apply plugin: 'application' //可选(可自动生成shell启动脚本)
// mainClassName = 'App' //可选（与上行的application插件配套出现）


//这里是关键（把java与scala的源代码目录全映射到scala上,
// 这样gradle compileScala时就能同时编译java与scala的源代码）

sourceSets {
    main {
        scala {
            srcDirs = ['src/main/scala', 'src/main/java']
        }
        java {
            srcDirs = []
        }
    }
    test {
        scala {
            srcDirs = ['src/test/scala', 'src/test/java']
        }
        java {
            srcDirs = []
        }
    }
}

group 'com.jingyou'
version '1.0-SNAPSHOT'

sourceCompatibility = 1.8
targetCompatibility = 1.8

repositories {
    // 使用国内的源
    maven { url 'https://maven.aliyun.com/repository/public' }
    mavenCentral()
}

//可选（项目初始化时,可用gradle cDirs生成scala及java的src目录）

//task createDirs << {
//    sourceSets*.scala.srcDirs*.each { it.mkdirs() }
//    sourceSets*.java.srcDirs*.each { it.mkdirs() }
//    sourceSets*.resources.srcDirs*.each { it.mkdirs() }
//}

dependencies {
    compile("org.springframework.boot:spring-boot-starter-data-jpa")
    compile 'org.springframework.boot:spring-boot-starter-web'
    compile("org.springframework.boot:spring-boot-starter-actuator")
    runtime("mysql:mysql-connector-java")
    compile "org.scala-lang:scala-library:2.11.8"
    compile "org.scala-lang:scala-compiler:2.11.8"
    compile "org.scala-lang:scala-reflect:2.11.8"
    testImplementation 'org.scalatest:scalatest_2.11:3.0.0'

    implementation 'org.apache.spark:spark-core_2.11:2.4.0'
    testCompile group: 'junit', name: 'junit', version: '4.12'
    compileOnly group: 'org.projectlombok', name: 'lombok', version: '1.18.8'
    compile 'org.apache.cxf:cxf-spring-boot-starter-jaxws:3.2.6' //引入cxf框架
}

//可选,如果想让生成的jar可直接运行,建议加上
jar {
    baseName = 'gradeAppraisal'
    version = "0.1"
    manifest {
        attributes 'Main-Class': 'com.jingyou.App'
    }
}

//configurations {
//    all*.exclude module: 'logback-classic'
//}
//创建初始目录：gradle cDirs (注：这是缩写方式，与gradle createDirs完全相同)
//编译： gradle compileScala
//生成jar包： gradle jar
//生成带启动脚本的可运行包： gradle installDist

