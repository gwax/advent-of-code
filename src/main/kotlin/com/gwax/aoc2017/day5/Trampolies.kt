package com.gwax.aoc2017.day5

fun processJumps(jumps: List<Int>): Int {
    var i = 0
    var n = 0
    val stack = jumps.toMutableList()
    do {
        val newi = i + stack[i]
        stack[i]++
        n++
        i = newi
    } while (0 <= i && i < jumps.size)
    return n
}

fun processJumps2(jumps: List<Int>): Int {
    var i = 0
    var n = 0
    val stack = jumps.toMutableList()
    do {
        val jumpdist = stack[i]
        val newi = i + jumpdist
        stack[i] += if (jumpdist >= 3) -1 else 1
        n++
        i = newi
    } while (0 <= i && i < jumps.size)
    return n
}

fun main(args: Array<String>) {
    val jumpstack = input.lines().map { it.toInt(10) }
    println(processJumps(jumpstack))
    println(processJumps2(jumpstack))
}

val input = """
0
0
0
2
2
-1
-3
-3
0
-6
-9
0
-1
-12
-9
-2
0
-14
-6
-2
-10
-12
-10
-13
-2
1
-6
-14
-2
-11
-7
-9
-15
-22
-25
-26
-19
-28
1
-2
-38
-39
-30
-18
0
-26
-1
-24
-1
-30
-44
-35
-9
-32
-5
-34
-4
-15
-21
-30
-10
-32
-19
-40
-12
-49
-58
-2
-14
-51
-37
-9
-4
-48
-64
-37
-55
-40
-37
2
-22
-68
-57
-57
-83
-65
-38
-22
-20
-78
-27
-40
-4
-83
-33
-47
-74
-41
-74
-68
-18
-8
-27
-23
-53
-70
-43
-99
-48
-90
1
-74
-9
-80
-96
-33
-7
-53
-98
-54
-47
-84
-81
-79
-86
-14
-115
-121
-30
-28
-13
-113
-41
-20
-34
-19
-71
-39
-17
-91
-115
-108
-74
-134
-12
-91
-27
-59
-27
-132
-34
-8
-52
-93
-17
-151
-93
-102
-62
-62
-120
-25
-75
-35
-162
-61
-107
-83
-106
-23
-168
-42
-13
-74
-52
-169
-123
-95
-174
-56
-43
-84
-21
-5
-120
-130
-55
-3
-93
-158
-61
-4
-74
-65
-157
-112
-147
-24
-23
-155
-82
-73
-25
-82
-42
-200
-120
-57
-96
-38
-121
-25
-211
-117
-42
-181
-56
-204
-193
-88
-143
-167
-42
-178
-204
-57
-120
-33
-164
-122
-219
-136
-174
0
-36
-64
-77
-34
-158
-163
-194
-212
-101
-48
-216
-141
-142
-189
-101
-144
-158
-114
-88
-251
-172
-173
-94
-89
-12
-188
-218
-130
-33
-117
-39
-245
-195
-20
-37
-183
-24
-48
-159
-36
-68
-96
-215
-127
-241
-171
-44
-2
-203
-27
-37
-241
-173
-193
-143
-52
-187
-54
-236
-219
-71
-197
-162
-245
-133
-153
-240
-250
-91
-128
-20
-263
-124
-46
-290
-194
-191
-60
-92
-97
-284
-60
-44
-208
-59
-6
-40
-292
-1
-14
-27
-71
-144
-289
-16
-266
-80
-138
-215
-249
-104
-29
-260
-124
-178
-331
-283
-47
-253
-80
-316
-62
-15
-327
-64
-201
-26
-60
-110
-117
-13
-99
-139
-124
-25
-242
-92
-10
-322
-110
-169
-26
-353
-284
-234
-361
-95
-37
-93
-186
-90
-26
-90
-268
-179
-305
-378
-145
-238
-358
-186
-108
-120
-75
-109
-279
-192
-308
-197
-373
-19
-38
-238
-133
-60
-334
-45
-169
-167
-257
-107
-222
-380
-321
-99
-177
-268
-224
-45
-323
-407
-167
-125
-243
-331
-268
-132
-254
-204
-191
-280
-242
-223
-313
-237
-234
-274
-327
-271
-362
-39
-194
-184
-16
-214
-46
-199
-108
-332
-316
-29
-327
-200
-52
-260
-128
-103
-67
-109
-432
-399
-153
-403
-176
-434
1
-225
-286
-375
-206
-395
-275
-120
-404
-381
-156
-215
-350
-257
-293
-231
-114
-52
-149
-296
-440
-413
-285
-400
-265
-378
-75
-381
-281
-436
-137
-335
-111
-92
-12
-27
-20
-208
-322
-151
-264
-207
-361
-314
-258
-81
-327
-440
-286
-108
-272
-392
-17
-40
-486
-287
-205
-211
-160
1
-216
-114
-32
-143
-113
-489
-109
-56
-371
-258
-430
-29
-475
-141
-477
-169
-473
-264
-225
-123
-412
-395
-391
-28
-527
-493
-471
-401
-510
-430
-154
-34
-533
-544
-39
-535
-553
-447
-144
-517
-482
-362
-265
-434
-504
-526
-31
-53
-170
-44
-126
-75
-163
-136
-508
-564
-289
-68
-541
-165
-496
-78
-117
-268
-326
-361
-194
-483
-495
-560
-215
-363
-275
-426
-160
-333
-182
-457
-96
-200
-569
-236
-31
-365
-419
-43
-366
-385
-322
-430
-334
-156
-377
-184
-522
-289
-129
-87
-501
-606
-84
-163
-318
-552
-442
-590
-137
-517
-161
-333
-497
-150
-188
-401
-613
-131
-595
-18
-591
-134
-44
-637
-500
0
-349
-483
-258
-124
-449
-260
-150
-269
-305
-650
-619
-328
-478
-5
-514
-73
-261
-503
-101
-480
-37
-192
-497
-298
-464
-514
-515
-203
-17
-302
-9
-409
-285
-140
-46
-136
-470
-544
-666
-590
-382
-546
-619
-194
-543
-323
-264
-673
-177
-342
-539
-507
-660
-655
-272
-28
-181
-266
-242
-337
-116
-421
-537
-24
-6
-241
-110
-255
-429
-31
-380
-214
-337
-514
-68
-102
-320
-12
-648
-180
-192
-554
-182
-303
-623
-583
-686
-367
-325
-488
-71
-466
-625
-402
-104
-348
-690
-714
-708
-212
-302
-286
-44
-386
-455
-456
-353
-469
-145
-116
-412
-273
-705
-331
-519
-592
-630
-396
-82
-434
-35
-436
-490
-471
-738
-488
-476
-295
-399
-262
-44
-761
-121
-643
-383
-221
-11
-380
-555
-382
-68
-554
-621
-27
-549
-661
-197
-116
-339
-577
-206
-790
-283
-248
-163
-503
-481
-573
-308
-650
-42
-23
-451
-72
-470
-709
-589
-495
-377
-246
-5
-667
-697
-585
-511
0
-787
-559
-320
-81
-782
-660
-153
-111
-162
-584
-103
-774
-827
-453
-815
-290
-794
-667
-524
-281
-230
-205
-333
-495
-705
-568
-519
-132
-819
-190
-736
-135
-649
-712
-126
-233
-827
-353
-197
-803
-19
-124
-691
-234
-96
-690
-500
-321
-277
-56
-838
-512
-6
-70
-611
-285
-253
-14
-545
-143
-193
-352
-755
-634
-572
-320
-132
-522
-688
-273
-194
-613
-492
-715
-624
-581
-505
-185
-468
-853
-445
-407
-605
-86
-583
-160
-746
-436
-673
-597
-479
-278
-259
-503
-121
-710
-169
-363
-639
-325
-146
-760
-673
-177
-400
-431
-782
-756
-166
-300
-461
-917
-872
-786
-921
-346
-137
-638
-396
-812
-107
-303
-55
-70
-675
-477
-520
-843
-632
-290
-497
-758
-20
-636
-166
-681
-459
-591
-332
-65
-814
-373
-909
-72
-71
-513
-387
-209
-199
-777
-663
-635
-874
-596
-733
-226
-950
-845
-321
-94
-450
-672
-665
-469
-369
-415
-210
-290
-911
-140
-413
-823
-74
-833
-163
-577
-576
-926
-683
-417
-970
-11
-460
-689
-493
-879
-797
-578
-918
-633
-591
-915
-550
-646
-33
-122
-184
-324
-1006
-937
-215
-253
-420
-495
-797
-950
-631
-409
-528
-312
-282
-594
-377
-258
-1017
-354
-871
-25
-650
-765
-246
-710
-815
-772
-733
-920
-1001
-355
-581
-782
-711
-1044
-922
-74
-391
-3
-847
-1020
-963
-1036
-1007
-123
-605
-642
-591
-648
-688
-555
-937
-862
-885
-553
-509
-723
-696
-406
-960
-160
-633
-823
-874
-1076
-962
-736
-984
-486
-645
-932
-734
-744
-21
-20
-506
""".trimIndent()