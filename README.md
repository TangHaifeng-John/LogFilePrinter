
## 引入依赖库  


	项目根目录build.gradle文件添加
        allprojects {
            repositories {
                google()
                jcenter()
                maven { url 'https://jitpack.io' }
            }
        }
         
    
    需要使用日志存储的模块build.gradle文件添加
       dependencies {
            api 'com.github.TangHaifeng:LogFilePrinter:1.0.1'
        }


## 配置信息
```
//构建日志配置
LogConfig logConfig = new LogConfig.Builder()
		//设置日志文件的绝对路径，如果要写入外部存储卡，请添加存储权限
                .absolutePath(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "filelog" + File.separator + "test.log")
                //设置文件大小，目前日志只会生成一个文件，设置的文件大小后，会自动裁剪文件的前半段内容
                .fileSize(1 * 1024 * 1024)
                .build();
                //初始化
FileLog.init(logConfig);
```


## 使用方法

```			
FileLog.i("可以打印一条普通的提示信息")
FileLog.e("打印一条错误日志",new Exception())
```
## License

## 功能点
日志写入是在子线程，因此不影响性能
支持日志文件最大限制
支持设置日志文件路径
后续会增加多日志文件支持

<pre>
Copyright 2020 John

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
</pre>








