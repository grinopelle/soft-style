package PACKAGE.ui.theme // <- cambia PACKAGE con il pacchetto della tua app

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import NOMEPROGETTO.shared.generated.resources.Res
import NOMEPROGETTO.shared.generated.resources.bricolage_extrabold
import NOMEPROGETTO.shared.generated.resources.figtree_bold
import NOMEPROGETTO.shared.generated.resources.figtree_medium
import NOMEPROGETTO.shared.generated.resources.figtree_regular
import NOMEPROGETTO.shared.generated.resources.figtree_semibold
import org.jetbrains.compose.resources.Font

/**
 * Stile "Soft", lo stesso di TRBK (trbk/docs/stile.md): stessi nomi e valori.
 * Un solo colore d'azione, il lime, sempre con testo scuro sopra.
 */
@Immutable
data class SoftColors(
    val bg: Color,
    val surface: Color,
    val surface2: Color,
    val ink: Color,
    val muted: Color,
    val line: Color,
    val input: Color,
    val lime: Color = Color(0xFFCFFF3D),
    val limeEdge: Color = Color(0xFF8DB300),
    val onLime: Color = Color(0xFF1A1A1A),
    val active: Color,
    val edge: Color,
    val danger: Color,
    val shadow: Color,
)

val LightSoftColors = SoftColors(
    bg = Color(0xFFF4F4EF),
    surface = Color(0xFFFFFFFF),
    surface2 = Color(0xFFE8E8E1),
    ink = Color(0xFF1A1A1A),
    muted = Color(0xFF5E5E58),
    line = Color(0xFFE4E3DC),
    input = Color(0xFFCFCEC6),
    active = Color(0xFFE7F5B8),
    edge = Color(0xFF1A1A1A),
    danger = Color(0xFFB3261E),
    shadow = Color(0x121A1A1A),
)

val DarkSoftColors = SoftColors(
    bg = Color(0xFF161616),
    surface = Color(0xFF212121),
    surface2 = Color(0xFF2B2B2B),
    ink = Color(0xFFF2F2EE),
    muted = Color(0xFFA8A8A2),
    line = Color(0xFF303030),
    input = Color(0xFF484848),
    active = Color(0xFF33401A),
    edge = Color(0xFF000000),
    danger = Color(0xFFFF8A80),
    shadow = Color(0x59000000),
)

@Immutable
data class SoftType(
    val display: TextStyle,
    val title: TextStyle,
    val heading: TextStyle,
    val body: TextStyle,
    val bodyStrong: TextStyle,
    val small: TextStyle,
    val caption: TextStyle,
    val label: TextStyle,
    /** Numeri in evidenza (durate, timer). */
    val number: TextStyle,
)

private val LocalSoftColors = staticCompositionLocalOf { LightSoftColors }
private val LocalSoftType = staticCompositionLocalOf<SoftType> { error("SoftTheme mancante") }

object Soft {
    val colors: SoftColors
        @Composable @ReadOnlyComposable get() = LocalSoftColors.current
    val type: SoftType
        @Composable @ReadOnlyComposable get() = LocalSoftType.current
}

@Composable
fun SoftTheme(dark: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (dark) DarkSoftColors else LightSoftColors
    val display = FontFamily(Font(Res.font.bricolage_extrabold, FontWeight.ExtraBold))
    val sans = FontFamily(
        Font(Res.font.figtree_regular, FontWeight.Normal),
        Font(Res.font.figtree_medium, FontWeight.Medium),
        Font(Res.font.figtree_semibold, FontWeight.SemiBold),
        Font(Res.font.figtree_bold, FontWeight.Bold),
    )
    val type = SoftType(
        display = TextStyle(fontFamily = display, fontWeight = FontWeight.ExtraBold, fontSize = 36.sp, lineHeight = 38.sp),
        title = TextStyle(fontFamily = display, fontWeight = FontWeight.ExtraBold, fontSize = 26.sp, lineHeight = 29.sp),
        heading = TextStyle(fontFamily = sans, fontWeight = FontWeight.Bold, fontSize = 18.sp, lineHeight = 23.sp),
        body = TextStyle(fontFamily = sans, fontWeight = FontWeight.Normal, fontSize = 16.sp, lineHeight = 24.sp),
        bodyStrong = TextStyle(fontFamily = sans, fontWeight = FontWeight.Bold, fontSize = 16.sp, lineHeight = 22.sp),
        small = TextStyle(fontFamily = sans, fontWeight = FontWeight.Normal, fontSize = 14.sp, lineHeight = 20.sp),
        caption = TextStyle(fontFamily = sans, fontWeight = FontWeight.Medium, fontSize = 13.sp, lineHeight = 18.sp),
        label = TextStyle(fontFamily = sans, fontWeight = FontWeight.Bold, fontSize = 12.sp, lineHeight = 16.sp, letterSpacing = 0.5.sp),
        number = TextStyle(fontFamily = display, fontWeight = FontWeight.ExtraBold, fontSize = 18.sp, lineHeight = 22.sp),
    )
    // Material serve per campi di testo, menu e dialoghi: gli diamo gli stessi colori.
    val scheme = if (dark) {
        darkColorScheme(
            primary = colors.lime, onPrimary = colors.onLime,
            background = colors.bg, onBackground = colors.ink,
            surface = colors.surface, onSurface = colors.ink,
            surfaceVariant = colors.surface2, onSurfaceVariant = colors.muted,
            surfaceContainer = colors.surface, surfaceContainerHigh = colors.surface,
            outline = colors.input, outlineVariant = colors.line, error = colors.danger,
        )
    } else {
        lightColorScheme(
            primary = colors.lime, onPrimary = colors.onLime,
            background = colors.bg, onBackground = colors.ink,
            surface = colors.surface, onSurface = colors.ink,
            surfaceVariant = colors.surface2, onSurfaceVariant = colors.muted,
            surfaceContainer = colors.surface, surfaceContainerHigh = colors.surface,
            outline = colors.input, outlineVariant = colors.line, error = colors.danger,
        )
    }
    val material = Typography().let { t ->
        Typography(
            bodyLarge = t.bodyLarge.copy(fontFamily = sans),
            bodyMedium = t.bodyMedium.copy(fontFamily = sans),
            bodySmall = t.bodySmall.copy(fontFamily = sans),
            labelLarge = t.labelLarge.copy(fontFamily = sans),
            labelMedium = t.labelMedium.copy(fontFamily = sans),
            titleMedium = t.titleMedium.copy(fontFamily = sans),
        )
    }
    CompositionLocalProvider(LocalSoftColors provides colors, LocalSoftType provides type) {
        MaterialTheme(colorScheme = scheme, typography = material, content = content)
    }
}
