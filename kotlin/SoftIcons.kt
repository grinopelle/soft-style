package PACKAGE.ui.theme // <- cambia PACKAGE con il pacchetto della tua app

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.addPathNodes
import androidx.compose.ui.unit.dp

/** Icone a linea (24x24), le stesse del wireframe. Il colore lo decide `Icon(tint = …)`. */
object SoftIcons {
    val Timer = stroke("Timer", circle(12f, 13f, 8f), "M12 9v4l2.5 2.5", "M9 2h6")
    val List = stroke(
        "List", "M9 6h11", "M9 12h11", "M9 18h11",
        circle(4.5f, 6f, 1f), circle(4.5f, 12f, 1f), circle(4.5f, 18f, 1f),
    )
    val Target = stroke("Target", circle(12f, 12f, 9f), circle(12f, 12f, 5f), circle(12f, 12f, 1f))
    val Chart = stroke("Chart", "M3 20h18", "M7 16v-5", "M12 16V5", "M17 16v-8")
    val Play = filled("Play", "M7 4l13 8-13 8z")
    val Stop = filled("Stop", "M5 5h14v14H5z")
    val Pause = filled("Pause", "M6 5h4v14H6z", "M14 5h4v14h-4z")
    val Plus = stroke("Plus", "M12 5v14", "M5 12h14", width = 2.5f)
    val Check = stroke("Check", "M5 12l5 5 9-10", width = 3f)
    val ChevronLeft = stroke("ChevronLeft", "M15 6l-6 6 6 6", width = 2.5f)
    val ChevronRight = stroke("ChevronRight", "M9 6l6 6-6 6", width = 2.5f)
    val ChevronDown = stroke("ChevronDown", "M6 9l6 6 6-6", width = 2.5f)
    val Sync = stroke("Sync", "M20 12a8 8 0 1 1-2.3-5.6", "M20 4v4h-4")
    val Calendar = stroke("Calendar", "M6 5h12a3 3 0 0 1 3 3v10a3 3 0 0 1-3 3H6a3 3 0 0 1-3-3V8a3 3 0 0 1 3-3z", "M3 10h18", "M8 3v4", "M16 3v4")
    val More = filled("More", circle(12f, 5f, 1.8f), circle(12f, 12f, 1.8f), circle(12f, 19f, 1.8f))

    private fun circle(cx: Float, cy: Float, r: Float) =
        "M${cx - r} ${cy}a$r $r 0 1 0 ${2 * r} 0a$r $r 0 1 0 ${-2 * r} 0"

    private fun stroke(name: String, vararg paths: String, width: Float = 2f) = build(name, paths, width, filled = false)

    private fun filled(name: String, vararg paths: String) = build(name, paths, 0f, filled = true)

    private fun build(name: String, paths: Array<out String>, width: Float, filled: Boolean): ImageVector {
        val builder = ImageVector.Builder(name, 24.dp, 24.dp, 24f, 24f)
        paths.forEach { d ->
            builder.addPath(
                pathData = addPathNodes(d),
                fill = if (filled) SolidColor(Color.Black) else null,
                stroke = if (filled) null else SolidColor(Color.Black),
                strokeLineWidth = width,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round,
            )
        }
        return builder.build()
    }
}
