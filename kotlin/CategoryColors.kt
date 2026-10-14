package PACKAGE.ui.theme // <- cambia PACKAGE con il pacchetto della tua app

import androidx.compose.ui.graphics.Color

/**
 * Colori per categorie (tag, etichette): tinte tenui con la stessa luminosità del lime tenue
 * (`active`), così stanno bene accanto al lime. Il testo sopra è sempre scuro, in entrambi i temi.
 * Salvare l'indice insieme alla categoria, così il colore resta uguale su tutti i dispositivi.
 */
val CategoryPalette = listOf(
    Color(0xFFE7F5B8), // lime tenue
    Color(0xFFC9E8F7), // cielo
    Color(0xFFFFDDB8), // pesca
    Color(0xFFE3D9FF), // lavanda
    Color(0xFFFFD3E2), // rosa
    Color(0xFFCDEFD9), // menta
    Color(0xFFFFF1B3), // burro
    Color(0xFFD9E2F7), // pervinca
)

fun categoryColor(index: Int): Color = CategoryPalette[(index and Int.MAX_VALUE) % CategoryPalette.size]

/** Testo sopra un colore di categoria. */
val OnCategoryColor = Color(0xFF1A1A1A)
