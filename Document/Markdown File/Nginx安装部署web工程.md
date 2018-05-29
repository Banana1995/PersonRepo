# Nginx安装部署web工程

## Nginx安装

###1. 下载依赖包

- 如果你的服务器可以连接网络的话可以直接通过命令的方式下载tar包

  1. 下载PCRE库

     `wget ftp://ftp.csx.cam.ac.uk/pub/software/programming/pcre/pcre-8.38.tar.gz  `

  2. 下载zlib库

     `wget http://zlib.net/zlib-1.2.10.tar.gz `

  3. 下载OpenSSL库

     `wget https://www.openssl.org/source/openssl-1.0.2o.tar.gz `

- 如果你的服务器无法联网，可以自行去上面的`wget`命令后面的地址下载对应的tar包文件，ftp路径也可以直接通过浏览器打开，再将他们传到服务器中。

### 2. 安装依赖包

- 安装PCRE库

  -  `tar -zxvf pcre-8.37.tar.gz ` 先使用命令解压tar包

  - `cd pcre-8.38` 进去解压后的文件目录

  - `./configure` 运行初始化脚本

    **如果此时你不是用root用户安装，或者希望指定安装根路径，可以使用`--prefix=`参数来指定安装路径** 

    如：使用`./configure --prefix=/home/test/pcre`命令来指定将pcre安装到`/home/tese/pcre`目录下

  - `make` 使用make命令尝试编译

  - `make install` 编译安装

- 安装zlib库

  -  `tar -zxvf zlib-1.2.10.tar.gz ` 先使用命令解压tar包

  - `cd zlib-1.2.10` 进去解压后的文件目录

  - `./configure` 运行初始化脚本

    **如果此时你不是用root用户安装，或者希望指定安装根路径，可以使用`--prefix=`参数来指定安装路径** 

    如：使用`./configure --prefix=/home/test/zlib`命令来指定将zlib安装到`/home/test/zlib`目录下

  - `make` 使用make命令尝试编译

  - `make install` 编译安装

- 安装OpenSSL库

  - `tar -zxvf openssl-1.0.2o.tar.gz ` 使用命令解压tar包即可

### 3. 安装Nginx

####3.1 安装步骤

- 先下载Nginx的tar包，我这里选择的是最新的稳定版`nginx-1.14.0.tar.gz`

  跟上面安装依赖包一样，如果你的服务器可以联网的话建议使用`wget http://nginx.org/download/nginx-1.14.0.tar.gz `命令来下载tar包；如果不能联网的话建议自己通过浏览器访问http地址下载

- `tar -zxvf nginx-1.14.0.tar.gz ` 解压Nginx的tar包

- `cd nginx-1.14.0` 进入解压后的目录

- `./configure`运行初始化脚本

  **注意：Nginx默认的安装路径是`/usr/local/nginx`,如果你不是使用root用户的话，就不能使用该路径。这里同样可以使用`--prefix=`参数来指定安装路径** 

  同时，你可以使用`--with-pcre=/home/test/pcre-8.38`来指定pcre安装路径

  使用`--with-zlib=/home/test/zlib`来指定zlib的安装路径

- `make` 使用make命令尝试编译

- `make install` 编译安装

- `cd sbin` 进入sbin目录下

- `./nginx` 启动Nginx  同时可以使用 `./nginx -s reload` 重启Nginx 使用`./nginx -s stop` 关停Nginx

#### 3.2 Nginx常用编译选项

