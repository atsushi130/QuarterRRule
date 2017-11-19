/*
 * Copyright (C) 2017 Atsushi Miyake.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package extension

import java.lang.Exception
import java.sql.Timestamp
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * Timestamp 変換用のデフォルトフォーマット
 */
private val String.defaultFormat: String
    get() = "yyyy/MM/dd HH:mm:ss"

/**
 * フォーマットをもとに文字列から Timestamp へ変換
 * @param pattern 変換フォーマット
 * @return 変換後の Timestamp
 */
fun String.toFormatTimestamp(pattern: String = this.defaultFormat): Timestamp? = try {
    val format   = DateTimeFormatter.ofPattern(pattern)
    val dateTime = LocalDateTime.parse(this, format)
    Timestamp.valueOf(dateTime)
} catch (exception: Exception) {
    null
}