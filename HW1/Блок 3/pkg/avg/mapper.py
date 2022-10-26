#!/usr/bin/env python
# -*-coding:utf-8 -*
import sys
import csv

fields = [
    "id",
    "name",
    "host_id",
    "host_name",
    "neighbourhood_group",
    "neighbourhood",
    "latitude",
    "longitude",
    "room_type",
    "price",
    "minimum_nights",
    "number_of_reviews",
    "last_review",
    "reviews_per_month",
    "calculated_host_listings_count",
    "availability_365"
]

res_cnt = 0
res_avg = 0.0

for row in csv.reader(sys.stdin, delimiter=","):
    row = dict(zip(fields, row))

    try:
        price = float(row["price"])
    except:
        continue

    # m_i = (c_j * m_j +  c_k * m_k) / (c_j + c_k)
    res_avg = (res_cnt * res_avg + price) / (res_cnt + 1)
    res_cnt += 1

print("{}\t{}".format(res_cnt, res_avg))
