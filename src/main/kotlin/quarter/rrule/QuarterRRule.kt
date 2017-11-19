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

package quarter.rrule

import com.google.ical.compat.jodatime.LocalDateIteratorFactory
import extension.toTimestamp
import org.joda.time.LocalDate
import org.springframework.stereotype.Component
import java.sql.Timestamp

@Component("quarterRRule")
class QuarterRRule {

    // 3 ヶ月ごとの毎月 1 日を繰返日とする RRule
    private val rRule = RRule(freq = FREQ.MONTHLY, interval = 3, byMonthDay = 1)

    /**
     * 四半期の 1 日を取得
     * @param nowLocalDate 現日時
     * @exception QuarterRRuleException.NotFound 四半期期 の 1 日が存在しない場合
     * @return 四半期の 1 日
     */
    @Throws(QuarterRRuleException.NotFound::class)
    fun findTimestampBy(nowLocalDate: LocalDate): Timestamp {

        val now = nowLocalDate.toDate()

        // RRule の開始日
        val start = LocalDate(nowLocalDate.year, 1, 1)

        // RRule をもとに開始日以降の繰返日の無限リストを生成
        val infiniteDates = LocalDateIteratorFactory.createLocalDateIterable(this.rRule.string, start, true)

        // 経過済み四半期一覧を生成
        val passedQuarterDates = infiniteDates.takeWhile { it.toDate() <= now }

        try {
            val localDate = passedQuarterDates.last()
            return localDate.toTimestamp()
        } catch (exception: Exception) {
            throw QuarterRRuleException.NotFound()
        }
    }
}