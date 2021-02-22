# redis for linux安装

**流程：**
- `wget http://download.redis.io/releases/redis-3.2.0.tar.gz`：在线下载redis。
- `tar -zxvf redis-3.2.0.tar.gz`：解压缩redis。
- `In -s redis-3.2.0 redis`：建立软连接。
- `cd redis`：进入redis目录。
- `make`：进行编译。
- `make install`：安装并生成可执行文件，之后就可以在任意一个目录下执行redis命令。