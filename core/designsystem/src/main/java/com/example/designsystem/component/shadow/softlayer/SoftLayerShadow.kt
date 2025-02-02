package com.example.designsystem.component.shadow.softlayer


import android.view.View
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.addOutline
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.scale
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.withSave
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.designsystem.component.shadow.common.ShadowsPlusDefaults
import com.example.designsystem.component.shadow.common.clipShadowByPath
import com.example.designsystem.component.shadow.common.spreadScale
import android.graphics.Paint as NativePaint

/**
 * A [NativePaint.setShadowLayer]/[View.LAYER_TYPE_SOFTWARE] layer type based shadow [Modifier].
 *
 * You must use it with [SoftLayerShadowContainer].
 *
 * @param radius The shadow radius.
 * @param color The shadow color.
 * @param shape The shadow shape.
 * @param spread The shadow spread.
 * @param offset The shadow offset.
 * @param isAlphaContentClip Indicates whether the alpha (transparent) content should be clipped to the [shape].
 * @return The applied SoftLayerShadow [Modifier].
 * @see SoftLayerShadowContainer
 * @author GIGAMOLE
 */
fun Modifier.softLayerShadow(
    radius: Dp = ShadowsPlusDefaults.ShadowRadius,
    color: Color = ShadowsPlusDefaults.ShadowColor,
    shape: Shape = ShadowsPlusDefaults.ShadowShape,
    spread: Dp = ShadowsPlusDefaults.ShadowSpread,
    offset: DpOffset = ShadowsPlusDefaults.ShadowOffset,
    isAlphaContentClip: Boolean = ShadowsPlusDefaults.IsAlphaContentClip
): Modifier = this.drawWithCache {
    val radiusPx = radius.toPx()
    val isRadiusValid = radiusPx > 0.0F
    val paint = Paint().apply {
        this.color = if (isRadiusValid) {
            Color.Transparent
        } else {
            color
        }

        asFrameworkPaint().apply {
            isDither = true
            isAntiAlias = true

            if (isRadiusValid) {
                setShadowLayer(
                    radiusPx,
                    offset.x.toPx(),
                    offset.y.toPx(),
                    color.toArgb()
                )
            }
        }
    }
    val shapeOutline = shape.createOutline(
        size = size,
        layoutDirection = LayoutDirection.Rtl,
        density = this
    )
    val shapePath = Path().apply {
        addOutline(outline = shapeOutline)
    }

    val drawShadowBlock: DrawScope.() -> Unit = {
        drawIntoCanvas { canvas ->
            canvas.withSave {
                if (isRadiusValid.not()) {
                    canvas.translate(
                        dx = offset.x.toPx(),
                        dy = offset.y.toPx()
                    )
                }

                if (spread.value != 0.0F) {
                    canvas.scale(
                        sx = spreadScale(
                            spread = spread.toPx(),
                            size = size.width
                        ),
                        sy = spreadScale(
                            spread = spread.toPx(),
                            size = size.height
                        ),
                        pivotX = center.x,
                        pivotY = center.y
                    )
                }

                canvas.drawOutline(
                    outline = shapeOutline,
                    paint = paint
                )
            }
        }
    }

    onDrawBehind {
        if (isAlphaContentClip) {
            clipShadowByPath(
                path = shapePath,
                block = drawShadowBlock
            )
        } else {
            drawShadowBlock()
        }
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFFFFFFFF
)
private annotation class SoftLayerShadowPrePApiPreview

@Preview(
    showBackground = true,
    backgroundColor = 0xFFFFFFFF
)
private annotation class SoftLayerShadowPostPApiPreview

@SoftLayerShadowPrePApiPreview
@SoftLayerShadowPostPApiPreview
private annotation class SoftLayerShadowApisPreviews

@SoftLayerShadowApisPreviews
@Composable
private fun SoftLayerShadowPreview_Default() {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 56.dp),
            contentAlignment = Alignment.Center
        ) {SoftLayerShadowContainer {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .aspectRatio(ratio = 1.0F)
                    .softLayerShadow()
                    .background(color = Color.White)
            )
        }
    }
}

@SoftLayerShadowApisPreviews
@Composable
private fun SoftLayerShadowPreview_Custom() {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 56.dp),
            contentAlignment = Alignment.Center
        ) {
            SoftLayerShadowContainer {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .aspectRatio(ratio = 1.0F)
                    .softLayerShadow(
                        color = Color.Red.copy(alpha = 0.3F),
                        offset = DpOffset(
                            x = 16.dp,
                            y = 16.dp
                        ),
                        radius = 24.dp,
                        spread = (-8).dp,
                        shape = CircleShape
                    )
                    .background(
                        color = Color.White,
                        shape = CircleShape
                    )
            )
        }
    }
}