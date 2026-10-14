# Righe da incollare nel CLAUDE.md di un nuovo progetto

Copia il blocco qui sotto in fondo al `CLAUDE.md` del nuovo progetto (crealo se non c'è).

---

## Stile grafico: "Soft"

- Lo stile è **Soft**, lo stesso di TRBK e Tacca. Regole e valori: `C:\Users\posta\dev\soft-style\README.md`
  (kit da copiare) e `C:\Users\posta\dev\trbk\docs\stile.md` (regole complete).
- Per wireframe e tavole di design usa il Design System **Soft**:
  https://claude.ai/artifact/EH3wzo3u975xVKoNNFAaB5
- App Kotlin Multiplatform: copia `soft-style/kotlin/` in `ui/theme/` e `soft-style/fonts/` in
  `composeResources/font/`, poi usa `SoftTheme`, `Soft.colors`, `Soft.type` e i componenti
  (`PrimaryButton`, `SecondaryButton`, `SoftCard`, `PillChip`, `Segmented`, `SoftTextField`, `MoreMenu`).
- Siti web in HTML/CSS: copia `soft-style/css/soft.css` e `soft-style/fonts/`.
- Non inventare colori, caratteri o forme nuove: usa solo quelli del kit. Un solo colore d'azione
  (lime, sempre con testo scuro sopra), tema chiaro e scuro automatico, caratteri serviti dal
  prodotto e non da Google Fonts.
- Prima di sviluppare schermate nuove, prepara un wireframe nello stile Soft e chiedi approvazione.
