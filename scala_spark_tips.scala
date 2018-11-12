good_data.
select ($"identifier".as("info_deviceAdvertiserID"), $"startLocalDateTime",  (col("startLocalDateTime").cast("timestamp").cast("double")*1000).as("timestamp")).
        withColumn("utc_date", to_utc_timestamp($"startLocalDateTime", zone)).
        withColumn("utc_timestamp", $"utc_date".cast("timestamp")).
        withColumn("datefi", date_format($"utc_timestamp", "yyyyMMdd")).show(false)
