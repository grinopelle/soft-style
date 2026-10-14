package PACKAGE.ui.theme // <- cambia PACKAGE con il pacchetto della tua app

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

val ControlShape = RoundedCornerShape(14.dp)
val CardShape = RoundedCornerShape(18.dp)
val HeroShape = RoundedCornerShape(24.dp)

/** Il bordo inferiore pieno che fa sembrare il pulsante "da premere". */
private fun Modifier.pressEdge(color: Color, depth: Dp, radius: Dp) = drawBehind {
    drawRoundRect(color, topLeft = Offset(0f, depth.toPx()), size = size, cornerRadius = CornerRadius(radius.toPx()))
}

/** Pulsante principale: fondo lime, testo e icona sempre scuri. */
@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    enabled: Boolean = true,
) {
    val c = Soft.colors
    Row(
        modifier
            .alpha(if (enabled) 1f else 0.45f)
            .padding(bottom = 4.dp)
            .pressEdge(c.limeEdge, 4.dp, 14.dp)
            .clip(ControlShape)
            .background(c.lime)
            .clickable(enabled = enabled, role = Role.Button, onClick = onClick)
            .heightIn(min = 56.dp)
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (icon != null) IconBox(icon, c.onLime)
        Text(text, style = Soft.type.bodyStrong.copy(fontSize = Soft.type.heading.fontSize), color = c.onLime)
    }
}

