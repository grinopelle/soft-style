# Kit stile "Soft"

Lo stile grafico di grinopelle, usato in **TRBK** e **Tacca**. Questa cartella è il posto unico da
cui copiarlo in ogni nuova app. Le regole complete e la loro storia sono in
`C:\Users\posta\dev\trbk\docs\stile.md` (approvato il 26/09/2026); qui c'è tutto quello che serve
per applicarlo.

## Contenuto

| Cartella / file | Cosa | Per |
|---|---|---|
| `tokens.json` | Tutti i valori: colori chiaro/scuro, caratteri, testi, angoli, ombre | Riferimento unico, wireframe |
| `css/soft.css` | Variabili e classi pronte (pulsanti, riquadri, campi, pillole) | Siti e app web in HTML/CSS |
| `kotlin/` | Tema e componenti Compose Multiplatform (da Tacca) | App Kotlin Multiplatform |
| `fonts/` | Bricolage Grotesque (titoli) e Figtree (testi), licenza OFL | Tutti |
| `CLAUDE-snippet.md` | Righe da incollare nel `CLAUDE.md` di un nuovo progetto | Istruzioni a Claude |

Per i siti Next.js + Tailwind c'è già la versione completa in `trbk/src/app/globals.css`.

## Le regole in breve

- **Morbido e amichevole**: angoli arrotondati, ombre leggere sui riquadri, tanto spazio.
- **Un solo colore d'azione: il lime `#CFFF3D`**, sempre con testo scuro `#1A1A1A` sopra (anche
  nel tema scuro). Si usa per:
  - l'azione principale della schermata (un solo pulsante lime per schermata);
  - ciò che succede **adesso** (es. il timer in corso);
  - le scelte attive (filtri, interruttori; voce di menu attiva in lime tenue `active`).
  - **Mai** come colore del testo su fondo chiaro: non si legge.
- **Chiaro e scuro automatici**, stessi nomi dei colori con valori diversi.
- **Caratteri**: titoli Bricolage Grotesque 800, tutto il resto Figtree. Serviti dall'app/sito,
  mai da Google Fonts (privacy).
- **Pulsanti**: principale lime, alto 56, angoli 14, bordo inferiore `lime-edge` di 4 px ("da
  premere"), icona facoltativa in un quadratino con bordo. Secondario: fondo `surface`, bordo
  1,5 px `ink`, ombra netta di 3 px.
- **Riquadri**: angoli 18, bordo `line`, ombra leggera. Riquadro in evidenza: angoli 24.
- **Etichette di sezione**: 12 px, maiuscolo, grassetto, colore `muted`.
- **Categorie** (tag, etichette): 8 tinte tenui (`color.category`) con testo scuro; salvare
  l'indice del colore con la categoria.
- **Icone**: a linea, 24×24, tratto 2, angoli arrotondati; nessuna emoji.
- **Errori e cancellazioni**: colore `danger`, sempre con conferma.
- **Accessibilità**: aree toccabili almeno 44 px, contrasto del testo almeno 4,5:1 (già
  verificato per tutti i colori del kit).

### Colori

| Nome | Chiaro | Scuro | Uso |
|---|---|---|---|
| `bg` | `#F4F4EF` | `#161616` | Sfondo |
| `surface` | `#FFFFFF` | `#212121` | Riquadri, campi |
| `surface-2` | `#E8E8E1` | `#2B2B2B` | Fondo tenue |
| `ink` | `#1A1A1A` | `#F2F2EE` | Testo e bordi forti |
| `muted` | `#5E5E58` | `#A8A8A2` | Testo secondario |
| `line` | `#E4E3DC` | `#303030` | Linee leggere |
| `input` | `#CFCEC6` | `#484848` | Bordo dei campi |
| `lime` | `#CFFF3D` | `#CFFF3D` | Azione, "adesso", scelta attiva |
| `lime-edge` | `#8DB300` | `#8DB300` | Bordo inferiore dei pulsanti lime |
| `active` | `#E7F5B8` | `#33401A` | Voce di menu attiva |
| `danger` | `#B3261E` | `#FF8A80` | Errori, cancellazioni |

## Come usarlo

### App Kotlin Multiplatform (come Tacca)
1. Copia `fonts/*.ttf` in `shared/src/commonMain/composeResources/font/`.
2. Copia i file di `kotlin/` in `shared/src/commonMain/kotlin/<tuo/pacchetto>/ui/theme/`.
3. In ogni file sostituisci `PACKAGE` con il pacchetto dell'app; in `SoftTheme.kt` sostituisci
   `NOMEPROGETTO` con il nome usato dalle risorse generate (lo trovi in un import di
   `generated.resources` nel progetto nuovo, es. `nomeapp.shared.generated.resources`).
4. Avvolgi l'app in `SoftTheme { … }` e usa `Soft.colors`, `Soft.type`, `PrimaryButton`,
   `SecondaryButton`, `SoftCard`, `PillChip`, `Segmented`, `SoftTextField`, `MoreMenu`, `SoftIcons`.

### Sito o app web in HTML/CSS (come grinopelle.eu)
1. Copia `css/soft.css` e `fonts/` nel sito (stessa struttura, o aggiorna i percorsi `../fonts`).
2. Usa le variabili (`var(--lime)`…) e le classi `soft-*` (`soft-btn`, `soft-card`, `soft-pill`…).

### Wireframe e tavole di design
Esiste il Design System **"Soft"** su claude.ai (vedi `CLAUDE-snippet.md` per il link): le nuove
tavole lo usano per colori, testi e componenti.

## Logo a righe (stile TOURBOOK)
Tecnica in `trbk/src/lib/brand/logo.ts`: ogni lettera è una linea centrale su un'altezza di 100,
ripassata con 4 spessori decrescenti alternando pieno e vuoto (2 righe nelle icone piccolissime).
Versione Compose in `006/shared/.../ui/theme/TaccaLogo.kt`.
