# ES可视化界面

**概念：** ES不同于Solr自带图形化界面，但可以在谷歌浏览器上安装ES的head插件，完成图形化界面的效果，完成索引数据的查看，但安装ES的head插件之前，需要先安装node.js和grunt。
    - 
- 安装node：傻瓜式安装:
    - `node-v14.16.0-x64.msi`
    - cmd: `node -v`：查看node是否安装成功。
- 安装grunt：
    - cmd: `npm install -g grunt-cli`
    - cmd: `grunt -version`：grunt是否安装成功。
- 将 `elasticsearch-head-master.zip` 解压缩到硬盘：
    - 在根目录下的 `Gruntfile.js` 中找到 `connect:server:options` 块并添加 `hostname: '*'`。
- 在 `elasticsearch-head-master` 根目录下执行安装：
    - cmd: `npm install`
- 运行插件（每次启动都要执行）：
    - cmd: `npm run start` 或 `grunt server`
- cli: `localhost:9100`