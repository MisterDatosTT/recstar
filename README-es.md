# RecStar

Selecciona un idioma: [English](README.md) | [简体中文](README-zhCN.md) | [日本語](README-ja.md) | [한국어](README-ko.md) | [Русский](README-ru.md) | [Español](README-es.md)

Una aplicación de grabación de reclists (lista de grabaciones) al estilo de UTAU para Escritorio/iOS/Android.

![platforms.png](readme_images/platforms.png)

## Funciones

- Gestiona sesiones de grabación con configuraciones individuales (lista de grabación, música de fondo para guía de grabación, etc.)
- Gestiona listas de grabación con sus archivos de comentarios correspondientes.
- Grabación continua con música de fondo como guía.
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

1. Prepara la lista de grabación (de UTAU) que se adapte a tus necesidades, Las listas de grabación utilizadas en las capturas de pantalla son de [巽式連続音録音リスト](https://tatsu3.hateblo.jp/entry/ar426004)(Tatsumi).
2. Haz clic en el botón "+" para crear una nueva sesión.
3. Haz clic en el botón "..." para importar una lista de grabación. Si necesitas importar un archivo de comentarios estilo OREMO, impórtalo junto con la lista de grabación (aparecerá un diálogo para pedirte que selecciones el archivo de comentarios).
4. Haz clic en el elemento importado y se creará la sesión.
5. (Opcional) Si quieres usar música de fondo como guía, haz clic en el botón de la nota musical para importar y seleccionar un archivo de música de fondo. También se puede importar un archivo de configuración de música de fondo estilo OREMO para grabación continua y recorte.
   Nota: En Escritorio, el archivo de configuración debe estar en el mismo directorio que el archivo de música de fondo, y nombrarse como <mismo_nombre_que_el_archivo_de_música_de_fondo>.txt, para que se detecte automáticamente.
6. Haz clic en el botón de grabar para comenzar la grabación.
7. Haz clic en el gráfico del audio para escuchar los archivos grabados.
8. Después de grabar, haz clic en el botón "..." y selecciona "Exportar" o "Abrir directorio" para acceder a los archivos grabados.

## Retroalimentación.

Si tienes algún comentario, únete a nuestro [Servidor de Discord](https://discord.gg/TyEcQ6P73y) y busca el canal #recstar, o abre un "issue" en este repositorio.
Por favor, proporciona la información que te aparece al ir a "Settings" -> "About" -> "Copy Device Info" junto a capturas de pantalla relevantes al reportar problemas.

Si estás usando la versión de escritorio, por favor proporciona también los archivos de registro ubicados en el directorio `logs` dentro del directorio de la aplicación (puedes abrirlo con el menú de ventana)("Help" -> "Open App Directory" en la app).

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
