# RecStar

Selecciona un idioma: [English](README.md) | [简体中文](README-zhCN.md) | [日本語](README-ja.md) | [한국어](README-ko.md) | [Русский](README-ru.md) | [Español](README-es.md)

Una aplicación de grabación de reclists (lista de grabaciones) al estilo de UTAU para Escritorio/iOS/Android.

![platforms.png](readme_images/platforms.png)

## Funciones

- Gestiona sesiones de grabación con configuraciones individuales (lista de grabación, música de fondo para guía de grabación, etc.)
- Gestiona listas de grabación con sus archivos de comentarios correspondientes.
- Grabación continua con música de fondo para guía.
- Acciones de automatización (por ejemplo, reproducción automática después de grabar).
- Tasa de muestreo (sample rate) y profundidad de bits (bit rate) configurables.
- (Solo en la version de Escritorio) Dispositivos de entrada/salida de audio seleccionables.
- Detección automática de codificación (encode) al cargar archivos de texto.
- Diseño de interfaz de usuario adaptable para modos horizontal y vertical.
- Temas "claro" y "oscuro".
- Soporte multilenguaje (inglés, japonés, chino, coreano, español).

## Descarga

Consulta la [página de lanzamientos](https://github.com/sdercolin/recstar/releases) para ver la versión más reciente.

### Escritorio

- Windows: `~win64.zip`
- macOS (Intel): `~mac-x64.dmg`
- macOS (Apple Silicon): `~mac-arm64.dmg`
- Ubuntu: `~amd64.deb`

Para otros tipos de Linux os, por favor intenta compilarlo por tu cuenta.

### Android

#### APK

Adjunto en la página de lanzamientos.

#### Play Store

Busca `RecStar` en la Play Store, o usa el siguiente enlace:
https://play.google.com/store/apps/details?id=com.sdercolin.recstar

### iOS

Busca `RecStar` en la App Store.

## Primeros pasos

1. Prepare la lista de grabación (de UTAU) que se adapte a sus necesidades, Las listas de grabación utilizadas en las capturas de pantalla son de [巽式連続音録音リスト](https://tatsu3.hateblo.jp/entry/ar426004)(Tatsumi).
2. Click the "+" button to create a new session.
3. Click the "..." button to import a reclist. If you need to import an OREMO-style comment file, please import it
   together with the reclist (a dialog will pop up to ask you to select the comment file).
4. Click the imported item, and the session will be created.
5. (Optional) If you want to use a guide BGM, click the music note button to import and select a BGM file. An
   OREMO-style BGM config file can be imported as well for continuous recording and trimming.
   Note: On Desktop, the config file should be in the same directory as the BGM file, and named
   as `<same_name_with_the_guide_bgm_file>.txt`, to be automatically detected.
6. Click the record button to start recording.
7. Click the audio graph to listen back to the recorded files.
8. After recording, click the "..." button and select "Export" or "Open Directory" to access the recorded files.

## Feedback

If you have any feedback, please join our [Discord Server](https://discord.gg/TyEcQ6P73y) and find the #recstar channel,
or open an issue in this repository.

Please provide the info from "Settings" -> "About" -> "Copy Device Info" and relevant screenshots when reporting issues.

If you are using the desktop version, please also provide the log files located in the `logs` directory under the app
directory (you can open it with window menu "Help" -> "Open App Directory" in the app).

## Getting started with development

RecStar is built with [Compose Multiplatform](https://github.com/JetBrains/compose-jb).

See the [README of the project template](README-compose.md) for instructions on how to get started.

<details>
<summary>Other recommended settings</summary>

1. Install the `Kotlin KDoc Formatter` plugin, and use the following settings:
   [![KDoc Formatter settings](readme_images/kdoc_settings.png)](readme_images/kdoc_settings.png)
2. Run `./gradlew addKtlintFormatGitPreCommitHook` once to add a pre-commit hook that will automatically format your
   code before committing.
3. If in string definition files (e.g. [StringsEnglish.kt](shared/src/commonMain/kotlin/ui/string/StringEnglish.kt)), if
   the formatter of your Android Studio is always turning the wildcard imports into single imports, adjust the settings
   to allow wildcard imports on package `ui.string`.

</details>

## Credits

Logo designed by InochiPM.
