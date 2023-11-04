package ui.string

import ui.string.Strings.*

@Suppress("REDUNDANT_ELSE_IN_WHEN")
fun Strings.ja(): String? =
    when (this) {
        CommonOkay -> "OK"
        CommonCancel -> "キャンセル"
        CommonYes -> "はい"
        CommonNo -> "いいえ"
        CommonBack -> "戻る"
        CommonMore -> "その他"
        CommonError -> "エラー"
        AlertNeedManualPermissionGrantTitle -> "権限が必要です"
        AlertNeedManualPermissionGrantMessage -> "アプリは音声を録音するために権限が必要です。システム設定で権限を許可してください。"
        ErrorReadFileFailedMessage -> "ファイルの読み込みに失敗しました。"
        ErrorExportDataFailedMessage -> "データのエクスポートに失敗しました。"
        ExceptionRenameSessionExisting -> "名前が {0} のセッションが既に存在します。"
        ExceptionRenameSessionInvalid -> "無効なセッション名：{0}"
        ExceptionRenameSessionUnexpected -> "セッション名の編集に失敗しました。"
        ToastExportDataSuccess -> "成功しました"
        ToastExportDataCancel -> "キャンセルされました"
        MainScreenAllSessions -> "すべてのセッション"
        MainScreenNewSession -> "新しいセッションを開始"
        MainScreenEmpty -> "セッションがありません。"
        SessionScreenCurrentSentenceLabel -> "録音中："
        SessionScreenActionOpenDirectory -> "ディレクトリを開く"
        SessionScreenActionExport -> "エクスポート"
        SessionScreenActionRenameSession -> "セッション名を編集"
        CreateSessionReclistScreenTitle -> "録音リストを選択"
        CreateSessionReclistScreenActionImport -> "録音リストをインポート"
        CreateSessionReclistScreenEmpty -> "録音リストをインポートしてください。"
        CreateSessionReclistScreenContinue -> "完了"
        CreateSessionReclistScreenFailure -> "セッションの作成に失敗しました。"
        else -> null
    }
