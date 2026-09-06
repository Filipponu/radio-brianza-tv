# Radio Brianza TV 1.1

- Nuovo streaming: https://stream.radiobrianza.it/live/livestream.m3u8
- Logo originale incluso nel progetto e nella schermata iniziale su fondo nero.
- Schermata iniziale visibile per almeno 2 secondi e fino alla riproduzione.
- Icona e banner TV collegati a un drawable Android con cornice 16:9.
- Versione incrementata a 1.1 (versionCode 2).

Verifiche eseguite: risposta HTTP 200 della playlist HLS; controllo della logica JavaScript con simulazione di avvio del video, durata minima del logo e riproduzione HLS nativa senza libreria Hls disponibile.

Non ancora eseguiti: compilazione Android e prova su Fire TV. In questo computer mancano JDK 17 e Android SDK; il repository contiene il workflow GitHub Actions Build APK per compilare dopo il caricamento delle modifiche.

Il PNG fornito è conservato senza modifiche; il drawable Android lo adatta alla cornice orizzontale. Il launcher del dispositivo può applicare una propria cornice all'icona.

L'APK di debug prodotto da GitHub Actions potrebbe avere una firma differente dalla versione installata: per un aggiornamento diretto occorre usare la stessa chiave di firma.