> make是用来编译的，它从Makefile中读取指令，然后编译。
>
> make install是用来安装的，它也从Makefile中读取指令，安装到指定的位置。
>
> configure命令是用来检测你的安装平台的目标特征的。它定义了系统的各个方面，包括nginx的被允许使用的连接处理的方法，比如它会检测你是不是有CC或GCC，并不是需要CC或GCC，它是个shell脚本，执行结束时，它会创建一个Makefile文件。nginx的configure命令支持以下参数：
>
> - `--prefix=*path*`    定义一个目录，存放服务器上的文件 ，也就是nginx的安装目录。默认使用 `/usr/local/nginx。`
>
> - `--sbin-path=*path*` 设置nginx的可执行文件的路径，默认为  `*prefix*/sbin/nginx`.
>
> - `--conf-path=*path*`  设置在nginx.conf配置文件的路径。nginx允许使用不同的配置文件启动，通过命令行中的-c选项。默认为`*prefix*/conf/nginx.conf`.
>
> - `--pid-path=*path*  设置nginx.pid文件，将存储的主进程的进程号。安装完成后，可以随时改变的文件名 ， 在nginx.conf配置文件中使用 PID指令。默认情况下，文件名 为``*prefix*/logs/nginx.pid`.
>
> - `--error-log-path=*path*` 设置主错误，警告，和诊断文件的名称。安装完成后，可以随时改变的文件名 ，在nginx.conf配置文件中 使用 的error_log指令。默认情况下，文件名 为`*prefix*/logs/error.log`.
>
> - `--http-log-path=*path*`  设置主请求的HTTP服务器的日志文件的名称。安装完成后，可以随时改变的文件名 ，在nginx.conf配置文件中 使用 的access_log指令。默认情况下，文件名 为`*prefix*/logs/access.log`.
>
> - `--user=*name*`  设置nginx工作进程的用户。安装完成后，可以随时更改的名称在nginx.conf配置文件中 使用的 user指令。默认的用户名是nobody。
>
> - `--group=*name*`  设置nginx工作进程的用户组。安装完成后，可以随时更改的名称在nginx.conf配置文件中 使用的 user指令。默认的为非特权用户。
>
> - `--with-select_module` `--without-select_module 启用或禁用构建一个模块来允许服务器使用select()方法。该模块将自动建立，如果平台不支持的kqueue，epoll，rtsig或/dev/poll。`
>
> - `--with-poll_module` `--without-poll_module` 启用或禁用构建一个模块来允许服务器使用poll()方法。该模块将自动建立，如果平台不支持的kqueue，epoll，rtsig或/dev/poll。
>
> - `--without-http_gzip_module` — 不编译压缩的HTTP服务器的响应模块。编译并运行此模块需要zlib库。
>
> - `--without-http_rewrite_module`  不编译重写模块。编译并运行此模块需要PCRE库支持。
>
> - `--without-http_proxy_module` — 不编译http_proxy模块。
>
> - `--with-http_ssl_module` — 使用https协议模块。默认情况下，该模块没有被构建。建立并运行此模块的OpenSSL库是必需的。
>
> - `--with-pcre=*path*` — 设置PCRE库的源码路径。PCRE库的源码（版本4.4 - 8.30）需要从PCRE网站下载并解压。其余的工作是Nginx的./ configure和make来完成。正则表达式使用在location指令和 ngx_http_rewrite_module 模块中。
>
> - `--with-pcre-jit` —编译PCRE包含“just-in-time compilation”（1.1.12中， pcre_jit指令）。
>
> - `--with-zlib=*path*` —设置的zlib库的源码路径。要下载从 zlib（版本1.1.3 - 1.2.5）的并解压。其余的工作是Nginx的./ configure和make完成。ngx_http_gzip_module模块需要使用zlib 。
>
> - `--with-cc-opt=*parameters*` — 设置额外的参数将被添加到CFLAGS变量。例如,当你在FreeBSD上使用PCRE库时需要使用:`--with-cc-opt="-I /usr/local/include。`.如需要需要增加 `select()支持的文件数量`:`--with-cc-opt="-D FD_SETSIZE=2048".`
>
> - `--with-ld-opt=*parameters*` —设置附加的参数，将用于在链接期间。例如，当在FreeBSD下使用该系统的PCRE库,应指定:`--with-ld-opt="-L /usr/local/lib".`
>
> - 典型实例(下面为了展示需要写在多行，执行时内容需要在同一行)
>
>   ```
>   ./configure
>       --sbin-path=/usr/local/nginx/nginx
>       --conf-path=/usr/local/nginx/nginx.conf
>       --pid-path=/usr/local/nginx/nginx.pid
>       --with-http_ssl_module
>       --with-pcre=../pcre-4.4
>       --with-zlib=../zlib-1.1.3
>   ```
>
>   

##部署web工程

### 1. 关于Nginx配置文件

### 2. 部署前端工程



## 遇到的问题



## 参考链接

[Nginx安装](http://www.nginx.cn/install)

