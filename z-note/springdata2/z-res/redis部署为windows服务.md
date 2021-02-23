# redis部署成Windows服务

**概念：** cmd启动的redis服务在窗口关闭时会自动关闭，可以将redis部署为一个windows服务以保持长时间的启动：
- 部署流程：部署后可在 `计算机/服务` 中找到redis服务，推荐启动类型设置为手动：
    - cmd: `redis-server --service-install test/6379.conf --service-name redis6379`
- 常用的redis服务命令：任何服务重复启动均会报错：
    - cmd: `redis-server --service-uninstall --service-name redis6379`：在redis根目录下卸载redis6379服务。
    - cmd: `redis-server --service-start --service-name redis6379`：在redis根目录启动redis6379服务。
    - cmd: `redis-server --service-stop --service-name redis6379`：在redis根目录下停止redis6379服务。
    - cmd: `net start redis6379`：在任意位置启动redis6379服务。
    - cmd: `net stop redis6379`：在任意位置停止redis6379服务。