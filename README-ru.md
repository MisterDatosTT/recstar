# RecStar

Выберите язык: [English](README.md) | [简体中文](README-zhCN.md) | [日本語](README-ja.md) | [한국어](README-ko.md) | [Русский](README-ru.md) | [Español](README-es.md)

Приложение для записи реклиста в стиле UTAU для Desktop/iOS/Android.

![platforms.png](readme_images/platforms.png)

## Особенности

- Управление сеансами записи с индивидуальными настройками (реклист, направляющий BGM и т. д.)
- Управление реклистами с соответствующими файлами комментариев
- Непрерывная запись с направляющим BGM
- Автоматизация действий (например, автоматическое прослушивание после записи)
- Настраиваемые частота дискретизации и битовая глубина
- (только desktop) Выбор устройств ввода/вывода звука
- Автоматическое определение кодировки при загрузке текстовых файлов
- Адаптивная компоновка пользовательского интерфейса для ландшафтного и портретного режимов
- Светлая и темная тема
- Поддержка нескольких языков (английский, японский, китайский, корейский, русский)

## Скачать

Последнюю версию смотрите на [странице релизов](https://github.com/sdercolin/recstar/releases).

### Desktop

- Windows: `~win64.zip`
- macOS (Intel): `~mac-x64.dmg`
- macOS (Apple Silicon): `~mac-arm64.dmg`
- Ubuntu: `~amd64.deb`

Для других типов ОС Linux, пожалуйста, попробуйте собрать самостоятельно.

### Android

#### APK

Прикреплено на странице релиза.

#### Play Store

Найдите `RecStar` в Play Store или воспользуйтесь следующей ссылкой:
https://play.google.com/store/apps/details?id=com.sdercolin.recstar

### iOS

Найдите `RecStar` в магазине App Store.

## Приступая к работе

1. Подготовьте реклисты UTAU, которые соответствуют вашим потребностям. На скриншотах использованы реклисты
   с сайта [巽式連続音録音リスト](https://tatsu3.hateblo.jp/entry/ar426004).
3. Нажмите кнопку «+», чтобы создать новую сессию.
4. Нажмите кнопку «...», чтобы импортировать реклист. Если вам нужно импортировать файл комментариев в стиле OREMO,
   импортируйте его вместе с реклистом (появится диалоговое окно с предложением выбрать файл комментариев).
6. Щелкните импортированный элемент, и сессия будет создана.
7. (Необязательно) Если вы хотите использовать направляющий BGM, нажмите кнопку музыкальной ноты, чтобы импортировать и выбрать BGM-файл.
   Можно также импортировать файл конфигурации BGM в стиле OREMO для непрерывной записи и обрезки.
   Примечание: На рабочем столе файл конфигурации должен находиться в той же директории, что и BGM-файл, и называться `<одно имя_с_направляющим_bgm_file>.txt`,
   чтобы он был автоматически обнаружен. Нажмите на импортированный элемент, и сессия будет создана.
9. Нажмите кнопку записи, чтобы начать запись.
10. Нажмите на аудиограмму, чтобы прослушать записанные файлы.
11. После записи нажмите кнопку «...» и выберите «Экспорт» или «Открыть каталог», чтобы получить доступ к записанным файлам.

## Обратная связь

Если у вас есть какие-либо замечания, присоединяйтесь к нашему [серверу Discord](https://discord.gg/TyEcQ6P73y) и найдите канал #recstar,
или откройте проблему (issue) в этом репозитории

Пожалуйста, предоставьте информацию из раздела «Настройки» -> «О программе» -> «Скопировать информацию об устройстве» и соответствующие скриншоты при сообщении о проблеме.

Если вы используете настольную версию, пожалуйста, предоставьте также файлы журналов, расположенные в директории `logs` в каталоге приложения
(открыть его можно с помощью оконного меню «Справка» -> «Открыть каталог приложения» в приложении).

## Приступая к разработке

RecStar построен на базе [Compose Multiplatform](https://github.com/JetBrains/compose-jb).

Инструкции по началу работы см. в [README шаблона проекта](README-compose.md).

<details>
<summary>Другие рекомендуемые настройки</summary>

1. Установите плагин `Kotlin KDoc Formatter` и используйте следующие настройки:
   [![KDoc Formatter settings](readme_images/kdoc_settings.png)](readme_images/kdoc_settings.png)
2. Выполните команду `./gradlew addKtlintFormatGitPreCommitHook` один раз, чтобы добавить хук предварительной коммисии,
   который будет автоматически форматировать ваш код перед коммисией.
4. Если в файлах определения строк (например, [StringsEnglish.kt](shared/src/commonMain/kotlin/ui/string/StringEnglish.kt))
   форматтер Android Studio постоянно превращает импорт подстановочных символов в одиночный импорт, настройте параметры,
   чтобы разрешить импорт подстановочных символов в пакете `ui.string`.

</details>

## Авторство

Логотип разработан InochiPM.
