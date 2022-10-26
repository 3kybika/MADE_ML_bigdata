Package for copying to docker container:  
```
docker cp ./pkg namenode:/pkg
```

```
cd pkg  
hdfs dfs -mkdir data  
hdfs dfs -copyFromLocal AB_NYC_2019.csv data  
```
