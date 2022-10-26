#!/usr/bin/env python
# -*-coding:utf-8 -*
import sys

COLUMN_SPLITTER = "\t"

res_cnt = 0
res_avg = 0.0

for line in sys.stdin:
    try:
        chunk_cnt, chunk_avg  = line.strip().split(COLUMN_SPLITTER)

        chunk_cnt = int(chunk_cnt)
        chunk_avg = float(chunk_avg)

    except ValueError:
        continue

    # m_i = (c_j * m_j +  c_k * m_k) / (c_j + c_k)
    res_avg = (res_cnt * res_avg + chunk_cnt * chunk_avg) / (res_cnt + chunk_cnt)
    res_cnt += chunk_cnt

print(res_avg)
