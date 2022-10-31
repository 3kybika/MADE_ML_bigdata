#!/usr/bin/env python
# -*-coding:utf-8 -*
import sys

COLUMN_SPLITTER = "\t"

res_var = 0
res_avg = 0
res_cnt = 0

for line in sys.stdin:
    try:
        chunk_avg, chunk_var, count = map(float, line.rstrip().split())

        chunk_avg = float(chunk_avg)
        chunk_var = float(chunk_var)
        count = int(count)
    except ValueError:
        continue

    # v_i = (c_j * v_j +   * v_k) / (c_j + c_k) + c_j * c_k * ((m_j - m_k) / (c_j + c_k)) ^ 2
    res_var = (res_var * res_cnt + chunk_var * count) / (res_cnt + count) + res_cnt * count * ((res_avg - chunk_avg) / (res_cnt + count)) ** 2
    # m_i = (c_j * m_j + c_k * m_k) / (c_j + c_k)
    res_avg = (res_avg * res_cnt + chunk_avg * count) / (res_cnt + count)

    res_cnt += count

print(res_var)
