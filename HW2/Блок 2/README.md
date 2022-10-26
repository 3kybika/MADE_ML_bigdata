
docker cp ./artists.csv docker-hadoop-hive-parquet_namenode_1:/artists.csv


<pre>(base) <font color="#26A269"><b>alex@alex-pc</b></font>:<font color="#12488B"><b>~/project/made/3_sem/ML_BD/HW2/HW2</b></font>$ 
docker cp ./artists.csv docker-hadoop-hive-parquet_namenode_1:/artists.csv
</pre>
<pre>bash-5.0# (base) <font color="#26A269"><b>alex@alex-pc</b></font>:<font color="#12488B"><b>~</b></font>$ docker exec -it docker-hadoop-hive-parquet_namenode_1  /bin/bash
root@26d99d579f51:/# hdfs dfs -ls
ls: `.&apos;: No such file or directory
root@26d99d579f51:/# hdfs dfs -ls /
Found 3 items
drwxr-xr-x   - root supergroup          0 2022-10-26 12:02 /rmstate
drwxrwxr-x   - root supergroup          0 2022-10-26 12:02 /tmp
drwxr-xr-x   - root supergroup          0 2022-10-26 12:02 /user
root@26d99d579f51:/# hdfs dfs -ls /user
Found 2 items
drwxr-xr-x   - root supergroup          0 2022-10-26 12:02 /user/hive
drwxr-xr-x   - hue  hue                 0 2022-10-26 12:02 /user/hue
root@26d99d579f51:/# hdfs dfs -ls /user/root
ls: `/user/root&apos;: No such file or directory
root@26d99d579f51:/# hdfs dfs -ls /user/hive
Found 1 items
drwxrwxr-x   - root supergroup          0 2022-10-26 12:02 /user/hive/warehouse
root@26d99d579f51:/# hdfs dfs -ls /
Found 3 items
drwxr-xr-x   - root supergroup          0 2022-10-26 12:02 /rmstate
drwxrwxr-x   - root supergroup          0 2022-10-26 12:02 /tmp
drwxr-xr-x   - root supergroup          0 2022-10-26 12:02 /user
root@26d99d579f51:/# hdfs dfs -mkdir /data
root@26d99d579f51:/# ls
artists.csv  bin  boot	dev  entrypoint.sh  etc  hadoop  hadoop-data  home  lib  lib64	media  mnt  opt  proc  root  run  run.sh  sbin	srv  sys  tmp  usr  var
root@26d99d579f51:/# hdfs dfs -mkdir /data/artists
root@26d99d579f51:/# hdfs dfs -copyFromLocal artists.csv /data/artists/
root@26d99d579f51:/# hive
bash: hive: command not found
root@26d99d579f51:/# ^D
</pre>
<pre>(base) <font color="#26A269"><b>alex@alex-pc</b></font>:<font color="#12488B"><b>~/project/made/3_sem/ML_BD/HW2</b></font>$ docker exec -it docker-hadoop-hive-parquet_hive-server_1  /bin/bash
root@fd09d00c8449:/opt# hive
SLF4J: Class path contains multiple SLF4J bindings.
SLF4J: Found binding in [jar:file:/opt/hive/lib/log4j-slf4j-impl-2.6.2.jar!/org/slf4j/impl/StaticLoggerBinder.class]
SLF4J: Found binding in [jar:file:/opt/hadoop-2.7.4/share/hadoop/common/lib/slf4j-log4j12-1.7.10.jar!/org/slf4j/impl/StaticLoggerBinder.class]
SLF4J: See http://www.slf4j.org/codes.html#multiple_bindings for an explanation.
SLF4J: Actual binding is of type [org.apache.logging.slf4j.Log4jLoggerFactory]

Logging initialized using configuration in file:/opt/hive/conf/hive-log4j2.properties Async: true
Hive-on-MR is deprecated in Hive 2 and may not be available in the future versions. Consider using a different execution engine (i.e. spark, tez) or using Hive 1.X releases.
hive&gt; 
</pre>