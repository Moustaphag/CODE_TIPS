
// Time Zone Managing in spark with different function
good_data.
select ($"identifier".as("info_deviceAdvertiserID"), $"startLocalDateTime",  
        (col("startLocalDateTime").cast("timestamp").cast("double")*1000).as("timestamp")).
        withColumn("utc_date", to_utc_timestamp($"startLocalDateTime", zone)).
        withColumn("utc_timestamp", $"utc_date".cast("timestamp")).
        withColumn("datefi", date_format($"utc_timestamp", "yyyyMMdd")).show(false)


// filter with structfiel

// 1 creating a fake dataframe
val df = Seq(
 (20181011,25,100,20180525),
(20181011,50,100,20180524),
(20181109,125,200,20180629),
(20181109,0,75,20180629),
(20181109,700,1000,20180629),
(20181109,1025,725,20180629)
).toDF("day", "info_date", "timestamp", "dateVisit")

// 2. create a strucfield columns
df.withColumn("diff", struct($"timestamp" - $"info_date", $"timestamp")).printSchema()

/*
 |-- day: integer (nullable = false)
 |-- info_date: integer (nullable = false)
 |-- timestamp: integer (nullable = false)
 |-- dateVisit: integer (nullable = false)
 |-- diff: struct (nullable = false)
 |    |-- col1: integer (nullable = false)
 |    |-- timestamp: integer (nullable = false)
 */
// 3. sort and filter on the structfield
df.withColumn("diff", struct($"timestamp" - $"info_date", $"timestamp")).
sort($"diff").
filter($"diff".getItem("col1")>0).show(false)

/*
+--------+---------+---------+---------+----------+
|day     |info_date|timestamp|dateVisit|diff      |
+--------+---------+---------+---------+----------+
|20181011|50       |100      |20180524 |[50,100]  |
|20181109|0        |75       |20180629 |[75,75]   |
|20181011|25       |100      |20180525 |[75,100]  |
|20181109|125      |200      |20180629 |[75,200]  |
|20181109|700      |1000     |20180629 |[300,1000]|
+--------+---------+---------+---------+----------+
*/
