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
        CommonEdit -> "編集"
        CommonCheck -> "選択"
        AlertNeedManualPermissionGrantTitle -> "権限が必要です"
        AlertNeedManualPermissionGrantMessage -> "アプリは音声を録音するために権限が必要です。システム設定で権限を許可してください。"
        ErrorReadFileFailedMessage -> "ファイルの読み込みに失敗しました。"
        ErrorExportDataFailedMessage -> "データのエクスポートに失敗しました。"
        ExceptionRenameSessionExisting -> "名前が {0} のセッションが既に存在します。"
        ExceptionRenameSessionInvalid -> "無効なセッション名：{0}"
        ExceptionRenameSessionUnexpected -> "セッション名の編集に失敗しました。"
        ToastExportDataSuccess -> "成功しました"
        ToastExportDataCancel -> "キャンセルされました"
        ToastImportReclistSuccess -> "インポートに成功しました"
        ToastImportReclistFailure -> "インポートに失敗しました"
        MainScreenAllSessions -> "すべてのセッション"
        MainScreenItemSelecting -> "{0} 件選択"
        MainScreenNewSession -> "新しいセッションを開始"
        MainScreenEmpty -> "セッションがありません。"
        MainScreenDeleteItemsConfirmationTitle -> "セッションを削除"
        MainScreenDeleteItemsConfirmationMessage -> "本当に {0} 件のセッションを削除しますか？録音ファイルは完全に削除されます。"
        SessionScreenCurrentSentenceLabel -> "録音中："
        SessionScreenNoData -> "データがありません。"
        SessionScreenActionOpenDirectory -> "ディレクトリを開く"
        SessionScreenActionExport -> "エクスポート"
        SessionScreenActionRenameSession -> "セッション名を編集"
        SessionScreenTogglePlaying -> "再生/停止"
        CreateSessionReclistScreenTitle -> "録音リストを選択"
        CreateSessionReclistScreenActionImport -> "録音リストをインポート"
        CreateSessionReclistScreenEmpty -> "録音リストをインポートしてください。"
        CreateSessionReclistScreenContinue -> "完了"
        CreateSessionReclistScreenFailure -> "セッションの作成に失敗しました。"
        MenuFile -> "ファイル"
        MenuFileNewSession -> "新しいセッションを開始"
        MenuFileImportReclist -> "録音リストをインポート"
        MenuFileOpenDirectory -> "ディレクトリを開く"
        MenuFileBack -> "戻る"
        MenuEdit -> "編集"
        MenuEditRenameSession -> "セッション名を編集"
        MenuEditEditSessionList -> "セッションリストを編集"
        MenuAction -> "操作"
        MenuActionNextSentence -> "次へ"
        MenuActionPreviousSentence -> "前へ"
        MenuActionToggleRecording -> "録音/停止"
        else -> null
    }
