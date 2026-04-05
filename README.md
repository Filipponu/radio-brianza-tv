# Radio RBS TV — APK per FireStick

App Android minimalista che riproduce lo streaming HLS della radio in fullscreen su Amazon FireStick.

## Come ottenere l'APK

1. Carica questa cartella su GitHub (repo pubblico o privato)
2. GitHub Actions compila automaticamente l'APK ad ogni push
3. Vai su **Actions → Build APK → Artifacts** e scarica `RadioRBS-TV-debug.zip`
4. Estrai l'`app-debug.apk`

## Come installare su FireStick

1. Su FireStick: **Impostazioni → My Fire TV → Opzioni sviluppatore → App da fonti sconosciute → ON**
2. Scarica l'APK sul PC
3. Usa [ADB WiFi](https://developer.amazon.com/docs/fire-tv/connecting-adb-to-device.html) oppure carica l'APK su un server web e scaricalo con l'app **Downloader** (gratis su Amazon Store)

### Via ADB (da PC)
```bash
adb connect <IP_FIRESTICK>:5555
adb install app-debug.apk
```

### Via Downloader
1. Installa Downloader da Amazon Appstore
2. Apri Downloader, inserisci l'URL diretto dell'APK
3. Installa

## Personalizzazione

- **Nome app:** `app/src/main/res/values/strings.xml`
- **Stream URL:** `app/src/main/assets/player.html` → variabile `STREAM`
- **Package ID:** `app/build.gradle` → `applicationId`
