# ES可视化界面

**概念：** ES不同于Solr自带图形化界面，但可以在谷歌浏览器上安装ES的head插件，完成图形化界面的效果，完成索引数据的查看，但安装ES的head插件之前，需要先安装node.js和grunt。
    - `z-res/node-v14.16.0-x64.msi`
    - `z-res/elasticsearch-head-master.zip`

**流程：** 
- 安装node，下一步式安装直到安装完毕。
- CMD中使用 `node -v` 命令查看node是否安装成功。
- 安装grunt：CMD中执行 `npm install -g grunt-cli`。
    - 成功：`added 100 packages in 40.123s` 
- 使用 `grunt -version` 命令查看grunt是否安装成功。
    - 成功：`grunt-cli v1.2.0`
- 安装 `elasticsearch-head-master` 免安装版。
- 修改 `elasticsearch-head-master` 根目录下的 `Gruntfile.js`，添加 `hostname`。
- CMD进入到 `elasticsearch-head-master` 根目录下。
- 执行 `npm install` 开始安装。
    - 成功：`added 516 packages in 1100.17s`
- 执行 `grunt server` 或 `npm run start` 运行插件（每次启动都要执行）。
    - 成功：`Started connect web server on http://localhost:9100`
- 测试接口：`localhost:9100`。

**配置：** Gruntfile.js
```js
connect: {
	server: {
		options: {
		    hostname: '*',
			port: 9100,
			base: '.',
			keepalive: true
		}
	}
}
```