/*
 *     TimeRangePicker/TimeRangePicker.library.main
 *     DragOrigin.kt Copyrighted by Yamin Siahmargooei at 2025/11/11
 *     DragOrigin.kt Last modified at 2025/11/11
 *     This file is part of TimeRangePicker/TimeRangePicker.library.main.
 *     Copyright (C) 2025  Yamin Siahmargooei
 *
 *     TimeRangePicker/TimeRangePicker.library.main is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     TimeRangePicker/TimeRangePicker.library.main is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with TimeRangePicker.  If not, see <https://www.gnu.org/licenses/>.
 */

package ir.yamins.timerangepicker

internal sealed interface DragOrigin {
    data object Start : DragOrigin
    data object End : DragOrigin
}