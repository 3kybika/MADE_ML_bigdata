#!/usr/bin/env bash

set -x

HADOOP_STREAMING_JAR=/opt/hadoop-3.2.1/share/hadoop/tools/lib/hadoop-streaming-3.2.1.jar

HDFS_OUTPUT_DIR_AVG=/result_hw1/avg
HDFS_OUTPUT_DIR_VAR=/result_hw1/var

hdfs dfs -rm -r -skipTrash $HDFS_OUTPUT_DIR_AVG
yarn jar $HADOOP_STREAMING_JAR \
    -D mapreduce.job.name="HW1_var_calc" \
    -file "avg/mapper.py"
    -mapper "avg/mapper.py"
    -file "avg/reducer.py"
    -reducer "avg/reducer.py"
    -numReduceTasks 1
    -input data
    -output $HDFS_OUTPUT_DIR_AVG

hdfs dfs -rm -r -skipTrash $HDFS_OUTPUT_DIR_VAR
yarn jar $HADOOP_STREAMING_JAR \
    -D mapreduce.job.name="HW1_var_calc" \
    -file "var/mapper.py"
    -mapper "var/mapper.py"
    -file "var/reducer.py"
    -reducer "var/reducer.py"
    -numReduceTasks 1
    -input data
    -output $HDFS_OUTPUT_DIR_VAR