/** Pulsante secondario: fondo chiaro, bordo scuro e ombra netta sotto. */
@Composable
fun SecondaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    enabled: Boolean = true,
    onLime: Boolean = false,
) {
    val c = Soft.colors
    // Sopra il riquadro lime resta bianco e nero anche nel tema scuro.
    val fill = if (onLime) Color.White else c.surface
    val ink = if (onLime) c.onLime else c.ink
    val edge = if (onLime) c.onLime else c.edge
    Row(
        modifier
            .alpha(if (enabled) 1f else 0.45f)
            .padding(bottom = 3.dp)
            .pressEdge(edge, 3.dp, 14.dp)
            .clip(ControlShape)
            .background(fill)
            .border(1.5.dp, ink, ControlShape)
            .clickable(enabled = enabled, role = Role.Button, onClick = onClick)
            .heightIn(min = 52.dp)
            .padding(horizontal = 18.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (icon != null) IconBox(icon, ink)
        Text(text, style = Soft.type.bodyStrong, color = ink)
    }
}

@Composable
fun TextLink(text: String, onClick: () -> Unit, modifier: Modifier = Modifier, color: Color = Soft.colors.ink) {
    Box(
        modifier
            .clip(ControlShape)
            .clickable(role = Role.Button, onClick = onClick)
            .heightIn(min = 44.dp)
            .padding(horizontal = 12.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(text, style = Soft.type.small.copy(fontWeight = Soft.type.bodyStrong.fontWeight), color = color)
    }
}

@Composable
private fun IconBox(icon: ImageVector, color: Color) {
    Box(
        Modifier.size(28.dp).border(1.5.dp, color, RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center,
    ) {
        Icon(icon, contentDescription = null, tint = color, modifier = Modifier.size(14.dp))
    }
}

@Composable
fun SoftCard(
    modifier: Modifier = Modifier,
    padding: PaddingValues = PaddingValues(16.dp),
    fill: Color = Soft.colors.surface,
    flat: Boolean = false,
    content: @Composable ColumnScope.() -> Unit,
) {
    val c = Soft.colors
    Column(
        modifier
            .then(if (flat) Modifier else Modifier.shadow(10.dp, CardShape, ambientColor = c.shadow, spotColor = c.shadow))
            .clip(CardShape)
            .background(fill)
            .border(1.dp, c.line, CardShape)
            .padding(padding),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        content = content,
    )
}

@Composable
fun SectionLabel(text: String, modifier: Modifier = Modifier, color: Color = Soft.colors.muted) {
    Text(text.uppercase(), modifier = modifier, style = Soft.type.label, color = color)
}

/** Pillola selezionabile: lime quando è attiva. */
@Composable
fun PillChip(
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    trailing: String? = null,
    /** Colore del tag, mostrato come pallino. */
    dot: Color? = null,
) {
    val c = Soft.colors
    Row(
        modifier
            .clip(CircleShape)
            .background(if (selected) c.lime else c.surface)
            .border(1.dp, if (selected) c.lime else c.line, CircleShape)
            .clickable(role = Role.Checkbox, onClick = onClick)
            .heightIn(min = 40.dp)
            .padding(horizontal = 14.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (dot != null) {
            Box(Modifier.size(12.dp).clip(CircleShape).background(dot).border(1.dp, OnCategoryColor.copy(alpha = 0.25f), CircleShape))
        }
        Text(text, style = Soft.type.small.copy(fontWeight = Soft.type.bodyStrong.fontWeight), color = if (selected) c.onLime else c.ink)
        if (trailing != null) Text(trailing, style = Soft.type.small, color = if (selected) c.onLime else c.muted)
    }
}

/** Etichetta piccola non cliccabile (es. un tag dentro una riga). */
@Composable
fun Pill(text: String, bordered: Boolean = false, color: Color = Soft.colors.ink, fill: Color? = null) {
    val c = Soft.colors
    Box(
        Modifier
            .clip(CircleShape)
            .then(if (bordered) Modifier.border(1.5.dp, color, CircleShape) else Modifier.background(fill ?: c.surface2))
            .padding(horizontal = 10.dp, vertical = 3.dp),
    ) {
        Text(text, style = Soft.type.caption.copy(fontWeight = Soft.type.bodyStrong.fontWeight), color = color)
    }
}

@Composable
fun Segmented(options: List<String>, selectedIndex: Int, onSelect: (Int) -> Unit, modifier: Modifier = Modifier) {
    val c = Soft.colors
    Row(
        modifier.fillMaxWidth().clip(ControlShape).background(c.surface2).padding(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        options.forEachIndexed { i, label ->
            val on = i == selectedIndex
            Box(
                Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(11.dp))
                    .background(if (on) c.surface else Color.Transparent)
                    .clickable(role = Role.Tab) { onSelect(i) }
                    .height(40.dp),
                contentAlignment = Alignment.Center,
            ) {
                Text(label, style = Soft.type.small.copy(fontWeight = Soft.type.bodyStrong.fontWeight), color = if (on) c.ink else c.muted)
            }
        }
    }
}

@Composable
fun SoftTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    val c = Soft.colors
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(placeholder, style = Soft.type.body, color = c.muted) },
        singleLine = true,
        isError = isError,
        textStyle = Soft.type.body.copy(color = c.ink),
        shape = ControlShape,
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = c.ink,
            unfocusedBorderColor = c.input,
            errorBorderColor = c.danger,
            focusedContainerColor = c.surface,
            unfocusedContainerColor = c.surface,
            errorContainerColor = c.surface,
            cursorColor = c.ink,
        ),
        modifier = modifier.fillMaxWidth(),
    )
}

/** Pulsante "⋯" con menu: ogni voce è (testo, pericolosa?, azione). */
@Composable
fun MoreMenu(items: List<Triple<String, Boolean, () -> Unit>>, contentDescription: String = "Altre azioni") {
    var open by remember { mutableStateOf(false) }
    Box {
        IconButton(onClick = { open = true }) {
            Icon(SoftIcons.More, contentDescription = contentDescription, tint = Soft.colors.muted, modifier = Modifier.size(20.dp))
        }
        DropdownMenu(expanded = open, onDismissRequest = { open = false }, containerColor = Soft.colors.surface) {
            items.forEach { (label, danger, action) ->
                DropdownMenuItem(
                    text = { Text(label, style = Soft.type.body, color = if (danger) Soft.colors.danger else Soft.colors.ink) },
                    onClick = {
                        open = false
                        action()
                    },
                )
            }
        }
    }
}
