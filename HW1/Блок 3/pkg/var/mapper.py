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
res_sum = 0.0
res_sqr_sum = 0.0

for row in csv.reader(sys.stdin, delimiter=","):
    row = dict(zip(fields, row))

    try:
        price = float(row["price"])
    except:
        continue

    res_sum += price
    res_sqr_sum += price ** 2
    res_cnt += 1

res_avg = res_sum / res_cnt
res_var = res_sqr_sum / res_cnt - res_avg ** 2

print('{0}\t{1}\t{2}'.format(res_avg, res_var, res_cnt))
