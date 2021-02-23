**概念：** linux系统可以使用sh脚本文件为集群节点分配槽：
- `mkdir script`：在redis根目录下创建一个script文件夹。
- `cd script`：进入script文件夹。
- `vim addslots.sh`：编写addslots.sh脚本文件。
- `sh addslots.sh 127.0.0.1 7001 0 5461`：使用sh脚本为7001分配槽0-5461。
- `sh addslots.sh 127.0.0.1 7002 5462 10922`：使用sh脚本为7002分配槽5462-10922。
- `sh addslots.sh 127.0.0.1 7003 10923 16383`：使用sh脚本为7003分配槽10923-16383。
- `redis-cli -p 7001 cluster slots`：查看7000的槽信息，包括主从信息。

**脚本：** addslots.sh
```txt
ip=$1
port=$2
start=$3
end=$4
for slot in `seq ${start} ${end}`
do
    echo "slot:${slot}"
    redis-cli -h {ip} -p {port} cluster addslots ${slot}
done
```