package ui.string

import ui.string.Strings.*

fun Strings.zhHans(): String? =
    when (this) {
        CommonOkay -> "确定"
        CommonCancel -> "取消"
        CommonYes -> "是"
        CommonNo -> "否"
        CommonBack -> "返回"
        else -> null
    }
