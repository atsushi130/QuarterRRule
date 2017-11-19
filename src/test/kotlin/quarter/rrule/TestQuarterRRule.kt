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

import extension.toFormatTimestamp
import org.joda.time.LocalDate
import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.runners.MockitoJUnitRunner
import kotlin.test.assertEquals

@RunWith(Enclosed::class)
class TestQuarterRRule {

    @RunWith(MockitoJUnitRunner::class)
    class 四半期の1日を取得するとき {

        @InjectMocks
        private lateinit var quarterRRule: QuarterRRule

        @Test
        fun 四半期期首日が2017年7月1日であること() {
            val result = this.quarterRRule.findTimestampBy(LocalDate(2017, 7, 2))
            assertEquals("2017/07/01 00:00:00".toFormatTimestamp(), result)
        }

        @Test
        fun 四半期期首日が2017年1月1日であること() {
            val result = this.quarterRRule.findTimestampBy(LocalDate(2017, 1, 1))
            assertEquals("2017/01/01 00:00:00".toFormatTimestamp(), result)
        }
    }
}